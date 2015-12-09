package com.lehman.commons.vo;

import com.mysql.jdbc.StringUtils;

import java.util.Map;

/**
 * @author WangBin
 * @Description:
 * @date 2013-04-28
 */
public class FrameUserAuthVO {
    private String accountId;
    private String password;
    private String role;
    private String dftpage;
    private String permission;
    private String indexPage;

    public FrameUserAuthVO() {
        indexPage = "/index";
    }

    public FrameUserAuthVO( Map< String, Object > fields ) {
        accountId = String.valueOf( fields.get( "keyUserId" ) );
        password = String.valueOf( fields.get( "password" ) );
        role = String.valueOf( fields.get( "keyRoleId" ) );
        dftpage = String.valueOf( fields.get( "indexPageResourceId" ) );

        indexPage = "/index";
        if ( fields.containsKey( "indexPageResourceId" )
                && !StringUtils.isNullOrEmpty( String.valueOf( fields.get( "indexPageResourceId" ) ) ) ) {
            indexPage = String.valueOf( fields.get( "indexPageResourceId" ) );
        }
    }

    public String getName() {
        return accountId;
    }

    public void setName( String name ) {
        this.accountId = name;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId( String accountId ) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole( String role ) {
        this.role = role;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission( String permission ) {
        this.permission = permission;
    }

    public String getDftpage() {
        return dftpage;
    }

    public void setDftpage( String dftpage ) {
        this.dftpage = dftpage;
    }

    public String getIndexPage() {
        return indexPage;
    }

    public void setIndexPage( String indexPage ) {
        this.indexPage = indexPage;
    }
}
