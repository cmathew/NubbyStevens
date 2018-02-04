package com.example.cmathew.nubbystevens.database;

import android.arch.persistence.room.Insert;

import com.example.cmathew.nubbystevens.entity.Vehicle;
import com.example.cmathew.nubbystevens.entity.VehicleMake;
import com.example.cmathew.nubbystevens.entity.VehicleModel;

public class VehicleCreator {
    private DealershipDatabase database;

    public VehicleCreator(DealershipDatabase database) {
        this.database = database;
    }

    public VehicleMake findOrCreateMake(String makeName) {
        VehicleMake make = database.makeDao().findByName(makeName);
        if (make != null) {
            return make;
        }

        make = new VehicleMake(makeName);
        long makeId = database.makeDao().insertMake(make);
        make.setDatabaseId(makeId);

        return make;
    }

    public VehicleModel findOrCreateModel(String modelName, VehicleMake make) {
        VehicleModel model = database.modelDao().findBy(modelName, make.getDatabaseId());
        if (model != null) {
            return model;
        }

        model = new VehicleModel(modelName, make.getDatabaseId());
        long modelId = database.modelDao().insertModel(model);
        model.setDatabaseId(modelId);

        return model;
    }

    public long insertVehicle(String makeName, String modelName, int productionYear) {
        VehicleMake make = findOrCreateMake(makeName);
        VehicleModel model = findOrCreateModel(modelName, make);

        Vehicle newCar = new Vehicle(productionYear, model.getDatabaseId());
        return database.vehicleDao().insertVehicle(newCar);
    }
}
