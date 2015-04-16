package com.test.sqlitemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by seyfullah.bilgin on 9.4.2015.
 */
public class Database implements IDatabase {

    private String mName;
    private Context mContext;
    private ArrayList<Table> mTables;
    private SQLiteDatabase mSQLiteDatabase;


    public Database(String name, Context context) throws IllegalArgumentException {

        if (name == "")
            throw new IllegalArgumentException("name parameter must not be empty");

        if (context == null)
            throw new IllegalArgumentException("context parameter must not be null");

        mName = name;
        mContext = context;

        final String path = mContext.getDatabasePath(name).getPath();
        Log.i("DB", path);
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(path, null);
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void setName(String name) {
        mName = name;
    }

    public SQLiteDatabase getSQLiteDatabase() {
        return mSQLiteDatabase;
    }

    @Override
    public void addTable(Table table) throws IllegalArgumentException {

        if (table == null)
            throw new IllegalArgumentException("table parameter must not be null");

        Log.i("QUERY", table.getQuery());

        try {

            mSQLiteDatabase.execSQL(table.getQuery());
            table.setDatabase(this);

        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }

        Log.i("DatabaseManager", "table created");

    }


    @Override
    public void removeTable(String name) throws IllegalArgumentException {

        if (name.isEmpty())
            throw new IllegalArgumentException("table parameter must not be empty");

        removeTable(getTable(name));
    }


    @Override
    public void removeTable(Table table) {

        if (table == null)
            throw new IllegalArgumentException("table parameter must not be null");

        if(!hasTable(table)) {
            Log.i("SLM Error", "table not found!");
            return;
        }

        try {
            mSQLiteDatabase.execSQL("drop table " + table.getName());
            mTables.remove(table);
        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean hasTable(String name) throws IllegalArgumentException {

        if (name.isEmpty())
            throw new IllegalArgumentException("name parameter must not be empty");

        for (ITable table : mTables)
            if (table.getName().equals(name))
                return true;

        return false;
    }


    @Override
    public boolean hasTable(Table table) throws IllegalArgumentException {

        if (table == null)
            throw new IllegalArgumentException("table parameter must not be null");

        return hasTable(table.getName());
    }


    @Override
    public Table getTable(String name) {

        for (Table table : mTables)
            if (table.getName().equals(name))
                return table;

        return null;
    }

    @Override
    public ArrayList<Table> getTables() {

        return mTables;
    }

    @Override
    public void setTables(ArrayList<Table> value) {
        mTables = value;
    }


    @Override
    public void execSQL(String query) {
        try {
            mSQLiteDatabase.execSQL(query);
        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }

    }


}