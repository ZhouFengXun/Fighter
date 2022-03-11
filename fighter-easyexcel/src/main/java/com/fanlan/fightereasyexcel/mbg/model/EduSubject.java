package com.fanlan.fightereasyexcel.mbg.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class EduSubject extends BaseRowModel {
    @ApiModelProperty(value = "课程类别ID")
    @ExcelProperty("课程类别ID")
    private String id;

    @ApiModelProperty(value = "类别名称")
    @ExcelProperty("类别名称")
    private String title;

    @ApiModelProperty(value = "父ID")
    @ExcelProperty("父ID")
    private String parentId;

    @ApiModelProperty(value = "排序字段")
    @ExcelProperty("排序字段")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    @ExcelProperty("创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @ExcelProperty("更新时间")
    private Date gmtModified;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", parentId=").append(parentId);
        sb.append(", sort=").append(sort);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}