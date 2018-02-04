package com.example.cmathew.nubbystevens;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmathew.nubbystevens.database.DealershipDatabase;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    @Inject
    DealershipDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();

        addInventoryListFragment();

        FloatingActionButton fab = findViewById(R.id.add_vehicle_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVehicleEntryFragment();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        getSupportFragmentManager().removeOnBackStackChangedListener(this);
    }

    private void addInventoryListFragment() {
        InventoryFragment inventoryFragment = InventoryFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.content_container, inventoryFragment, "inventory_list")
                .commit();
    }

    private void addVehicleEntryFragment() {
        RegisterVehicleFragment registerVehicleFragment = RegisterVehicleFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null);
        registerVehicleFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.DealershipTheme);
        registerVehicleFragment.show(transaction, "register_vehicle");
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_main_activity);
        toolbar.setTitle(R.string.title_inventory);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onBackStackChanged() {
        Log.v("Nubby", "Back stack changed");
    }
}
