package com.yw.android.store.bean.store;

import java.io.Serializable;

/**
 * Created by feng on 2017/9/20.
 * 商城首页导航栏
 */

public class StoreNavBean implements Serializable{

    private String sid;
    private String title;
    private String subTitle;
    private String parentSid;
    private String parentName;
    private String logoSid;
    private String logoPath;
    private String type;
    private String typeName;
    private String displayable;
    private String editable;
    private String channelSid;
    private String rank;
    private String position;
    private String positionName;
    private String template;
    private String templateName;
    private String state;
    private String stateName;
    private PositionGroupsBean positionGroups;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getParentSid() {
        return parentSid;
    }

    public void setParentSid(String parentSid) {
        this.parentSid = parentSid;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getLogoSid() {
        return logoSid;
    }

    public void setLogoSid(String logoSid) {
        this.logoSid = logoSid;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDisplayable() {
        return displayable;
    }

    public void setDisplayable(String displayable) {
        this.displayable = displayable;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public String getChannelSid() {
        return channelSid;
    }

    public void setChannelSid(String channelSid) {
        this.channelSid = channelSid;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public PositionGroupsBean getPositionGroups() {
        return positionGroups;
    }

    public void setPositionGroups(PositionGroupsBean positionGroups) {
        this.positionGroups = positionGroups;
    }

    @Override
    public String toString() {
        return "StoreNavBean{" +
                "sid='" + sid + '\'' +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", parentSid='" + parentSid + '\'' +
                ", parentName='" + parentName + '\'' +
                ", logoSid='" + logoSid + '\'' +
                ", logoPath='" + logoPath + '\'' +
                ", type='" + type + '\'' +
                ", typeName='" + typeName + '\'' +
                ", displayable='" + displayable + '\'' +
                ", editable='" + editable + '\'' +
                ", channelSid='" + channelSid + '\'' +
                ", rank='" + rank + '\'' +
                ", position='" + position + '\'' +
                ", positionName='" + positionName + '\'' +
                ", template='" + template + '\'' +
                ", templateName='" + templateName + '\'' +
                ", state='" + state + '\'' +
                ", stateName='" + stateName + '\'' +
                ", positionGroups=" + positionGroups +
                '}';
    }
}
