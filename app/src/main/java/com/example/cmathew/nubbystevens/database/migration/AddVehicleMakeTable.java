package com.example.cmathew.nubbystevens.database.migration;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import com.example.cmathew.nubbystevens.database.contract.VehicleMakeContract;

public final class AddVehicleMakeTable extends Migration {
    public AddVehicleMakeTable(){
        super(1, 2);
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
        String createTableQuery = String.format("CREATE TABLE %s (" +
                        "%s INTEGER, " +
                        "%s TEXT NOT NULL," +
                        "PRIMARY KEY(%s ASC))",
                VehicleMakeContract.TABLE_NAME,
                VehicleMakeContract.VehicleMakeEntry._ID,
                VehicleMakeContract.VehicleMakeEntry.COLUMN_NAME);

        database.execSQL(createTableQuery);
    }
}