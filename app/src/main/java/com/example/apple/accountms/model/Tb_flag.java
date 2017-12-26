package com.example.apple.accountms.model;

/**
 * Created by apple on 2017/12/26.
 */

public class Tb_flag { //便签信息实体类
    private int _id;
    private String flag;

    public Tb_flag(int _id, String flag) {
        this._id = _id;
        this.flag = flag;
    }

    public Tb_flag() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
