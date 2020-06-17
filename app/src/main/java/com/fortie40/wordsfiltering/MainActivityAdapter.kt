package com.fortie40.wordsfiltering

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainActivityAdapter(private val names: List<String>):
    RecyclerView.Adapter<MainActivityAdapter.MainActivityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.name_layout, parent, false)
        return MainActivityViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
        val name = names[position]
        holder.bind(name)
    }

    class MainActivityViewHolder(private val nItemView: View): RecyclerView.ViewHolder(nItemView) {

        fun bind(nameA: String) {
            val name = nItemView.findViewById<TextView>(R.id.name)
            name.text = nameA
        }
    }
}