package com.hnen.transdata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * @YSH 调度任务多线程配置
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {




    @Value("${hsm.batch.num:10}")
    public  void setHsmBatchNumber(int hsmBatchNum) {
        ScheduleConfig.HSM_BATCH_NUMBER = hsmBatchNum;
    }



    @Value("${case.batch.num:10}")
    public  void setCaseBatchNumber(int caseBatchNum) {
        ScheduleConfig.CASE_BATCH_NUMBER = caseBatchNum;
    }


    public   static  int HSM_BATCH_NUMBER;

    public  static   int CASE_BATCH_NUMBER;


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(3));
    }


    @Bean("caseTaskScheduler")
    public ThreadPoolTaskScheduler getCaseThreadPoolTaskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.setThreadNamePrefix("caseScheduler");
        taskScheduler.setRemoveOnCancelPolicy(true);
        return  taskScheduler;
    };
    @Bean("hsmTaskScheduler")
    public ThreadPoolTaskScheduler getHsmThreadPoolTaskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(1);
        taskScheduler.setThreadNamePrefix("hsmScheduler-");
        taskScheduler.setRemoveOnCancelPolicy(true);
        return  taskScheduler;
    };




}

