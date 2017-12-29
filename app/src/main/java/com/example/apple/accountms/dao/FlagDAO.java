package com.example.apple.accountms.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.apple.accountms.model.Tb_flag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2017/12/27.
 */

public class FlagDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    public FlagDAO(Context context){
        helper = new DBOpenHelper(context);
    }
    /**
     * 添加便签
     * @param tb_flag
     */
    public void add(Tb_flag tb_flag) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id",tb_flag.get_id());
        values.put("flag",tb_flag.getFlag());
        long i = db.insert("tb_flag",null,values);
        if (i == -1){
            Log.d("", "FlagDAO--add: 添加失败");
        }else {
            Log.d("", "FlagDAO--add: 添加成功");
        }
        db.close();
    }

    /**
     * 更新便签
     *
     * @param tb_flag
     */
    public void update(Tb_flag tb_flag){
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("flag",tb_flag.getFlag());
        int i = db.update("tb_flag",values,"_id=?",new String[]{String.valueOf(tb_flag.get_id())});
        if (i == 0){
            Log.d("", "FlagDAO--update: 更新失败");
        }else {
            Log.d("", "FlagDAO--update: 更新成功");
        }
        db.close();
    }
    /**
     * 查找便签
     * @param id
     * @return
     */
    public Tb_flag find(int id){
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select _id,flag from tb_flag where _id=?",new String[]{String.valueOf(id)});
        if (cursor.moveToNext()){
            db.close();
            Log.d("", "FlagDAO--find: 查询成功");
            return new Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("flag"))
            );
        }
        db.close();
        return null;
    }
    /**
     * 获取便签信息
     * @param start 启始位置
     * @param count
     * @return
     */
    public List<Tb_flag> getScrollData(int start, int count){
        List<Tb_flag> tb_flag = new ArrayList<Tb_flag>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_flag limit ?,?",
                new String[]{String.valueOf(start),String.valueOf(count)});
        while (cursor.moveToNext()){
            tb_flag.add(new Tb_flag(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("flag"))
            ));
        }
        cursor.close();
        db.close();
        Log.d("", "FlagDAO--getScrollData: 获取成功 从"+start+"起"+count+"条信息");
        return tb_flag;
    }

    /**
     * 获取总记录数
     * @return
     */
    public long getCount(){
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from tb_flag",null);
        if (cursor.moveToNext()){
            Log.d("", "FlagDAO--getCount: 获取"+cursor.getLong(0)+"条成功");
            db.close();
            return cursor.getLong(0);
        }
        db.close();
        return 0;
    }
    /**
     * 获取收入最大编号
     * @return
     */
    public int getMaxId(){
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select max(_id) from tb_flag",null);
        while (cursor.moveToLast()){
            Log.d("", "FlagDAO--getMaxId: 获取成功");
            return cursor.getInt(0);
        }
        return 0;
    }

    public void detele(int i) {
        db = helper.getWritableDatabase();
        db.delete("tb_flag","_id = ?",new String[]{String.valueOf(i)});

    }
}
