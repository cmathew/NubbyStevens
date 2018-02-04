package com.example.cmathew.nubbystevens;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cmathew.nubbystevens.database.DealershipDatabase;
import com.example.cmathew.nubbystevens.database.VehicleCreator;
import com.example.cmathew.nubbystevens.entity.Vehicle;
import com.example.cmathew.nubbystevens.entity.VehicleMake;
import com.example.cmathew.nubbystevens.entity.VehicleMinimal;
import com.example.cmathew.nubbystevens.entity.VehicleModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public class EditVehicleFragment extends DialogFragment {
    private static final String ARG_VEHICLE_ID = "vehicle_id";

    @Inject
    DealershipDatabase database;

    @BindView(R.id.toolbar_edit_vehicle)
    Toolbar toolbar;

    @BindView(R.id.edit_vehicle_button)
    Button editVehicleButton;

    private Unbinder unbinder;

    private long vehicleId;

    public EditVehicleFragment() {
        // Required empty public constructor
    }

    public static EditVehicleFragment newInstance(long vehicleId) {
        EditVehicleFragment fragment = new EditVehicleFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_VEHICLE_ID, vehicleId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            vehicleId = getArguments().getLong(ARG_VEHICLE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_vehicle, container, false);

        this.unbinder = ButterKnife.bind(this, view);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        setupToolbar(actionBar);

        VehicleMinimal vehicleMinimal = database.vehicleDao().findById(vehicleId);
        VehicleEntryFragment entryFragment = VehicleEntryFragment.newInstance(
                vehicleMinimal.getMakeName(),
                vehicleMinimal.getModelName(),
                vehicleMinimal.getProductionYear());
        getChildFragmentManager().beginTransaction().replace(R.id.edit_vehicle_entry, entryFragment).commit();

        editVehicleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestSaveChanges();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    private void setupToolbar(ActionBar actionBar) {
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_nav_close);
        actionBar.setTitle(R.string.title_edit_inventory);
    }

    private VehicleEntryFragment getVehicleEntryFragment() {
        return (VehicleEntryFragment) getChildFragmentManager().findFragmentById(R.id.edit_vehicle_entry);
    }

    private void requestSaveChanges() {
        VehicleEntryFragment entryFragment = getVehicleEntryFragment();
        entryFragment.resetInputValidation();

        boolean inputIsValid = entryFragment.validateInput();
        if (!inputIsValid) {
            return;
        }

        saveChanges();
        acknowledgeChanges();
    }

    private void saveChanges() {
        VehicleMinimal minimal = getVehicleEntryFragment().getEntry();

        VehicleCreator creator = new VehicleCreator(database);
        VehicleMake make = creator.findOrCreateMake(minimal.getMakeName());
        VehicleModel model = creator.findOrCreateModel(minimal.getModelName(), make);
        Vehicle vehicle = new Vehicle(minimal.getProductionYear(), model.getDatabaseId());
        vehicle.setDatabaseId(vehicleId);

        database.vehicleDao().updateVehicle(vehicle);
    }

    private void acknowledgeChanges() {
        Toast.makeText(getActivity(), R.string.edit_vehicle_success, Toast.LENGTH_SHORT).show();
        dismiss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                dismiss();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
