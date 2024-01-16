package com.example.demo.config;

import com.example.demo.entity.Order;
import com.example.demo.repo.OrderRepository;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Iterator;

@Component
public class OrderReader extends FlatFileItemReader<Order> {
    @Autowired
    OrderRepository orderRepository;
    private Iterator repoIterator;

    public OrderReader(){
        super();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames(new String[]{"customerId", "itemId","itemPrice","itemName","purchaseDate"});
        BeanWrapperFieldSetMapper<Order> fieldSetMapper =  new BeanWrapperFieldSetMapper<Order>();
        fieldSetMapper.setTargetType(Order.class);
        fieldSetMapper.setConversionService(testConversionService());
        DefaultLineMapper<Order> orderDefaultLineMapper = new DefaultLineMapper<Order>();
        orderDefaultLineMapper.setFieldSetMapper(fieldSetMapper);
        orderDefaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        setLineMapper(orderDefaultLineMapper);
        setResource(new ClassPathResource("orders.csv"));
    }

    @Bean
    ConversionService testConversionService(){
        DefaultConversionService defaultConversionService = new DefaultConversionService();
        DefaultConversionService.addDefaultConverters(defaultConversionService);
        defaultConversionService.addConverter(new Converter<String, Timestamp>() {
            @Override
            public Timestamp convert(String text) {
                return Timestamp.valueOf(text);
            }
        });
        return defaultConversionService;
    }
}
