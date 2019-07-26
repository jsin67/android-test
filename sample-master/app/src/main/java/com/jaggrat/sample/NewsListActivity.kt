package com.jaggrat.sample

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.jaggrat.sample.listener.NewsPostListener
import com.jaggrat.sample.model.NewsPostBase
import com.jaggrat.sample.model.NewsPostVertical
import com.jaggrat.sample.model.NewsPostViewModel
import com.jaggrat.sample.presenter.NewsPostPresenterImp
import com.jaggrat.sample.ui.NewsAdapter
import com.jaggrat.sample.view.NewsPostListView
import kotlinx.android.synthetic.main.activity_transaction_list.*

/**
 * Lists all the news post on UI.
 */
class NewsListActivity :  BaseActivity<NewsPostPresenterImp>(), NewsPostListView, NewsPostListener {
    private lateinit var progressBar: ProgressBar
    private val adapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_list)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        news_list.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
            it.addItemDecoration(DividerItemDecoration(it.context, RecyclerView.VERTICAL))
        }
        progressBar = this.loading
        presenter.onViewCreated()
    }

    /**
     * Update UI with new data.
     * @param newsPost: NewsPost data
     */
    override fun updateUI(newsPost: List<NewsPostBase>) {
        adapter.setNewsUIData(newsPost)
    }

    /**
     * Show detail of news post.
     * @param newsPost: NewsPost data
     */
    override fun showDetail(newsPost: List<NewsPostVertical>) {

    }

    /**
     * Instantiate the presenter
     */
    override fun instantiatePresenter(): NewsPostPresenterImp {
        return NewsPostPresenterImp(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    /**
     * Shows error with the error message.
     * @param error: Error message.
     */
    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    /**
     * Shows loading on UI.
     */
    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    /**
     * Hides loading on UI.
     */
    override fun hideLoading() {
        progressBar.visibility = View.GONE

    }

    /**
     * Handles news click listener
     * @param model: Model data.
     */
    override fun onNewsPostTapped(model: NewsPostViewModel) {
        Toast.makeText(this, model.summary, Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater =  menuInflater
        inflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_reload -> {
            presenter.reloadData()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

}
