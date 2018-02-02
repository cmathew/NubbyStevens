package com.example.cmathew.nubbystevens.database.access;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import com.example.cmathew.nubbystevens.entity.Vehicle;

import io.reactivex.Maybe;

@Dao
public interface VehicleDao {
    @Query("SELECT * FROM vehicle " +
            "INNER JOIN vehicle_model as model ON model._id = vehicle.model_id " +
            "INNER JOIN vehicle_make as make ON make._id = model.make_id " +
            "WHERE vehicle.production_year = :productionYear LIMIT 1")
    Vehicle findByYear(int productionYear);

    @Query("SELECT vehicle._id, vehicle.production_year, model.name as vehicle_model_name, make.name as vehicle_make_name FROM vehicle " +
            "INNER JOIN vehicle_model as model ON model._id = vehicle.model_id " +
            "INNER JOIN vehicle_make as make ON make._id = model.make_id")
    Cursor getCursorAll();

    @Insert
    long insertVehicle(Vehicle vehicle);
}

