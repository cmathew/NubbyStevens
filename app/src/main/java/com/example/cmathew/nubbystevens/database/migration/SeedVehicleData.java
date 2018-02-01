package com.example.cmathew.nubbystevens.database.migration;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RawRes;

import com.example.cmathew.nubbystevens.R;
import com.example.cmathew.nubbystevens.csv.VehicleParser;
import com.example.cmathew.nubbystevens.entity.Vehicle;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class SeedVehicleData extends Migration {
    private Context context;

    public SeedVehicleData(Context context) {
        super(4, 5);

        this.context = context;
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
        try {
            seedData(database);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Migration Failed!");
        }
    }

    private long seedData(SupportSQLiteDatabase database) throws IOException {
        long recordsInserted = 0;

        InputStream csvStream = context.getResources().openRawResource(R.raw.initial_inventory);
        VehicleParser parser = new VehicleParser(database);
        List<Vehicle> vehicles = parser.parseCsvStream(csvStream);
        for (Vehicle vehicle : vehicles) {
            //recordsInserted += database.insert(vehicle);
        }
        csvStream.close();

        return recordsInserted;
    }
}
