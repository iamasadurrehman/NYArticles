package com.a2l.nyarticles.ui.activities

import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.a2l.nyarticles.R
import com.a2l.nyarticles.base.BaseActivity
import com.a2l.nyarticles.common.Constants.Companion.Article_Url
import kotlinx.android.synthetic.main.activity_article_detail.*

/**
 * Package Name : com.a2l.nyarticles.ui.activities
 * Created by Asad Ur Rehman on 2/20/2020
 * Copyright (c) 2020 Asad. All rights reserved.
 */

class ArticleDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {

            val url = bundle.getString(Article_Url)!!
            setupWebView(url)

        } else {
            finish()
        }


    }

    private fun setupWebView(url: String) {

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        wvArticleDetail.setWebViewClient(webViewClient())
        wvArticleDetail.getSettings().setLoadsImagesAutomatically(true)
        wvArticleDetail.getSettings().setJavaScriptEnabled(true)
        wvArticleDetail.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)
        wvArticleDetail.loadUrl(url)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    class webViewClient : WebViewClient() {
        override fun onPageStarted(
            view: WebView?,
            url: String?,
            favicon: Bitmap?
        ) { // TODO Auto-generated method stubsuper.onPageStarted(view, url, favicon);
        }

        override fun shouldOverrideUrlLoading(
            view: WebView,
            url: String?
        ): Boolean { // TODO Auto-generated method stub
            view.loadUrl(url)
            return true
        }
    }
}
