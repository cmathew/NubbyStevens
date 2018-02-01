package com.example.cmathew.nubbystevens.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

import com.example.cmathew.nubbystevens.database.contract.VehicleMakeContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleMakeContract.VehicleMakeEntry;
import com.example.cmathew.nubbystevens.database.contract.VehicleModelContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleModelContract.VehicleModelEntry;

@Entity(
    foreignKeys = {
        @ForeignKey(
                entity = VehicleMake.class,
                parentColumns = VehicleMakeEntry._ID,
                childColumns = VehicleModelEntry.COLUMN_MAKE_ID,
                onDelete = ForeignKey.CASCADE
        )
    },
    indices = {
        @Index(
                value = {
                    VehicleModelContract.VehicleModelEntry.COLUMN_NAME,
                    VehicleModelEntry.COLUMN_MAKE_ID
                },
                unique = true
        )
    }
)
public class VehicleModel {
    private long databaseId;
    private VehicleMake make;
    private String name;

    public VehicleModel(String name, VehicleMake make) {
        this.name = name;
        this.make = make;
    }

    public VehicleMake getMake() {
        return make;
    }

    public String getName() {
        return name;
    }

    public long getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(long id) {
        this.databaseId = id;
    }
}
