package com.lehman.commons.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class TbCfrmRole {
    private String keyRoleId;
    private String indexPageResourceId;
    private String remarks;
    private String createdBy;
    @JSONField( format = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createdTime;
    private String modifiedBy;
    @JSONField( format = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date modifiedTime;

    @Transient
    protected List< TbCfrmAuthorization > tbCfrmAuthorizations;

    public String getKeyRoleId() {
        return keyRoleId;
    }

    public void setKeyRoleId( String keyRoleId ) {
        this.keyRoleId = keyRoleId;
    }

    public String getIndexPageResourceId() {
        return indexPageResourceId;
    }

    public void setIndexPageResourceId( String indexPageResourceId ) {
        this.indexPageResourceId = indexPageResourceId;
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

    public List< TbCfrmAuthorization > getTbCfrmAuthorizations() {
        return tbCfrmAuthorizations;
    }

    public void setTbCfrmAuthorizations( List< TbCfrmAuthorization > tbCfrmAuthorizations ) {
        this.tbCfrmAuthorizations = tbCfrmAuthorizations;
    }
}