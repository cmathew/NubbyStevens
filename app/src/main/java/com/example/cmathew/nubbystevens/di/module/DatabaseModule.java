package com.example.cmathew.nubbystevens.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.cmathew.nubbystevens.R;
import com.example.cmathew.nubbystevens.database.DealershipDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    @Provides
    @Singleton
    DealershipDatabase provideDatabase(Application application) {
        String dbName = application.getResources().getString(R.string.database_name);
        return Room.databaseBuilder(application, DealershipDatabase.class, dbName)
                .allowMainThreadQueries()
                .build();
    }
}