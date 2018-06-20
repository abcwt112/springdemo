package com.labofjet.springdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TaskService {

    private static Logger log = LoggerFactory.getLogger(TaskService.class);

    //@Async("executor")
    public void executeTask() {
        log.error("test      " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
