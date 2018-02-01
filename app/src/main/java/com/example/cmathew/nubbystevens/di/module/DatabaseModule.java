package com.example.cmathew.nubbystevens.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

import com.example.cmathew.nubbystevens.R;
import com.example.cmathew.nubbystevens.database.DealershipDatabase;
import com.example.cmathew.nubbystevens.database.migration.AddVehicleMakeTable;
import com.example.cmathew.nubbystevens.database.migration.AddVehicleModelTable;
import com.example.cmathew.nubbystevens.database.migration.AddVehicleTable;
import com.example.cmathew.nubbystevens.database.migration.SeedVehicleData;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    @Provides
    @Singleton
    RoomDatabase provideDatabase(Application application) {
        String dbName = application.getResources().getString(R.string.database_name);
        return Room.databaseBuilder(application, DealershipDatabase.class, dbName)
                .addMigrations(new AddVehicleMakeTable(), new AddVehicleModelTable(), new AddVehicleTable())
                .build();
    }
}