package com.example.cmathew.nubbystevens.database;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.provider.ContactsContract;

import com.example.cmathew.nubbystevens.DealershipApplication;
import com.example.cmathew.nubbystevens.R;
import com.example.cmathew.nubbystevens.csv.VehicleParser;
import com.example.cmathew.nubbystevens.entity.Vehicle;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

public class InventorySeeder {
    @Inject
    Application application;

    @Inject
    DealershipDatabase database;

    public InventorySeeder(DealershipApplication application) {
        application.getApplicationComponent().inject(this);
    }

    public void seed() throws IOException {
        InputStream csvStream = application.getResources().openRawResource(R.raw.initial_inventory);
        VehicleParser parser = new VehicleParser(database);
        List<Vehicle> vehicles = parser.parseCsvStream(csvStream);
        for (Vehicle vehicle : vehicles) {
            database.vehicleDao().insertVehicle(vehicle);
        }
        csvStream.close();
    }
}
