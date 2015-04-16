package com.test.sqlitemanager;

import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by seyfullah.bilgin on 8.4.2015.
 */
public class Table implements ITable {


    private String mName;
    private String mQuery;
    private ArrayList<IColumn> mColumns;


    private Database mDatabase;


    public Table(String name) throws  IllegalArgumentException {

        if(name.isEmpty())
            throw new  IllegalArgumentException ("name parameter must not be empty");

        mName = name;
        mColumns = new ArrayList<>();
    }


    public Table(String name, ArrayList<IColumn> columns) throws  IllegalArgumentException {

        if(name.isEmpty())
            throw new  IllegalArgumentException ("name parameter must not be empty");

        if(columns == null)
            throw new  IllegalArgumentException ("columns parameter must not be null");

        mName = name;
        mColumns = columns;
    }


    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void setName(String value) {

        try {
            mDatabase.getSQLiteDatabase().execSQL("alter table "+mName+" rename to "+value);
            mName = value;
        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }
    }


    public Database getDatabase() {
        return mDatabase;
    }

    public void setDatabase(Database value) {
        this.mDatabase = value;
    }


    @Override
    public String getQuery() {

        if(mColumns.size() == 0) {
            Log.i("ERROR", "Table has not any described field" );
            return null;
        }

        mQuery = "create table '" + mName+"' (";

        int size = mColumns.size();

        for (int i = 0; i < size; i++) {

            IColumn column = mColumns.get(i);

            mQuery += "'"+column.getName()+"' "+column.getType()+ (i < size-1 ? ", " : "");
        }

        mQuery += ")";

        return mQuery;
    }

    @Override
    public void addColumn(IColumn column) {
        mColumns.add(column);
    }

    @Override
    public void removeColumn(IColumn column) {
        mColumns.remove(column);
    }

    @Override
    public boolean hasColumn(String name)throws IllegalArgumentException {

        if (name.isEmpty())
            throw new IllegalArgumentException("name parameter must not be empty");

        for(IColumn column : mColumns)
            if(column.getName().equals(name))
                return true;

        return false;
    }

    @Override
    public boolean hasColumn(IColumn column) {

        if (column == null)
            throw new IllegalArgumentException("column parameter must not be null");

        return hasColumn(column.getName());
    }

    @Override
    public long addRecord(ContentValues values) {
        return mDatabase.getSQLiteDatabase().insert(mName,null,values);
    }

    @Override
    public int removeRecord(String whereClause, String[] whereArgs) {
        return mDatabase.getSQLiteDatabase().delete(mName,whereClause ,whereArgs);
    }

    @Override
    public int removeRecordById(String IdColumn,String id) {
        return mDatabase.getSQLiteDatabase().delete(mName, IdColumn + " = ?",new String[]{id});
    }


    @Override
    public void removeAllRecords() {
        mDatabase.execSQL("delete from "+mName);
    }


    @Override
    public int updateRecord(ContentValues values, String whereClause, String[] whereArgs) {
        return mDatabase.getSQLiteDatabase().update(mName,values,whereClause, whereArgs);
    }

    @Override
    public int updateRecordById(String IdColumn,String id, ContentValues values) {
        return mDatabase.getSQLiteDatabase().update(mName,values, IdColumn + " = ?",new String[]{id});
    }


    @Override
    public IColumn getColumn(String name) {

        for(IColumn column : mColumns)
            if(column.getName().equals(name))
                return column;

        return null;
    }

    @Override
    public ArrayList<IColumn> getColumns() {
        return mColumns;
    }

    @Override
    public void setColumns(ArrayList<IColumn> value) {
        mColumns = value;
    }


}
