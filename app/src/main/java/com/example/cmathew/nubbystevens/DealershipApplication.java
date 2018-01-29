package com.example.cmathew.nubbystevens;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.cmathew.nubbystevens.database.DealershipHelper;
import com.facebook.stetho.Stetho;

public class DealershipApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        DealershipHelper openHelper = new DealershipHelper(this);
        SQLiteDatabase database = openHelper.getWritableDatabase();

        /*
        VehicleMake tesla = new VehicleMake("Tesla");
        VehicleMakeClient makeClient = new VehicleMakeClient(database);
        makeClient.insert(tesla);
        */
    }
}
