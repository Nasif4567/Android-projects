package com.example.trustbooddonation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AppListAdapter extends BaseAdapter {

    private Context mContext1;
    ArrayList<AppCons> arrayList1;

    //filter varialbles
    private ValueFilter valueFilter1;
    private ArrayList<AppCons> mStringFilterList1;
    //------------------------------------------------------------------

    public AppListAdapter(@NonNull Context context, ArrayList<AppCons> arraylist) {
        mContext1 = context;
        arrayList1 = arraylist;
        mStringFilterList1 = arrayList1;
        getFilter();
    }

    @Override
    public int getCount() {
        return this.arrayList1.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.adapter_view_layout1, null);


        String fn = arrayList1.get(position).getFna();
        String ln = arrayList1.get(position).getLna();
        String pn = arrayList1.get(position).getPna();
        String bg = arrayList1.get(position).getBga();
        String db = arrayList1.get(position).getDaa();


        TextView fnv = (TextView) convertView.findViewById(R.id.fnv1);
        TextView lnv = (TextView) convertView.findViewById(R.id.lnv1);
        TextView pnv = (TextView) convertView.findViewById(R.id.pnv1);
        TextView dbv = (TextView) convertView.findViewById(R.id.dbv1);
        TextView bgv = (TextView) convertView.findViewById(R.id.bgv1);


        fnv.setText(fn);
        lnv.setText(ln);
        pnv.setText(pn);
        dbv.setText(db);
        bgv.setText(bg);

        return convertView;


    }

    //---------------------------------------------------------------filter work ----------------

    public Filter getFilter() {
        if (valueFilter1 == null) {

            valueFilter1 = new ValueFilter();
        }

        return valueFilter1;
    }

    private class ValueFilter extends Filter {

        //Invoked in a worker thread to filter the data according to the constraint.
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<AppCons> filterList = new ArrayList<AppCons>();
                for (int i = 0; i < mStringFilterList1.size(); i++) {
                    if ((mStringFilterList1.get(i).getPna().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {
                        AppCons cus = new AppCons();
                        cus.setPna(mStringFilterList1.get(i).getPna());
                        cus.setFna(mStringFilterList1.get(i).getFna());
                        cus.setLna(mStringFilterList1.get(i).getLna());
                        cus.setDaa(mStringFilterList1.get(i).getDaa());
                        ;
                        cus.setBga(mStringFilterList1.get(i).getBga());
                        filterList.add(cus);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList1.size();
                results.values = mStringFilterList1;
            }
            return results;
        }


        //Invoked in the UI thread to publish the filtering results in the user interface.
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            arrayList1 = (ArrayList<AppCons>) results.values;
            notifyDataSetChanged();
        }
    }

}

