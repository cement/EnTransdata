package com.hnen.transdata.mapper.hbase;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

@Mapper
@Qualifier(value="hbaseDistDataSource")
public interface ICaseDistMaper {
    @Select("select *from YGP.T_POLICE")
    public List<Map<String,Object>> connectTest();



    @Insert(
            "upsert into INTERFACE.VV_AJDWXXB" +
                    "(" +
                        "  id," +
                        "  caseCode," +
                        "  unitId," +
                        "  unitName," +
                        "  legalPerson," +
                        "  unitPhone," +
                        "  banningTime," +
                        "  revokeLicense, " +
                        "  actTime" +
                    ")" +
                    "  values" +
                    "(" +
                        "   #{ID}, " +
                        "   #{CASECODE}, " +
                        "   #{UNITID}, " +
                        "   #{UNITNAME}, " +
                        "   #{LEGALPERSON}, " +
                        "   #{UNITPHONE}, " +
                        "   #{BANNINGTIME}, " +
                        "   #{REVOKELICENSE}, " +
                        "   #{ACTTIME} " +
                    "  )"
    )
    //插入案件单位信息表[vv_ajdwxxb]
    public Integer  insertUnitInfo(Map<String, Object> unitInfo);

    //获取 [案件单位信息表(VV_AJDWXXB)] 最大Acttime
    @Select("SELECT MAX(ACTTIME) FROM INTERFACE.VV_AJDWXXB")
    public String getUnitInfoLastActtime();

    //插入案件基础信息表[vv_ajjcxxb]
    @Insert(
            "UPSERT  into INTERFACE.VV_AJJCXXB" +
                    "(" +
                           "id, "+
                           "orgId ,"+
                           "utilId, "+
                           "caseType   ,"+
                           "code, "+
                           "treatment, "+
                           "filingTime,  "+
                           "measuresTime, "+
                           "caseTime, "+
                           "acceptTime, "+
                           "closedTime, "+
                           "caseCode, "+
                           "caseName, "+
                           "loss, "+
                           "actFlag   ,"+
                           "actTime "+
                    ")" +
                    "  VALUES" +
                    "(" +
                            "#{ID}, "+
                            "#{ORGID },"+
                            "#{UTILID}, "+
                            "#{CASETYPE   },"+
                            "#{CODE}, "+
                            "#{TREATMENT}, "+
                            "#{FILINGTIME},  "+
                            "#{MEASURESTIME}, "+
                            "#{CASETIME}, "+
                            "#{ACCEPTTIME}, "+
                            "#{CLOSEDTIME}, "+
                            "#{CASECODE}, "+
                            "#{CASENAME}, "+
                            "#{LOSS}, "+
                            "#{ACTFLAG}, "+
                            "#{ACTTIME }"+
                    "  )"
    )
    //案件基础信息表[vv_ajjcxxb]
    public Integer insertCasebaseInfo(Map<String, Object> unitInfo);

    //获取 [案件基础信息表(VV_AJJCXXB)] 最大Acttime
    @Select("SELECT MAX(ACTTIME) FROM INTERFACE.VV_AJJCXXB")
    public String getCasebaseInfoLastActtime();





    //案件人员信息表[VV_AJRYXXB]
    @Insert(
            "upsert into INTERFACE.VV_AJRYXXB" +
                    "(" +
                            " id, " +
                            " caseId, " +
                            " peopleId, " +
                            " photo, " +
                            " paperType, " +
                            " paperNumber, " +
                            " name, " +
                            " sex, " +
                            " bName, " +
                            " nikeName, " +
                            " birthday , " +
                            " age , " +
                            " nationality , " +
                            " nation, " +
                            " education, " +
                            " occupation, " +
                            " workUtil, " +
                            " political, " +
                            " householdRegister, " +
                            " permanentAddress, " +
                            " pesidentialPlanning, " +
                            " address, " +
                            " peopleType, " +
                            " height, " +
                            " reality, " +
                            " speciality, " +
                            " reason , " +
                            " characteristic, " +
                            " tel, " +
                            " charge, " +
                            " criminalRecord, " +
                            " corpusdelicti, " +
                            " familyName, " +
                            " familyAddr, " +
                            " relationShip, " +
                            " familyTel, " +
                            " litigation, " +
                            " sentence, " +
                            " tfCriminalRecord, " +
                            " population, " +
                            " custodyTime, " +
                            " custodyImplementTime, " +
                            " extendedDetention, " +
                            " extendedTerm, " +
                            " arrestTime, " +
                            " yarrestTime, " +
                            " darrestTime, " +
                            " narrestTime, " +
                            " bail, " +
                            " guarantor, " +
                            " bond, " +
                            " confiscation, " +
                            " confiscationMoney, " +
                            " returnDate, " +
                            " returnMoney, " +
                            " detentionTime, " +
                            " releaseTime, " +
                            " forceTime, " +
                            " forceNum, " +
                            " communityTime, " +
                            " communityNum, " +
                            " directAction, " +
                            " prosecutionTime, " +
                            " administrativesanction, " +
                            " administration, " +
                            " detentionPeriod, " +
                            " penaltyAmount, " +
                            " punishNum, " +
                            " filingTime, " +
                            " caseUtil, " +
                            " sponsor, " +
                            " xzjlqx, " +
                            " actTime  " +
                    ")" +
                    "  VALUES" +
                    "(" +
                            "#{ID}, " +
                            "#{CASEID}, " +
                            "#{PEOPLEID}, " +
                            "#{PHOTO}, " +
                            "#{PAPERTYPE}, " +
                            "#{PAPERNUMBER}, " +
                            "#{NAME}, " +
                            "#{SEX}, " +
                            "#{BNAME}, " +
                            "#{NIKENAME}, " +
                            "#{BIRTHDAY }, " +
                            "#{AGE }, " +
                            "#{NATIONALITY }, " +
                            "#{NATION}, " +
                            "#{EDUCATION}, " +
                            "#{OCCUPATION}, " +
                            "#{WORKUTIL}, " +
                            "#{POLITICAL}, " +
                            "#{HOUSEHOLDREGISTER}, " +
                            "#{PERMANENTADDRESS}, " +
                            "#{PESIDENTIALPLANNING}, " +
                            "#{ADDRESS}, " +
                            "#{PEOPLETYPE}, " +
                            "#{HEIGHT}, " +
                            "#{REALITY}, " +
                            "#{SPECIALITY}, " +
                            "#{REASON }, " +
                            "#{CHARACTERISTIC}, " +
                            "#{TEL}, " +
                            "#{CHARGE}, " +
                            "#{CRIMINALRECORD}, " +
                            "#{CORPUSDELICTI}, " +
                            "#{FAMILYNAME}, " +
                            "#{FAMILYADDR}, " +
                            "#{RELATIONSHIP}, " +
                            "#{FAMILYTEL}, " +
                            "#{LITIGATION}, " +
                            "#{SENTENCE}, " +
                            "#{TFCRIMINALRECORD}, " +
                            "#{POPULATION}, " +
                            "#{CUSTODYTIME}, " +
                            "#{CUSTODYIMPLEMENTTIME}, " +
                            "#{EXTENDEDDETENTION}, " +
                            "#{EXTENDEDTERM}, " +
                            "#{ARRESTTIME}, " +
                            "#{YARRESTTIME}, " +
                            "#{DARRESTTIME}, " +
                            "#{NARRESTTIME}, " +
                            "#{BAIL}, " +
                            "#{GUARANTOR}, " +
                            "#{BOND}, " +
                            "#{CONFISCATION}, " +
                            "#{CONFISCATIONMONEY}, " +
                            "#{RETURNDATE}, " +
                            "#{RETURNMONEY}, " +
                            "#{DETENTIONTIME}, " +
                            "#{RELEASETIME}, " +
                            "#{FORCETIME}, " +
                            "#{FORCENUM}, " +
                            "#{COMMUNITYTIME}, " +
                            "#{COMMUNITYNUM}, " +
                            "#{DIRECTACTION}, " +
                            "#{PROSECUTIONTIME}, " +
                            "#{ADMINISTRATIVESANCTION}, " +
                            "#{ADMINISTRATION}, " +
                            "#{DETENTIONPERIOD}, " +
                            "#{PENALTYAMOUNT}, " +
                            "#{PUNISHNUM}, " +
                            "#{FILINGTIME}, " +
                            "#{CASEUTIL}, " +
                            "#{SPONSOR}, " +
                            "#{XZJLQX}, " +
                            "#{ACTTIME} " +
                    "  )"
    )
   //案件人员信息表[vv_ajryxxb]
    public Integer insertCasepeopleInfo(Map casePeopleInfo);

    //获取 [案件人员信息表(VV_AJRYXXB)] 最大Acttime
    @Select("SELECT MAX(ACTTIME) FROM INTERFACE.VV_AJRYXXB")
    public String getCasepeopleInfoLastActtime();




    //处罚信息表[vv_dccfxxb]
    @Insert(
            "upsert into INTERFACE.VV_DCCFXXB" +
                    "(" +
                        "id, " +
                        "caseId, " +
                        "name, " +
                        "idcard, " +
                        "birthday, " +
                        "sex, " +
                        "address, " +
                        "charges, " +
                        "chargesId, " +
                        "lawsBasis, " +
                        "punishmentMethod, " +
                        "evidence, " +
                        "ifPeople, " +
                        "unitName, " +
                        "legalPerson, " +
                        "unitAddr, " +
                        "unitPhone, " +
                        "documentation, " +
                        "writeTime, " +
                        "actFlag, " +
                        "actTime " +
                    ")" +
                    "  VALUES" +
                    "(" +
                        "#{ID}, " +
                        "#{CASEID}, " +
                        "#{NAME}, " +
                        "#{IDCARD}, " +
                        "#{BIRTHDAY}, " +
                        "#{SEX}, " +
                        "#{ADDRESS}, " +
                        "#{CHARGES}, " +
                        "#{CHARGESID}, " +
                        "#{LAWSBASIS}, " +
                        "#{PUNISHMENTMETHOD}, " +
                        "#{EVIDENCE}, " +
                        "#{IFPEOPLE}, " +
                        "#{UNITNAME}, " +
                        "#{LEGALPERSON}, " +
                        "#{UNITADDR}, " +
                        "#{UNITPHONE}, " +
                        "#{DOCUMENTATION}, " +
                        "#{WRITETIME}, " +
                        "#{ACTFLAG},  " +
                        "#{ACTTIME }" +
                    "  )"
    )
    //处罚信息表[vv_dccfxxb]
    public Integer insertPunishmentInfo(Map punishInfo);

    //获取 [处罚信息表(VV_DCCFXXB)] 最大Acttime
    @Select("SELECT MAX(ACTTIME) FROM INTERFACE.VV_DCCFXXB")
    public String getPunishmentInfoLastActtime();



    //电子笔录信息表[vv_dzblxxb]
    @Insert(
            "UPSERT INTO INTERFACE.VV_DZBLXXB" +
                    "(" +
                        "id ,  " +
                        "caseId ,  " +
                        "time,  " +
                        "addr,  " +
                        "askingPeople ,  " +
                        "askingPeopleUtil ,  " +
                        "noteTaker ,  " +
                        "noteTakerUtil ,  " +
                        "questioned ,  " +
                        "questionedSex ,  " +
                        "questionedBir ,  " +
                        "idcard ,  " +
                        "questionedRegister ,  " +
                        "questionedTel ,  " +
                        "questionedUtil,  " +
                        "questionedMember ,  " +
                        "questionedAddr,  " +
                        "qa ,  " +
                        "actTime   " +
                    ")" +
                    "  VALUES" +
                    "(" +
                        "#{ID },  " +
                        "#{CASEID },  " +
                        "#{TIME},  " +
                        "#{ADDR},  " +
                        "#{ASKINGPEOPLE },  " +
                        "#{ASKINGPEOPLEUTIL },  " +
                        "#{NOTETAKER },  " +
                        "#{NOTETAKERUTIL },  " +
                        "#{QUESTIONED },  " +
                        "#{QUESTIONEDSEX },  " +
                        "#{QUESTIONEDBIR },  " +
                        "#{IDCARD },  " +
                        "#{QUESTIONEDREGISTER },  " +
                        "#{QUESTIONEDTEL },  " +
                        "#{QUESTIONEDUTIL},  " +
                        "#{QUESTIONEDMEMBER },  " +
                        "#{QUESTIONEDADDR},  " +
                        "#{QA },  " +
                        "#{ACTTIME   }" +
                    "  )"
    )
    //电子笔录信息表[vv_dzblxxb]
    public Integer insertRecordInfo(Map recordInfo);

    //获取 [电子笔录信息表(VV_DZBLXXB)] 最大Acttime
    @Select("SELECT MAX(ACTTIME) FROM INTERFACE.VV_DZBLXXB")
    public String getRecordInfoLastActtime();


    //接警案件表[VV_JJAJDJB]
    @Insert(
            "UPSERT INTO INTERFACE.VV_JJAJDJB" +
                    "(" +
                        "id, " +
                        "orgId," +
                        "alarmName," +
                        "incidentTime," +
                        "findTime," +
                        "callPoliceTime," +
                        "findAddr," +
                        "callPoliceMode," +
                        "name," +
                        "sex," +
                        "age," +
                        "util," +
                        "addr," +
                        "tel," +
                        "victimName," +
                        "victimSex," +
                        "victimUtil," +
                        "victimAddr," +
                        "victimTel," +
                        "briefCase," +
                        "deathNum," +
                        "injuredNum," +
                        "economicLoss," +
                        "leaderShip," +
                        "treatment," +
                        "filingTime," +
                        "informant," +
                        "formPeople," +
                        "formTime," +
                        "caseCode," +
                        "jqlx," +
                        "actTime  " +
                    ")" +
                    "  VALUES" +
                    "(" +
                        "#{ID},  " +
                        "#{ORGID}, " +
                        "#{ALARMNAME}, " +
                        "#{INCIDENTTIME}, " +
                        "#{FINDTIME}, " +
                        "#{CALLPOLICETIME}, " +
                        "#{FINDADDR}, " +
                        "#{CALLPOLICEMODE}, " +
                        "#{NAME}, " +
                        "#{SEX}, " +
                        "#{AGE}, " +
                        "#{UTIL}, " +
                        "#{ADDR}, " +
                        "#{TEL}, " +
                        "#{VICTIMNAME}, " +
                        "#{VICTIMSEX}, " +
                        "#{VICTIMUTIL}, " +
                        "#{VICTIMADDR}, " +
                        "#{VICTIMTEL}, " +
                        "#{BRIEFCASE}, " +
                        "#{DEATHNUM}, " +
                        "#{INJUREDNUM}, " +
                        "#{ECONOMICLOSS}, " +
                        "#{LEADERSHIP}, " +
                        "#{TREATMENT}, " +
                        "#{FILINGTIME}, " +
                        "#{INFORMANT}, " +
                        "#{FORMPEOPLE}, " +
                        "#{FORMTIME}, " +
                        "#{CASECODE}, " +
                        "#{JQLX}, " +
                        "#{ACTTIME}  "  +
                    "  )"
    )
    //接警案件表[vv_jjajdjb]
    public Integer insertReceiveCaseInfo(Map receiveCaseInfo);
    //获取 [电子笔录信息表(VV_JJAJDJB)] 最大Acttime
    @Select("SELECT MAX(ACTTIME) FROM INTERFACE.VV_JJAJDJB")
    public String getReceiveCaseInfoLastActtime();
}
