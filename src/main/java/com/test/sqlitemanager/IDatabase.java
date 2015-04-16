package com.test.sqlitemanager;

import java.util.ArrayList;

/**
 * Created by seyfullah.bilgin on 9.4.2015.
 */
public interface IDatabase {

    public String getName();
    public void setName(String name);

    public void execSQL(String query);
    public void addTable(Table table);
    public void removeTable(Table table);
    public void removeTable(String name);
    public boolean hasTable(String name);
    public boolean hasTable(Table table);
    public Table getTable(String name);
    public ArrayList<Table> getTables();
    public void setTables(ArrayList<Table> value);

/*

    public String getDataAsJson ();
    public void drop();*/

}
