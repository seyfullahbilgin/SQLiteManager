package com.test.sqlitemanager;

/**
 * Created by seyfullah.bilgin on 9.4.2015.
 */
public interface IColumn {

    public String getName();
    public void setName(String value);

    public String getType();
    public void setType(String value);

    public boolean isPrimaryKey();

}
