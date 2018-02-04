package com.example.cmathew.nubbystevens.database.access;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.example.cmathew.nubbystevens.entity.Vehicle;
import com.example.cmathew.nubbystevens.entity.VehicleMinimal;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface VehicleDao {
    @Query("SELECT vehicle._id as id, vehicle.production_year as productionYear, model.name as modelName, make.name as makeName FROM vehicle " +
            "INNER JOIN vehicle_model as model ON model._id = vehicle.model_id " +
            "INNER JOIN vehicle_make as make ON make._id = model.make_id " +
            "WHERE vehicle._id = :id LIMIT 1")
    VehicleMinimal findById(long id);

    @Query("SELECT vehicle._id as id, vehicle.production_year as productionYear, model.name as modelName, make.name as makeName FROM vehicle " +
            "INNER JOIN vehicle_model as model ON model._id = vehicle.model_id " +
            "INNER JOIN vehicle_make as make ON make._id = model.make_id")
    Flowable<List<VehicleMinimal>> getAll();

    @Insert
    long insertVehicle(Vehicle vehicle);

    @Update
    int updateVehicle(Vehicle vehicle);

    @Query("DELETE FROM vehicle WHERE vehicle._id = :vehicleId")
    void deleteVehicle(long vehicleId);
}

