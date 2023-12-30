package com.churickoid.dynamicStringsSample.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.churickoid.dynamicStringsSample.R

class TestAdapter(private val list: Array<Int>) :
    RecyclerView.Adapter<TestAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_recycler)
        val textView: TextView = view.findViewById(R.id.text_recycler)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_test2, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(R.drawable.ic_launcher_background)
        holder.textView.text = position.toString()
    }

    override fun getItemCount() = list.size


}