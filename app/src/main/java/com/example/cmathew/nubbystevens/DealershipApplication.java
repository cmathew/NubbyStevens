package com.example.cmathew.nubbystevens;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.cmathew.nubbystevens.csv.VehicleParser;
import com.example.cmathew.nubbystevens.database.DealershipDatabase;
import com.example.cmathew.nubbystevens.di.component.ApplicationComponent;
import com.example.cmathew.nubbystevens.di.component.DaggerApplicationComponent;
import com.example.cmathew.nubbystevens.entity.Vehicle;
import com.facebook.stetho.Stetho;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class DealershipApplication extends Application implements HasSupportFragmentInjector {
    @Inject
    DealershipDatabase database;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        this.applicationComponent = DaggerApplicationComponent.builder()
                .application(this)
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
