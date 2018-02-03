package com.example.cmathew.nubbystevens;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmathew.nubbystevens.database.DealershipDatabase;
import com.example.cmathew.nubbystevens.entity.VehicleMinimal;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class InventoryFragment extends Fragment {
    @Inject
    DealershipDatabase database;

    private RecyclerView vehicleList;
    private VehicleAdapter vehicleAdapter;

    private Disposable vehicleSub;

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

        this.vehicleAdapter = new VehicleAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View listView = inflater.inflate(R.layout.fragment_inventory_list, container, false);

        this.vehicleList = listView.findViewById(R.id.inventory_list);
        LinearLayoutManager linearMgr = new LinearLayoutManager(getContext());
        vehicleList.setLayoutManager(linearMgr);
        vehicleList.setAdapter(vehicleAdapter);

        return listView;
    }

    @Override
    public void onStart() {
        super.onStart();

        Flowable<List<VehicleMinimal>> vehicleObs = database.vehicleDao()
                .getAll()
                .observeOn(AndroidSchedulers.mainThread());

        this.vehicleSub = vehicleObs.subscribe(new Consumer<List<VehicleMinimal>>() {
            @Override
            public void accept(@NonNull List<VehicleMinimal> vehicleMinimals) throws Exception {
                vehicleAdapter.setCars(vehicleMinimals);
                vehicleAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

        vehicleSub.dispose();
    }
}
