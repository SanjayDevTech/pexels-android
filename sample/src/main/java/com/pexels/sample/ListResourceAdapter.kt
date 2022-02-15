package com.pexels.sample

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pexels.sample.databinding.LayoutItemBinding
import com.pexels.sample.model.ViewItem

class ListResourceAdapter(
    private val onClick: (item: ViewItem) -> Unit,
) : RecyclerView.Adapter<ListResourceAdapter.ResourceViewHolder>() {

    private val colorMap = mutableMapOf<String, ColorDrawable>()

    var items: List<ViewItem> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ResourceViewHolder(
        private val binding: LayoutItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ViewItem) {
            if (item.color !in colorMap && item.color != null) {
                colorMap[item.color] = ColorDrawable(Color.parseColor(item.color))
            }
            binding.imageView.load(item.url) {
                crossfade(true)
                item.color?.let { placeholder(colorMap[item.color]) }
            }
            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemBinding.inflate(
            inflater, parent, false,
        )
        return ResourceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        holder.bind(item = items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}