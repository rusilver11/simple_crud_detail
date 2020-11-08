package com.example.simple_crud_detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapter (val dataItem : List<HashMap<String,String>>, val detailitem : MainActivity) :
    RecyclerView.Adapter<adapter.HolderItem>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HolderItem {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.adapter_item,p0,false)
        return HolderItem(v)
    }

    override fun getItemCount(): Int {
        return dataItem.size
    }

    override fun onBindViewHolder(p0: HolderItem, p1: Int) {
        val data = dataItem.get(p1)

        p0.title.setText(data.get("title"))

        //detail click
        p0.itemView.setOnClickListener(View.OnClickListener{
            val iditem = data.get("id")
            val context = detailitem
            val intent = Intent(context, detail_item::class.java)
            intent.putExtra("id",iditem)
            context.startActivity(intent)
        })
    }
    class HolderItem (v : View) : RecyclerView.ViewHolder(v){
        val title = v.findViewById<TextView>(R.id.item1)
    }
}