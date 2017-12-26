package com.example.apple.accountms.model;

/**
 * Created by apple on 2017/12/26.
 */

public class Tb_outaccount {//支出信息实体类
    private int _id;
    private String money;
    private String time;
    private String type;
    private String address;
    private String mark;

    public Tb_outaccount() {
    }

    public Tb_outaccount(int _id, String money, String time, String type, String address, String mark) {
        this._id = _id;
        this.money = money;
        this.time = time;
        this.type = type;
        this.address = address;
        this.mark = mark;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
