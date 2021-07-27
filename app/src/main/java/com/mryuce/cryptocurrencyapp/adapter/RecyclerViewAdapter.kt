package com.mryuce.cryptocurrencyapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mryuce.cryptocurrencyapp.R
import com.mryuce.cryptocurrencyapp.model.CryptoModel
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(private val cryptoList:ArrayList<CryptoModel>,private val listener:Listener) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {


    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }
    private val colors:Array<String> = arrayOf("#a10d61","#0d17a1","#0da159","#0da159","#72a10d")

    class RowHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind(cryptoModel: CryptoModel,colors:Array<String>,position: Int,listener:Listener){
            itemView.setBackgroundColor(Color.parseColor(colors[position%5]))
            itemView.textName.text=cryptoModel.currency
            itemView.textPrice.text=cryptoModel.price
            itemView.setOnClickListener{
                listener.onItemClick(cryptoModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {


        holder.bind(cryptoList[position],colors,position,listener)

    }

    override fun getItemCount(): Int {
        return cryptoList.count()
    }
}