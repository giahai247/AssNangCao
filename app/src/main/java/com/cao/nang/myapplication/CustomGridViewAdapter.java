package com.cao.nang.myapplication;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by this on 13/11/2017.
 */

public class CustomGridViewAdapter extends ArrayAdapter<Item> {
        Context context;
        int resourceId;
        ArrayList<Item> data = new ArrayList<Item>();

        // Constuctor
	public CustomGridViewAdapter(Context context, int resourceId,
                                 ArrayList<Item> data) {
            super(context, resourceId, data);
            this.context = context;
            this.resourceId = resourceId;
            this.data = data;
        }

        // Khong hieu
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resourceId, parent, false);

            holder = new RecordHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.itemText);
            holder.imageItem = (ImageView) row.findViewById(R.id.itemImage);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }
        Item item = data.get(position);
        holder.txtTitle.setText(item.getTitle());
        holder.imageItem.setImageBitmap(item.getImage());
        return row;
    }

    static class RecordHolder {
        TextView txtTitle;
        ImageView imageItem;
    }
}
