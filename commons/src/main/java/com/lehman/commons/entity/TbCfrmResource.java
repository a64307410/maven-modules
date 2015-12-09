package com.lehman.commons.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2015/8/15.
 */
public class TbCfrmResource {
    private String keyResourceId;
    private String name;
    private String menu;
    private String remarks;
    private String createdBy;
    @JSONField( format = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createdTime;
    private String modifiedBy;
    @JSONField( format = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date modifiedTime;




    public String getKeyResourceId() {
        return keyResourceId;
    }

    public void setKeyResourceId( String keyResourceId ) {
        this.keyResourceId = keyResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu( String menu ) {
        this.menu = menu;
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

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        TbCfrmResource that = ( TbCfrmResource ) o;

        if ( keyResourceId != null ? !keyResourceId.equals( that.keyResourceId ) : that.keyResourceId != null )
            return false;
        if ( name != null ? !name.equals( that.name ) : that.name != null ) return false;
        if ( menu != null ? !menu.equals( that.menu ) : that.menu != null ) return false;
        if ( remarks != null ? !remarks.equals( that.remarks ) : that.remarks != null ) return false;
        if ( createdBy != null ? !createdBy.equals( that.createdBy ) : that.createdBy != null ) return false;
        if ( createdTime != null ? !createdTime.equals( that.createdTime ) : that.createdTime != null ) return false;
        if ( modifiedBy != null ? !modifiedBy.equals( that.modifiedBy ) : that.modifiedBy != null ) return false;
        if ( modifiedTime != null ? !modifiedTime.equals( that.modifiedTime ) : that.modifiedTime != null )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = keyResourceId != null ? keyResourceId.hashCode() : 0;
        result = 31 * result + ( name != null ? name.hashCode() : 0 );
        result = 31 * result + ( menu != null ? menu.hashCode() : 0 );
        result = 31 * result + ( remarks != null ? remarks.hashCode() : 0 );
        result = 31 * result + ( createdBy != null ? createdBy.hashCode() : 0 );
        result = 31 * result + ( createdTime != null ? createdTime.hashCode() : 0 );
        result = 31 * result + ( modifiedBy != null ? modifiedBy.hashCode() : 0 );
        result = 31 * result + ( modifiedTime != null ? modifiedTime.hashCode() : 0 );
        return result;
    }
}
