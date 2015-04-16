package com.test.sqlitemanager;

/**
 * Created by seyfullah.bilgin on 10.4.2015.
 */
public interface IPrimaryColumn extends IColumn {

    public boolean isAutoIncrement();
    public void setAutoIncrement(boolean value);

}
