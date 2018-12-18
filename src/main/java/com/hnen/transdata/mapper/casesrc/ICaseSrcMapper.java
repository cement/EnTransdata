package com.hnen.transdata.mapper.casesrc;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

@Mapper
@Qualifier(value = "caseSrcDataSource")

public interface ICaseSrcMapper {


    @Select("select  * from VV_AJDWXXB")

    public List<Map<String, Object>> connectTest();

    //获取 案件单位信息表(VV_AJDWXXB)
    @Select(
            "<script>" +
            "SELECT * FROM(" +
                "  SELECT  " +
                "   id as id," +
                "   案件编号 as caseCode," +
                "   单位编号 as unitId," +
                "   单位名称 as unitName," +
                "   法定代表 as legalPerson," +
                "   单位联系电话 as unitPhone," +
                "   取缔日期 as banningTime," +
                "   吊销许可证 as revokeLicense," +
                "   actTime as actTime" +
                "   FROM  VV_AJDWXXB  " +
                "   ORDER BY ACTTIME " +
            ")" +

            " <where>   " +

            " <if test='batchNum!=null and batchNum!=\"\"'>" +
            "    AND ROWNUM  <![CDATA[  <  ]]>  ${batchNum}" +
            "</if>" +
            " <if test='lastActTime!=null and lastActTime!=\"\"'>" +
            "    AND actTime  <![CDATA[  >  ]]> #{lastActTime}" +
            "</if>" +

            "</where>" +

            "</script>"
    )
    //案件单位信息表(VV_AJDWXXB)
    public List<Map<String, Object>> getUnitInfo(@Param("batchNum") Integer batchNum, @Param("lastActTime") String lastActTime);




    //案件基础信息表[VV_AJJCXXB]
    @Select(
            "<script>" +
                    "SELECT * FROM(" +
                        "SELECT  " +
                        "    id as id," +
                        "    所属区域 as orgId," +
                        "    办案单位 as utilId," +
                        "    案件类型 as caseType," +
                        "    案件类别 as code," +
                        "    处理措施 as treatment," +
                        "    立案日期 as filingTime," +
                        "    措施日期 as measuresTime," +
                        "    破案日期 as caseTime," +
                        "    受案日期 as acceptTime," +
                        "    结案日期 as closedTime," +
                        "    案件编号 as caseCode," +
                        "    案件名称 as caseName," +
                        "    损失金额 as loss," +
                        "    操作标志 as actFlag," +
                        "    actTime as actTime  " +
                        "  FROM VV_AJJCXXB " +
                        " ORDER BY ACTTIME " +
                    ")" +
                    " <where>   " +

                    " <if test='batchNum!=null and batchNum!=\"\"'>" +
                    "    AND ROWNUM  <![CDATA[  <  ]]>  ${batchNum}" +
                    "</if>" +
                    " <if test='lastActTime!=null and lastActTime!=\"\"'>" +
                    "    AND actTime  <![CDATA[  >  ]]> #{lastActTime}" +
                    "</if>" +

                    "</where>" +
                    "</script>"
    )


    //案件基础信息表[VV_AJJCXXB]
    public List<Map<String, Object>> getCasebaseInfo(@Param("batchNum") Integer batchNum, @Param("lastActTime") String lastActTime);


    //案件人员信息表[VV_AJRYXXB]
    @Select(
            "<script>" +
                    "SELECT * FROM(" +
                        "SELECT  " +
                        "    id  as id, " +
                        "    案件编号  as caseId, " +
                        "    人员编号  as peopleId, " +
                        "    照片  as photo, " +
                        "    证件类型  as paperType, " +
                        "    证件号码  as paperNumber, " +
                        "    姓名  as name, " +
                        "    性别  as sex, " +
                        "    曾用名  as bName, " +
                        "    别名绰号  as nikeName, " +
                        "    出生日期  as birthday , " +
                        "    年龄  as age , " +
                        "    国籍  as nationality , " +
                        "    民族  as nation, " +
                        "    文化程度  as education, " +
                        "    职业  as occupation, " +
                        "    工作单位  as workUtil, " +
                        "    政治面貌  as political, " +
                        "    户籍区划  as householdRegister, " +
                        "    户籍地址  as permanentAddress, " +
                        "    居住区划  as pesidentialPlanning, " +
                        "    居住地址  as address, " +
                        "    人员类别  as peopleType, " +
                        "    身高  as height, " +
                        "    现实状况  as reality, " +
                        "    专长特长  as speciality, " +
                        "    作案原因  as reason , " +
                        "    作案特点  as characteristic, " +
                        "    联系电话  as tel, " +
                        "    涉嫌罪名  as charge, " +
                        "    犯罪前科  as criminalRecord, " +
                        "    DBMS_LOB.SUBSTR(犯罪事实)  as corpusdelicti, " +
                        "    家属姓名  as familyName, " +
                        "    家属地址  as familyAddr, " +
                        "    双方关系  as relationShip, " +
                        "    家属电话  as familyTel, " +
                        "    检查公诉  as litigation, " +
                        "    法院判决  as sentence, " +
                        "    有无前科  as tfCriminalRecord, " +
                        "    本市人口  as population, " +
                        "    刑拘日期  as custodyTime, " +
                        "    刑拘执行日期  as custodyImplementTime, " +
                        "    延长拘留  as extendedDetention, " +
                        "    延长期限  as extendedTerm, " +
                        "    报捕日期  as arrestTime, " +
                        "    批捕日期  as yarrestTime, " +
                        "    逮捕日期  as darrestTime, " +
                        "    不批捕日期  as narrestTime, " +
                        "    取保日期  as bail, " +
                        "    保证人  as guarantor, " +
                        "    保证金  as bond, " +
                        "    没收日期  as confiscation, " +
                        "    没收金额  as confiscationMoney, " +
                        "    退还日期  as returnDate, " +
                        "    TO_CHAR(退还金额)  as returnMoney, " +
                        "    监居日期  as detentionTime, " +
                        "    释放日期  as releaseTime, " +
                        "    强戒日期  as forceTime, " +
                        "    强戒文号  as forceNum, " +
                        "    社区戒毒日期  as communityTime, " +
                        "    社区戒毒文号  as communityNum, " +
                        "    是否直诉  as directAction, " +
                        "    起诉日期  as prosecutionTime, " +
                        "    行政处罚  as administrativesanction, " +
                        "    行处情况  as administration, " +
                        "    拘留期限  as detentionPeriod, " +
                        "    罚款金额  as penaltyAmount, " +
                        "    处罚文号  as punishNum, " +
                        "    立案日期  as filingTime, " +
                        "    办案单位  as caseUtil, " +
                        "    主办人  as sponsor, " +
                        "    TO_CHAR(行政拘留期限)  as xzjlqx, " +
                        "    actTime  as actTime  " +
                        " FROM VV_AJRYXXB " +
                        " ORDER BY ACTTIME " +
                    ")" +
                    " <where>   " +

                    " <if test='batchNum!=null and batchNum!=\"\"'>" +
                    "    AND ROWNUM  <![CDATA[  <  ]]>  ${batchNum}" +
                    "</if>" +
                    " <if test='lastActTime!=null and lastActTime!=\"\"'>" +
                    "    AND actTime  <![CDATA[  >  ]]> #{lastActTime}" +
                    "</if>" +

                    "</where>" +
                    "</script>")

    //案件人员信息表[vv_ajryxxb]
    public List<Map<String, Object>> getCasepeopleInfo(@Param("batchNum") Integer batchNum, @Param("lastActTime") String lastActTime);



    //处罚信息表[VV_DCCFXXB]
    @Select(
            "<script>" +
                    "SELECT * FROM(" +
                        "SELECT  " +
                        "    id as id,  " +
                        "    接警编号 as caseId,  " +
                        "    姓名 as name,  " +
                        "    证件号码 as idcard,  " +
                        "    出生日期 as birthday,  " +
                        "    性别 as sex,  " +
                        "    住址地址 as address,  " +
                        "    涉嫌罪名 as charges,  " +
                        "    涉嫌罪名代码 as chargesId,  " +
                        "    法律依据 as lawsBasis,  " +
                        "    处罚方式 as punishmentMethod,  " +
                        "    违法证据 as evidence,  " +
                        "    是否人员 as ifPeople,  " +
                        "    单位名称 as unitName,  " +
                        "    法定人姓名 as legalPerson,  " +
                        "    单位地址 as unitAddr,  " +
                        "    单位电话 as unitPhone,  " +
                        "    现查明 as documentation,  " +
                        "    填发时间 as writeTime,  " +
                        "    操作标志 as actFlag,  " +
                        "    actTime as actTime " +
                        " FROM VV_DCCFXXB " +
                        " ORDER BY ACTTIME " +
                    ")" +
                    " <where>   " +

                    " <if test='batchNum!=null and batchNum!=\"\"'>" +
                    "    AND ROWNUM  <![CDATA[  <  ]]>  ${batchNum}" +
                    "</if>" +
                    " <if test='lastActTime!=null and lastActTime!=\"\"'>" +
                    "    AND actTime  <![CDATA[  >  ]]> #{lastActTime}" +
                    "</if>" +

                    "</where>" +
                    "</script>"
    )

    //处罚信息表[vv_dccfxxb]
    public List<Map<String, Object>> getPunishmentInfo(@Param("batchNum") Integer batchNum, @Param("lastActTime") String lastActTime);



    //电子笔录信息表[VV_DZBLXXBB]
    @Select(

            "<script>" +
                    "SELECT * FROM(" +
                        "SELECT  " +
                        "    id as id ,  " +
                        "    案件编号 as caseId ,  " +
                        "    时间 as time,  " +
                        "    地点 as addr,  " +
                        "    询问人 as askingPeople ,  " +
                        "    询问人工作的岗位 as askingPeopleUtil ,  " +
                        "    记录人 as noteTaker ,  " +
                        "    记录人单位 as noteTakerUtil ,  " +
                        "    被询问人 as questioned ,  " +
                        "    被询问人性别 as questionedSex ,  " +
                        "    被询问人出生日期 as questionedBir ,  " +
                        "    身份证号码 as idcard ,  " +
                        "    被询问人户籍地 as questionedRegister ,  " +
                        "    被询问人联系方式 as questionedTel ,  " +
                        "    被询问人工作单位 as questionedUtil,  " +
                        "    被询问人是否党员 as questionedMember ,  " +
                        "    被询问人现住址 as questionedAddr,  " +
                        "    DBMS_LOB.SUBSTR(问答) as qa ,  " +
                        "    actTime  as  actTime   " +
                        " FROM VV_DZBLXXBB " +
                        " ORDER BY ACTTIME " +
                    ")" +
                    " <where>   " +

                    " <if test='batchNum!=null and batchNum!=\"\"'>" +
                    "    AND ROWNUM  <![CDATA[  <  ]]>  ${batchNum}" +
                    "</if>" +
                    " <if test='lastActTime!=null and lastActTime!=\"\"'>" +
                    "    AND actTime  <![CDATA[  >  ]]> #{lastActTime}" +
                    "</if>" +

                    "</where>" +
            "</script>"
    )

    //电子笔录信息表[vv_dzblxxb]
    public List<Map<String, Object>> getRecordInfo(@Param("batchNum") Integer batchNum, @Param("lastActTime") String lastActTime);

    //接警案件表[VV_JJAJDJB]
    @Select(
            "<script>" +
                    "SELECT * FROM(" +
                        "SELECT  " +
                        "     id as id, " +
                        "     所属区域 as orgId," +
                        "     警情名称 as alarmName," +
                        "     发案时间 as incidentTime," +
                        "     发现时间 as findTime," +
                        "     报警时间 as callPoliceTime," +
                        "     发现详址 as findAddr," +
                        "     报警方式 as callPoliceMode," +
                        "     报警人姓名 as name," +
                        "     报警人性别 as sex," +
                        "     报案人年龄 as age," +
                        "     报案人单位 as util," +
                        "     报案人住址 as addr," +
                        "     报案人联系电话 as tel," +
                        "     事主姓名 as victimName," +
                        "     事主性别 as victimSex," +
                        "     事主单位 as victimUtil," +
                        "     事主住址 as victimAddr," +
                        "     事主联系电话 as victimTel," +
                        "     简要案情及损失情况 as briefCase," +
                        "     死亡人数 as deathNum," +
                        "     受伤人数 as injuredNum," +
                        "     经济损失 as economicLoss," +
                        "     领导意见 as leaderShip," +
                        "     处理情况 as treatment," +
                        "     立案日期 as filingTime," +
                        "     报案人 as informant," +
                        "     填表人 as formPeople," +
                        "     填表时间 as formTime," +
                        "     案件编号 as caseCode," +
                        "     警情类型 as jqlx," +
                        "     actTime as actTime  " +
                        " FROM VV_JJAJDJB " +
                        " ORDER BY ACTTIME " +
                    ")" +
                    " <where>   " +

                    " <if test='batchNum!=null and batchNum!=\"\"'>" +
                    "    AND ROWNUM  <![CDATA[  <  ]]>  ${batchNum}" +
                    "</if>" +
                    " <if test='lastActTime!=null and lastActTime!=\"\"'>" +
                    "    AND actTime  <![CDATA[  >  ]]> #{lastActTime}" +
                    "</if>" +

                    "</where>" +
            "</script>"

    )

    //接警案件表[VV_JJAJDJB]
    public List<Map<String, Object>> getReceiveCaseInfo(@Param("batchNum") Integer batchNum, @Param("lastActTime") String lastActTime);

}
