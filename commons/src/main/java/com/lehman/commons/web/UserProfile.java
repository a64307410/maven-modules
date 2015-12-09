package com.lehman.commons.web;

import com.frame.commons.vo.FrameUserMenuVO;
import net.eoutech.webmin.commons.entity.TbAgent;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class UserProfile implements Serializable {


    private static final long serialVersionUID = -2761248791769538367L;

    /**
     * 用户ID,内部唯一标识
     */
    private String userId;

    /**
     * 用户类型0为运维管理员，1为运营 管理员 ，2为运营人员，3为专家人员，4为 外部普通人员
     */
    private String type;

    /**
     * 用户名
     */
    private String userName;

    // 用户 md5 密码
    private String password;

    /**
     * 用户登录帐号
     */
    private String loginAccount;
    // 角色
    private String role;


    private String userType;
    private String index;
    private List<FrameUserMenuVO> menu;
    private String host;
    //
    private boolean isLogin;
    private Date loginDate;
    private Date lastLoginTime;
    private String lastLoginIP;


    //////////////////// 代理商信息
    private TbAgent tbAgent;

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

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate( Date loginDate ) {
        this.loginDate = loginDate;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setIsLogin( boolean isLogin ) {
        this.isLogin = isLogin;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName( String userName ) {
        this.userName = userName;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount( String loginAccount ) {
        this.loginAccount = loginAccount;
    }

    public String getRole() {
        return role;
    }

    public void setRole( String role ) {
        this.role = role;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType( String userType ) {
        this.userType = userType;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex( String index ) {
        this.index = index;
    }

    public List<FrameUserMenuVO> getMenu() {
        return menu;
    }

    public void setMenu( List<FrameUserMenuVO> menu ) {
        this.menu = menu;
    }

    public String getHost() {
        return host;
    }

    public void setHost( String host ) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public TbAgent getTbAgent() {
        return tbAgent;
    }

    public void setTbAgent( TbAgent tbAgent ) {
        this.tbAgent = tbAgent;
    }
}
