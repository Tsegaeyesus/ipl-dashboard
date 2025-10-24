package com.dreamtech.ipldb.data;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JobTrigger {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job matchJob;


    @Bean
    public CommandLineRunner commandLineRunner(JobLauncher jobLauncher){

       return run->{
           JobParameters jobParameters=new JobParametersBuilder()
                   .addLong("time",System.currentTimeMillis()).toJobParameters();
           jobLauncher.run(matchJob,jobParameters);
        };
    }
}
