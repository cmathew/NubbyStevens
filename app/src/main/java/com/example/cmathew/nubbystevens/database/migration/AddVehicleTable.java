package com.example.cmathew.nubbystevens.database.migration;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import com.example.cmathew.nubbystevens.database.contract.VehicleMakeContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleModelContract;

public final class AddVehicleTable extends Migration {
    public AddVehicleTable() {
        super(3, 4);
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
        String createTableQuery = String.format("CREATE TABLE %s (" +
                        "%s INTEGER, " + // ID
                        "%s TEXT NOT NULL" + // NAME
                        "PRIMARY KEY(%s ASC), " +
                        "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE)",
                VehicleModelContract.TABLE_NAME,
                VehicleModelContract.VehicleModelEntry._ID,
                VehicleModelContract.VehicleModelEntry.COLUMN_NAME,

                // Primary key
                VehicleModelContract.VehicleModelEntry._ID,

                // Vehicle make foreign key
                VehicleModelContract.VehicleModelEntry.COLUMN_MAKE_ID,
                VehicleMakeContract.TABLE_NAME,
                VehicleMakeContract.VehicleMakeEntry._ID);

        database.execSQL(createTableQuery);
    }
}
