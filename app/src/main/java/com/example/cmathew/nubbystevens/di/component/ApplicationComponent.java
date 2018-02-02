package com.example.cmathew.nubbystevens.di.component;

import android.app.Application;

import com.example.cmathew.nubbystevens.DealershipApplication;
import com.example.cmathew.nubbystevens.database.InventorySeeder;
import com.example.cmathew.nubbystevens.di.binding.ActivityBindingModule;
import com.example.cmathew.nubbystevens.di.binding.FragmentBindingModule;
import com.example.cmathew.nubbystevens.di.module.DatabaseModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {
    DatabaseModule.class,
    // Required by the dagger-android framework
    AndroidSupportInjectionModule.class,
    // Specifies valid injection targets
    ActivityBindingModule.class,
    FragmentBindingModule.class})

public interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        ApplicationComponent.Builder application(Application application);
        ApplicationComponent build();
    }

    void inject(DealershipApplication app);
    void inject(InventorySeeder seeder);
}
