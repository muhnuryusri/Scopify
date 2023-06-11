package com.example.scopify.ui.home.source

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scopify.databinding.ItemSourceBinding
import com.example.scopify.domain.entity.Source

class SourceAdapter: RecyclerView.Adapter<SourceAdapter.ViewHolder>() {
    private val listData = ArrayList<Source>()
    var onItemClick: ((Source) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Source>) {
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearData() {
        listData.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSourceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    inner class ViewHolder(private val binding: ItemSourceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Source) {
            with(binding) {
                tvSource.text = data.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}