package com.example.maewdamn.chakritfinal2016;

/**
 * Created by Chakrit Tokuhara on 17/5/2559.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tasks";
    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;
    private static final String INSERT = "insert into " + TABLE_NAME + " (id, name) values (?, ?)";
    private List<Integer> idList = new ArrayList<>();
    private List<String> nameList = new ArrayList<>();

    public DBHelper(Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void insert(int id, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        db.insert(TABLE_NAME, null, contentValues);
    }

    public int update(long id, String name) {
        int numRows;
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        numRows = db.update(TABLE_NAME, contentValues, " id = " + id, null);
        Log.v("DBHelper", "num rows update is " + numRows);
        return numRows;
    }

    public void deleteAll() {
        this.db.delete(TABLE_NAME, null, null);
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        private static String TAG_NAME = "OpenHelper";

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.v(TAG_NAME, "Creating table");
            db.execSQL("CREATE TABLE " + TABLE_NAME + "(id integer primary key, name text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.v(TAG_NAME, "Upgrading table");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        }
    }

    public void selectById(int id) {
        String whereClause = "id = ?";
        String[] whereArgs = new String[] {
                new Integer(id).toString()
        };
        Cursor cursor = this.db.query(TABLE_NAME, new String[] {"id", "name"}, whereClause, whereArgs, null, null, null);
        idList = new ArrayList<Integer>();
        nameList = new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                idList.add(cursor.getInt(0));
                nameList.add(cursor.getString(1));
                Log.v("DBHelper", new Integer(cursor.getInt(0)).toString());
                Log.v("DBHelper", cursor.getString(1));
            } while (cursor.moveToNext()); }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    public void selectAll() {
        long count  = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        idList = new ArrayList<Integer>();
        nameList = new ArrayList<String>();
        for (int i=1; i<= count; i++) {
            String whereClause = "id = ?";
            String[] whereArgs = new String[] {
                    new Integer(i).toString()
            };
            Cursor cursor = this.db.query(TABLE_NAME, new String[] {"id", "name"}, whereClause, whereArgs, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    idList.add(cursor.getInt(0));
                    nameList.add(cursor.getString(1));
                    Log.v("DBHelper", new Integer(cursor.getInt(0)).toString());
                    Log.v("DBHelper", cursor.getString(1));
                } while (cursor.moveToNext()); }

            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
}
