package com.example.cmathew.nubbystevens;

public class VehicleModel {
    private long databaseId;
    private VehicleMake make;
    private String name;

    public VehicleModel(String name, VehicleMake make) {
        this.name = name;
        this.make = make;
    }

    public VehicleMake getMake() {
        return make;
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
}
