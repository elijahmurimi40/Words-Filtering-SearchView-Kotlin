package com.fortie40.wordsfiltering

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.*

class MainActivityAdapter(names: List<String>):
    RecyclerView.Adapter<MainActivityAdapter.MainActivityViewHolder>(), Filterable {

    private var originalList: List<String> = names
    private var mFilteredList: List<String> = names
    var string: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.name_layout, parent, false)
        return MainActivityViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mFilteredList.size
    }

    override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
        val name = mFilteredList[position]
        holder.bind(name)
    }

    override fun getFilter(): Filter {
        return this.filter
    }

    private val filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults = runBlocking {
            val chaString = constraint.toString()

            mFilteredList = if (chaString.isEmpty()) {
                originalList
            } else {
                delay(3000)
                val filteredList = originalList
                    .filter { it.toLowerCase(Locale.getDefault()).contains(chaString) }
                    .toMutableList()
                Log.i("adapter", "********")
                Log.i("adapter", "filtered")
                filteredList
            }
            val filterResults = FilterResults()
            filterResults.values = mFilteredList
            return@runBlocking filterResults
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            mFilteredList = results!!.values as List<String>
            notifyDataSetChanged()
        }
    }

    class MainActivityViewHolder(private val nItemView: View): RecyclerView.ViewHolder(nItemView) {

        fun bind(nameA: String) {
            val name = nItemView.findViewById<TextView>(R.id.name)
            name.text = nameA
        }
    }
}