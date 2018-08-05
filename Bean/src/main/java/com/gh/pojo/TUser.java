package com.gh.pojo;


import com.gh.pojo.base.BaseEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TUser extends BaseEntity {
    private Integer id;
    @Size(min = 1,max = 20,message ="{user.lenth.sizeerrer}")
    @NotNull(message = "{user.loginacct.isnull}")
    private String loginacct;
    @Size(min = 1,max = 20,message ="{user.lenth.sizeerrer}")
    @NotNull(message = "{user.passwore.isnull}")
    private String userpswd;
    private String username;
    private String email;
    private String createtime;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginacct() {
        return loginacct;
    }

    public void setLoginacct(String loginacct) {
        this.loginacct = loginacct == null ? null : loginacct.trim();
    }

    public String getUserpswd() {
        return userpswd;
    }

    public void setUserpswd(String userpswd) {
        this.userpswd = userpswd == null ? null : userpswd.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }
}