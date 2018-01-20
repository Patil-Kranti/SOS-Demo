package com.example.krantipatil.sosdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kranti Patil on 20-01-2018.
 */

public class ListAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public ListAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.single_row_item, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.name = row.findViewById(R.id.tv_name);
            layoutHandler.phone = row.findViewById(R.id.tv_phone_number);
            row.setTag(layoutHandler);

        } else {
            layoutHandler = (LayoutHandler) row.getTag();
        }
        ContactModel contactModel = (ContactModel) this.getItem(position);
        layoutHandler.name.setText(contactModel.getName());
        layoutHandler.phone.setText(contactModel.getPhoneNumber());
        return row;

    }

    static class LayoutHandler {
        TextView name, phone;
    }
}
