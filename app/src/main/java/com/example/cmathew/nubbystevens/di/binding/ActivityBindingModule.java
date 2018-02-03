package com.example.cmathew.nubbystevens.di.binding;

import com.example.cmathew.nubbystevens.MainActivity;
import com.example.cmathew.nubbystevens.di.scope.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();
}