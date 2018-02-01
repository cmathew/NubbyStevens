package com.example.cmathew.nubbystevens.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.example.cmathew.nubbystevens.database.contract.VehicleContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleContract.VehicleEntry;
import com.example.cmathew.nubbystevens.database.contract.VehicleModelContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleModelContract.VehicleModelEntry;

@Entity(foreignKeys = {@ForeignKey(entity = VehicleModel.class,
        parentColumns = VehicleModelEntry._ID,
        childColumns = VehicleEntry.COLUMN_MODEL_ID,
        onDelete = ForeignKey.CASCADE)})
public class Vehicle {
    @PrimaryKey
    @ColumnInfo(name = VehicleEntry._ID)
    private int databaseId;

    @ColumnInfo(name = VehicleEntry.COLUMN_PRODUCTION_YEAR)
    private int productionYear;

    @ColumnInfo(name = VehicleEntry.COLUMN_MODEL_ID)
    public int modelId;

    public int getDatabaseId() {
        return databaseId;
    }

    public int getModelId() {
        return modelId;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setDatabaseId(int databaseId) {
        this.databaseId = databaseId;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }
}
