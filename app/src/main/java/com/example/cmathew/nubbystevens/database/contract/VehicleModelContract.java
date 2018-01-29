package com.example.cmathew.nubbystevens.database.contract;

import android.provider.BaseColumns;

public final class VehicleModelContract {
    public static final String TABLE_NAME = "vehicle_model";
    public static final String INDEX_NAME_MODEL_NAME = String.format("index_%s_on_%s", TABLE_NAME, VehicleModelEntry.COLUMN_NAME_MODEL_NAME);

    private VehicleModelContract() {}
    public static class VehicleModelEntry implements BaseColumns {
        public static final String COLUMN_NAME_MODEL_NAME = "name";
    }
}
