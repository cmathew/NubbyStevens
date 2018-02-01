package com.example.cmathew.nubbystevens.database.contract;

import android.provider.BaseColumns;

public final class VehicleContract {
    public static final String TABLE_NAME = "vehicle";
    public static final String INDEX_PRODUCTION_YEAR = String.format("index_%s_on_%s", TABLE_NAME, VehicleContract.VehicleEntry.COLUMN_PRODUCTION_YEAR);

    private VehicleContract() {}

    public static class VehicleEntry implements BaseColumns {
        public static final String COLUMN_MODEL_ID = "model_id";
        public static final String COLUMN_PRODUCTION_YEAR = "production_year";
    }
}
