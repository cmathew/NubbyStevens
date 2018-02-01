package com.example.cmathew.nubbystevens;

public class Vehicle {
    private VehicleModel model;
    private int productionYear;

    public Vehicle(int productionYear, VehicleModel model) {
        this.productionYear = productionYear;
        this.model = model;
    }

    public VehicleModel getModel() {
        return model;
    }

    public int getProductionYear() {
        return productionYear;
    }
}
