package com.dinosys.sportbook.di

import android.content.Context
import com.dinosys.sportbook.application.SportbookApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: SportbookApp){

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app;
    }

}