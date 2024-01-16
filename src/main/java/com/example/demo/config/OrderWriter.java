package com.example.demo.config;

import com.example.demo.entity.Order;
import com.example.demo.repo.OrderRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderWriter implements ItemWriter<Order> {

    @Autowired
    OrderRepository orderRepository;


    @Override
    public void write(Chunk<? extends Order> chunk) throws Exception {
        List<Order> orders = (List<Order>) chunk.getItems();
        orderRepository.saveAll(orders);
        FlatFileItemWriter<Order> writer = new FlatFileItemWriter<Order>();
        writer.setAppendAllowed(true);
        writer.setEncoding("UTF-8");
        writer.setResource(new FileSystemResource("C:\\Users\\dovie\\outputData.csv"));
        DelimitedLineAggregator<Order> delimitedLineAggregator = new DelimitedLineAggregator<>();
        delimitedLineAggregator.setDelimiter(",");
        BeanWrapperFieldExtractor<Order> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<>();
        beanWrapperFieldExtractor.setNames(new String[]{"id", "customerId", "itemId", "itemPrice", "itemName", "purchaseDate"});
        delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
        writer.setLineSeparator("\n");
        writer.setLineAggregator(delimitedLineAggregator);
        writer.open(new ExecutionContext());
        writer.write(chunk);
        writer.close();

    }
}
