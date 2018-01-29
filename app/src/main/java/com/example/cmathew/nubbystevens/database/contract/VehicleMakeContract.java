package com.example.cmathew.nubbystevens.database.contract;

import android.provider.BaseColumns;

public final class VehicleMakeContract {
    public static final String TABLE_NAME = "vehicle_make";
    public static final String INDEX_NAME_MAKE_NAME = String.format("index_%s_on_%s", TABLE_NAME, VehicleMakeEntry.COLUMN_NAME_MAKE_NAME);

    private VehicleMakeContract() {}

    public static class VehicleMakeEntry implements BaseColumns {
        public static final String COLUMN_MAKE_ID = "make_id";
        public static final String COLUMN_NAME_MAKE_NAME = "name";
    }
}





