package com.example.cmathew.nubbystevens.database.client;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.cmathew.nubbystevens.entity.VehicleModel;
import com.example.cmathew.nubbystevens.database.contract.VehicleModelContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleModelContract.VehicleModelEntry;
import com.example.cmathew.nubbystevens.database.contract.VehicleMakeContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleMakeContract.VehicleMakeEntry;

public class VehicleModelClient {
    private SupportSQLiteDatabase database;

    public VehicleModelClient(SupportSQLiteDatabase database) {
        this.database = database;
    }

    public void createTable() {
        String createTableQuery = String.format("CREATE TABLE %s (" +
                        "%s INTEGER, " + // ID
                        "%s TEXT NOT NULL" + // NAME
                        "PRIMARY KEY(%s ASC), " +
                        "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE)",
                VehicleModelContract.TABLE_NAME,
                VehicleModelEntry._ID,
                VehicleModelEntry.COLUMN_NAME,

                // Primary key
                VehicleModelEntry._ID,

                // Vehicle make foreign key
                VehicleModelEntry.COLUMN_MAKE_ID,
                VehicleMakeContract.TABLE_NAME,
                VehicleMakeEntry._ID);

        database.execSQL(createTableQuery);
    }

    public void createNameIndex() {
        String createIndexQuery = String.format("CREATE UNIQUE INDEX %s on %s(%s)",
                VehicleModelContract.INDEX_NAME,
                VehicleModelContract.TABLE_NAME,
                VehicleModelEntry.COLUMN_NAME);

        database.execSQL(createIndexQuery);
    }

    public long insert(VehicleModel model) {
        return database.insert(VehicleModelContract.TABLE_NAME, null, buildContentValues(model));
    }

    public Cursor find(String modelName) {
        return database.query(
                VehicleModelContract.TABLE_NAME,
                null,
                String.format("%s = ?", VehicleModelEntry.COLUMN_NAME),
                new String[] { modelName }, null, null, null);
    }

    private ContentValues buildContentValues(VehicleModel model) {
        ContentValues cv = new ContentValues();
        cv.put(VehicleModelEntry.COLUMN_NAME, model.getName());
        cv.put(VehicleModelEntry.COLUMN_MAKE_ID, model.getMake().getDatabaseId());
        return cv;
    }
}
