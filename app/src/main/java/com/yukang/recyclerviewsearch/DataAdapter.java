package com.yukang.recyclerviewsearch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by simple on 17-3-11.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> implements Filterable {
    private ArrayList<AndroidVersion> mArrayList;
    private ArrayList<AndroidVersion> mFilteredList;

    public DataAdapter(ArrayList<AndroidVersion> arrayList) {
        this.mArrayList = arrayList;
        this.mFilteredList = arrayList;
    }

    @Override
    public DataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.MyViewHolder holder, int position) {
        holder.tv_name.setText(mFilteredList.get(position).getName());
        holder.tv_version.setText(mFilteredList.get(position).getVer());
        holder.tv_api_level.setText(mFilteredList.get(position).getApi());
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    mFilteredList = mArrayList;
                } else {
                    ArrayList<AndroidVersion> filteredList = new ArrayList<>();
                    for (AndroidVersion androidVersion : mArrayList) {
                        if (androidVersion.getApi().toLowerCase().contains(charString) || androidVersion.getName().toLowerCase().contains(charString) || androidVersion.getVer().toLowerCase().contains(charString)) {
                            filteredList.add(androidVersion);
                        }
                    }
                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFilteredList = (ArrayList<AndroidVersion>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_version, tv_api_level;

        public MyViewHolder(View view) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_version = (TextView) view.findViewById(R.id.tv_version);
            tv_api_level = (TextView) view.findViewById(R.id.tv_api);
        }
    }
}
