package com.example.cmathew.nubbystevens.database.access;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.cmathew.nubbystevens.entity.VehicleModel;

@Dao
public interface VehicleModelDao {
    @Query("SELECT * FROM vehicle_model " +
            "WHERE vehicle_model.name = :name AND vehicle_model.make_id = :makeId LIMIT 1")
    VehicleModel findBy(String name, long makeId);

    @Insert
    long insertModel(VehicleModel model);
}
