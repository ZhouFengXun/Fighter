package com.fanlan.fightertree.service.impl;

import com.fanlan.fightertree.entity.myDept;
import com.fanlan.fightertree.mapper.myDeptMapper;
import com.fanlan.fightertree.service.myDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class myDeptServiceImpl implements myDeptService {
    @Resource
    myDeptMapper myDeptMapper;
    @Override
    public List<myDept> getTreeData() {
        //保存头节点
        List<myDept> arrayList = new ArrayList<>();
        //获取表中数据
        List<myDept> myDeptList = myDeptMapper.selectList(null);
        //将数据转换成map
        Map<Integer, myDept> myDeptMap = myDeptList.stream().collect(Collectors.toMap(myDept::getDeptId, myDept -> myDept));
        //添加子节点
        myDeptList.stream().forEach(myDept -> {
            if (myDept.getParentId() == 0){
                arrayList.add(myDept);
            }else {
                myDept MyDept = myDeptMap.get(myDept.getParentId());
                MyDept.getChildren().add(myDept);
            }
        });
        return arrayList;
    }
}
