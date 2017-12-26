package com.example.apple.accountms.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.apple.accountms.model.Tb_inaccount;

/**
 * Created by apple on 2017/12/26.
 */

public class InaccountDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public InaccountDAO(Context context) {
        helper = new DBOpenHelper(context);//初始化DBOpenHelper 对象
    }

    /**
     * 添加收入信息
     * @param tb_inaccount
     */
    public void add(Tb_inaccount tb_inaccount) {
        db = helper.getWritableDatabase();
//        db.execSQL("insert into tb_inaccount(_id,money,time,type,handler,mark) values (?,?,?,?,?,?)", new Object[]{tb_inaccount.get_id(),
//                tb_inaccount.getMoney(), tb_inaccount.getTime(), tb_inaccount.getType(), tb_inaccount.getHandler(), tb_inaccount.getMark()});

        ContentValues values = new ContentValues();
        values.put("_id",tb_inaccount.get_id());
        values.put("money",tb_inaccount.getMoney());
        values.put("time",tb_inaccount.getTime());
        values.put("type",tb_inaccount.getType());
        values.put("handler",tb_inaccount.getHandler());
        values.put("mark",tb_inaccount.getMark());
        long i = db.insert("tb_inaccount",null,values);
        if (i == -1){
            Log.d("", "InaccountDAO--add: 添加失败");
        }else {
            Log.d("", "InaccountDAO--add: 添加成功");
        }
        db.close();
    }

    /**
     * 更新收入操作
     *
     * @param tb_inaccount
     */
    public void update(Tb_inaccount tb_inaccount){
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("money",tb_inaccount.getMoney());
        values.put("time",tb_inaccount.getTime());
        values.put("type",tb_inaccount.getType());
        values.put("handler",tb_inaccount.getHandler());
        values.put("mark",tb_inaccount.getMark());
        int i = db.update("tb_inaccount",values,"_id=?",new String[]{String.valueOf(tb_inaccount.get_id())});
        if (i == 0){
            Log.d("", "InaccountDAO--update: 更新失败");
        }else {
            Log.d("", "InaccountDAO--update: 更新成功");
        }
        db.close();
    }
    public Tb_inaccount find(int id){
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select _id,money,time,type,handler,mark from tb_inaccount where _id='?'",new String[]{String.valueOf(id)});
        return null;
    }
}
