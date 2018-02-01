package com.example.cmathew.nubbystevens.database.contract;

import android.provider.BaseColumns;

public final class VehicleModelContract {
    public static final String TABLE_NAME = "vehicle_model";
    //public static final String INDEX_NAME = String.format("index_%s_on_%s", TABLE_NAME, VehicleModelEntry.COLUMN_NAME);

    private VehicleModelContract() {}
    public static class VehicleModelEntry implements BaseColumns {
        public static final String COLUMN_MAKE_ID = "make_id";
        public static final String COLUMN_NAME = "name";
    }
}
