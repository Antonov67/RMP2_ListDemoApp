package com.example.rmp2_listdemoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> items;

    public CustomAdapter(Context context, List<String> items) {
        super(context, R.layout.item_layout, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_layout, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.itemText);
        Button deleteButton = convertView.findViewById(R.id.deleteButton);

        textView.setText(items.get(position));

        deleteButton.setOnClickListener(v -> {
            items.remove(position);
            notifyDataSetChanged();
        });

        return convertView;
    }
}
