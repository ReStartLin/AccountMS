package com.example.apple.accountms.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.apple.accountms.model.Tb_outaccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2017/12/27.
 */

public class OutaccountDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public OutaccountDAO(Context context) {
        helper = new DBOpenHelper(context);//初始化DBOpenHelper 对象
    }
    
    /**
     * 添加支出信息
     * @param tb_outaccount
     */
    public void add(Tb_outaccount tb_outaccount) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id",tb_outaccount.get_id());
        values.put("money",tb_outaccount.getMoney());
        values.put("time",tb_outaccount.getTime());
        values.put("type",tb_outaccount.getType());
        values.put("address",tb_outaccount.getAddress());
        values.put("mark",tb_outaccount.getMark());
        long i = db.insert("tb_outaccount",null,values);
        if (i == -1){
            Log.d("", "OutaccountDAO--add: 添加失败");
        }else {
            Log.d("", "OutaccountDAO--add: 添加成功");
        }
        db.close();
    }

    /**
     * 更新支出操作
     *
     * @param tb_outaccount
     */
    public void update(Tb_outaccount tb_outaccount){
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("money",tb_outaccount.getMoney());
        values.put("time",tb_outaccount.getTime());
        values.put("type",tb_outaccount.getType());
        values.put("address",tb_outaccount.getAddress());
        values.put("mark",tb_outaccount.getMark());
        int i = db.update("tb_outaccount",values,"_id=?",new String[]{String.valueOf(tb_outaccount.get_id())});
        if (i == 0){
            Log.d("", "OutaccountDAO--update: 更新失败");
        }else {
            Log.d("", "OutaccountDAO--update: 更新成功");
        }
        db.close();
    }

    /**
     * 查找支出信息
     * @param id
     * @return
     */
    public Tb_outaccount find(int id){
        db = helper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select _id,money,time,type,address,mark from tb_outaccount where _id='?'",new String[]{String.valueOf(id)});
        Cursor cursor = db.rawQuery("select _id,money,time,type,address,mark from tb_outaccount where _id="+String.valueOf(id),null);
        if (cursor.moveToNext()){
            db.close();
            Log.d("", "OutaccountDAO--find: 查询成功");
            return new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("mark"))
            );
        }
        db.close();
        return null;
    }

    /**
     * 删除支出信息
     * @param ids
     */
    public void detele(Integer... ids){
        if (ids.length > 0){
            StringBuffer sb = new StringBuffer();
            for (int i = 0;i<ids.length;i++){
                sb.append('?').append(',');
            }
            sb.deleteCharAt(sb.length()-1);
            db = helper.getWritableDatabase();
            db.execSQL("delete from tb_outaccount where _id in("+sb+")",ids);
            db.close();
            Log.d("", "OutaccountDAO--delete: 删除成功");
        }
    }

    /**
     * 获取支出信息
     * @param start 启始位置
     * @param count
     * @return
     */
    public List<Tb_outaccount> getScrollData(int start, int count){
        List<Tb_outaccount> tb_outaccount = new ArrayList<Tb_outaccount>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_outaccount limit ?,?",
                new String[]{String.valueOf(start),String.valueOf(count)});
//        Cursor cursor = db.rawQuery("select * from tb_outaccount limit "+String.valueOf(start)+","+String.valueOf(count),null);
        while (cursor.moveToNext()){
            tb_outaccount.add(new Tb_outaccount(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("mark"))
            ));
        }
        cursor.close();
        db.close();
        Log.d("", "OutaccountDAO--getScrollData: 获取成功 从"+start+"起"+count+"条信息");
        return tb_outaccount;
    }

    /**
     * 获取总记录数
     * @return
     */
    public long getCount(){
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from tb_outaccount",null);
        if (cursor.moveToNext()){
            Log.d("", "OutaccountDAO--getCount: 获取"+cursor.getLong(0)+"条成功");
            db.close();
            return cursor.getLong(0);
        }
        db.close();
        return 0;
    }

    /**
     * 获取支出最大编号
     * @return
     */
    public int getMaxId(){
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select max(_id) from tb_outaccount",null);
        while (cursor.moveToLast()){
            Log.d("", "OutaccountDAO--getMaxId: 获取成功");
            return cursor.getInt(0);
        }
        return 0;
    }
}
