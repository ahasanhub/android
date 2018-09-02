package com.contextidea.dagger2sample;

import java.util.Date;

public class MyDateExampleImp implements MyDateExample {
    private  long mDate;
    public MyDateExampleImp(){
        mDate=new Date().getTime();
    }
    @Override
    public long GetDate() {
        return mDate;
    }
}
