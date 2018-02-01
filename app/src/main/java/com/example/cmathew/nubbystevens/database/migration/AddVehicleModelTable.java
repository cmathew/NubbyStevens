package com.example.cmathew.nubbystevens.database.migration;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import com.example.cmathew.nubbystevens.database.contract.VehicleMakeContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleModelContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleModelContract.VehicleModelEntry;

public final class AddVehicleModelTable extends Migration {
    public AddVehicleModelTable() {
        super(2, 3);
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
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
                VehicleMakeContract.VehicleMakeEntry._ID);

        database.execSQL(createTableQuery);
    }
}
