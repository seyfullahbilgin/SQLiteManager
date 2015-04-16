package com.test.sqlitemanager;

import android.util.Log;

/**
 * Created by seyfullah.bilgin on 9.4.2015.
 */
public class Column implements IColumn {

    private String mName;
    private String mType;


    public Column(String name, String type) throws  IllegalArgumentException {

        if(name.isEmpty())
            throw new  IllegalArgumentException ("name parameter must not be empty!");

        this.mName = name.toLowerCase();
        this.mType = !type.isEmpty() ? type.toLowerCase() : SQLiteDataType.TEXT;

        //Log.i("Column","name: "+mName+"  type: "+mType);
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void setName(String value) {

        mName = value;
    }

    @Override
    public String getType() {
        return mType;
    }

    @Override
    public void setType(String value) {
        mType = value;
    }

    @Override
    public boolean isPrimaryKey() {
        return false;
    }


}
