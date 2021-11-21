package com.example.laba2.ui.cities_list;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laba2.CityObj;
import com.example.laba2.CurrentCitySharedPreferences;
import com.example.laba2.R;

import java.util.ArrayList;
import java.util.List;

public class CityCardAdapter extends RecyclerView.Adapter<CityCardAdapter.ViewHolder> {

    List<CityObj> _cities = new ArrayList<>();

    public CityCardAdapter(List<CityObj> cities) {
        _cities = cities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.getTextViewTitle().setText(_cities.get(position).Name);
        holder.getTextViewCountry().setText(_cities.get(position).Country);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_current_city);
                CurrentCitySharedPreferences.SetValue(holder.context, CurrentCitySharedPreferences.CURRENT_CITY_NAME, _cities.get(position).Name);
                CurrentCitySharedPreferences.SetValue(holder.context, CurrentCitySharedPreferences.CURRENT_CITY_LANGUAGE, _cities.get(position).Language);
                CurrentCitySharedPreferences.SetValue(holder.context, CurrentCitySharedPreferences.CURRENT_CITY_COUNTRY, _cities.get(position).Country);
                CurrentCitySharedPreferences.SetValue(holder.context, CurrentCitySharedPreferences.CURRENT_CITY_POPULATION, _cities.get(position).Population);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (_cities == null) return -1;
        return _cities.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textViewTitle;
        private final TextView textViewCountry;
        ConstraintLayout mainLayout;
        Context context;

        public ViewHolder(View view) {
            super(view);
            textViewTitle = view.findViewById(R.id.card_city_title);
            textViewCountry = view.findViewById(R.id.card_city_country);
            mainLayout = view.findViewById(R.id.card_layout);
            context = view.getContext();
        }

        public TextView getTextViewTitle() {
            return textViewTitle;
        }
        public TextView getTextViewCountry() {
            return textViewCountry;
        }
    }

    public void Clear() {
        if(_cities != null) {
            _cities.clear();
            notifyDataSetChanged();
        }
    }

    // Add a list of items -- change to type used
    public void AddAll(List<CityObj> newCities) {
        _cities.addAll(newCities);
        notifyDataSetChanged();
    }
}
