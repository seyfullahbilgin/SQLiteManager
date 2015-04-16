package com.test.sqlitemanager;

/**
 * Created by seyfullah.bilgin on 10.4.2015.
 */
public class PrimaryColumn implements IPrimaryColumn {

    private String mName;
    private String mType;
    private boolean mIsAutoIncrement;


    public PrimaryColumn(String name, String type, boolean isAutoIncrement) throws  IllegalArgumentException {

        if(name == "" || type == "")
            throw  new IllegalArgumentException("name and type paramaters must not be empty");

        mName = name;
        mType = type;
        mIsAutoIncrement = isAutoIncrement;
    }


    @Override
    public boolean isAutoIncrement() {
        return mIsAutoIncrement;
    }

    @Override
    public void setAutoIncrement(boolean value) {
        mIsAutoIncrement = value;
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
        return true;
    }

}
