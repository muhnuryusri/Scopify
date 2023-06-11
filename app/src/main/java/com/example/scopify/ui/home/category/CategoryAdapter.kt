package com.example.scopify.ui.home.category

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.scopify.R
import com.example.scopify.databinding.ItemCategoryBinding
import com.example.scopify.domain.entity.Category

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private val listData = ArrayList<Category>()
    private var selectedCategory: Category? = null
    private var selectedItemPosition: Int = RecyclerView.NO_POSITION
    var onItemClick: ((Category) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Category>) {
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listData[position]

        val backgroundRes = if (position == selectedItemPosition) {
            R.drawable.custom_category_item_selected
        } else {
            R.drawable.custom_category_item
        }
        holder.itemView.setBackgroundResource(backgroundRes)

        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(data: Category) {
            with(binding) {
                tvCategory.text = data.category
                val textColorRes = if (adapterPosition == selectedItemPosition) {
                    R.color.white
                } else {
                    R.color.gray_400
                }
                tvCategory.setTextColor(ContextCompat.getColor(itemView.context, textColorRes))
                itemView.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        selectedItemPosition = position
                        selectedCategory = data
                        notifyDataSetChanged()
                        onItemClick?.invoke(data)
                    }
                }
            }
        }

    }
}