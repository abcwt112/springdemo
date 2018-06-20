package com.labofjet.springdemo.scheduler;

import com.labofjet.springdemo.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

@Component
@Configuration
public class TestScheduler implements SchedulingConfigurer {

    private static Logger log = LoggerFactory.getLogger(TestScheduler.class);

    @Autowired
    TaskService taskService;

    @Scheduled(fixedRate = 2000)
    //  @Async("executor")
//@Scheduled(cron = "0 0/15 7-23 * * ?")
//      @Scheduled(cron = "0/2 * * * * ?")
    public void test() {
        taskService.executeTask();
    }

    @Scheduled(fixedRate = 2000)
    public void test2() {
        taskService.executeTask();
    }

    @Bean("executor")
    public Executor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setThreadNamePrefix("test---------");
        executor.initialize();
        return executor;
    }

    @Bean("schedulerExecutor")
    public ThreadPoolTaskScheduler schedulerExecutor() {
        //创建一个线程池调度器
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        //设置线程池容量
        scheduler.setPoolSize(20);
        //线程名前缀
        scheduler.setThreadNamePrefix("task-");
        //等待时常
        scheduler.setAwaitTerminationSeconds(60);
        //当调度器shutdown被调用时等待当前被调度的任务完成
        scheduler.setWaitForTasksToCompleteOnShutdown(false);
        //设置当任务被取消的同时从当前调度器移除的策略
        scheduler.setRemoveOnCancelPolicy(true);
        return scheduler;

    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.setTaskScheduler(schedulerExecutor());
    }

}
