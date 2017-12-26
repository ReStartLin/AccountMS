package com.example.apple.accountms.model;

/**
 * Created by apple on 2017/12/26.
 */

public class Tb_pwd { //密码信息实体类
    private String password;

    public Tb_pwd(String password) {
        this.password = password;
    }

    public Tb_pwd() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
