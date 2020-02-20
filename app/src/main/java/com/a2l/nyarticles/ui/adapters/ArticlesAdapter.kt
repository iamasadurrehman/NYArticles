package com.a2l.nyarticles.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.a2l.nyarticles.R
import com.a2l.nyarticles.common.ext.loadImage
import com.a2l.nyarticles.databinding.ItemArticleBinding
import com.a2l.nyarticles.models.Article
import kotlinx.android.synthetic.main.item_article.view.*

/**
 * Package Name : com.a2l.nyarticles.ui.adapters
 * Created by Asad Ur Rehman on 2/20/2020
 * Copyright (c) 2020 Asad. All rights reserved.
 */

class ArticlesAdapter(
    private var articles: MutableList<Article>,
    private var onItemClick: (Article) -> Unit
) : RecyclerView.Adapter<ArticlesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemBinding: ItemArticleBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_article, parent, false)
        return ArticlesViewHolder(itemBinding)
    }

    fun update(articleList: List<Article>) {
        articles = articleList.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val article: Article = articles[position]
        holder.bind(article, onItemClick)
    }

}

class ArticlesViewHolder(val binding: ItemArticleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        article: Article,
        onItemClick: (Article) -> Unit
    ) {

        binding.article = article
        binding.root.ivAvatar.loadImage(article.media[0].mediaMetadata[0].url)
        binding.root.setOnClickListener { onItemClick(article) }
    }

}
