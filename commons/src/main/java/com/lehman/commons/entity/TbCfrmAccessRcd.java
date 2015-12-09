package com.lehman.commons.entity;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2015/8/15.
 */
public class TbCfrmAccessRcd {
    private Integer keyLogId;
    private Byte idxType;
    private Timestamp idxDateTime;
    private String msel;
    private String idxUserId;
    private String idxResourceId;
    private String reqRemoteIp;
    private String reqOs;
    private String reqBrowser;
    private String reqAction;
    private String reqUrl;
    private String reqOperation;
    private Byte result;
    private String reason;
    private Integer respCode;
    private String response;
    private String remarks;

    public Integer getKeyLogId() {
        return keyLogId;
    }

    public void setKeyLogId( Integer keyLogId ) {
        this.keyLogId = keyLogId;
    }

    public Byte getIdxType() {
        return idxType;
    }

    public void setIdxType( Byte idxType ) {
        this.idxType = idxType;
    }

    public Timestamp getIdxDateTime() {
        return idxDateTime;
    }

    public void setIdxDateTime( Timestamp idxDateTime ) {
        this.idxDateTime = idxDateTime;
    }

    public String getMsel() {
        return msel;
    }

    public void setMsel( String msel ) {
        this.msel = msel;
    }

    public String getIdxUserId() {
        return idxUserId;
    }

    public void setIdxUserId( String idxUserId ) {
        this.idxUserId = idxUserId;
    }

    public String getIdxResourceId() {
        return idxResourceId;
    }

    public void setIdxResourceId( String idxResourceId ) {
        this.idxResourceId = idxResourceId;
    }

    public String getReqRemoteIp() {
        return reqRemoteIp;
    }

    public void setReqRemoteIp( String reqRemoteIp ) {
        this.reqRemoteIp = reqRemoteIp;
    }

    public String getReqOs() {
        return reqOs;
    }

    public void setReqOs( String reqOs ) {
        this.reqOs = reqOs;
    }

    public String getReqBrowser() {
        return reqBrowser;
    }

    public void setReqBrowser( String reqBrowser ) {
        this.reqBrowser = reqBrowser;
    }

    public String getReqAction() {
        return reqAction;
    }

    public void setReqAction( String reqAction ) {
        this.reqAction = reqAction;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl( String reqUrl ) {
        this.reqUrl = reqUrl;
    }

    public String getReqOperation() {
        return reqOperation;
    }

    public void setReqOperation( String reqOperation ) {
        this.reqOperation = reqOperation;
    }

    public Byte getResult() {
        return result;
    }

    public void setResult( Byte result ) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason( String reason ) {
        this.reason = reason;
    }

    public Integer getRespCode() {
        return respCode;
    }

    public void setRespCode( Integer respCode ) {
        this.respCode = respCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse( String response ) {
        this.response = response;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks( String remarks ) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        TbCfrmAccessRcd that = ( TbCfrmAccessRcd ) o;

        if ( keyLogId != null ? !keyLogId.equals( that.keyLogId ) : that.keyLogId != null ) return false;
        if ( idxType != null ? !idxType.equals( that.idxType ) : that.idxType != null ) return false;
        if ( idxDateTime != null ? !idxDateTime.equals( that.idxDateTime ) : that.idxDateTime != null ) return false;
        if ( msel != null ? !msel.equals( that.msel ) : that.msel != null ) return false;
        if ( idxUserId != null ? !idxUserId.equals( that.idxUserId ) : that.idxUserId != null ) return false;
        if ( idxResourceId != null ? !idxResourceId.equals( that.idxResourceId ) : that.idxResourceId != null )
            return false;
        if ( reqRemoteIp != null ? !reqRemoteIp.equals( that.reqRemoteIp ) : that.reqRemoteIp != null ) return false;
        if ( reqOs != null ? !reqOs.equals( that.reqOs ) : that.reqOs != null ) return false;
        if ( reqBrowser != null ? !reqBrowser.equals( that.reqBrowser ) : that.reqBrowser != null ) return false;
        if ( reqAction != null ? !reqAction.equals( that.reqAction ) : that.reqAction != null ) return false;
        if ( reqUrl != null ? !reqUrl.equals( that.reqUrl ) : that.reqUrl != null ) return false;
        if ( reqOperation != null ? !reqOperation.equals( that.reqOperation ) : that.reqOperation != null )
            return false;
        if ( result != null ? !result.equals( that.result ) : that.result != null ) return false;
        if ( reason != null ? !reason.equals( that.reason ) : that.reason != null ) return false;
        if ( respCode != null ? !respCode.equals( that.respCode ) : that.respCode != null ) return false;
        if ( response != null ? !response.equals( that.response ) : that.response != null ) return false;
        if ( remarks != null ? !remarks.equals( that.remarks ) : that.remarks != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = keyLogId != null ? keyLogId.hashCode() : 0;
        result1 = 31 * result1 + ( idxType != null ? idxType.hashCode() : 0 );
        result1 = 31 * result1 + ( idxDateTime != null ? idxDateTime.hashCode() : 0 );
        result1 = 31 * result1 + ( msel != null ? msel.hashCode() : 0 );
        result1 = 31 * result1 + ( idxUserId != null ? idxUserId.hashCode() : 0 );
        result1 = 31 * result1 + ( idxResourceId != null ? idxResourceId.hashCode() : 0 );
        result1 = 31 * result1 + ( reqRemoteIp != null ? reqRemoteIp.hashCode() : 0 );
        result1 = 31 * result1 + ( reqOs != null ? reqOs.hashCode() : 0 );
        result1 = 31 * result1 + ( reqBrowser != null ? reqBrowser.hashCode() : 0 );
        result1 = 31 * result1 + ( reqAction != null ? reqAction.hashCode() : 0 );
        result1 = 31 * result1 + ( reqUrl != null ? reqUrl.hashCode() : 0 );
        result1 = 31 * result1 + ( reqOperation != null ? reqOperation.hashCode() : 0 );
        result1 = 31 * result1 + ( result != null ? result.hashCode() : 0 );
        result1 = 31 * result1 + ( reason != null ? reason.hashCode() : 0 );
        result1 = 31 * result1 + ( respCode != null ? respCode.hashCode() : 0 );
        result1 = 31 * result1 + ( response != null ? response.hashCode() : 0 );
        result1 = 31 * result1 + ( remarks != null ? remarks.hashCode() : 0 );
        return result1;
    }
}
