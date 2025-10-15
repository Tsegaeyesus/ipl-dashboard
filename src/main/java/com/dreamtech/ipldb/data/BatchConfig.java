package com.dreamtech.ipldb.data;


import com.dreamtech.ipldb.model.Match;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig{

    public final PlatformTransactionManager platformTransactionManager;
    public final JobRepository jobRepository;
    public final DataSource dataSource;
    public final JobCompletionListener jobCompletionListener;
    public final JobLauncher jobLauncher;

    String[] columns =new String []{
            "id","season","city","date","match_type","player_of_match","venue","team1","team2","toss_winner","toss_decision","winner","result","result_margin","target_runs","target_overs","super_over","method","umpire1","umpire2"
    };

    public BatchConfig(PlatformTransactionManager platformTransactionManager, JobRepository jobRepository, DataSource dataSource, JobCompletionListener jobCompletionListener, JobLauncher jobLauncher) {
        this.platformTransactionManager = platformTransactionManager;
        this.jobRepository = jobRepository;
        this.dataSource = dataSource;
        this.jobCompletionListener = jobCompletionListener;
        this.jobLauncher = jobLauncher;
    }

    //reader
    @Bean
    public FlatFileItemReader<MatchInput> reader(){
        return new FlatFileItemReaderBuilder<MatchInput>()
                .name("Match data reader")
                .resource(new ClassPathResource("matches.csv"))
                .delimited()
                .names(columns)
                .targetType(MatchInput.class)
                .build();
    }
    //processor
    @Bean
    public MatchDataProcess matchDataProcess(){
        return new MatchDataProcess();
    }

    //writer
    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Match>()
                .dataSource(dataSource)
                .sql("INSERT INTO match ( city, date, player_of_match, venue, team1, team2, toss_winner, toss_decision, match_winner, result, result_margin, umpire1, umpire2) " +
                        "VALUES (:city, :date, :playerOfMatch, :venue, :team1, :team2, :tossWinner, :tossDecision, :matchWinner, :result, :resultMargin, :umpire1, :umpire2);"

                ).beanMapped().build();
    }

    //step
    @Bean
    public Step matchStep1(){
        return new StepBuilder("Match Import Step",jobRepository)
                .<MatchInput,Match>chunk(3,platformTransactionManager)
                .reader(reader())
                .processor(matchDataProcess())
                .writer(writer(dataSource))
                .build();
    }

    @Bean
    public Job matchJob(){
        return new JobBuilder("Job Step",jobRepository)
                .listener(jobCompletionListener)
                .start(matchStep1())
                .build();
    }

    @Bean
    public CommandLineRunner commandLineRunner(){
        return run->{
            JobParameters jobParameter=
                    new JobParametersBuilder()
                            .addLong("Time of Ex",System.currentTimeMillis()).toJobParameters();
            jobLauncher.run(matchJob(),jobParameter);
        };
    }

}