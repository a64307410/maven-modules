package com.lehman.commons.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TbCfrmUser {
    private String keyUserId;
    private String idxGroupId_tbGroup;
    private String idxRoleId_tbRole;
    private String password;
    private Integer state;
    private Integer locked;
    private Integer loginFailTimes;
    private Integer loginTimes;
    @JSONField( format = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date lastLoginTime;
    private String lastLoginIP;
    private String email;
    private String phoneNumber;
    private String accessIPs;
    private String language;
    private String remarks;
    private String createdBy;

    @JSONField( format = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createdTime;
    private String modifiedBy;

    @JSONField( format = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date modifiedTime;


    public String getKeyUserId() {
        return keyUserId;
    }

    public void setKeyUserId( String keyUserId ) {
        this.keyUserId = keyUserId;
    }

    public String getIdxGroupId_tbGroup() {
        return idxGroupId_tbGroup;
    }

    public void setIdxGroupId_tbGroup( String idxGroupId_tbGroup ) {
        this.idxGroupId_tbGroup = idxGroupId_tbGroup;
    }

    public String getIdxRoleId_tbRole() {
        return idxRoleId_tbRole;
    }

    public void setIdxRoleId_tbRole( String idxRoleId_tbRole ) {
        this.idxRoleId_tbRole = idxRoleId_tbRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState( Integer state ) {
        this.state = state;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked( Integer locked ) {
        this.locked = locked;
    }

    public Integer getLoginFailTimes() {
        return loginFailTimes;
    }

    public void setLoginFailTimes( Integer loginFailTimes ) {
        this.loginFailTimes = loginFailTimes;
    }

    public Integer getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes( Integer loginTimes ) {
        this.loginTimes = loginTimes;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime( Date lastLoginTime ) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP( String lastLoginIP ) {
        this.lastLoginIP = lastLoginIP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber( String phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccessIPs() {
        return accessIPs;
    }

    public void setAccessIPs( String accessIPs ) {
        this.accessIPs = accessIPs;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage( String language ) {
        this.language = language;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks( String remarks ) {
        this.remarks = remarks;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy( String createdBy ) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime( Date createdTime ) {
        this.createdTime = createdTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy( String modifiedBy ) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime( Date modifiedTime ) {
        this.modifiedTime = modifiedTime;
    }
}