package com.example.cmathew.nubbystevens;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cmathew.nubbystevens.entity.VehicleMinimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class VehicleEntryFragment extends Fragment {
    private static final String ARG_VEHICLE_MAKE_NAME = "vehicle_make_name";
    private static final String ARG_VEHICLE_MODEL_NAME = "vehicle_model_name";
    private static final String ARG_VEHICLE_PRODUCTION_YEAR = "vehicle_production_year";

    @BindView(R.id.vehicle_make_entry)
    EditText vehicleMakeEntry;
    @BindView(R.id.vehicle_make_layout)
    TextInputLayout vehicleMakeLayout;

    @BindView(R.id.vehicle_model_entry)
    EditText vehicleModelEntry;
    @BindView(R.id.vehicle_model_layout)
    TextInputLayout vehicleModelLayout;

    @BindView(R.id.vehicle_year_entry)
    EditText productionYearEntry;
    @BindView(R.id.vehicle_year_layout)
    TextInputLayout productionYearLayout;

    private Unbinder unbinder;

    public VehicleEntryFragment() {
        // Required empty public constructor
    }

    public static VehicleEntryFragment newInstance(String makeName, String modelName, int productionYear) {
        VehicleEntryFragment fragment = new VehicleEntryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_VEHICLE_MAKE_NAME, makeName);
        args.putString(ARG_VEHICLE_MODEL_NAME, modelName);
        args.putInt(ARG_VEHICLE_PRODUCTION_YEAR, productionYear);
        fragment.setArguments(args);
        return fragment;
    }

    public boolean validateInput() {
        final String requiredFieldError = getString(R.string.error_required_field);
        String makeEntry = vehicleMakeEntry.getText().toString();
        if (TextUtils.isEmpty(makeEntry)) {
            vehicleMakeLayout.setError(requiredFieldError);
            return false;
        }

        String modelEntry = vehicleModelEntry.getText().toString();
        if (TextUtils.isEmpty(modelEntry)) {
            vehicleModelLayout.setError(requiredFieldError);
            return false;
        }

        String yearEntry = productionYearEntry.getText().toString();
        if (TextUtils.isEmpty(yearEntry)) {
            productionYearLayout.setError(requiredFieldError);
            return false;
        }

        return true;
    }

    public void resetInputValidation() {
        vehicleMakeEntry.setError(null);
        vehicleModelEntry.setError(null);
        productionYearEntry.setError(null);
    }

    public VehicleMinimal getEntry() {
        String makeEntry = vehicleMakeEntry.getText().toString();
        String modelEntry = vehicleModelEntry.getText().toString();
        String yearEntry = productionYearEntry.getText().toString();
        int productionYear = Integer.parseInt(yearEntry);

        return new VehicleMinimal(0, productionYear, makeEntry, modelEntry);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_entry, container, false);
        this.unbinder = ButterKnife.bind(this, view);

        Bundle args = getArguments();
        if (args != null) {
            String makeValue = args.getString(ARG_VEHICLE_MAKE_NAME);
            vehicleMakeEntry.setText(makeValue);

            String modelName = args.getString(ARG_VEHICLE_MODEL_NAME);
            vehicleModelEntry.setText(modelName);

            int productionYear = args.getInt(ARG_VEHICLE_PRODUCTION_YEAR);
            productionYearEntry.setText(String.valueOf(productionYear));
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
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
