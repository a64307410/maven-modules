package com.lehman.commons.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.frame.commons.entity.TbCfrmResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class FrameResourceViewVO {

    private String keyResourceId;
    private boolean parent;
    private String menu;
    private String topMenu;
    private String topMenuRs;
    private String nameRs;
    private String name;


    private String remarks;
    private String createdBy;
    @JSONField( format = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createdTime;
    private String modifiedBy;
    @JSONField( format = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date modifiedTime;

    public FrameResourceViewVO() {
    }

    public FrameResourceViewVO( TbCfrmResource tbRes ) {
        keyResourceId = tbRes.getKeyResourceId();
        menu = tbRes.getMenu();
        name = tbRes.getName();
        topMenu = name.split( "_" )[0];

        remarks = tbRes.getRemarks();
        createdBy = tbRes.getCreatedBy();
        createdTime = tbRes.getCreatedTime();
        modifiedBy = tbRes.getModifiedBy();
        modifiedTime = tbRes.getModifiedTime();


    }

    public String getKeyResourceId() {
        return keyResourceId;
    }

    public void setKeyResourceId( String keyResourceId ) {
        this.keyResourceId = keyResourceId;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu( String menu ) {
        this.menu = menu;
    }

    public boolean isParent() {
        return parent;
    }

    public void setParent( boolean parent ) {
        this.parent = parent;
    }

    public String getTopMenu() {
        return topMenu;
    }

    public void setTopMenu( String topMenu ) {
        this.topMenu = topMenu;
    }

    public String getTopMenuRs() {
        return topMenuRs;
    }

    public void setTopMenuRs( String topMenuRs ) {
        this.topMenuRs = topMenuRs;
    }

    public String getNameRs() {
        return nameRs;
    }

    public void setNameRs( String nameRs ) {
        this.nameRs = nameRs;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
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
