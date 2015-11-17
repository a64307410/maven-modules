package com.user.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2015/8/18.
 */
@Entity
@Table( name = "tbBlackList", schema = "", catalog = "ViFi" )
public class BlackList {
    @Column( name = "ipaddr" )
    private String ipaddr;
    @Column( name = "expiryDate" )
    private String expiryDate;
    @Column( name = "crtTm" )
    private Timestamp crtTm;
    @Column( name = "crtBy" )
    private String crtBy;


    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr( String ipaddr ) {
        this.ipaddr = ipaddr;
    }


    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate( String expiryDate ) {
        this.expiryDate = expiryDate;
    }


    public Timestamp getCrtTm() {
        return crtTm;
    }

    public void setCrtTm( Timestamp crtTm ) {
        this.crtTm = crtTm;
    }


    public String getCrtBy() {
        return crtBy;
    }

    public void setCrtBy( String crtBy ) {
        this.crtBy = crtBy;
    }


}
