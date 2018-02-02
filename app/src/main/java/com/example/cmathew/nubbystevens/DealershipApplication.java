package com.example.cmathew.nubbystevens;

import android.app.Application;

import com.example.cmathew.nubbystevens.csv.VehicleParser;
import com.example.cmathew.nubbystevens.database.DealershipDatabase;
import com.example.cmathew.nubbystevens.di.component.ApplicationComponent;
import com.example.cmathew.nubbystevens.di.component.DaggerApplicationComponent;
import com.example.cmathew.nubbystevens.entity.Vehicle;
import com.facebook.stetho.Stetho;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

public class DealershipApplication extends Application {
    @Inject
    DealershipDatabase database;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        this.applicationComponent = DaggerApplicationComponent.builder()
                .application(this)
                .build();
        applicationComponent.inject(this);

        try {
            seedData();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Migration Failed!");
        }
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void seedData() throws IOException {
        InputStream csvStream = getResources().openRawResource(R.raw.initial_inventory);
        VehicleParser parser = new VehicleParser(database);
        List<Vehicle> vehicles = parser.parseCsvStream(csvStream);
        for (Vehicle vehicle : vehicles) {
            database.vehicleDao().insertVehicle(vehicle);
        }
        csvStream.close();
    }
}
