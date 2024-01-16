package com.example.demo.config;

import com.example.demo.entity.Order;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor implements ItemProcessor<Order, Order> {

    @BeforeStep
    public void beforeStep(final StepExecution stepExecution){
        JobParameters jobParameters = stepExecution.getJobParameters();
        System.out.println(jobParameters.getString("time"));
    }
    @Override
    public Order process(Order item){
        item.setItemPrice(item.getItemPrice() + 1);
        return item;
    }
}
