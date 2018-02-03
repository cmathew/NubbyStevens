package com.example.cmathew.nubbystevens.entity;

public class VehicleMinimal {
    private long id;
    private int productionYear;
    private String makeName;
    private String modelName;

    public VehicleMinimal(long id, int productionYear, String makeName,  String modelName) {
        this.id = id;
        this.productionYear = productionYear;
        this.makeName = makeName;
        this.modelName = modelName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
