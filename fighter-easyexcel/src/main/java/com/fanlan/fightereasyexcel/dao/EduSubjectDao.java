package com.fanlan.fightereasyexcel.dao;

import com.fanlan.fightereasyexcel.mbg.model.EduSubject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EduSubjectDao {
    /**
     * 自定义批量导入数据
     * @param list
     * @return
     */
    int addLists(@Param("list") List<EduSubject> list);
}
