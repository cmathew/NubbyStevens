package com.example.cmathew.nubbystevens.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.example.cmathew.nubbystevens.database.contract.VehicleMakeContract.VehicleMakeEntry;
import com.example.cmathew.nubbystevens.database.contract.VehicleModelContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleModelContract.VehicleModelEntry;

@Entity(
    tableName = VehicleModelContract.TABLE_NAME,
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
                VehicleModelEntry.COLUMN_NAME,
                VehicleModelEntry.COLUMN_MAKE_ID
            },
            unique = true
        ), @Index(value = VehicleModelEntry.COLUMN_MAKE_ID),
    }
)
public class VehicleModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = VehicleModelEntry._ID)
    private long databaseId;

    @ColumnInfo(name = VehicleModelEntry.COLUMN_MAKE_ID)
    private long makeId;

    @ColumnInfo(name = VehicleModelEntry.COLUMN_NAME)
    private String name;

    public VehicleModel(String name, long makeId) {
        this.name = name;
        this.makeId = makeId;
    }

    public long getMakeId() {
        return makeId;
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

    public void setMakeId(long makeId) {
        this.makeId = makeId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
