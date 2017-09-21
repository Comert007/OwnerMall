package com.yw.android.store.bean.user;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by feng on 2017/9/19.
 */

public class PrincipalBean implements Serializable{

    /**
     * name : admin
     * nickname : admin
     * mobile : 13912345678
     * email : null
     * avatar : null
     * signature : null
     * gender : null
     * genderName : null
     * birthday : null
     * idNumber : null
     * areaName : 四川省,成都市,青白江区
     * invitationCode : null
     * hasPassword : true
     * userTypes : ["Member","Employee","Administrator","Author","Cloud"]
     * registerTime : 2017-04-13 00:55:37
     */

    private String name;
    private String nickname;
    private String mobile;
    private String email;
    private String avatar;
    private String signature;
    private String gender;
    private String genderName;
    private String birthday;
    private String idNumber;
    private String areaName;
    private String invitationCode;
    private boolean hasPassword;
    private String registerTime;
    private ArrayList<String> userTypes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public boolean isHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(boolean hasPassword) {
        this.hasPassword = hasPassword;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public ArrayList<String> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(ArrayList<String> userTypes) {
        this.userTypes = userTypes;
    }

    @Override
    public String toString() {
        return "PrincipalBean{" +
                "name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", signature='" + signature + '\'' +
                ", gender='" + gender + '\'' +
                ", genderName='" + genderName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", areaName='" + areaName + '\'' +
                ", invitationCode='" + invitationCode + '\'' +
                ", hasPassword=" + hasPassword +
                ", registerTime='" + registerTime + '\'' +
                ", userTypes=" + userTypes +
                '}';
    }
}
