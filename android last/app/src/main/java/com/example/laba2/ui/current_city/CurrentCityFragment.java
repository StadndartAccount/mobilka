package com.example.laba2.ui.current_city;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.laba2.CurrentCitySharedPreferences;
import com.example.laba2.R;

public class CurrentCityFragment extends Fragment {
    private TextView textView_name;
    private TextView textView_country;
    private TextView textView_language;
    private TextView textView_population;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_current_city, container, false);
        return root;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView_name = view.findViewById(R.id.current_city_name);
        textView_country = view.findViewById(R.id.country_name);
        textView_language = view.findViewById(R.id.national_language);
        textView_population = view.findViewById(R.id.population);

        String city_name = CurrentCitySharedPreferences.GetString(view.getContext(), CurrentCitySharedPreferences.CURRENT_CITY_NAME);
        String city_language = CurrentCitySharedPreferences.GetString(view.getContext(), CurrentCitySharedPreferences.CURRENT_CITY_LANGUAGE);
        String city_country = CurrentCitySharedPreferences.GetString(view.getContext(), CurrentCitySharedPreferences.CURRENT_CITY_COUNTRY);
        Integer city_population = CurrentCitySharedPreferences.GetInteger(view.getContext(), CurrentCitySharedPreferences.CURRENT_CITY_POPULATION);

        if(city_name != null) {
            textView_name.setText(city_name);
            textView_country.setText("Страна: " + city_country);
            textView_language.setText("Национальный язык: " + city_language);
            textView_population.setText("Численность населения: " + String.valueOf(city_population));
        } else {
            textView_name.setText("Город не выбран!");
            textView_country.setText("Страна: - ");
            textView_language.setText("Национальный язык: - ");
            textView_population.setText("Численность населения: - ");
        }
    }
}