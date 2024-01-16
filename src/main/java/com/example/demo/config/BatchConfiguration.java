package com.example.demo.config;

import com.example.demo.entity.Order;
import com.example.demo.repo.OrderRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private OrderReader orderReader;

    @Autowired
    private OrderProcessor orderProcessor;


    @Autowired
    private OrderWriter orderWriter;



    @Bean
    public Job importOrderJob(JobRepository jobRepository, JobCompletionNotificationListener listener, Step step1){
        return new JobBuilder("importOrderJob", jobRepository)
                .incrementer( new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager manager){
        return new StepBuilder("step1", jobRepository)
                .<Order, Order>chunk(10, manager)
                .reader(orderReader)
                .processor(orderProcessor)
                .writer(orderWriter)
                .build();
    }

}
