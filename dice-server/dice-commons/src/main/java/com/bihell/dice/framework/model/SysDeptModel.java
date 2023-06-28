package com.bihell.dice.framework.model;


import com.bihell.dice.framework.entity.BaseEntity;

import java.util.List;

/**
 * 部门模型
 *
 * @author Tang
 */
public class SysDeptModel extends BaseEntity {

    @java.io.Serial
    private static final long serialVersionUID = -118874730758289766L;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 父部门id
     */
    private Long parentId;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 部门状态
     */
    private String status;

    /**
     * 删除标志
     */
    private String delFlag;

    /**
     * 子部门
     */
    private List<SysDeptModel> children;


    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public List<SysDeptModel> getChildren() {
        return children;
    }

    public void setChildren(List<SysDeptModel> children) {
        this.children = children;
    }

}
