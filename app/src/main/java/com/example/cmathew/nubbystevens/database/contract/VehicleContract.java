package com.example.cmathew.nubbystevens.database.contract;

import android.provider.BaseColumns;

public final class VehicleContract {
    public static final String TABLE_NAME = "vehicle";

    private VehicleContract() {}

    public static class VehicleEntry implements BaseColumns {
        public static final String COLUMN_MODEL_ID = "model_id";
        public static final String COLUMN_PRODUCTION_YEAR = "production_year";
    }
}
