package com.kintetsu.aspirev3.randomizer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Aspire V3 on 8/21/2016.
 */

public class ObjectAdapter extends RecyclerView.Adapter<ObjectAdapter.ViewHolder> {

    private List<String> objectList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView objectName;

        public ViewHolder(TextView view) {
            super(view);
            objectName = view;
        }
    }

    public ObjectAdapter(String[] list) {
        this.objectList = Arrays.asList(list);
    }

    public ObjectAdapter(List<String> list) {
        this.objectList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.object_row, parent, false);
        TextView tv = (TextView) view.findViewById(R.id.object);
        ViewHolder v = new ViewHolder(tv);
        return v;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int n) {
        holder.objectName.setText(objectList.get(n));
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }
}
