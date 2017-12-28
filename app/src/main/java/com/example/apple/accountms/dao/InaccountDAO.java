package com.example.apple.accountms.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.apple.accountms.model.Tb_inaccount;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 查找收入信息
     * @param id
     * @return
     */
    public Tb_inaccount find(int id){
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select _id,money,time,type,handler,mark from tb_inaccount where _id='?'",new String[]{String.valueOf(id)});
        if (cursor.moveToNext()){
            db.close();
            Log.d("", "InaccountDAO--find: 查询成功");
            return new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("handler")),
                    cursor.getString(cursor.getColumnIndex("mark"))
            );
        }
        db.close();
        return null;
    }

    /**
     * 删除收入信息
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
            db.execSQL("delete from tb_inaccount where _id in("+sb+")",ids);
            db.close();
            Log.d("", "InaccountDAO--detele: 删除成功");
        }
    }

    /**
     * 获取收入信息
     * @param start 启始位置
     * @param count
     * @return
     */
    public List<Tb_inaccount> getScrollData(int start,int count){
        List<Tb_inaccount> tb_inaccount = new ArrayList<Tb_inaccount>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_inaccount limit "+String.valueOf(start)+","+String.valueOf(count),null);
//                                                new String[]{String.valueOf(start),String.valueOf(count)});
        while (cursor.moveToNext()){
            tb_inaccount.add(new Tb_inaccount(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("handler")),
                    cursor.getString(cursor.getColumnIndex("mark"))
            ));
        }
        cursor.close();
        db.close();
        Log.d("", "InaccountDAO--getScrollData: 获取成功 从"+start+"起"+count+"条信息");
        return tb_inaccount;
    }

    /**
     * 获取总记录数
     * @return
     */
    public long getCount(){
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from tb_inaccount",null);
        if (cursor.moveToNext()){
            Log.d("", "InaccountDAO--getCount: 获取"+cursor.getLong(0)+"条成功");
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
        Cursor cursor = db.rawQuery("select max(_id) from tb_inaccount",null);
        while (cursor.moveToLast()){
            Log.d("", "InaccountDAO--getMaxId: 获取成功");
            return cursor.getInt(0);
        }
        return 0;
    }
}
