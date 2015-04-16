package com.test.sqlitemanager;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by seyfullah.bilgin on 15.4.2015.
 */
public class DatabaseUtils {

    private static SQLiteDatabase mSqLiteDatabase;

    public static void setSqLiteDatabase(SQLiteDatabase sqLiteDatabase) {
        mSqLiteDatabase = sqLiteDatabase;
    }


    public static long insert(String table, String nullColumnHack, ContentValues values) {
        return mSqLiteDatabase.insert(table,nullColumnHack,values);
    }

    public static long insertOrThrow(String table, String nullColumnHack, ContentValues values)
            throws SQLException {
        return mSqLiteDatabase.insertWithOnConflict(table, nullColumnHack, values, 0);
    }

    public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        return mSqLiteDatabase.updateWithOnConflict(table, values, whereClause, whereArgs, 0);
    }

    public int delete(String table, String whereClause, String[] whereArgs) {
        return  mSqLiteDatabase.delete(table, whereClause, whereArgs);
    }

}
