package com.contextidea.dagger2sample;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MyModule {
    @Provides
    @Singleton
    static MyDateExample provideMyDateExample(){
        return new MyDateExampleImp();
    }
}
