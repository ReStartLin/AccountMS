package com.example.apple.accountms.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by apple on 2017/12/26.
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;//数据库版本号
    private static final String DBNAME = "account.db";//数据库名字
    public DBOpenHelper(Context context) {
        super(context,DBNAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL("create table tb_outaccount(_id integer primary key,money decimal,time varchar(10),"+
                                                    "type varchar(10),address varchar(100),mark varchar(200))");//创建支出信息表
        db.execSQL("create table tb_inaccount(_id integer primary key,money decimal,time varchar(10),"+
                                                    "type varchar(10),handler varchar(100),mark varchar(200))");//创建收入信息表
        db.execSQL("create table tb_pwd(password varchar(20))");//创建密码表
        db.execSQL("create table tb_flag(_id integer primary key,flag varchar(200))");//创建便签信息表
    }
    //更新版本操作
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
