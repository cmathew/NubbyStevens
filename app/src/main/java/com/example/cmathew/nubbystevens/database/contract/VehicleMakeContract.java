package com.example.cmathew.nubbystevens.database.contract;

import android.provider.BaseColumns;

public final class VehicleMakeContract {
    public static final String TABLE_NAME = "vehicle_make";

    private VehicleMakeContract() {}

    public static class VehicleMakeEntry implements BaseColumns {
        public static final String COLUMN_NAME = "name";
    }
}





