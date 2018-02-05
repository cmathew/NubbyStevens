package com.example.cmathew.nubbystevens;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmathew.nubbystevens.database.DealershipDatabase;
import com.example.cmathew.nubbystevens.entity.VehicleMinimal;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    @Inject
    DealershipDatabase database;

    private SearchView searchMenuAction;

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

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            applyInventoryFilter(query);
        }
    }

    private void applyInventoryFilter(@NonNull String query) {
        InventoryFragment inventoryFragment = (InventoryFragment) getSupportFragmentManager().findFragmentByTag("inventory_list");
        inventoryFragment.setFilter(query);
    }

    private void removeInventoryFilter() {
        InventoryFragment inventoryFragment = (InventoryFragment) getSupportFragmentManager().findFragmentByTag("inventory_list");
        inventoryFragment.removeFilter();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager == null) {
            return false;
        }

        MenuItem searchMenuItem = menu.findItem(R.id.menu_action_search);
        this.searchMenuAction = (SearchView) searchMenuItem.getActionView();
        searchMenuAction.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchMenuAction.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                removeInventoryFilter();
                return false;
            }
        });

        return true;
    }
}
