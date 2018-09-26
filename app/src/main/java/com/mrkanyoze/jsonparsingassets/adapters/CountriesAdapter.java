package com.mrkanyoze.jsonparsingassets.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mrkanyoze.jsonparsingassets.R;
import com.mrkanyoze.jsonparsingassets.models.Country;

import java.util.ArrayList;

/**
 * Created by Victor Kanyoze on 9/26/2018.
 */

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {
    private static final int EMPTY_VIEW = 2;
    private ArrayList<Country> mArrayList;


    Country m;


    public CountriesAdapter(ArrayList<Country> arrayList) {
        mArrayList = arrayList;

    }

    @Override
    public CountriesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.country_list, viewGroup, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountriesAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_country.setText(mArrayList.get(i).getCountry());


        m = mArrayList.get(i);


    }

    @Override
    public int getItemCount() {

        return mArrayList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_country;

        public ViewHolder(View view) {
            super(view);

            tv_country = view.findViewById(R.id.txtCountry);

        }
    }
}

