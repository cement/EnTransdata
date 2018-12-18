package com.hnen.transdata.mapper.hsmsrc;


import com.hnen.transdata.entity.Hsmdata;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper
@Qualifier(value = "hsmSrcDataSource")
/**
 * @Author YSH
 * 获取HSM表数据
 */
public interface IHsmSrcMapper {


/*                        " <where>" +
                                "      <if test='lastInsertTime!=null and lastInsertTime!=\"\"'> " +
                                "         AND inserttime  <![CDATA[  >  ]]> #{lastInsertTime} " +
                                "     </if>" +
                                "</where>" +*/
    @Select(
            "<script>" +
                    "SELECT * FROM " +
                    "(" +
                    " SELECT id,name,contents,inserttime,transtime " +
                    " FROM HSM " +
                    " WHERE name = ‘ygp_report’ OR name = 'ygp_repeat' " +
                    ") " +
                    "<where>" +
                    "    <if test='batchNum!=null and batchNum!=\"\"'>" +
                    "         AND rownum <![CDATA[  <=  ]]>  #{batchNum} " +
                    "     </if>" +
                    "</where>" +
            "</script>"
    )
    //获取name 为 ygp_reprot 和 name 为ygp_repeat 的全部数据
    public  List<Hsmdata> getHsmData(@Param("batchNum") Integer batchNum);


    @Select(
            "<script>" +
                    "SELECT * FROM " +
                    "(" +
                    " SELECT id,name,contents,inserttime,transtime " +
                    " FROM HSM " +
                    " WHERE name = 'ygp_report' " +
                    ") " +
                    "<where>" +
                    "    <if test='batchNum!=null and batchNum!=\"\"'>" +
                    "         AND rownum <![CDATA[  <=  ]]>  #{batchNum} " +
                    "     </if>" +
                    "</where>" +
            "</script>"
    )
    //获取name 为 ygp_repeat 的数据
    public  List<Hsmdata> getHsmReport(@Param("batchNum") Integer batchNum);

    @Select(
            "<script>" +
                    "SELECT * FROM " +
                    "(" +
                    " SELECT id,name,contents,inserttime,transtime " +
                    " FROM HSM " +
                    " WHERE name = 'ygp_repeat' " +
                    ") " +
                    "<where>" +
                    "    <if test='batchNum!=null and batchNum!=\"\"'>" +
                    "         AND rownum <![CDATA[  <=  ]]>  #{batchNum} " +
                    "     </if>" +
                    "</where>" +
            "</script>"
    )
    //获取 name 为ygp_repeat 的数据
    public  List<Hsmdata> getHsmRepeat(@Param("batchNum") Integer batchNum);

    @Delete("delete from hsm where id = #{id}")
    //根据id删除记录
    public  Integer deleteHsmDataById(@Param("id") String id);



}
