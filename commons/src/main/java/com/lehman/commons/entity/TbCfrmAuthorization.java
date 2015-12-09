package com.lehman.commons.entity;

import org.springframework.data.annotation.Transient;

import java.util.Date;

/**
 * Created by Administrator on 2015/8/15.
 */
public class TbCfrmAuthorization {
    private Integer keyAuthId;
    private String idxRoleId_tbRole;
    private String resourceName;
    private String remarks;
    private String action;
    private String createdBy;
    private Date createdTime;
    private String modifiedBy;
    private Date modifiedTime;


    // 对应的四个操作权限
    @Transient
    private Boolean banDetails;
    @Transient
    private Boolean banAdd;
    @Transient
    private Boolean banEdit;
    @Transient
    private Boolean banDel;


    public Integer getKeyAuthId() {
        return keyAuthId;
    }

    public Boolean getBanDetails() {
        return banDetails;
    }

    public void setBanDetails( Boolean banDetails ) {
        this.banDetails = banDetails;
    }

    public Boolean getBanAdd() {
        return banAdd;
    }

    public void setBanAdd( Boolean banAdd ) {
        this.banAdd = banAdd;
    }

    public Boolean getBanEdit() {
        return banEdit;
    }

    public void setBanEdit( Boolean banEdit ) {
        this.banEdit = banEdit;
    }

    public Boolean getBanDel() {
        return banDel;
    }

    public void setBanDel( Boolean banDel ) {
        this.banDel = banDel;
    }

    public void setBanDetails( boolean banDetails ) {
        this.banDetails = banDetails;
    }

    public void setBanAdd( boolean banAdd ) {
        this.banAdd = banAdd;
    }

    public void setBanEdit( boolean banEdit ) {
        this.banEdit = banEdit;
    }

    public void setBanDel( boolean banDel ) {
        this.banDel = banDel;
    }

    public void setKeyAuthId( Integer keyAuthId ) {
        this.keyAuthId = keyAuthId;
    }

    public String getIdxRoleId_tbRole() {
        return idxRoleId_tbRole;
    }

    public void setIdxRoleId_tbRole( String idxRoleId_tbRole ) {
        this.idxRoleId_tbRole = idxRoleId_tbRole;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName( String resourceName ) {
        this.resourceName = resourceName;
    }

    public String getAction() {
        return action;
    }

    public void setAction( String action ) {
        this.action = action;
    }

    public String getRemarks() {
        return remarks;
    }

    //  对应的四个操作权限,赋值
    public void setRemarks( String remarks ) {
        try {
            String[] auths = remarks.split( "\\|" );
            banDetails = "1".equals( auths[0] );
            banAdd = "1".equals( auths[1] );
            banEdit = "1".equals( auths[2] );
            banDel = "1".equals( auths[3] );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
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
