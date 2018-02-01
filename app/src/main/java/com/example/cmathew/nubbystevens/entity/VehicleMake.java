package com.example.cmathew.nubbystevens.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.example.cmathew.nubbystevens.database.contract.VehicleMakeContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleMakeContract.VehicleMakeEntry;

@Entity(
        tableName = VehicleMakeContract.TABLE_NAME,
        indices = {
                @Index(
                        value = {VehicleMakeEntry.COLUMN_NAME},
                        unique = true
                )
        }
)
public class VehicleMake {
    @PrimaryKey
    @ColumnInfo(name = VehicleMakeEntry._ID)
    private long databaseId;

    @ColumnInfo(name = VehicleMakeEntry.COLUMN_NAME)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(long id) {
        this.databaseId = id;
    }
}
