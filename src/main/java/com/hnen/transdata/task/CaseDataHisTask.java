package com.hnen.transdata.task;


import com.hnen.transdata.service.ICaseDataTaskService;
import com.hnen.transdata.serviceimpl.CasedataHisTaskServiceImp;
import com.hnen.transdata.serviceimpl.CasedataTaskServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CaseDataHisTask implements ICaseDataTaskService {


    public  static  AtomicBoolean switchCaseHisUnit = new AtomicBoolean(true);
    public  static  AtomicBoolean switchCaseHisBase = new AtomicBoolean(true);
    public  static  AtomicBoolean switchCaseHisPeople = new AtomicBoolean(true);
    public  static  AtomicBoolean switchCaseHisPunish = new AtomicBoolean(true);
    public  static  AtomicBoolean switchCaseHisRecord = new AtomicBoolean(true);
    public  static  AtomicBoolean switchCaseHisReceive = new AtomicBoolean(true);
    @Autowired
    private CasedataHisTaskServiceImp caseHisService;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static final Logger logger = LoggerFactory.getLogger(CasedataTaskServiceImpl.class);


    public static void stopImportData(){
        switchCaseHisUnit .set(false);
        switchCaseHisBase .set(false);
        switchCaseHisPeople .set(false);
        switchCaseHisPunish .set(false);
        switchCaseHisRecord .set(false);
        switchCaseHisReceive .set(false);
    }

    public static void startImportData(){
        switchCaseHisUnit .set(true);
        switchCaseHisBase .set(true);
        switchCaseHisPeople .set(true);
        switchCaseHisPunish .set(true);
        switchCaseHisRecord .set(true);
        switchCaseHisReceive .set(true);
    }



    @Scheduled(fixedDelayString = "1000")
    public void scheduledTest(){


        logger.info("------------"+atomicInteger.incrementAndGet()+"---------------------");
    }
    @Override
    @Scheduled(fixedDelayString = "10000")
    public void importCaseUnitInfo() throws IOException {
        if (switchCaseHisUnit.get()){
            caseHisService.importCaseUnitInfo();
        }
    }

    @Override
    @Scheduled(fixedDelayString = "10000")
    public void importCaseBaseInfo() throws IOException {
        if (switchCaseHisBase.get()){
            caseHisService.importCaseBaseInfo();
        }
    }

    @Override
    @Scheduled(fixedDelayString = "10000")
    public void importCasePeopleInfo() throws IOException {
        if (switchCaseHisPeople.get()){
            caseHisService.importCasePeopleInfo();
        }
    }

    @Override
    @Scheduled(fixedDelayString = "10000")
    public void importCasePunishInfo() throws IOException {
        if (switchCaseHisPunish.get()){
            caseHisService.importCasePunishInfo();
        }
    }

    @Override
    @Scheduled(fixedDelayString = "10000")
    public void importCaseRecordInfo() throws IOException {
        if (switchCaseHisRecord.get()){
            caseHisService.importCaseRecordInfo();
        }
    }

    @Override
    @Scheduled(fixedDelayString = "10000")
    public void importCaseReceiveInfo() throws IOException {
        if (switchCaseHisReceive.get()){
            caseHisService.importCaseReceiveInfo();
        }
    }
}
