package com.dreamtech.ipldb.data;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionListener implements JobExecutionListener {

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus().equals(BatchStatus.COMPLETED)){
            System.out.println("Batch Job completed successfully!!!!");
        }
    }
}