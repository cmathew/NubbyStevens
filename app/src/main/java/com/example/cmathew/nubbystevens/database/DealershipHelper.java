package com.example.cmathew.nubbystevens.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cmathew.nubbystevens.R;
import com.example.cmathew.nubbystevens.database.client.VehicleClient;
import com.example.cmathew.nubbystevens.database.client.VehicleMakeClient;

import java.io.IOException;

public class DealershipHelper extends SQLiteOpenHelper {
    // DB Version
    private static final int DATABASE_VERSION = 2;

    private Context context;

    public DealershipHelper(Context context) {
        super(context, context.getResources().getString(R.string.database_name), null, DATABASE_VERSION);

        this.context = context;
    }

    // Create original state, then migrate to target
    @Override
    public void onCreate(SQLiteDatabase db) {
        onUpgrade(db, 0, DATABASE_VERSION);
    }

    private boolean migrationNeeded(int oldVersion, int newVersion, int migrationIndex) {
        return oldVersion < migrationIndex && newVersion >= migrationIndex;
    }

    // Apply incremental schema changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (migrationNeeded(oldVersion, newVersion, 1)) {
            VehicleMakeClient makeClient = new VehicleMakeClient(db);
            makeClient.createTable();
            makeClient.createNameIndex();
        }

        if (migrationNeeded(oldVersion, newVersion, 2)) {
            VehicleClient vehicleClient = new VehicleClient (db);
            try {
                vehicleClient.seedData(context, R.raw.initial_inventory);
            } catch (IOException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Migration Failed!");
            }
        }
    }

}
