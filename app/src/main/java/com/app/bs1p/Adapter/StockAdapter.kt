package com.app.bs1p.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.bs1p.Model.Stock
import com.app.bs1p.R

class StockAdapter(private val benih: MutableList<Stock>, var context: Context) :
    RecyclerView.Adapter<StockAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_stock, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stock = benih[position]
        holder.benih.text =
            HtmlCompat.fromHtml(stock.stockBenihNama, HtmlCompat.FROM_HTML_MODE_LEGACY)
        holder.jumlah.text =
            HtmlCompat.fromHtml(stock.stockBenihJumlah, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    override fun getItemCount(): Int {
        return benih.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var benih: TextView
        var jumlah: TextView

        init {
            benih = v.findViewById(R.id.tvStock)
            jumlah = v.findViewById(R.id.tvJumlah)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Stock>?) {
        benih.clear()
        benih.addAll(data!!)
        notifyDataSetChanged()
    }
}