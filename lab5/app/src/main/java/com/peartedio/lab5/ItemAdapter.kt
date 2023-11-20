package com.peartedio.lab5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val items: List<Item>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_id)
        val titleView: TextView = view.findViewById(R.id.item_title)
        val descView: TextView = view.findViewById(R.id.item_desc)
        val userView: TextView = view.findViewById(R.id.item_user)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = items[position]
        viewHolder.idView.text = item.id
        viewHolder.userView.text = item.userId
        viewHolder.titleView.text = item.title
        viewHolder.descView.text =  item.body
    }

    override fun getItemCount() = items.size

}