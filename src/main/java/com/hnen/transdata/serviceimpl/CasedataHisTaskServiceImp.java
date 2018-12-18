package com.hnen.transdata.serviceimpl;

import com.hnen.transdata.config.ScheduleConfig;
import com.hnen.transdata.mapper.hbase.ICaseDistHisMaper;
import com.hnen.transdata.mapper.casesrc.ICaseSrcMapper;
import com.hnen.transdata.service.ICaseDataTaskService;
import com.hnen.transdata.websocket.JobtaskWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CasedataHisTaskServiceImp implements ICaseDataTaskService {


    private static final Logger logger = LoggerFactory.getLogger(CasedataHisTaskServiceImp.class);


    @Autowired
    private ICaseSrcMapper caseSrcMapper;

    @Autowired
    private ICaseDistHisMaper distHisMaper;



    //案件单位信息HIS表(VV_HIS_AJDWXXB)
    @Override
    public void importCaseUnitInfo() throws IOException {

        List<Map<String, Object>> infoList = null;
        try {
            String lastActtime = distHisMaper.getUnitBaseHisInfoLastActtime();
            infoList = caseSrcMapper.getUnitInfo(ScheduleConfig.CASE_BATCH_NUMBER, lastActtime);
            logger.info("获取 案件单位信息HIS表 数据:{}",infoList);
            JobtaskWebSocket.sendMessageGroup("获取 案件单位信息HIS表 数据:"+infoList);
        } catch (Exception e) {
            logger.error("获取 案件单位信息HIS表 数据失败;  错误信息:{}",e.getMessage());
            JobtaskWebSocket.sendMessageGroup("获取 案件单位信息HIS表 数据失败,错误信息:"+e.getMessage());
            e.printStackTrace();
        }


        int num=0;
        for (int i = 0,size=infoList.size(); i < size; i++) {
            try {
                logger.info("  "+ i +" :  "+"{}",infoList.get(i));
                num += distHisMaper.insertUnitBaseHisInfo(infoList.get(i));
            } catch (Exception e) {
                logger.error("插入案件单位信息HIS表 数据{}失败;  错误信息:{}",infoList.get(i),e.getMessage());
                JobtaskWebSocket.sendMessageGroup("插入案件单位信息HIS表 数据失败;  数据信息:"+infoList.get(i)+"错误信息: "+e.getMessage());
                e.printStackTrace();
            }
        }
        logger.info("插入 案件单位信息HIS表(VV_HIS_AJDWXXB) ["+num+"] 条数据");
        JobtaskWebSocket.sendMessageGroup("插入 案件单位信息HIS表(VV_HIS_AJDWXXB) ["+num+"] 条数据");

    }

    //案件基础信息HIS表[VV_AJJCXXB]
    @Override
    public void importCaseBaseInfo() throws IOException {

        List<Map<String, Object>> infoList = null;
        try {
            String lastActtime = distHisMaper.getCasebaseInfoLastActtime();
            infoList = caseSrcMapper.getCasebaseInfo(ScheduleConfig.CASE_BATCH_NUMBER, lastActtime);
            logger.info("获取 案件基础信息HIS表 数据:{}",infoList);
            JobtaskWebSocket.sendMessageGroup("获取 案件基础信息HIS表 数据"+infoList);
        } catch (Exception e) {
            logger.error("获取数据失败;  错误信息:{}",e.getMessage());
            JobtaskWebSocket.sendMessageGroup("获取 案件基础信息HIS表 数据失败;  错误信息:"+e.getMessage());
            e.printStackTrace();
        }


        int num=0;
        for (int i = 0,size=infoList.size(); i < size; i++) {
            try {
                logger.info("  "+ i +" :  "+"{}",infoList.get(i));
                num += distHisMaper.insertCasebaseInfo(infoList.get(i));
            } catch (Exception e) {
                logger.error("插入 案件基础信息HIS表  数据{}失败;  错误信息:{}",infoList.get(i),e.getMessage());
                JobtaskWebSocket.sendMessageGroup("插入 案件基础信息HIS表 数据失败;  数据信息:"+infoList.get(i)+"错误信息: "+e.getMessage());
                e.printStackTrace();
            }
        }

//        String lastActtime = distHisMaper.getCasebaseInfoLastActtime();
//        List<Map<String, Object>> infoList = caseSrcMapper.getCasebaseInfo(ScheduleConfig.DEFAULE_BATCH_NUMBER, lastActtime);
//
//        logger.info("{}",infoList);
//        int num = 0;
//        for (int i = 0,size=infoList.size(); i < size; i++) {
//            Map<String, Object> unitInfo = infoList.get(i);
//            System.out.print("  "+ i +" :  ");
//            logger.info("{}",unitInfo);
//            num += distHisMaper.insertCasebaseInfo(unitInfo);
//        }
        logger.info("插入 案件基础信息HIS表(VV_HIS_AJDWXXB) ["+num+"] 条数据");
        JobtaskWebSocket.sendMessageGroup("插入 案件基础信息HIS表(VV_HIS_AJDWXXB) ["+num+"] 条数据");
    }

    //案件人员信息HIS表[VV_HIS_AJRYXXB]
    @Override
    public void importCasePeopleInfo() throws IOException {

        List<Map<String, Object>> infoList = null;
        try {
            String lastActtime = distHisMaper.getCasePeopleHisInfoLastActtime();
            infoList = caseSrcMapper.getCasepeopleInfo(ScheduleConfig.CASE_BATCH_NUMBER, lastActtime);
            logger.info("获取 案件人员信息HIS表 数据:{}",infoList);
            JobtaskWebSocket.sendMessageGroup("获取 案件人员信息HIS表 数据"+infoList);
        } catch (Exception e) {
            logger.error("获取 案件人员信息HIS表 数据失败;  错误信息:{}",e.getMessage());
            JobtaskWebSocket.sendMessageGroup("获取 案件人员信息HIS表 数据失败;  错误信息:"+e.getMessage());
            e.printStackTrace();
        }


        int num=0;
        for (int i = 0,size=infoList.size(); i < size; i++) {
            try {
                logger.info("  "+ i +" :  "+"{}",infoList.get(i));
                num += distHisMaper.insertCasePeopleHisInfo(infoList.get(i));
            } catch (Exception e) {
                logger.error("插入 案件人员信息HIS表 数据{}失败;  错误信息:{}",infoList.get(i),e.getMessage());
                JobtaskWebSocket.sendMessageGroup("插入 案件人员信息HIS表 数据失败;  数据信息:"+infoList.get(i)+"错误信息: "+e.getMessage());
                e.printStackTrace();
            }
        }

//
//        String lastActtime = distHisMaper.getCasepeopleInfoLastActtime();
//        List<Map<String, Object>> peopleInfoList = caseSrcMapper.getCasepeopleInfo(ScheduleConfig.DEFAULE_BATCH_NUMBER, lastActtime);
//
//        logger.info("{}",peopleInfoList);
//        int num = 0;
//        for (int i = 0,size=peopleInfoList.size(); i < size; i++) {
//            Map  peopleInfo = peopleInfoList.get(i);
//            System.out.print("  "+ i +" :  ");
//            logger.info("{}",peopleInfo);
//            num += distHisMaper.insertCasepeopleInfo(peopleInfo);
//        }
        logger.info("插入 案件人员信息HIS表(VV_HIS_AJRYXXB)   ["+num+"] 条数据");
        JobtaskWebSocket.sendMessageGroup("插入 案件人员信息HIS表(VV_HIS_AJRYXXB) ["+num+"] 条数据");
    }


    //处罚信息HIS表[VV_HIS_DCCFXXB]
    @Override
    public void importCasePunishInfo() throws IOException {

        List<Map<String, Object>> infoList = null;
        try {
            String lastActtime = distHisMaper.getPunishmentHisInfoLastActtime();
            infoList = caseSrcMapper.getPunishmentInfo(ScheduleConfig.CASE_BATCH_NUMBER, lastActtime);
            logger.info("获取 处罚信息HIS表 数据:{}",infoList);
            JobtaskWebSocket.sendMessageGroup("获取 处罚信息HIS表  数据"+infoList);
        } catch (Exception e) {
            logger.error("获取 处罚信息HIS表 数据失败;  错误信息:{}",e.getMessage());
            JobtaskWebSocket.sendMessageGroup("获取 处罚信息HIS表  数据"+e.getMessage());

            e.printStackTrace();
        }


        int num=0;
        for (int i = 0,size=infoList.size(); i < size; i++) {
            try {
                logger.info("  "+ i +" :  "+"{}",infoList.get(i));
                num += distHisMaper.insertPunishmentHisInfo(infoList.get(i));
            } catch (Exception e) {
                logger.error("插入处罚信息HIS表 数据{}失败;  错误信息:{}",infoList.get(i),e.getMessage());
                JobtaskWebSocket.sendMessageGroup("获取 处罚信息HIS表  数据"+e.getMessage());
                JobtaskWebSocket.sendMessageGroup("插入 处罚信息HIS表 数据失败;  数据信息:"+infoList.get(i)+"错误信息: "+e.getMessage());
                e.printStackTrace();
            }
        }

//
//        String lastActtime = distHisMaper.getPunishmentInfoLastActtime();
//        List<Map<String, Object>> infoList = caseSrcMapper.getPunishmentInfo(ScheduleConfig.DEFAULE_BATCH_NUMBER, lastActtime);
//
//        logger.info("{}",infoList);
//
//        int num = 0;
//        for (int i = 0,size=infoList.size(); i < size; i++) {
//            Map  punishInfo = infoList.get(i);
//            System.out.print("  "+ i +" :  ");
//            logger.info("{}",punishInfo);
//            num += distHisMaper.insertPunishmentInfo(punishInfo);
//        }
        logger.info("插入 处罚信息HIS表[VV_HIS_DCCFXXB]   "+num+" 条数据");
        JobtaskWebSocket.sendMessageGroup("插入 处罚信息HIS表(VV_HIS_DCCFXXB) ["+num+"] 条数据");
    }


    //电子笔录信息HIS表[VV_HIS_DZBLXXBB]
    @Override
    public void importCaseRecordInfo() throws IOException {


        List<Map<String, Object>> infoList = null;
        try {
            String lastActtime = distHisMaper.getCaseRecordHisInfoLastActtime();
            infoList = caseSrcMapper.getRecordInfo(ScheduleConfig.CASE_BATCH_NUMBER, lastActtime);
            logger.info("获取 电子笔录信息HIS表 数据:{}",infoList);
            JobtaskWebSocket.sendMessageGroup("获取 电子笔录信息HIS表  数据"+infoList);
        } catch (Exception e) {
            logger.error("获取 电子笔录信息HIS表 数据失败;  错误信息:{}",e.getMessage());
            JobtaskWebSocket.sendMessageGroup("获取 电子笔录信息HIS表  数据"+e.getMessage());
            e.printStackTrace();
        }


        int num=0;
        for (int i = 0,size=infoList.size(); i < size; i++) {
            try {
                logger.info("  "+ i +" :  "+"{}",infoList.get(i));
                num += distHisMaper.insertCaseRecordHisInfo(infoList.get(i));
            } catch (Exception e) {
                logger.error("插入 电子笔录信息HIS表 数据{}失败;  错误信息:{}",infoList.get(i),e.getMessage());
                JobtaskWebSocket.sendMessageGroup("插入 电子笔录信息HIS表 数据失败;  数据信息:"+infoList.get(i)+"错误信息: "+e.getMessage());
                e.printStackTrace();
            }
        }

//
//        String lastActtime = distHisMaper.getRecordInfoLastActtime();
//        List<Map<String, Object>> infoList = caseSrcMapper.getRecordInfo(ScheduleConfig.DEFAULE_BATCH_NUMBER, lastActtime);
//
//        logger.info("{}",infoList);
//        int num = 0;
//        for (int i = 0,size=infoList.size(); i < size; i++) {
//            Map  punishInfo = infoList.get(i);
//            System.out.print("  "+ i +" :  ");
//            logger.info("{}",punishInfo);
//            num += distHisMaper.insertRecordInfo(punishInfo);
//        }
        logger.info("插入 电子笔录信息HIS表(VV_HIS_DZBLXXBB)   ["+num+"] 条数据");
        JobtaskWebSocket.sendMessageGroup("插入 电子笔录信息HIS表(VV_HIS_DZBLXXBB) ["+num+"] 条数据");

    }

    //接警案件HIS表[VV_HIS_JJAJDJB]
    @Override
    public void importCaseReceiveInfo() throws IOException {


        List<Map<String, Object>> infoList = null;
        try {
            String lastActtime = distHisMaper.getCaseReceiveHisInfoLastActtime();
            infoList = caseSrcMapper.getReceiveCaseInfo(ScheduleConfig.CASE_BATCH_NUMBER, lastActtime);
            logger.info("获取 接警案件HIS表 数据:{}",infoList);
            JobtaskWebSocket.sendMessageGroup("获取 接警案件HIS表  数据"+infoList);
        } catch (Exception e) {
            logger.error("获取 接警案件HIS表 数据失败;  错误信息:{}",e.getMessage());
            JobtaskWebSocket.sendMessageGroup("获取 接警案件HIS表  数据"+e.getMessage());
            e.printStackTrace();
        }


        int num=0;
        for (int i = 0,size=infoList.size(); i < size; i++) {
            try {
                logger.info("  "+ i +" :  "+"{}",infoList.get(i));
                num += distHisMaper.insertCaseReceiveHisInfo(infoList.get(i));
            } catch (Exception e) {
                logger.error("插入接警案件HIS表 数据{}失败;  错误信息:{}",infoList.get(i),e.getMessage());
                JobtaskWebSocket.sendMessageGroup("插入 接警案件HIS表  数据失败;  数据信息:"+infoList.get(i)+"错误信息: "+e.getMessage());
                e.printStackTrace();
            }
        }

//        String lastActtime = distHisMaper.getReceiveCaseInfoLastActtime();
//        List<Map<String, Object>> infoList = caseSrcMapper.getReceiveCaseInfo(ScheduleConfig.DEFAULE_BATCH_NUMBER, lastActtime);
//
//        logger.info("{}",infoList);
//        int num = 0;
//        for (int i = 0,size=infoList.size(); i < size; i++) {
//            Map  info = infoList.get(i);
//            System.out.print("  "+ i +" :  ");
//            logger.info("{}",info);
//            num += distHisMaper.insertReceiveCaseInfo(info);
//        }
        logger.info("插入 接警案件HIS表[VV_HIS_JJAJDJB]   "+num+"  条数据");
        JobtaskWebSocket.sendMessageGroup("插入 接警案件HIS表(VV_HIS_JJAJDJB) ["+num+"] 条数据");
    }

}
