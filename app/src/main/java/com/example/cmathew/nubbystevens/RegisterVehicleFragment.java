package com.example.cmathew.nubbystevens;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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
import com.example.cmathew.nubbystevens.entity.VehicleMinimal;
import com.example.cmathew.nubbystevens.entity.VehicleModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public class RegisterVehicleFragment extends DialogFragment {
    @Inject
    DealershipDatabase database;

    @BindView(R.id.register_vehicle_button)
    Button registerVehicleButton;

    @BindView(R.id.toolbar_register_vehicle)
    Toolbar toolbar;

    private Unbinder unbinder;

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

        this.unbinder = ButterKnife.bind(this, view);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        setupToolbar(actionBar);

        registerVehicleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestVehicleRegistration();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        VehicleEntryFragment entryFragment = getVehicleEntryFragment();
        if (entryFragment != null) {
            getFragmentManager().beginTransaction().remove(entryFragment).commit();
        }

        unbinder.unbind();
    }

    private VehicleEntryFragment getVehicleEntryFragment() {
        return (VehicleEntryFragment) getFragmentManager().findFragmentById(R.id.register_vehicle_entry);
    }

    private void requestVehicleRegistration() {
        VehicleEntryFragment entryFragment = getVehicleEntryFragment();
        entryFragment.resetInputValidation();

        boolean inputIsValid = entryFragment.validateInput();
        if (!inputIsValid) {
            return;
        }

        registerVehicle();
        acknowledgeRegistration();
    }

    private void registerVehicle() {
        VehicleMinimal minimal = getVehicleEntryFragment().getEntry();
        VehicleCreator creator = new VehicleCreator(database);
        creator.insertVehicle(minimal);
    }

    private void acknowledgeRegistration() {
        Toast.makeText(getActivity(), R.string.register_vehicle_success, Toast.LENGTH_SHORT).show();
        dismiss();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();

        super.onCreateOptionsMenu(menu, inflater);
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
