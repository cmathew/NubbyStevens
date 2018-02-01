package com.example.cmathew.nubbystevens.database;

/**
 * Created by chris on 2/1/2018.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.cmathew.nubbystevens.entity.Vehicle;

@Database(entities = {Vehicle.class}, version = 1)
public abstract class DealershipDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}

