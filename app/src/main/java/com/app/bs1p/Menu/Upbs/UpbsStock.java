package com.app.bs1p.Menu.Upbs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.app.bs1p.Adapter.StockAdapter;
import com.app.bs1p.CallApi.ApiCall;
import com.app.bs1p.CallApi.ApiEndpoint;
import com.app.bs1p.Menu.Navbar.Agrostandar;
import com.app.bs1p.Menu.Navbar.Dashboard;
import com.app.bs1p.Menu.Navbar.Kontak;
import com.app.bs1p.Menu.Navbar.Layanan;
import com.app.bs1p.Menu.Navbar.Organisasi;
import com.app.bs1p.Model.ResponseStock;
import com.app.bs1p.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpbsStock extends AppCompatActivity {
    ApiEndpoint api;
    RecyclerView rv;
    RecyclerView.LayoutManager lm;
    StockAdapter stockAdapter;
    List stock = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upbs_stock);

        // Set Recycler View
        rv = findViewById(R.id.rvStock);
        stockAdapter = new StockAdapter(stock, getApplicationContext());
        lm = new LinearLayoutManager(UpbsStock.this);
        rv.setLayoutManager(lm);
        rv.setAdapter(stockAdapter);

        api = ApiCall.getApi().create(ApiEndpoint.class);

        api.getBenih().enqueue(new Callback<ResponseStock>() {
            @Override
            public void onResponse(@NonNull Call<ResponseStock> call, @NonNull Response<ResponseStock> response) {
                assert response.body() != null;
                UpbsStock.this.stock = response.body().getHasil();
                stockAdapter.setData(stock);
            }
            @Override
            public void onFailure(@NonNull Call<ResponseStock> call, @NonNull Throwable t) {

            }
        });

        BottomNavigationView botNavbar = findViewById(R.id.navbar_kontak);
        botNavbar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                startActivity(new Intent(this, Dashboard.class));
                return true;
            } else if (itemId == R.id.menu_organisasi) {
                startActivity(new Intent(this, Organisasi.class));
                return true;
            } else if (itemId == R.id.menu_layanan) {
                startActivity(new Intent(this, Layanan.class));
                return true;
            } else if (itemId == R.id.menu_agro) {
                startActivity(new Intent(this, Agrostandar.class));
                return true;
            } else if (itemId == R.id.menu_kontak) {
                startActivity(new Intent(this, Kontak.class));
                return true;
            }
            return false;
        });
    }
}