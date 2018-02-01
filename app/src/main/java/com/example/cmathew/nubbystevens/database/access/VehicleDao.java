package com.example.cmathew.nubbystevens.database.access;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.cmathew.nubbystevens.database.contract.VehicleContract;
import com.example.cmathew.nubbystevens.entity.Vehicle;
import com.example.cmathew.nubbystevens.entity.Vehicle;

@Dao
public interface VehicleDao {
    static String fuck = String.format("SELECT * FROM fuck WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1");
    @Query(fuck)
    Vehicle findByYear(int year);

    @Insert
    void insertAll(Vehicle... cars);
}

