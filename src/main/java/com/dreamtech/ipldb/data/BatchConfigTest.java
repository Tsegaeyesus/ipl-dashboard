package com.dreamtech.ipldb.data;

import com.dreamtech.Test.Person;
import com.dreamtech.ipldb.model.Match;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public FlatFileItemReader<MatchInput> reader(){
        return new FlatFileItemReaderBuilder<MatchInput>()
                .name("read match details")
                .resource(new ClassPathResource("matches.csv"))
                .delimited()
                .names("id","season","city","date","match_type","player_of_match","venue","team1","team2","toss_winner","toss_decision","winner","result","result_margin","target_runs","target_overs","super_over","method","umpire1","umpire2")
                .targetType(MatchInput.class)
                .build();
    }

    @Bean
    public MatchDataProcess processor(){
        return new MatchDataProcess();
    }
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource){

        return new JdbcBatchItemWriterBuilder<Match>()
                .sql("INSERT INTO Match (\n" +
                        "    id,\n" +
                        "    city,\n" +
                        "    date,\n" +
                        "    play_of_match,\n" +
                        "    venue,\n" +
                        "    team1,\n" +
                        "    team2,\n" +
                        "    toss_winner,\n" +
                        "    toss_decision,\n" +
                        "    match_winner,\n" +
                        "    result,\n" +
                        "    result_margin,\n" +
                        "    umpire1,\n" +
                        "    umpire2\n" +
                        ") VALUES (\n" +
                        "    :id,\n" +
                        "    :city,\n" +
                        "    :date,\n" +
                        "    :playOfMatch,\n" +
                        "    :venue,\n" +
                        "    :team1,\n" +
                        "    :team2,\n" +
                        "    :tossWinner,\n" +
                        "    :tossDecision,\n" +
                        "    :matchWinner,\n" +
                        "    :result,\n" +
                        "    :resultMargin,\n" +
                        "    :umpire1,\n" +
                        "    :umpire2\n" +
                        ");\n")
                .dataSource(dataSource)
                .beanMapped()
                .build();


    }

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("importUserJob", jobRepository)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                      FlatFileItemReader<MatchInput> reader, MatchDataProcess processor, JdbcBatchItemWriter<Match> writer) {
        return new StepBuilder("step1", jobRepository)
                .<MatchInput, Match>chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
