package com.example.videoplay.utils.dbUtils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Huangwy
 * @TIME 2016/3/30 20:56
 */
public class DateOpenHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "db_collection.db";
    private final static int VERSION = 1;
    public SQLiteDatabase dbConn;

    public DateOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        dbConn = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists table_attention(_id integer primary key autoincrement , integer videoId ,textView_top,textView_1_1,textView_1_2,textView_2_1,textView_2_2,textView_3_1,textView_3_2,imageView,tabindex)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("drop table if exists tb_mywords");
            onCreate(db);
        }
    }

    /**
     * @作用：执行带占位符的select语句，查询数据，返回Cursor
     * @param sql
     * @param selectionArgs
     * @return Cursor
     */
    public Cursor selectCursor(String sql, String[] selectionArgs) {
        return dbConn.rawQuery(sql, selectionArgs);
    }

    /**
     * @作用：执行带占位符的select语句，返回结果集的个数
     * @param sql
     * @param selectionArgs
     * @return int
     */
    public int selectCount(String sql, String[] selectionArgs) {
        Cursor cursor = dbConn.rawQuery(sql, selectionArgs);
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();
            return count;
        } else {
            return 0;
        }
    }

    /**
     * @作用：执行带占位符的select语句，返回多条数据，放进List集合中。
     * @param sql
     * @param selectionArgs
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> selectList(String sql,
                                                String[] selectionArgs) {
        Cursor cursor = dbConn.rawQuery(sql, selectionArgs);
        return cursorToList(cursor);
    }

    /**
     * @作用：将Cursor对象转成List集合
     * @param cursor
     *            cursor
     * @return List<Map<String, Object>>集合
     */
    public List<Map<String, Object>> cursorToList(Cursor cursor) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        String[] arrColumnName = cursor.getColumnNames();
        while (cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < arrColumnName.length; i++) {
                Object cols_value = cursor.getString(i);
                // switch (cursor.getType(i)) {
                // case 1:
                // cols_value = cursor.getInt(i);
                // break;
                // case 2:
                // cols_value = cursor.getFloat(i);
                // break;
                // case 3:
                // cols_value = cursor.getString(i);
                // break;
                // case 4:
                // cols_value = cursor.getBlob(i);
                // break;
                // default:
                // break;
                // }
                map.put(arrColumnName[i], cols_value);
            }
            list.add(map);
        }
        if (cursor != null) {
            cursor.close();
        }
        return list;
    }

    /**
     * @作用：执行带占位符的update、insert、delete语句，更新数据库，返回true或false
     * @param sql
     * @param bindArgs
     * @return boolean
     */
    public boolean execData(String sql, Object[] bindArgs) {
        try {
            dbConn.execSQL(sql, bindArgs);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void destroy() {
        if (dbConn != null) {
            dbConn.close();
        }
    }
}
