package com.saurabh.covid_19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Country_detials> {

    private Context context;
    private List<Country_detials> cdetails;
    private List<Country_detials> cdetailsFiltered;

    public CustomAdapter(Context context, List<Country_detials>cdetails) {
        super(context, R.layout.custom_list_item,cdetails);
        this.context=context;
        this.cdetails=cdetails;
        this.cdetailsFiltered=cdetails;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);

        TextView cname=view.findViewById(R.id.country_name);
        ImageView flag=view.findViewById(R.id.flag_img);

        cname.setText(cdetailsFiltered.get(position).getCountry());
        Glide.with(context).load(cdetailsFiltered.get(position).getFlag()).into(flag);

        return view;
    }

    @Override
    public int getCount() {
        return cdetailsFiltered.size();
    }

    @Nullable
    @Override
    public Country_detials getItem(int position) {
        return cdetailsFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults=new FilterResults();
                if(constraint==null||constraint.length()==0)
                {
                    filterResults.count=cdetails.size();
                    filterResults.values=cdetails;
                }
                else{
                    List<Country_detials>result=new ArrayList<>();
                    String search_str=constraint.toString().toLowerCase();
                    for(Country_detials item:cdetails)
                    {
                        if(item.getCountry().toLowerCase().contains(search_str))
                        {
                            result.add(item);
                        }
                        filterResults.count=result.size();
                        filterResults.values=result;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                cdetailsFiltered=(List<Country_detials>) results.values;
                Affectedcountries.alist=(List<Country_detials>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
