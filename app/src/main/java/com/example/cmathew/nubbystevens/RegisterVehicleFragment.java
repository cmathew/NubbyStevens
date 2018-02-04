package com.example.cmathew.nubbystevens;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cmathew.nubbystevens.database.DealershipDatabase;
import com.example.cmathew.nubbystevens.database.VehicleCreator;
import com.example.cmathew.nubbystevens.entity.Vehicle;
import com.example.cmathew.nubbystevens.entity.VehicleMake;
import com.example.cmathew.nubbystevens.entity.VehicleModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class RegisterVehicleFragment extends DialogFragment {
    @Inject
    DealershipDatabase database;

    private EditText vehicleMakeEntry;
    private EditText vehicleModelEntry;
    private EditText productionYearEntry;
    private Button registerVehicleButton;

    public RegisterVehicleFragment() {
        // Required empty public constructor
    }

    public static RegisterVehicleFragment newInstance() {
        RegisterVehicleFragment fragment = new RegisterVehicleFragment();
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
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);
    }

    private void setupToolbar(ActionBar actionBar) {
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_nav_close);
        actionBar.setTitle(R.string.title_add_inventory);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_vehicle, container, false);

        AppCompatActivity activity = (AppCompatActivity) getActivity();

        Toolbar toolbar = view.findViewById(R.id.toolbar_register_vehicle);
        activity.setSupportActionBar(toolbar);

        ActionBar actionBar = activity.getSupportActionBar();
        setupToolbar(actionBar);

        this.vehicleMakeEntry = view.findViewById(R.id.vehicle_make_entry);
        this.vehicleModelEntry = view.findViewById(R.id.vehicle_model_entry);
        this.productionYearEntry = view.findViewById(R.id.vehicle_year_entry);
        this.registerVehicleButton = view.findViewById(R.id.register_vehicle_button);

        productionYearEntry.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == 100 || id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    registerVehicle();
                    return true;
                }
                return false;
            }
        });

        registerVehicleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerVehicle();
            }
        });

        return view;
    }

    private void registerVehicle() {
        String makeEntry = vehicleMakeEntry.getText().toString();
        String modelEntry = vehicleModelEntry.getText().toString();
        String yearEntry = productionYearEntry.getText().toString();
        int productionYear = Integer.parseInt(yearEntry);

        VehicleCreator creator = new VehicleCreator(database);
        creator.insertVehicle(makeEntry, modelEntry, productionYear);

        acknowledgeRegistration();
    }

    private void acknowledgeRegistration() {
        Toast.makeText(getActivity(), "Vehicle Registered!", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onStop() {
        super.onStop();

        hideKeyboardFor(vehicleMakeEntry);
        hideKeyboardFor(vehicleModelEntry);
        hideKeyboardFor(productionYearEntry);
    }

    public void hideKeyboardFor(View view) {
        if (!view.hasFocus()) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
