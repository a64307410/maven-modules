package com.lehman.commons.entity;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2015/8/15.
 */
public class TbCfrmGroup {
    private String keyGoupId;
    private String idxParentsGrpId;
    private String dftRoleId;
    private String remarks;
    private String createdBy;
    private Timestamp createdTime;
    private String modifiedBy;
    private Timestamp modifiedTime;

    public String getKeyGoupId() {
        return keyGoupId;
    }

    public void setKeyGoupId( String keyGoupId ) {
        this.keyGoupId = keyGoupId;
    }

    public String getIdxParentsGrpId() {
        return idxParentsGrpId;
    }

    public void setIdxParentsGrpId( String idxParentsGrpId ) {
        this.idxParentsGrpId = idxParentsGrpId;
    }

    public String getDftRoleId() {
        return dftRoleId;
    }

    public void setDftRoleId( String dftRoleId ) {
        this.dftRoleId = dftRoleId;
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

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime( Timestamp createdTime ) {
        this.createdTime = createdTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy( String modifiedBy ) {
        this.modifiedBy = modifiedBy;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime( Timestamp modifiedTime ) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        TbCfrmGroup that = ( TbCfrmGroup ) o;

        if ( keyGoupId != null ? !keyGoupId.equals( that.keyGoupId ) : that.keyGoupId != null ) return false;
        if ( idxParentsGrpId != null ? !idxParentsGrpId.equals( that.idxParentsGrpId ) : that.idxParentsGrpId != null )
            return false;
        if ( dftRoleId != null ? !dftRoleId.equals( that.dftRoleId ) : that.dftRoleId != null ) return false;
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
        int result = keyGoupId != null ? keyGoupId.hashCode() : 0;
        result = 31 * result + ( idxParentsGrpId != null ? idxParentsGrpId.hashCode() : 0 );
        result = 31 * result + ( dftRoleId != null ? dftRoleId.hashCode() : 0 );
        result = 31 * result + ( remarks != null ? remarks.hashCode() : 0 );
        result = 31 * result + ( createdBy != null ? createdBy.hashCode() : 0 );
        result = 31 * result + ( createdTime != null ? createdTime.hashCode() : 0 );
        result = 31 * result + ( modifiedBy != null ? modifiedBy.hashCode() : 0 );
        result = 31 * result + ( modifiedTime != null ? modifiedTime.hashCode() : 0 );
        return result;
    }
}
