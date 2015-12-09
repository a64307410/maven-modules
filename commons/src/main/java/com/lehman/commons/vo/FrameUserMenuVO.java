package com.lehman.commons.vo;

/**
 * 用户菜单
 * 2015-11-19 11:46:07
 *
 * @author leijianjun
 */
public class FrameUserMenuVO {
    private String uri;
    private String menu;//一级菜单id
    private String iconClass; // 菜单图标

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }
}
