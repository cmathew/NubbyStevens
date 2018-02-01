package com.example.cmathew.nubbystevens.database.client;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.RawRes;

import com.example.cmathew.nubbystevens.Vehicle;
import com.example.cmathew.nubbystevens.VehicleMake;
import com.example.cmathew.nubbystevens.csv.VehicleParser;
import com.example.cmathew.nubbystevens.database.contract.VehicleMakeContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleMakeContract.VehicleMakeEntry;

public class VehicleMakeClient {
    private SQLiteDatabase database;

    public VehicleMakeClient(SQLiteDatabase database) {
        this.database = database;
    }

    public void createTable() {
        String createTableQuery = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT)",
                VehicleMakeContract.TABLE_NAME,
                VehicleMakeEntry._ID,
                VehicleMakeEntry.COLUMN_NAME);

        database.execSQL(createTableQuery);
    }

    public void createNameIndex() {
        String createIndexQuery = String.format("CREATE UNIQUE INDEX %s on %s(%s)",
                VehicleMakeContract.INDEX_NAME,
                VehicleMakeContract.TABLE_NAME,
                VehicleMakeEntry.COLUMN_NAME);

        database.execSQL(createIndexQuery);
    }

    public long insert(VehicleMake make) {
        return database.insert(VehicleMakeContract.TABLE_NAME, null, buildContentValues(make));
    }

    public Cursor find(String makeName) {
        return database.query(
                VehicleMakeContract.TABLE_NAME,
                null,
                String.format("%s = ?", VehicleMakeEntry.COLUMN_NAME),
                new String[] { makeName }, null, null, null);
    }

    private ContentValues buildContentValues(VehicleMake make) {
        ContentValues cv = new ContentValues();
        cv.put(VehicleMakeEntry.COLUMN_NAME, make.getName());
        return cv;
    }
}
