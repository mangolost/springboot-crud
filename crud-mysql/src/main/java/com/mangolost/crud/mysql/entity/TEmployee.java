package com.mangolost.crud.mysql.entity;

import java.util.Date;

/**
 *
 */
public class TEmployee {

    private Integer id;
    private Date createTime;
    private Date updateTime;
    private String employeeNo;
    private String employeeName;
    private Integer age;
    private String position;
    private String degree;
    private String remark;

    public TEmployee() {

    }

    public TEmployee(Integer id, Date createTime, Date updateTime, String employeeNo, String employeeName, Integer age, String position, String degree, String remark) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.employeeNo = employeeNo;
        this.employeeName = employeeName;
        this.age = age;
        this.position = position;
        this.degree = degree;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
