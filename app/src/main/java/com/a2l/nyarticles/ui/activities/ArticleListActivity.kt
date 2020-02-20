package com.a2l.nyarticles.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.a2l.nyarticles.R
import com.a2l.nyarticles.base.BaseActivity
import com.a2l.nyarticles.common.Constants.Companion.Article_Url
import com.a2l.nyarticles.common.Constants.Companion.DAILY_ARTICLES
import com.a2l.nyarticles.common.Constants.Companion.MONTHLY_ARTICLES
import com.a2l.nyarticles.common.Constants.Companion.WEEKLY_ARTICLES
import com.a2l.nyarticles.common.Failure
import com.a2l.nyarticles.common.Success
import com.a2l.nyarticles.common.exceptions.ExceptionProcessor
import com.a2l.nyarticles.common.exceptions.GeneralException
import com.a2l.nyarticles.common.showDialog
import com.a2l.nyarticles.models.Article
import com.a2l.nyarticles.ui.adapters.ArticlesAdapter
import com.a2l.nyarticles.viewmodels.ArticlesViewModel
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import kotlinx.android.synthetic.main.activity_article_list.*

/**
 * Package Name : com.a2l.nyarticles.ui.activities
 * Created by Asad Ur Rehman on 2/20/2020
 * Copyright (c) 2020 Asad. All rights reserved.
 */


class ArticleListActivity : BaseActivity() {

    private lateinit var viewModel: ArticlesViewModel
    private lateinit var exceptionProcessor: ExceptionProcessor

    private var articles: MutableList<Article> = ArrayList()
    private lateinit var articlesAdapter: ArticlesAdapter

    private lateinit var skeletonArticles: RecyclerViewSkeletonScreen


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)

        exceptionProcessor = ExceptionProcessor(this)

        initRecyclerView()

        viewModel = ViewModelProvider(this).get(ArticlesViewModel::class.java)
        observeMostPopularArticles()

        if (viewModel.data.value == null)
            getMostPopularArticles(section = DAILY_ARTICLES)

        swipeContainer.setOnRefreshListener {
            getMostPopularArticles(section = DAILY_ARTICLES)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.daily -> {
                getMostPopularArticles(DAILY_ARTICLES)
                true
            }

            R.id.weekly -> {
                getMostPopularArticles(WEEKLY_ARTICLES)
                true
            }
            R.id.monthly -> {
                getMostPopularArticles(MONTHLY_ARTICLES)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerView() {

        rvArticles.apply {
            layoutManager = LinearLayoutManager(this@ArticleListActivity)
            articlesAdapter = ArticlesAdapter(
                articles
            ) { article: Article -> itemClicked(article) }

            adapter = articlesAdapter

            skeletonArticles = Skeleton.bind(rvArticles)
                .adapter(articlesAdapter)
                .shimmer(true)
                .angle(10)
                .frozen(false)
                .duration(1200)
                .count(10)
                .color(R.color.light_trans)
                .load(R.layout.shimmer_articles)
                .show()
        }


    }

    private fun observeMostPopularArticles() {
        viewModel.data.observe(this, Observer {
            when (it?.status) {
                is Success -> {
                    Log.e("Success ", "Login")

                    stopRefreshing()
                    skeletonArticles.hide()

                    val status = it.data!!.status
                    if (status == "OK") {
                        articles = it.data.results as MutableList<Article>
                        articlesAdapter.update(articles)

                    } else {
                        //val msg = it.data.message
                        val msg = "Param 'period' is invalid."
                        let {
                            showDialog(
                                it,
                                "",
                                msg.toString()
                            ).show()
                        }
                    }
                }
                is Failure -> {
                    Log.e("Failure ", it.throwable.toString())

                    stopRefreshing()
                    exceptionProcessor.process(it.throwable ?: GeneralException())
                }
            }
        })
    }

    private fun getMostPopularArticles(section: String) {
        skeletonArticles.show()
        viewModel.getMostPopularArticles(section)
    }

    private fun itemClicked(article: Article) {

        if (article.url.isBlank()) return

        val intent = Intent(this, ArticleDetailActivity::class.java)
        intent.putExtra(Article_Url, article.url)
        startActivity(intent)
        //showToast(this, "Clicked: ${article.title}")
    }

    private fun stopRefreshing() {
        if (swipeContainer.isRefreshing) {
            swipeContainer.isRefreshing = false;
        }
    }
}
