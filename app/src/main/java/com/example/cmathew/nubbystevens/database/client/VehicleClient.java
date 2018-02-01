package com.example.cmathew.nubbystevens.database.client;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.RawRes;

import com.example.cmathew.nubbystevens.Vehicle;
import com.example.cmathew.nubbystevens.VehicleMake;
import com.example.cmathew.nubbystevens.csv.VehicleParser;
import com.example.cmathew.nubbystevens.database.contract.VehicleContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleContract.VehicleEntry;
import com.example.cmathew.nubbystevens.database.contract.VehicleMakeContract;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class VehicleClient {
    private SQLiteDatabase database;

    public VehicleClient(SQLiteDatabase database) {
        this.database = database;
    }

    public long seedData(Context context, @RawRes int carCsv) throws IOException {
        InputStream csvStream = context.getResources().openRawResource(carCsv);
        VehicleParser parser = new VehicleParser();
        List<Vehicle> vehicles = parser.parseCsvStream(csvStream);
        for (Vehicle vehicle : vehicles) {
            insert(vehicle)
        }
        csvStream.close();
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
