package com.xjtu.dbc.robserver.dynamic.home.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class DynamicHomeDto {
    private Integer userid;
    private String username;
    private String usersex;
    private String useravatar;
    private String useremail;
    private Integer roleid;
    private Integer userstatus;

    private Integer fans_num;
    private Integer dynamic_num;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userbirth;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date regtime;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getUseravatar() {
        return useravatar;
    }

    public void setUseravatar(String useravatar) {
        this.useravatar = useravatar;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(Integer userstatus) {
        this.userstatus = userstatus;
    }

    public Integer getFans_num() {
        return fans_num;
    }

    public void setFans_num(Integer fans_num) {
        this.fans_num = fans_num;
    }

    public Integer getDynamic_num() {
        return dynamic_num;
    }

    public void setDynamic_num(Integer dynamic_num) {
        this.dynamic_num = dynamic_num;
    }

    public Date getUserbirth() {
        return userbirth;
    }

    public void setUserbirth(Date userbirth) {
        this.userbirth = userbirth;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }
}
