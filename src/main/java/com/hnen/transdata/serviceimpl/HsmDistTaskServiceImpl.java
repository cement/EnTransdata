package com.hnen.transdata.serviceimpl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hnen.transdata.config.ScheduleConfig;
import com.hnen.transdata.entity.CurrentReport;
import com.hnen.transdata.entity.Hsmdata;
import com.hnen.transdata.entity.Repeat;
import com.hnen.transdata.entity.Report;
import com.hnen.transdata.mapper.hbase.IHsmDistMapper;
import com.hnen.transdata.mapper.hsmsrc.IHsmSrcMapper;
import com.hnen.transdata.service.IHsmDistTaskService;
import com.hnen.transdata.util.ReportUtil;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.List;



/**
 * @author YSH
 * @Date 20181120
 */
@Service
public class HsmDistTaskServiceImpl implements IHsmDistTaskService {


    private static final Logger logger = LoggerFactory.getLogger(HsmDistTaskServiceImpl.class);

//    @Value("${hsm.report.batch.num:10}")
//    public  int  hsm_report_batch_num;
//    @Value("${hsm.repeat.batch.num:10}")
//    public  int  hsm_repeat_batch_num;


    @Autowired
    private IHsmSrcMapper hsmSrcMapper;

    @Autowired
    private IHsmDistMapper hsmDistMapper;



    @Override
    public void importHsmdata() {


        List<Hsmdata> hsmDataList = hsmSrcMapper.getHsmData(ScheduleConfig.HSM_BATCH_NUMBER);

        int reportNum = 0, instuctNum = 0;


        for (int i = 0; i < hsmDataList.size(); i++) {
            Hsmdata hsmdata = hsmDataList.get(i);
            //转换成对象
            CurrentReport currentReport = convertHsmContent(hsmdata);
            //如果是 举报

            if ("ygp_report".equals(hsmdata.getName())) {

                //拿出 内层report
                Report report = currentReport.getReport();
                //获取地区ID
                String city = report.getCity().replace("市", "");
                String area = report.getDistrict().replaceAll("[县|市|区]$", "");
                String province = report.getProvince().replace("省", "");
                //根据省、市、 县(市、区)获取 县(市、区)的地区ID
                List<String> areaIdList = hsmDistMapper.getNationIdByName(province, city, area);
                logger.info("areaIdList:{}:" + areaIdList);

                if (areaIdList.size() == 0) {//如果没有根据名字获取到ID
                    logger.error("根据名称获取到0个区县id,{}", areaIdList);
                } else if (areaIdList.size() == 1) {//正常获取到一个
                    String areaId = areaIdList.get(0);
                    report.setDistrictId(areaId);
                    report.setCityId(areaId.substring(0, 3) + "00");
                    report.setReportKind(3);
                    String reportCode = ReportUtil.makeWxReportCode(areaId);
                    report.setReportCode(reportCode);
                    logger.info("currentReport: {}", JSON.toJSONString(currentReport, true));
                    Integer num = hsmDistMapper.insertHsmReport(currentReport);
                    //如果处理完毕，插入t_report表成功，删除它；
                    if (num > 0) {
                        reportNum += num;
//                        Integer delNum = hsmSrcMapper.deleteHsmDataById(hsmdata.getId());
//                        if (delNum > 0) {
//                            logger.info("删除id是{}的源数据成功,{}", hsmdata.getId());
//                        }
                    }
                } else {   // 获取到多个ID
                    logger.error("根据名称获取到多个区县id,{}", areaIdList);
                    //TODO 有多个nationID
                }
            }


            //如果是 回复

            if ("ygp_repeat".equals(hsmdata.getName())) {

                Repeat repeat = currentReport.getRepeat();
                //String reportCode = getRportCodeByMongoIdFromReport(currentReport.getId());
                List<String> reportCodeList = hsmDistMapper.getHsmReportCodeByMongoId(currentReport.getId());
                if (reportCodeList.size() == 0) {
                    logger.error("获取到0个reportCode,{}", reportCodeList);
                } else if (reportCodeList.size() == 1) {
                    String reportCode = reportCodeList.get(0);
                    logger.info("reportCode:{}", reportCode);
                    repeat.setReportCode(reportCodeList.get(0));
                    String updataRepeatId = hsmDistMapper.getRepeatIdByReportCodeAndPolice(reportCode, repeat.getPolice().getPoliceNo());
                    //测试用,相当于找到了reportCode+policeNo数据。
                    repeat.setRepeatId(Long.valueOf(updataRepeatId));
//                    repeat.setRepeatId(temp++);
                    logger.info("currentReport: {}", JSON.toJSONString(currentReport, true));

                    Integer num = hsmDistMapper.insertHsmRepeat(currentReport);
                    //如果处理完毕，更新t_report_instuct成功，删除它；
                    if (num > 0) {
                        reportNum += num;
//                        Integer delNum = hsmSrcMapper.deleteHsmDataById(hsmdata.getId());
//                        if (delNum > 0) {
//                            logger.info("删除id是{}的源数据成功,{}", hsmdata.getId());
//                        }
                    }
                } else {
                    logger.error("获取到多个reportCode,{}", reportCodeList);
                }


            }
        }

        logger.info("插入 黄赌举报表[ygp.t_report] {} 条数据.", reportNum);
        logger.info("插入 举报指令表[ygp.t_report_instuct] {}条数据.", instuctNum);
    }


    //导入举报信息
    @Override
    public void importHsmReport() {
        List<Hsmdata> hsmDataList = hsmSrcMapper.getHsmReport(ScheduleConfig.HSM_BATCH_NUMBER);
        //记录 插入条数
        int  reportNum = 0;
        for (int i = 0,size  = hsmDataList.size(); i <size ; i++) {
            Hsmdata hsmdata = hsmDataList.get(i);
            CurrentReport currentReport = convertHsmContent(hsmdata);
            Report report = currentReport.getReport();
            //获取地区ID
//            String provinceName = report.getProvince().replace("省", "");
//            String cityName = report.getCity().replace("市", "");
            String areaName = report.getDistrict(); //.replaceAll("[县|市|区]$", "");

            List<String> areaIdList = hsmDistMapper.getNationIdByAreaName(areaName);

            logger.info("areaIdList:{}:", areaIdList);
            if (areaIdList.size() < 1){
                logger.error("根据名称获取到{}个区县id,{}", areaIdList.size(),areaIdList);
            }else{
                String areaId = areaIdList.get(0);
                report.setDistrictId(areaId);
                report.setCityId(areaId.substring(0, 4) + "00");
                report.setReportKind(3);
                String reportCode = ReportUtil.makeWxReportCode(areaId);
                report.setReportCode(reportCode);
                logger.info("举报信息(json): currentReport: {}", JSON.toJSONString(currentReport));
                Integer num = hsmDistMapper.insertHsmReport(currentReport);
                //如果处理完毕，插入t_report表成功，删除它；
                if (num > 0) {
                    reportNum += num;
                    Integer delNum = hsmSrcMapper.deleteHsmDataById(hsmdata.getId());
                    if (delNum > 0) {
                        logger.info("删除id是{}的源数据成功,{}", hsmdata.getId(),hsmdata.getInserttime());
                    }
                }

            }

        }
        logger.info("插入 黄赌举报表[ygp.t_report] {} 条数据.", reportNum);
    }

    //导入指令信息
    @Override
    public void importHsmRepeat() {
        List<Hsmdata> hsmDataList = hsmSrcMapper.getHsmRepeat(ScheduleConfig.HSM_BATCH_NUMBER);
        int  repeatNum = 0;
        for (int i = 0; i < hsmDataList.size(); i++) {
            Hsmdata hsmdata = hsmDataList.get(i);
            //转换成对象
            CurrentReport currentReport = convertHsmContent(hsmdata);
            Repeat repeat = currentReport.getRepeat();
            //根据mongoId找到reportCode,getId() 就是取出mongoId
            List<String> reportCodeList = hsmDistMapper.getHsmReportCodeByMongoId(currentReport.getId());
            if (reportCodeList.size() != 1) {
                logger.error("根据mongoId获取到{}个reportCode,{}", reportCodeList.size(),reportCodeList);
                continue;
            }
            String reportCode = reportCodeList.get(0);
            logger.info("reportCode:{}", reportCode);
            repeat.setReportCode(reportCode);
            //取出处置警察信息
 //           String policeNo = repeat.getPolice().getPoliceNo();
//            if(policeNo == null || policeNo.isEmpty() ){
//                logger.warn("police is empry！");
//                continue;
//            }
            //找到policeNo不为空的记录
//         String updataRepeatId = hsmDistMapper.getRepeatIdByReportCodeAndPolice(reportCode, policeNo);
            //找到最后插入的repeatId,更新这条数据
            String updataRepeatId = hsmDistMapper.getLastedRepeatIdByReportCode(reportCode);
            //未找到匹配信息，不做处理
            if (updataRepeatId ==null || updataRepeatId.isEmpty()){
                logger.warn("未找到匹配reportCode[{}]的信息！",reportCode);
                continue;
            }
            //repeatId 是指令表的id,类型bigint
            repeat.setRepeatId(Long.valueOf(updataRepeatId));
            //测试用,相当于找到了reportCode+policeNo数据。
//            repeat.setRepeatId(temp++);
            logger.info("currentReport: {} ", JSON.toJSONString(currentReport));

            //根据上面找到的唯一id更新信息
            Integer num = hsmDistMapper.insertHsmRepeat(currentReport);
            //如果处理完毕，更新t_report_instuct成功，删除它；
            if (num > 0) {
                repeatNum += num;
                Integer delNum = hsmSrcMapper.deleteHsmDataById(hsmdata.getId());
                if (delNum > 0) {
                    logger.info("删除id是{}的源数据成功,{}", hsmdata.getId());
                }
            }

        }
        logger.info("更新 举报指令表[ygp.t_report_instuct] {}条数据.", repeatNum);
    }


    private List<String> getRportCodeByMongoIdFromReport(String mongoId) {
        List<String> reportCode = hsmDistMapper.getHsmReportCodeByMongoId(mongoId);
        return reportCode;
    }

    //解析content内容变成实体对象
    private CurrentReport convertHsmContent(Hsmdata hsmdata) {
        byte[] contents = hsmdata.getContents();
        String content = new String(contents, Charset.forName("GBK"));
        JSONObject jsonObject = JSONObject.parseObject(content);
        logger.info("解析content:{}", JSON.toJSONString(jsonObject));
        CurrentReport currentReport = jsonObject.toJavaObject(CurrentReport.class);
        return currentReport;
    }

    private Integer handleReport(CurrentReport currentReport) {
        Integer num = hsmDistMapper.insertHsmReport(currentReport);
        return num;
    }

    private Integer handleRepeat(CurrentReport currentReport) {
        Integer num = hsmDistMapper.insertHsmRepeat(currentReport);
        return num;
    }
}
