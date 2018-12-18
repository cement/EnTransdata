package com.hnen.transdata.task;


import com.hnen.transdata.service.ICaseDataTaskService;
import com.hnen.transdata.serviceimpl.CasedataTaskServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author YSH
 * @create 20181123
 *  descrition：@Scheduled(fixedDelayString = "10000")为 spring调度任务，为将案件倒数与边界倒数分开，
 *
 */
@Component
public class CaseDataTask implements ICaseDataTaskService {

    private static final Logger logger = LoggerFactory.getLogger(CasedataTaskServiceImpl.class);
    public static final Integer DEFAULT_FIX_DALY=10000;
    public static final Integer DEFAULT_FIX_RATE=10000;


//    public  static  AtomicBoolean switchCaseUnit = new AtomicBoolean(true);
//    public  static  AtomicBoolean switchCaseBase = new AtomicBoolean(true);
//    public  static  AtomicBoolean switchCasePeople = new AtomicBoolean(true);
//    public  static  AtomicBoolean switchCasePunish = new AtomicBoolean(true);
//    public  static  AtomicBoolean switchCaseRecord = new AtomicBoolean(true);
//    public  static  AtomicBoolean switchCaseReceive = new AtomicBoolean(true);
    @Autowired
    private CasedataTaskServiceImpl casedataService;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private  Runnable caseUnitTask = importCaseUnitInfoTask();
    private  Runnable caseBaseTask = importCaseBaseInfoTask();
    private  Runnable casePeopleTask = importCasePeopleInfoTask();
    private  Runnable casePunishTask = importCasePunishInfoTask();
    private  Runnable caseRecordTask = importCaseRecordInfoTask();
    private  Runnable caseReceiveTask = importCaseReceiveInfoTask();


    private ScheduledFuture<?> caseUintFuture;
    private ScheduledFuture<?> caseBaseFuture;
    private ScheduledFuture<?> casePeopleFuture;
    private ScheduledFuture<?> casePunishFuture;
    private ScheduledFuture<?> caseRecordFuture;
    private ScheduledFuture<?> caseReceiveFuture;

    @Autowired
    @Qualifier("caseTaskScheduler")
    private ThreadPoolTaskScheduler hsmScheduler;

//    public static void stopImportData(){
//        switchCaseUnit .set(false);
//        switchCaseBase .set(false);
//        switchCasePeople .set(false);
//        switchCasePunish .set(false);
//        switchCaseRecord .set(false);
//        switchCaseReceive .set(false);
//    }
//
//    public static void startImportData(){
//        switchCaseUnit .set(true);
//        switchCaseBase .set(true);
//        switchCasePeople .set(true);
//        switchCasePunish .set(true);
//        switchCaseRecord .set(true);
//        switchCaseReceive .set(true);
//    }





    @Scheduled(fixedDelayString = "10000")
    public void scheduledTest(){
        logger.info("------------"+atomicInteger.incrementAndGet()+"---------------------");
    }
    @Override
//    @Scheduled(fixedDelayString = "10000")
    public void importCaseUnitInfo()  {
//        if (switchCaseUnit.get()){
//            casedataService.importCaseUnitInfo();
//        }

    }

    @Override
//    @Scheduled(fixedDelayString = "10000")
    public void importCaseBaseInfo()  {
//        if (switchCaseBase.get()){
//            casedataService.importCaseBaseInfo();
//        }

    }

    @Override
//    @Scheduled(fixedDelayString = "10000")
    public void importCasePeopleInfo()  {
//        if (switchCasePeople.get()){
//            casedataService.importCasePeopleInfo();
//        }

    }

    @Override
//    @Scheduled(fixedDelayString = "10000")
    public void importCasePunishInfo()  {
//        if (switchCasePunish.get()){
//            casedataService.importCasePunishInfo();
//        }

    }

    @Override
//    @Scheduled(fixedDelayString = "10000")
    public void importCaseRecordInfo()  {
//        if (switchCaseRecord.get()){
//            casedataService.importCaseRecordInfo();
//        }

    }

    @Override
//    @Scheduled(fixedDelayString = "10000")
    public void importCaseReceiveInfo()  {
//        if (switchCaseReceive.get()){
//            casedataService.importCaseReceiveInfo();
//        }

    }


    public Runnable importCaseUnitInfoTask(){
        return new Runnable() {
            @Override
            public void run() {
                    casedataService.importCaseUnitInfo();
            }
        };
    }

    public Runnable importCaseBaseInfoTask(){
        return new Runnable() {
            @Override
            public void run() {
                casedataService.importCaseBaseInfo();
            }
        };
    }

    public Runnable importCasePeopleInfoTask() {
        return new Runnable() {

            @Override
            public void run() {
                  casedataService.importCasePeopleInfo();
            }
        };
    }

    public Runnable importCasePunishInfoTask() {
        return new Runnable() {

            @Override
            public void run() {
                 casedataService.importCasePunishInfo();
            }
        };
    }

    public Runnable importCaseRecordInfoTask() {
        return new Runnable() {

            @Override
            public void run() {
                casedataService.importCaseRecordInfo();
            }
        };
    }

    public Runnable importCaseReceiveInfoTask() {
        return new Runnable() {
            @Override
            public void run() {
                casedataService.importCaseReceiveInfo();
            }
        };
    }

    public void startCaseUnitTask() {
        caseUintFuture = hsmScheduler.scheduleWithFixedDelay(caseUnitTask, DEFAULT_FIX_DALY);
    }
    public void stopCaseUnitTask() {
        if (caseUintFuture != null && !caseUintFuture.isCancelled()&& !caseUintFuture.isDone()){
            caseUintFuture.cancel(true);
        }
    }


    public void startCaseBaseTask() {
        caseBaseFuture = hsmScheduler.scheduleWithFixedDelay(caseBaseTask, DEFAULT_FIX_DALY);
    }
    public void stopCaseBaseTask() {
        if (caseBaseFuture != null && !caseBaseFuture.isCancelled()&& !caseBaseFuture.isDone()){
            caseBaseFuture.cancel(true);
        }
    }

    public void startCasePeopleTask() {
        casePeopleFuture = hsmScheduler.scheduleWithFixedDelay(casePeopleTask, DEFAULT_FIX_DALY);
    }
    public void stopCasePeopleTask() {
        if (casePeopleFuture != null && !casePeopleFuture.isCancelled()&& !casePeopleFuture.isDone()){
            casePeopleFuture.cancel(true);
        }
    }

    public void startCasePunishTask() {
        casePunishFuture = hsmScheduler.scheduleWithFixedDelay(casePunishTask,DEFAULT_FIX_DALY);
    }
    public void stopCasePunishTask() {
        if (casePunishFuture != null && !casePunishFuture.isCancelled()&& !casePunishFuture.isDone()){
            casePunishFuture.cancel(true);
        }
    }

    public void startCaseRecordTask() {
        caseRecordFuture = hsmScheduler.scheduleWithFixedDelay(caseRecordTask,DEFAULT_FIX_DALY);

    }
    public void stopCaseRecordTask() {
        if (caseRecordFuture != null && !caseRecordFuture.isCancelled()&& !caseRecordFuture.isDone()){
            caseRecordFuture.cancel(true);
        }

    }

    public void startCaseReceiveTask() {
        caseReceiveFuture = hsmScheduler.scheduleWithFixedDelay(caseReceiveTask,DEFAULT_FIX_DALY);
    }
    public void stopCaseReceiveTask() {
        if (caseReceiveFuture != null && !caseReceiveFuture.isCancelled()&& !caseReceiveFuture.isDone()){
            caseReceiveFuture.cancel(true);
        }
    }

    public void startAllTask(){
        startCaseUnitTask();
        startCaseBaseTask();
        startCasePeopleTask();
        startCasePunishTask();
        startCaseRecordTask();
        startCaseReceiveTask();
    }
    public void stoptAllTask(){
        stopCaseUnitTask();
        stopCaseBaseTask();
        stopCasePeopleTask();
        stopCasePunishTask();
        stopCaseRecordTask();
        stopCaseReceiveTask();
    }
//    public void startReportTask(){
//        importHsmReport();
//    }
//    public void stoptReportTask(){
//        if (hsmReportFuture != null && !hsmReportFuture.isCancelled()&& !hsmReportFuture.isDone()){
//            hsmReportFuture.cancel(true);
//        }
//    }
//    public void restartReportTask(){
//        stoptReportTask();
//        startReportTask();
//    }


}
