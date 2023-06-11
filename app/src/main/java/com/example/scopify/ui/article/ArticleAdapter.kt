package com.example.scopify.ui.article

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.scopify.R
import com.example.scopify.databinding.ItemArticleBinding
import com.example.scopify.domain.entity.Article
import com.squareup.picasso.Picasso
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ArticleAdapter: RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private val listData = ArrayList<Article>()
    var onItemClick: ((Article) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Article>) {
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
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listData.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    inner class ViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(data: Article) {
            with(binding) {
                tvSource.text = data.author
                tvTitle.text = data.title

                val dateTime = ZonedDateTime.parse(data.date)
                val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
                val convertedDateTime = formatter.format(dateTime)
                tvDate.text = convertedDateTime

                Picasso.get()
                    .load(data.urlToImage)
                    .resize(300, 300)
                    .placeholder(R.drawable.ic_round_newspaper_24)
                    .centerCrop()
                    .into(imgArticle)
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}