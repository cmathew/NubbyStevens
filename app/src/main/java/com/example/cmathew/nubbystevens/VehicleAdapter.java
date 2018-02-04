package com.example.cmathew.nubbystevens;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.cmathew.nubbystevens.database.contract.VehicleContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleContract.VehicleEntry;
import com.example.cmathew.nubbystevens.database.contract.VehicleMakeContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleMakeContract.VehicleMakeEntry;
import com.example.cmathew.nubbystevens.database.contract.VehicleModelContract;
import com.example.cmathew.nubbystevens.database.contract.VehicleModelContract.VehicleModelEntry;

import com.example.cmathew.nubbystevens.entity.Vehicle;
import com.example.cmathew.nubbystevens.entity.VehicleMinimal;

import java.util.ArrayList;
import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {
    private List<VehicleMinimal> cars;

    public VehicleAdapter() {
        this.cars = new ArrayList<>();
    }

    public List<VehicleMinimal> getCars() {
        return cars;
    }

    public void setCars(List<VehicleMinimal> cars) {
        this.cars = cars;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_inventory, parent, false);

        return new VehicleAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VehicleMinimal car = cars.get(position);

        String makeDesc = car.getMakeName();
        String modelDesc = car.getModelName();
        String makeModelDesc = String.format("%s %s", makeDesc, modelDesc);
        int productionYear = car.getProductionYear();

        holder.makeModelText.setText(makeModelDesc);
        holder.yearText.setText(String.valueOf(productionYear));
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView makeModelText;
        TextView yearText;

        public ViewHolder(View view) {
            super(view);

            this.makeModelText = view.findViewById(R.id.vehicle_make_model_description);
            this.yearText = view.findViewById(R.id.vehicle_year_description);
        }
    }
}
