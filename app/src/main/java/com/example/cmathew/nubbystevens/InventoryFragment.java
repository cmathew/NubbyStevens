package com.example.cmathew.nubbystevens;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmathew.nubbystevens.database.DealershipDatabase;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class InventoryFragment extends ListFragment {
    @Inject
    DealershipDatabase database;

    public InventoryFragment() {

    }

    public static InventoryFragment newInstance() {
        InventoryFragment fragment = new InventoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Cursor cursor = database.vehicleDao().getCursorAll();
        if (getListAdapter() == null) {
            VehicleAdapter adapter = new VehicleAdapter(getActivity(), cursor, 0);
            setListAdapter(adapter);
        } else {
            ((VehicleAdapter) getListAdapter()).changeCursor(cursor);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inventory_list, container, false);
    }
}
