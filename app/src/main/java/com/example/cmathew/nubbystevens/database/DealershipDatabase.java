package com.example.cmathew.nubbystevens.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.cmathew.nubbystevens.database.access.VehicleDao;
import com.example.cmathew.nubbystevens.database.access.VehicleMakeDao;
import com.example.cmathew.nubbystevens.database.access.VehicleModelDao;
import com.example.cmathew.nubbystevens.entity.Vehicle;
import com.example.cmathew.nubbystevens.entity.VehicleMake;
import com.example.cmathew.nubbystevens.entity.VehicleModel;

@Database(
        entities = {
                Vehicle.class,
                VehicleMake.class,
                VehicleModel.class
        },
        version = DealershipDatabase.VERSION
)
public abstract class DealershipDatabase extends RoomDatabase {
    // DB Version
    public static final int VERSION = 4;

    public abstract VehicleDao vehicleDao();
    public abstract VehicleMakeDao makeDao();
    public abstract VehicleModelDao modelDao();
}

