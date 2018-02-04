package com.example.cmathew.nubbystevens.di.binding;

import com.example.cmathew.nubbystevens.EditVehicleFragment;
import com.example.cmathew.nubbystevens.InventoryFragment;
import com.example.cmathew.nubbystevens.RegisterVehicleFragment;
import com.example.cmathew.nubbystevens.di.scope.FragmentScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract InventoryFragment inventoryFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract RegisterVehicleFragment registerVehicleFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract EditVehicleFragment editVehicleFragment();
}