package com.hnen.transdata.serviceimpl;

import com.hnen.transdata.mapper.casesrc.ICaseSrcMapper;
import com.hnen.transdata.mapper.hbase.ICaseDistMaper;
import com.hnen.transdata.service.ICaseDataTaskService;
import com.hnen.transdata.websocket.JobtaskWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.hnen.transdata.config.ScheduleConfig.CASE_BATCH_NUMBER;

@Service
public class CasedataTaskServiceImpl implements ICaseDataTaskService {


    private static final Logger logger = LoggerFactory.getLogger(CasedataTaskServiceImpl.class);

    @Autowired
    private ICaseSrcMapper srcMapper;

    @Autowired
    private ICaseDistMaper distMaper;



    //案件单位信息表(VV_AJDWXXB)
    @Override
    public void importCaseUnitInfo(){
        List<Map<String, Object>> infoList = null;
        try {
            String lastActtime = distMaper.getUnitInfoLastActtime();
            infoList = srcMapper.getUnitInfo(CASE_BATCH_NUMBER, lastActtime);
            logger.info("获取 案件单位信息表 数据:{}",infoList);
            JobtaskWebSocket.sendMessageGroup("获取 案件单位信息表 数据:"+infoList);
        } catch (Exception e) {
            logger.error("获取 案件单位信息表 数据失败;  错误信息:{}",e.getMessage());
            JobtaskWebSocket.sendMessageGroup("获取 案件单位信息表 数据失败,错误信息:"+e.getMessage());
            e.printStackTrace();
        }


        int num=0;
        for (int i = 0,size=infoList.size(); i < size; i++) {
            try {
                logger.info("  "+ i +" :  "+"{}",infoList.get(i));
                num += distMaper.insertUnitInfo(infoList.get(i));
            } catch (Exception e) {
                logger.error("插入案件单位信息表 数据{}失败;  错误信息:{}",infoList.get(i),e.getMessage());
                JobtaskWebSocket.sendMessageGroup("插入案件单位信息表 数据失败;  数据信息:"+infoList.get(i)+"错误信息: "+e.getMessage());
                e.printStackTrace();
            }
        }
        logger.info("插入 案件单位信息表(VV_AJDWXXB) ["+num+"] 条数据");
        JobtaskWebSocket.sendMessageGroup("插入 案件单位信息表(VV_AJDWXXB) ["+num+"] 条数据");

    }

    //案件基础信息表[VV_AJJCXXB]
    @Override
    public void importCaseBaseInfo() {

        List<Map<String, Object>> infoList = null;
        try {
            String lastActtime = distMaper.getCasebaseInfoLastActtime();
            infoList = srcMapper.getCasebaseInfo(CASE_BATCH_NUMBER, lastActtime);
            logger.info("获取 案件基础信息表 数据:{}",infoList);
            JobtaskWebSocket.sendMessageGroup("获取 案件基础信息表 数据"+infoList);
        } catch (Exception e) {
            logger.error("获取数据失败;  错误信息:{}",e.getMessage());
            JobtaskWebSocket.sendMessageGroup("获取 案件基础信息表 数据失败;  错误信息:"+e.getMessage());
            e.printStackTrace();
        }


        int num=0;
        for (int i = 0,size=infoList.size(); i < size; i++) {
            try {
                logger.info("  "+ i +" :  "+"{}",infoList.get(i));
                num += distMaper.insertCasebaseInfo(infoList.get(i));
            } catch (Exception e) {
                logger.error("插入 案件基础信息表  数据{}失败;  错误信息:{}",infoList.get(i),e.getMessage());
                JobtaskWebSocket.sendMessageGroup("插入 案件基础信息表 数据失败;  数据信息:"+infoList.get(i)+"错误信息: "+e.getMessage());
                e.printStackTrace();
            }
        }

//        String lastActtime = distMaper.getCasebaseInfoLastActtime();
//        List<Map<String, Object>> infoList = srcMapper.getCasebaseInfo(ScheduleConfig.DEFAULE_BATCH_NUMBER, lastActtime);
//
//        logger.info("{}",infoList);
//        int num = 0;
//        for (int i = 0,size=infoList.size(); i < size; i++) {
//            Map<String, Object> unitInfo = infoList.get(i);
//            System.out.print("  "+ i +" :  ");
//            logger.info("{}",unitInfo);
//            num += distMaper.insertCasebaseInfo(unitInfo);
//        }
        logger.info("插入 案件基础信息表(VV_AJDWXXB) ["+num+"] 条数据");
        JobtaskWebSocket.sendMessageGroup("插入 案件基础信息表(VV_AJDWXXB) ["+num+"] 条数据");
    }

    //案件人员信息表[VV_AJRYXXB]
    @Override
    public void importCasePeopleInfo() {

        List<Map<String, Object>> infoList = null;
        try {
            String lastActtime = distMaper.getCasepeopleInfoLastActtime();
            infoList = srcMapper.getCasepeopleInfo(CASE_BATCH_NUMBER, lastActtime);
            logger.info("获取 案件人员信息表 数据:{}",infoList);
            JobtaskWebSocket.sendMessageGroup("获取 案件人员信息表 数据"+infoList);
        } catch (Exception e) {
            logger.error("获取 案件人员信息表 数据失败;  错误信息:{}",e.getMessage());
            JobtaskWebSocket.sendMessageGroup("获取 案件人员信息表 数据失败;  错误信息:"+e.getMessage());
            e.printStackTrace();
        }


        int num=0;
        for (int i = 0,size=infoList.size(); i < size; i++) {
            try {
                logger.info("  "+ i +" :  "+"{}",infoList.get(i));
                num += distMaper.insertCasepeopleInfo(infoList.get(i));
            } catch (Exception e) {
                logger.error("插入 案件人员信息表 数据{}失败;  错误信息:{}",infoList.get(i),e.getMessage());
                JobtaskWebSocket.sendMessageGroup("插入 案件人员信息表 数据失败;  数据信息:"+infoList.get(i)+"错误信息: "+e.getMessage());
                e.printStackTrace();
            }
        }

//
//        String lastActtime = distMaper.getCasepeopleInfoLastActtime();
//        List<Map<String, Object>> peopleInfoList = srcMapper.getCasepeopleInfo(ScheduleConfig.DEFAULE_BATCH_NUMBER, lastActtime);
//
//        logger.info("{}",peopleInfoList);
//        int num = 0;
//        for (int i = 0,size=peopleInfoList.size(); i < size; i++) {
//            Map  peopleInfo = peopleInfoList.get(i);
//            System.out.print("  "+ i +" :  ");
//            logger.info("{}",peopleInfo);
//            num += distMaper.insertCasepeopleInfo(peopleInfo);
//        }
        logger.info("插入 案件人员信息表(VV_AJRYXXB)   ["+num+"] 条数据");
        JobtaskWebSocket.sendMessageGroup("插入 案件人员信息表(VV_AJRYXXB) ["+num+"] 条数据");
    }


    //处罚信息表[VV_DCCFXXB]
    @Override
    public void importCasePunishInfo(){

        List<Map<String, Object>> infoList = null;
        try {
            String lastActtime = distMaper.getPunishmentInfoLastActtime();
            infoList = srcMapper.getPunishmentInfo(CASE_BATCH_NUMBER, lastActtime);
            logger.info("获取 处罚信息表 数据:{}",infoList);
            JobtaskWebSocket.sendMessageGroup("获取 处罚信息表  数据"+infoList);
        } catch (Exception e) {
            logger.error("获取 处罚信息表 数据失败;  错误信息:{}",e.getMessage());
            JobtaskWebSocket.sendMessageGroup("获取 处罚信息表  数据"+e.getMessage());

            e.printStackTrace();
        }


        int num=0;
        for (int i = 0,size=infoList.size(); i < size; i++) {
            try {
                logger.info("  "+ i +" :  "+"{}",infoList.get(i));
                num += distMaper.insertPunishmentInfo(infoList.get(i));
            } catch (Exception e) {
                logger.error("插入处罚信息表 数据{}失败;  错误信息:{}",infoList.get(i),e.getMessage());
                JobtaskWebSocket.sendMessageGroup("获取 处罚信息表  数据"+e.getMessage());
                JobtaskWebSocket.sendMessageGroup("插入 处罚信息表 数据失败;  数据信息:"+infoList.get(i)+"错误信息: "+e.getMessage());
                e.printStackTrace();
            }
        }

//
//        String lastActtime = distMaper.getPunishmentInfoLastActtime();
//        List<Map<String, Object>> infoList = srcMapper.getPunishmentInfo(ScheduleConfig.DEFAULE_BATCH_NUMBER, lastActtime);
//
//        logger.info("{}",infoList);
//
//        int num = 0;
//        for (int i = 0,size=infoList.size(); i < size; i++) {
//            Map  punishInfo = infoList.get(i);
//            System.out.print("  "+ i +" :  ");
//            logger.info("{}",punishInfo);
//            num += distMaper.insertPunishmentInfo(punishInfo);
//        }
        logger.info("插入 处罚信息表[VV_DCCFXXB]   "+num+" 条数据");
        JobtaskWebSocket.sendMessageGroup("插入 处罚信息表(VV_DCCFXXB) ["+num+"] 条数据");
    }


    //电子笔录信息表[VV_DZBLXXBB]
    @Override
    public void importCaseRecordInfo(){


        List<Map<String, Object>> infoList = null;
        try {
            String lastActtime = distMaper.getRecordInfoLastActtime();
            infoList = srcMapper.getRecordInfo(CASE_BATCH_NUMBER, lastActtime);
            logger.info("获取 电子笔录信息表 数据:{}",infoList);
            JobtaskWebSocket.sendMessageGroup("获取 电子笔录信息表  数据"+infoList);
        } catch (Exception e) {
            logger.error("获取 电子笔录信息表 数据失败;  错误信息:{}",e.getMessage());
            JobtaskWebSocket.sendMessageGroup("获取 电子笔录信息表  数据"+e.getMessage());
            e.printStackTrace();
        }


        int num=0;
        for (int i = 0,size=infoList.size(); i < size; i++) {
            try {
                logger.info("  "+ i +" :  "+"{}",infoList.get(i));
                num += distMaper.insertRecordInfo(infoList.get(i));
            } catch (Exception e) {
                logger.error("插入 电子笔录信息表 数据{}失败;  错误信息:{}",infoList.get(i),e.getMessage());
                JobtaskWebSocket.sendMessageGroup("插入 电子笔录信息表 数据失败;  数据信息:"+infoList.get(i)+"错误信息: "+e.getMessage());
                e.printStackTrace();
            }
        }

//
//        String lastActtime = distMaper.getRecordInfoLastActtime();
//        List<Map<String, Object>> infoList = srcMapper.getRecordInfo(ScheduleConfig.DEFAULE_BATCH_NUMBER, lastActtime);
//
//        logger.info("{}",infoList);
//        int num = 0;
//        for (int i = 0,size=infoList.size(); i < size; i++) {
//            Map  punishInfo = infoList.get(i);
//            System.out.print("  "+ i +" :  ");
//            logger.info("{}",punishInfo);
//            num += distMaper.insertRecordInfo(punishInfo);
//        }
        logger.info("插入 电子笔录信息表(VV_DZBLXXBB)   ["+num+"] 条数据");
        JobtaskWebSocket.sendMessageGroup("插入 电子笔录信息表(VV_DZBLXXBB) ["+num+"] 条数据");

    }

    //接警案件表[VV_JJAJDJB]
    @Override
    public void importCaseReceiveInfo() {


        List<Map<String, Object>> infoList = null;
        try {
            String lastActtime = distMaper.getReceiveCaseInfoLastActtime();
            infoList = srcMapper.getReceiveCaseInfo(CASE_BATCH_NUMBER, lastActtime);
            logger.info("获取 接警案件表 数据:{}",infoList);
            JobtaskWebSocket.sendMessageGroup("获取 接警案件表  数据"+infoList);
        } catch (Exception e) {
            logger.error("获取 接警案件表 数据失败;  错误信息:{}",e.getMessage());
            JobtaskWebSocket.sendMessageGroup("获取 接警案件表  数据"+e.getMessage());
            e.printStackTrace();
        }


        int num=0;
        for (int i = 0,size=infoList.size(); i < size; i++) {
            try {
                logger.info("  "+ i +" :  "+"{}",infoList.get(i));
                num += distMaper.insertReceiveCaseInfo(infoList.get(i));
            } catch (Exception e) {
                logger.error("插入接警案件表 数据{}失败;  错误信息:{}",infoList.get(i),e.getMessage());
                JobtaskWebSocket.sendMessageGroup("插入 接警案件表  数据失败;  数据信息:"+infoList.get(i)+"错误信息: "+e.getMessage());
                e.printStackTrace();
            }
        }

//        String lastActtime = distMaper.getReceiveCaseInfoLastActtime();
//        List<Map<String, Object>> infoList = srcMapper.getReceiveCaseInfo(ScheduleConfig.DEFAULE_BATCH_NUMBER, lastActtime);
//
//        logger.info("{}",infoList);
//        int num = 0;
//        for (int i = 0,size=infoList.size(); i < size; i++) {
//            Map  info = infoList.get(i);
//            System.out.print("  "+ i +" :  ");
//            logger.info("{}",info);
//            num += distMaper.insertReceiveCaseInfo(info);
//        }
        logger.info("插入 接警案件表[VV_JJAJDJB]   "+num+"  条数据");
        JobtaskWebSocket.sendMessageGroup("插入 接警案件表(VV_JJAJDJB) ["+num+"] 条数据");
    }

}
