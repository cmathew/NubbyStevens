package com.example.cmathew.nubbystevens.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.example.cmathew.nubbystevens.database.contract.VehicleContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleContract.VehicleEntry;
import com.example.cmathew.nubbystevens.database.contract.VehicleModelContract.VehicleModelEntry;

@Entity(
        tableName = VehicleContract.TABLE_NAME,
        indices = { @Index(value = VehicleEntry.COLUMN_MODEL_ID) },
        foreignKeys = {
                @ForeignKey(
                        entity = VehicleModel.class,
                        parentColumns = VehicleModelEntry._ID,
                        childColumns = VehicleEntry.COLUMN_MODEL_ID,
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class Vehicle {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = VehicleEntry._ID)
    private long databaseId;

    @ColumnInfo(name = VehicleEntry.COLUMN_PRODUCTION_YEAR)
    private int productionYear;

    @ColumnInfo(name = VehicleEntry.COLUMN_MODEL_ID)
    public long modelId;

    public Vehicle(int productionYear, long modelId) {
        this.productionYear = productionYear;
        this.modelId = modelId;
    }

    public long getDatabaseId() {
        return databaseId;
    }

    public long getModelId() {
        return modelId;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setDatabaseId(long databaseId) {
        this.databaseId = databaseId;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }
}
