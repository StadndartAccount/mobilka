package com.example.laba2.ui.cities_list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.laba2.City;
import com.example.laba2.CityObj;
import com.example.laba2.JsonPlaceHolderApi;
import com.example.laba2.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CitiesListFragment extends Fragment {
    private static boolean SortingDirectOrder = true;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeContainer;
    CityCardAdapter cityCardAdapter = new CityCardAdapter(new ArrayList<CityObj>());


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cities_list, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view_cities_list);

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchTimelineAsync();
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright);

        recyclerView.setAdapter(cityCardAdapter);
        fetchTimelineAsync();

    }

    public void fetchTimelineAsync() {
        swipeContainer.setRefreshing(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Lpirskaya/JsonLab/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<City>> call = jsonPlaceHolderApi.getCities();

        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {

                if(!response.isSuccessful()) {
                    Log.e("Запрос не удался", ":(");
                }

                cityCardAdapter.Clear();

                List<City> cities = response.body();
                List<CityObj> citiesToRecyclerView = new ArrayList<CityObj>();

                for (City city : cities) {
                    Log.i("city", " name: " + city.getName() + ", country: " + city.getCountry() + ", population: " + String.valueOf(city.getPopulation()) + ", language: " + city.getLanguage());
                    citiesToRecyclerView.add(new CityObj(city.getName(), city.getCountry(), city.getLanguage(), city.getPopulation()));
                }

                if(SortingDirectOrder) citiesToRecyclerView.sort(Comparator.comparing(CityObj::GetName));
                else citiesToRecyclerView.sort(Comparator.comparing(CityObj::GetName, Comparator.reverseOrder()));

                cityCardAdapter.AddAll(citiesToRecyclerView);
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Log.e("Запрос не удался", ":(");
            }
        });
    }

    public static void SetSortingOrder(boolean directOreder) {
        SortingDirectOrder = directOreder;
    }

    public static void ReverseSortingOrder() {
        SortingDirectOrder = !SortingDirectOrder;
    }

    public static boolean GetSortingOrder() {
        return SortingDirectOrder;
    }

}