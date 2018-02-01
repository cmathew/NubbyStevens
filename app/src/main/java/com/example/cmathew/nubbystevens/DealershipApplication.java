package com.example.cmathew.nubbystevens;

import android.app.Application;
import android.arch.persistence.room.RoomDatabase;

import com.example.cmathew.nubbystevens.di.component.ApplicationComponent;
import com.example.cmathew.nubbystevens.di.component.DaggerApplicationComponent;
import com.facebook.stetho.Stetho;

import javax.inject.Inject;

public class DealershipApplication extends Application {
    @Inject
    RoomDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .application(this)
                .build();
        applicationComponent.inject(this);

        /*
        VehicleMake tesla = new VehicleMake("Tesla");
        VehicleMakeClient makeClient = new VehicleMakeClient(database);
        makeClient.insert(tesla);
        */
    }
}
