package com.test.sqlitemanager;

import java.util.ArrayList;

/**
 * Created by seyfullah.bilgin on 9.4.2015.
 */
public interface IDatabaseManager {

    public Database createDatabase(String name);
    public void dropDatabase(String name);


    public Database getDatabase(String name);
    public ArrayList<Database> getDatabases();

 /*   public void createDatabase(Database database);


    public void dropDatabase(Database database);

    public boolean isDatabaseExists(String name);
    public boolean isDatabaseExists(Database database);

   */

}
