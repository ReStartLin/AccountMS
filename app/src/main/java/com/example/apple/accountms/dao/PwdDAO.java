package com.example.apple.accountms.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.apple.accountms.model.Tb_pwd;

/**
 * Created by apple on 2017/12/27.
 */

public class PwdDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public PwdDAO(Context context) {
        helper = new DBOpenHelper(context);//初始化DBOpenHelper 对象
    }

    /**
     * 添加支出信息
     * @param tb_pwd
     */
    public void add(Tb_pwd tb_pwd) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password",tb_pwd.getPassword());
        long i = db.insert("tb_pwd",null,values);
        if (i == -1){
            Log.d("", "PwdDAO--add: 添加失败");
        }else {
            Log.d("", "PwdDAO--add: 添加成功");
        }
        db.close();
    }

    /**
     * 获取总记录数
     * @return
     */
    public long getCount(){
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(password) from tb_pwd",null);
        if (cursor.moveToNext()){
            Log.d("", "PwdDAO--getCount: 获取"+cursor.getLong(0)+"条成功");
            db.close();
            return cursor.getLong(0);
        }
        db.close();
        return 0;
    }
    /**
     * 查找便签
     * @return
     */
    public Tb_pwd find(){
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select password from tb_pwd",null);
        if (cursor.moveToNext()){
            db.close();
            Log.d("", "Tb_pwd--find: 查询成功");
            return new Tb_pwd(
                    cursor.getString(cursor.getColumnIndex("password"))
            );
        }
        db.close();
        return new Tb_pwd();
    }

    public void update(Tb_pwd tb_pwd) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password",tb_pwd.getPassword());
        db.update("tb_pwd",values,"",null);
    }
}
