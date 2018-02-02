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

import java.util.List;

public class VehicleAdapter extends CursorAdapter {
    public VehicleAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View row = layoutInflater.inflate(R.layout.fragment_inventory, parent, false);
        return row;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView makeModelText = view.findViewById(R.id.vehicle_make_model_description);
        TextView yearText = view.findViewById(R.id.vehicle_year_description);

        String makeDescColumnName = String.format("%s_%s", VehicleMakeContract.TABLE_NAME, VehicleMakeEntry.COLUMN_NAME);
        String makeDesc = cursor.getString(cursor.getColumnIndexOrThrow(makeDescColumnName));
        String modelDescColumnName = String.format("%s_%s", VehicleModelContract.TABLE_NAME, VehicleModelEntry.COLUMN_NAME);
        String modelDesc = cursor.getString(cursor.getColumnIndexOrThrow(modelDescColumnName));
        int productionYear = cursor.getInt(cursor.getColumnIndexOrThrow(VehicleEntry.COLUMN_PRODUCTION_YEAR));

        String makeModelDesc = String.format("%s %s", makeDesc, modelDesc);
        makeModelText.setText(makeModelDesc);
        yearText.setText(String.valueOf(productionYear));

        /*
        NotificationResponder nr = new NotificationResponder();
        Notification note = nr.call(cursor);

        Vehicle car = cars.get(position);
        holder.makeText.setText(String.valueOf(car.get));
        holder.modelText.setText(String.valueOf(val));
        holder.yearText.setText(String.valueOf(val));
        */
    }
}