package com.hnen.transdata.mapper.hbase;


import com.hnen.transdata.entity.CurrentReport;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;


/**
 * @Author YSH
 * 导入数据库
 */
@Mapper
@Qualifier(value="hbaseDistDataSource")
public interface IHsmDistMapper {

//       " <where>" +
//               "      <if test='province!=null and province!=\"\"'> " +
//               "         AND nationName  like '%${province}%' " +
//               "     </if>" +
//               "      <if test='cityName!=null and cityName!=\"\"'> " +
//               "         AND nationName  like '%${cityName}%' " +
//               "     </if>" +
//               "      <if test='areaName!=null and areaName!=\"\"'> " +
//               "         AND nationName  like '%${areaName}%' " +
//               "     </if>" +
//               "</where>" +
    @Select(
                "select  nationid from pub.t_dic_nation " +
                        "where  " +
                        " and nationName  like '%${province}%' " +
                        " and nationName  like '%${cityName}%'" +
                        " and nationName  like '%${areaName}%' "
    )
    // 通过 省 市 县(区)名找到 地区码
    public List<String> getNationIdByName(@Param("province") String province, @Param("cityName") String cityName, @Param("areaName") String areaName);


    @Select(
            "select  nationid from pub.t_dic_nation " +
                    "where  " +
                    " nationId like '43%'" +
                    " and substr(nationId,5,2) !='00' " +
                    "and nationName like '%${areaName}%'"
    )
    // 通过 县(区)名找到 地区码
    public List<String> getNationIdByAreaName(@Param("areaName") String areaName);


    @Insert(
            "upsert into ygp.t_report" +
                    "(" +
                        " markCode, "+
                        " reportCode, "+
                        " ifYellowing, "+
                        " ifBet, "+
                        " reportKind, "+
                        " placeName, "+
                        " memo, "+
                        " cityid, "+
                        " areaid, "+
                        " placeadd, "+
//                        " result, "+
                        " reptime, "+
                        " openId, "+
                        " name, "+
                        " paperId, "+
                        " tel, "+
                        " mongoId "+
                    ")" +
                    "  values" +
                    "(" +
                        "  #{markCode}, " +
                        "  #{report.reportCode}, " +
                        "  ${report.ifYellowing?1:0}, " +
                        "  ${report.ifBet?1:0}, " +
                        "  #{report.reportKind}, " +
                        "  #{report.placeName}, " +
                        "  #{report.memo}, " +
                        "  #{report.cityId}, " +
                        "  #{report.districtId},  " +
                        "  #{report.placeAdd},  " +
//                        "  #{},  " +
                        "  #{report.reportTime},  " +
                        "  #{report.transer.openId},  " +
                        "  #{report.transer.name},  " +
                        "  #{report.transer.paperId},  " +
                        "  #{report.transer.tel},  " +
                        "  #{id} " +
                    "  )"
    )
    //插入 举报信息表[YGP.T_REPORT]
    public Integer  insertHsmReport(CurrentReport currentReport);


   //根据mongoId获取 已经插入举报表的reportCode。
   @Select("select reportCode from  ygp.t_report where mongoId= #{mongoId}")
   public List<String> getHsmReportCodeByMongoId(@Param("mongoId") String mongoId);


    //reportCode+ policeNo 获取已经插入举报表的id。
    @Select("select id from  ygp.t_report_instuct where reportCode= #{reportCode} and dealPoliceNo = #{policeNo}")
    public String getRepeatIdByReportCodeAndPolice(@Param("reportCode") String reportCode, @Param("policeNo") String policeNo);

    //reportCode+ policeNo 获取已经插入举报表的id。
    @Select("select max(id) from  ygp.t_report_instuct where reportCode= #{reportCode} ")
    public String getLastedRepeatIdByReportCode(@Param("reportCode") String reportCode);





    @Insert(
            "upsert into ygp.t_report_instuct" +
                    "(" +
                    " id, "+
                    " reportCode, "+
                    " ifTrue, "+
                    " caseMemo, "+
                    " repeatResult, "+
                    " sceneMemo, "+
                    " repeatTime, "+
                    " dealPoliceNo "+
                    ")" +
                    "  values" +
                    "(" +
                    "   #{repeat.repeatId}, " +
                    "   #{repeat.reportCode}, " +
                    "   #{repeat.ifTrue}, " +
                    "   #{repeat.caseMemo}, " +
                    "   #{repeat.repeatResult}, " +
                    "   #{repeat.sceneMemo}, " +
                    "   #{repeat.repeatTime}, " +
                    "   #{repeat.police.policeNo} " +
                    "  )"
    )
    //插入 举报指令信息表[ygp.t_report_instuct]
    public Integer  insertHsmRepeat(CurrentReport currentReport);
}
