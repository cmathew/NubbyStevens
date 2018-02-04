package com.example.cmathew.nubbystevens;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cmathew.nubbystevens.entity.VehicleMinimal;

import java.util.ArrayList;
import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {
    private List<VehicleMinimal> cars;
    private FragmentActivity context;

    public VehicleAdapter(FragmentActivity context) {
        this.context = context;
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

        holder.itemView.setOnClickListener(new VehicleClickListener(context, car.getId()));
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

    static class VehicleClickListener implements View.OnClickListener {
        private FragmentActivity context;
        private long vehicleId;

        public VehicleClickListener(FragmentActivity context, long vehicleId) {
            this.context = context;
            this.vehicleId = vehicleId;
        }

        @Override
        public void onClick(View view) {
            EditVehicleFragment editVehicleFragment = EditVehicleFragment.newInstance(vehicleId);
            FragmentManager fragmentManager = context.getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null);
            editVehicleFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.DealershipTheme);
            editVehicleFragment.show(transaction, "edit_vehicle");
        }
    }

}
