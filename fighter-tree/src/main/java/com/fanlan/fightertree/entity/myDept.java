package com.fanlan.fightertree.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class myDept {
    /**
     * id
     */
    private Integer deptId;
    /**
     * 上级部门
     */
    private Integer parentId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     */
    private Boolean status;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;



    /**
     * 子级部门
     */
    @TableField(exist = false)
    private List<myDept> children = new ArrayList<>();


}
