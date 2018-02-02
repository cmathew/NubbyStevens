package com.example.cmathew.nubbystevens.database.access;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.cmathew.nubbystevens.entity.VehicleMake;

import io.reactivex.Maybe;

@Dao
public interface VehicleMakeDao {
    @Query("SELECT * FROM vehicle_make WHERE vehicle_make.name = :name LIMIT 1")
    VehicleMake findByName(String name);

    @Insert
    long insertMake(VehicleMake make);
}
