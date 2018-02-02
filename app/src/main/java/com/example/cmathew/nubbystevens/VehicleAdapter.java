package com.example.cmathew.nubbystevens;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

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
        TextView makeText = view.findViewById(R.id.vehicle_make_description);
        TextView modelText = view.findViewById(R.id.vehicle_model_description);
        TextView yearText = view.findViewById(R.id.vehicle_year_description);

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