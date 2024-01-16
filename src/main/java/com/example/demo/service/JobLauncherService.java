package com.example.demo.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class JobLauncherService {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier("importOrderJob")
    Job job;

    public void runImportOrderJob(){
        JobParameters jobParameters = new JobParametersBuilder().addString("time", String.valueOf(System.currentTimeMillis())).toJobParameters();
        try{

            jobLauncher.run(job, jobParameters);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
