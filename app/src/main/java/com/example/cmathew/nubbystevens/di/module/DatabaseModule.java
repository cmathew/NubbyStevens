package com.example.cmathew.nubbystevens.di.module;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import com.example.cmathew.nubbystevens.DealershipApplication;
import com.example.cmathew.nubbystevens.R;
import com.example.cmathew.nubbystevens.database.InventorySeeder;
import com.example.cmathew.nubbystevens.database.DealershipDatabase;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

@Module
public class DatabaseModule {
    @Provides
    @Singleton
    DealershipDatabase provideDatabase(final Application application) {
        String dbName = application.getResources().getString(R.string.database_name);
        return Room.databaseBuilder(application, DealershipDatabase.class, dbName)
                .allowMainThreadQueries()
                .addCallback(new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        InventorySeeder seeder = new InventorySeeder((DealershipApplication) application);
                        seeder.seed();
                    }
                }).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable ex) {
                        ex.printStackTrace();
                    }
                });
            }
        }).build();
    }


}