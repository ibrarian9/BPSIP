package com.app.bpsip.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.bpsip.Model.Stock;
import com.app.bpsip.R;

import java.util.ArrayList;
import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder> {

    private final List<Stock> benih;
    Context context;

    public StockAdapter(List<Stock> benih, Context context) {
        this.benih = benih;
        this.context = context;
    }

    @NonNull
    @Override
    public StockAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_stock, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StockAdapter.ViewHolder holder, int position) {
        Stock stock = benih.get(position);

        holder.benih.setText(stock.getStockBenihNama());
        holder.jumlah.setText(stock.getStockBenihJumlah());
    }

    @Override
    public int getItemCount() {
        return benih.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView benih, jumlah;
        public ViewHolder(@NonNull View v) {
            super(v);

            benih = v.findViewById(R.id.tvStock);
            jumlah = v.findViewById(R.id.tvJumlah);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Stock> data) {
        benih.clear();
        benih.addAll(data);
        notifyDataSetChanged();
    }
}
