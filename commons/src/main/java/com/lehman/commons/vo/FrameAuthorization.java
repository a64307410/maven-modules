package com.lehman.commons.vo;

public class FrameAuthorization {
    private String role;
    private String resourceName;
    private String url;
    private String action = "*";

    public FrameAuthorization( String role, String resourceName, String url ) {
        this.role = role;
        this.resourceName = resourceName;
        this.url = url;
    }

    public String getRole() {
        return role;
    }

    public void setRole( String role ) {
        this.role = role;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName( String resourceName ) {
        this.resourceName = resourceName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    public String getAction() {
        return action;
    }

    public void setAction( String action ) {
        this.action = action;
    }
}
