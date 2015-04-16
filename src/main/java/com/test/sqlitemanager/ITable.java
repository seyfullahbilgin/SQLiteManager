package com.test.sqlitemanager;

import android.content.ContentValues;

import java.util.ArrayList;

/**
 * Created by seyfullah.bilgin on 8.4.2015.
 */
public interface ITable {


    public String getName();
    public void setName(String value);

    public String getQuery();

    public void addColumn(IColumn column);
    public void removeColumn(IColumn column);

    public IColumn getColumn(String name);
    public ArrayList<IColumn> getColumns();
    public void setColumns(ArrayList<IColumn> value);

    public boolean hasColumn(String name);
    public boolean hasColumn(IColumn column);

    public long addRecord(ContentValues values);

    public void removeAllRecords();
    public int removeRecord(String whereClause, String[] whereArgs);
    public int removeRecordById(String IdColumn, String id);

    public int updateRecord(ContentValues values, String whereClause, String[] whereArgs);
    public int updateRecordById(String IdColumn, String id, ContentValues values);


/*
    public HashMap<String, String> getRecord(int id);
    public ArrayList<HashMap<String, String>> getRecords();

    public String getDataAsJson ();
*/

}
