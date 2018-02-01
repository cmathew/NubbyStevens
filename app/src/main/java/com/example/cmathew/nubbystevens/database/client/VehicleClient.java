package com.example.cmathew.nubbystevens.database.client;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.RawRes;

import com.example.cmathew.nubbystevens.entity.Vehicle;
import com.example.cmathew.nubbystevens.csv.VehicleParser;
import com.example.cmathew.nubbystevens.database.contract.VehicleContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleContract.VehicleEntry;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class VehicleClient {
    private SupportSQLiteDatabase database;

    public VehicleClient(SupportSQLiteDatabase database) {
        this.database = database;
    }

    public long seedData(Context context, @RawRes int carCsv) throws IOException {
        long recordsInserted = 0;

        InputStream csvStream = context.getResources().openRawResource(carCsv);
        VehicleParser parser = new VehicleParser(database);
        List<Vehicle> vehicles = parser.parseCsvStream(csvStream);
        for (Vehicle vehicle : vehicles) {
            recordsInserted += insert(vehicle);
        }
        csvStream.close();

        return recordsInserted;
    }

    public long insert(Vehicle vehicle) {
        return database.insert(VehicleContract.TABLE_NAME, null, buildContentValues(vehicle));
    }

    private ContentValues buildContentValues(Vehicle vehicle) {
        ContentValues cv = new ContentValues();
        cv.put(VehicleEntry.COLUMN_MODEL_ID, vehicle.getModel().getDatabaseId());
        cv.put(VehicleEntry.COLUMN_PRODUCTION_YEAR, vehicle.getProductionYear());
        return cv;
    }
}
