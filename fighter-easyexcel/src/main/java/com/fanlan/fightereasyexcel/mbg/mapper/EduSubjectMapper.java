package com.fanlan.fightereasyexcel.mbg.mapper;

import com.fanlan.fightereasyexcel.mbg.model.EduSubject;
import com.fanlan.fightereasyexcel.mbg.model.EduSubjectExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EduSubjectMapper {
    int countByExample(EduSubjectExample example);

    int deleteByExample(EduSubjectExample example);

    int deleteByPrimaryKey(String id);

    int insert(EduSubject record);

    int insertSelective(EduSubject record);

    List<EduSubject> selectByExample(EduSubjectExample example);

    EduSubject selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EduSubject record, @Param("example") EduSubjectExample example);

    int updateByExample(@Param("record") EduSubject record, @Param("example") EduSubjectExample example);

    int updateByPrimaryKeySelective(EduSubject record);

    int updateByPrimaryKey(EduSubject record);
}