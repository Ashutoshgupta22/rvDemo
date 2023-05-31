package com.aspark.rvdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aspark.rvdemo.databinding.ItemImageBinding
import com.bumptech.glide.Glide

class ImagesAdapter(private val list: ArrayList<String>):
    RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    private lateinit var binding: ItemImageBinding

    class ViewHolder(binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

        val imageView = binding.ivImage

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.itemView)
            .load(list[position])
            .centerCrop()
            .into(holder.imageView)

    }
}