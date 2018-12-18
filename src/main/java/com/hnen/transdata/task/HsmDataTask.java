package com.hnen.transdata.task;

import com.hnen.transdata.service.IHsmDistTaskService;
import com.hnen.transdata.serviceimpl.HsmDistTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * @author YSH
 * @date: 20181123
 */
@Component
public class HsmDataTask implements IHsmDistTaskService {

    @Autowired
    private HsmDistTaskServiceImpl hsmDistService;

    @Autowired
    @Qualifier("hsmTaskScheduler")
    private ThreadPoolTaskScheduler hsmScheduler;


    private Runnable reportTask = importHsmReportTask();
    private Runnable repeatTask = importHsmRepeatTask();


    private ScheduledFuture<?> hsmReportFuture;
    private ScheduledFuture<?> hsmRepeatFuture;

    @Override
    public void importHsmdata() {
        hsmDistService.importHsmdata();
    }

    @Override
    public void importHsmReport() {
//         hsmReportFuture = hsmScheduler.scheduleWithFixedDelay(reportTask, new Date(),10000);
         hsmReportFuture = hsmScheduler.scheduleAtFixedRate(reportTask, new Date(),10000);
    }

    @Override
    public void importHsmRepeat() {
//        hsmRepeatFuture = hsmScheduler.scheduleWithFixedDelay(repeatTask, 10000);
        hsmRepeatFuture = hsmScheduler.scheduleAtFixedRate(repeatTask, 10000);
    }


    public Runnable importHsmReportTask(){
        return new Runnable() {
            @Override
            public void run() {
                hsmDistService.importHsmReport();
            }
        };
    }

    public Runnable importHsmRepeatTask(){
        return new Runnable() {
            @Override
            public void run() {
                hsmDistService.importHsmRepeat();
            }
        };
    }



    public void stopReportTask(){
        if (hsmReportFuture != null && !hsmReportFuture.isCancelled()&& !hsmReportFuture.isDone()){
            hsmReportFuture.cancel(true);
        }
    }
    public void startReportTask(){
        importHsmReport();
    }
    public void restartReportTask(){
        stopReportTask();
        startReportTask();
    }

    public void stopRepeatTask(){
        if (hsmRepeatFuture != null && !hsmRepeatFuture.isCancelled()&& !hsmRepeatFuture.isDone()) {
            hsmRepeatFuture.cancel(true);
        }
    }
    public void startRepeatTask(){
        importHsmRepeat();
    }
    public void restartRepeatTask(){
        stopRepeatTask();
        startRepeatTask();
    }

    public void startAllTask(){
        startReportTask();
        startRepeatTask();
    }
    public void stopAllTask(){
        stopReportTask();
        stopRepeatTask();
    }
    public void restartAllTask(){
        restartReportTask();
        restartRepeatTask();
    }


}
