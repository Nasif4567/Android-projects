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

public class CustomerListAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<CustomersCons> arrayList;

    //filter varialbles
    private ValueFilter valueFilter;
    private ArrayList<CustomersCons> mStringFilterList;
    //------------------------------------------------------------------

    public CustomerListAdapter(@NonNull Context context, ArrayList<CustomersCons> arraylist) {
        mContext = context;
        arrayList = arraylist;
        mStringFilterList =  arrayList;
        getFilter();
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.adapter_view_layout,null);



        String fn = arrayList.get(position).getFn();
        String ln = arrayList.get(position).getLn();
        String gn = arrayList.get(position).getGn();
        String db = arrayList.get(position).getDb();
        String pn = arrayList.get(position).getPn();
        String em = arrayList.get(position).getEm();
        String ad = arrayList.get(position).getAd();
        String bg = arrayList.get(position).getBg();


        TextView fnv = (TextView) convertView.findViewById(R.id.fnv);
        TextView lnv = (TextView) convertView.findViewById(R.id.lnv);
        TextView gnv = (TextView) convertView.findViewById(R.id.gnv);
        TextView pnv = (TextView) convertView.findViewById(R.id.pnv);
        TextView adv = (TextView) convertView.findViewById(R.id.adv);
        TextView dbv = (TextView) convertView.findViewById(R.id.dbv);
        TextView emv = (TextView) convertView.findViewById(R.id.emv);
        TextView bgv = (TextView) convertView.findViewById(R.id.bgv);


        fnv.setText(fn);
        lnv.setText(ln);
        pnv.setText(pn);
        adv.setText(ad);
        emv.setText(em);
        gnv.setText(gn);
        dbv.setText(db);
        bgv.setText(bg);

        return convertView;


    }

    //---------------------------------------------------------------filter work ----------------

    public Filter getFilter() {
        if(valueFilter==null) {

            valueFilter=new ValueFilter();
        }

        return valueFilter;
    }

    private class ValueFilter extends Filter {

        //Invoked in a worker thread to filter the data according to the constraint.
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results=new FilterResults();
            if(constraint!=null && constraint.length()>0){
                ArrayList<CustomersCons> filterList=new ArrayList<CustomersCons>();
                for(int i=0;i<mStringFilterList.size();i++){
                    if((mStringFilterList.get(i).getPn().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {
                        CustomersCons cus = new CustomersCons();
                        cus.setPn(mStringFilterList.get(i).getPn());
                        cus.setFn(mStringFilterList.get(i).getFn());
                        cus.setLn(mStringFilterList.get(i).getLn());
                        cus.setEm(mStringFilterList.get(i).getEm());
                        cus.setDb(mStringFilterList.get(i).getDb());
                        cus.setGn(mStringFilterList.get(i).getGn());
                        cus.setBg(mStringFilterList.get(i).getBg());
                        cus.setAd(mStringFilterList.get(i).getAd());
                        filterList.add(cus);
                    }
                }
                results.count=filterList.size();
                results.values=filterList;
            }else{
                results.count=mStringFilterList.size();
                results.values=mStringFilterList;
            }
            return results;
        }


        //Invoked in the UI thread to publish the filtering results in the user interface.
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            arrayList =(ArrayList<CustomersCons>) results.values;
            notifyDataSetChanged();
        }
    }















}