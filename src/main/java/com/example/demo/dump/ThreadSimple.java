package com.example.demo.dump;

import com.example.demo.anotation.JsonProcessor;
import com.example.demo.entity.Student;
import com.example.demo.user.User;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ThreadSimple {
    public static void main(String[] args) throws InterruptedException, IllegalAccessException {
        User user = new User();
        user.setFirstname("D vanh");
        user.setEmail("dovietanh@gmail.com");
        String json  = JsonProcessor.toJson(user);
        System.out.println(json);
    }

}