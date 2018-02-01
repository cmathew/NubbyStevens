package com.example.cmathew.nubbystevens.database.client;

import android.content.ContentValues;

import com.example.cmathew.nubbystevens.entity.VehicleMake;
import com.example.cmathew.nubbystevens.database.contract.VehicleMakeContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleMakeContract.VehicleMakeEntry;
import com.squareup.sqlbrite3.BriteDatabase;
import com.squareup.sqlbrite3.QueryObservable;

public class VehicleMakeClient {
    private BriteDatabase database;

    public VehicleMakeClient(BriteDatabase database) {
        this.database = database;
    }

    public void createTable() {
        String createTableQuery = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT)",
                VehicleMakeContract.TABLE_NAME,
                VehicleMakeEntry._ID,
                VehicleMakeEntry.COLUMN_NAME);

        database.execute(createTableQuery);
    }

    public void createNameIndex() {
        String createIndexQuery = String.format("CREATE UNIQUE INDEX %s on %s(%s)",
                VehicleMakeContract.INDEX_NAME,
                VehicleMakeContract.TABLE_NAME,
                VehicleMakeEntry.COLUMN_NAME);

        database.execute(createIndexQuery);
    }

    public long insert(VehicleMake make) {
        return database.insert(VehicleMakeContract.TABLE_NAME, null, buildContentValues(make));
    }

    public QueryObservable find(String makeName) {
        return database.createQuery(
                VehicleMakeContract.TABLE_NAME,
                String.format("%s = ?", VehicleMakeEntry.COLUMN_NAME),
                makeName).mapToOne();
    }

    private ContentValues buildContentValues(VehicleMake make) {
        ContentValues cv = new ContentValues();
        cv.put(VehicleMakeEntry.COLUMN_NAME, make.getName());
        return cv;
    }
}
