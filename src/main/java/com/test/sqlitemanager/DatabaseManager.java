package com.test.sqlitemanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by seyfullah.bilgin on 9.4.2015.
 */


public class DatabaseManager implements IDatabaseManager {

    private Context mContext;
    private SQLiteDatabase currentSQLiteDatabase;


    public DatabaseManager(Context context) {

        if (context == null)
            throw new IllegalArgumentException("context parameter must not be null!");

        mContext = context;
    }


    @Override
    public void createDatabase(String name) {

        if(hasDatabase(name)) {
            Log.i("SLM msg", "'" + name + "' database is already exists!");
            return;
        }

        Database database = new Database(name,mContext);
    }

    @Override
    public boolean hasDatabase(String name) {
        return mContext.getDatabasePath(name).exists();
    }


    @Override
    public Database getDatabase(String name) {

        if(!hasDatabase(name)) {
            Log.i("SLM msg", "'" + name + "' database not found!");
            return null;
        }

        Database database = new Database(name,mContext);
        currentSQLiteDatabase = database.getSQLiteDatabase();

        database.setTables(getTablesFromRealDatabase(database));

        return database;
    }


    public ArrayList<Table> getTablesFromRealDatabase (Database database)
    {
        String selectQuery =  "select name from sqlite_master where type = 'table';";

        ArrayList<Table> tables = new ArrayList<>();

        Cursor cursor = currentSQLiteDatabase.rawQuery(selectQuery,null);

        if(cursor.moveToFirst())
        {
            do
            {
                String tableName = cursor.getString(0);

                Table table = new Table(tableName,getColumnsOfRealTable(tableName));
                table.setDatabase(database);

                tables.add(table);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return tables;
    }


    private  ArrayList<IColumn> getColumnsOfRealTable (String table) {

        ArrayList <IColumn> columns  =  new ArrayList<>();

        String selectQuery = "pragma table_info("+table+");";

        Cursor cursor = currentSQLiteDatabase.rawQuery(selectQuery,null);

        if(cursor.moveToFirst())
        {
            do columns.add(new Column(cursor.getString(1),cursor.getString(2)));
            while (cursor.moveToNext());
        }

        cursor.close();

        return columns;
    }


}
