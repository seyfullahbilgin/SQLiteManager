package com.test.sqlitemanager;

import java.util.ArrayList;

/**
 * Created by seyfullah.bilgin on 9.4.2015.
 */
public interface IDatabaseManager {

    public void createDatabase(String name);
    public Database getDatabase(String name);
    public boolean hasDatabase(String name);


}
