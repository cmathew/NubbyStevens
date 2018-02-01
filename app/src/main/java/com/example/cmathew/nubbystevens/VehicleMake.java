package com.example.cmathew.nubbystevens;

public class VehicleMake {
    private long databaseId;
    private String name;

    public VehicleMake(String name) {
        this.name = name;
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
