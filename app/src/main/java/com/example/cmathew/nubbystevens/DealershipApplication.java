package com.example.cmathew.nubbystevens;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.cmathew.nubbystevens.database.DealershipHelper;
import com.example.cmathew.nubbystevens.database.VehicleMake;
import com.example.cmathew.nubbystevens.database.client.VehicleMakeClient;
import com.facebook.stetho.Stetho;

public class DealershipApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        VehicleMake tesla = new VehicleMake("Tesla");
        DealershipHelper openHelper = new DealershipHelper(this);
        SQLiteDatabase database = openHelper.getWritableDatabase();
        if (database.getVersion() == 0) {

        }

        VehicleMakeClient makeClient = new VehicleMakeClient(database);
        makeClient.insert(tesla);
    }
}
