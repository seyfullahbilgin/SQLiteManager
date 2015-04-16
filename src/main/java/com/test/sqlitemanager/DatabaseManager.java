package com.test.sqlitemanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by seyfullah.bilgin on 9.4.2015.
 */


public class DatabaseManager implements IDatabaseManager {

    private Context mContext;
    private ArrayList<Database> mDatabases;
    private SQLiteDatabase currentSQLiteDatabase;


    public DatabaseManager(Context context) {

        mContext = context;
        mDatabases = new ArrayList<>();
    }


    @Override
    public Database createDatabase(String name) {

        Database database = new Database(name,mContext);
        mDatabases.add(database);

        return database;
    }

    @Override
    public void dropDatabase(String name) {

       // currentSQLiteDatabase.del

    }


    @Override
    public Database getDatabase(String name) {

        Database database = new Database(name,mContext);
        currentSQLiteDatabase = database.getSQLiteDatabase();

        database.setTables(getTablesFromRealDatabase(database));
        
        return database;
    }

    @Override
    public ArrayList<Database> getDatabases() {

        return mDatabases;
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
        //sqLiteDatabase.close();

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
