package edu.buffalo.cse.cse486586.simpledynamo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "test.db";
    public static final String Dynamo_TABLE_NAME = "dynamo";
    public static final String Dynamo_COLUMN_ID = "key";
    public static final String Dynamo_COLUMN_NAME = "value";
    public static final String Dynamo_COLUMN_port = "port";
    public static final String Dynamo_COLUMN_version = "version";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table dynamo " +
                        "(key text, value text, port text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS dynamo");
        onCreate(db);
    }

    public boolean insertContact(String key, String value, String port)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("key", key);
        contentValues.put("value", value);
        contentValues.put("port", port);


        db.insert("dynamo", null, contentValues);
        return true;
    }

    public boolean getKeyData(String key)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from dynamo", null );
        res.moveToFirst();
        int flag=0;
        while(res.isAfterLast() == false){
            if(key.equalsIgnoreCase(res.getString(res.getColumnIndex(Dynamo_COLUMN_ID))))
            {
                flag=1;
                break;
            }

            res.moveToNext();
        }

        if(flag==1)
            return true;
        else
            return false;

    }
    public Cursor getData(String key){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from dynamo where key="+key+"", null );

        return res;
    }

    public String getPort(String key){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from dynamo ", null );
        res.moveToFirst();
        String thisport="";
        while(res.isAfterLast() == false){
            hp.put(res.getString(res.getColumnIndex(Dynamo_COLUMN_ID)), res.getString(res.getColumnIndex(Dynamo_COLUMN_NAME)));
            if(res.getString(res.getColumnIndex(Dynamo_COLUMN_ID)).equalsIgnoreCase(key))
            {
                thisport=res.getString(res.getColumnIndex(Dynamo_COLUMN_port));
            }
            res.moveToNext();
        }
        return thisport;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Dynamo_TABLE_NAME);
        return numRows;
    }
    public boolean updateContact (String key,String value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", value);
        db.update("dynamo", contentValues, "key = ? ", new String[] { key } );


        return true;
    }

    public Integer deleteContact (String key)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("dynamo",
                "key = ? ",
                new String[] { key });
    }
    public HashMap<String,String> getAllCotacts()
    {

        ArrayList array_list = new ArrayList();
        hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from dynamo", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(Dynamo_COLUMN_NAME)));
//            Log.d("messages of @", "mesg:" + res.getString(res.getColumnIndex(Dynamo_COLUMN_ID))+" belongs to:"+res.getString(res.getColumnIndex(Dynamo_COLUMN_port)));
            hp.put(res.getString(res.getColumnIndex(Dynamo_COLUMN_ID)),res.getString(res.getColumnIndex(Dynamo_COLUMN_NAME)));
            res.moveToNext();
        }
        return hp;
    }

    public HashMap<String,String> getDynamoByPort(String port)
    {

        ArrayList array_list = new ArrayList();
        hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from dynamo where port="+port, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(Dynamo_COLUMN_NAME)));
            hp.put(res.getString(res.getColumnIndex(Dynamo_COLUMN_ID)),res.getString(res.getColumnIndex(Dynamo_COLUMN_NAME)));
            res.moveToNext();
        }
        return hp;
    }
}
