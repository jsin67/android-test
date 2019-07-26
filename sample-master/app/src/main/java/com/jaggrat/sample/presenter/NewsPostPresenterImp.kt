package com.jaggrat.sample.presenter

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.util.Log
import com.jaggrat.sample.database.NewsPostDao
import com.jaggrat.sample.model.NewsPostBase
import com.jaggrat.sample.model.NewsPostHorizontal
import com.jaggrat.sample.model.NewsPostVertical
import com.jaggrat.sample.network.NewsPostApi
import com.jaggrat.sample.view.NewsPostListView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class NewsPostPresenterImp(postView: NewsPostListView) : BasePresenter<NewsPostListView>(postView){
    @Inject
    lateinit var newsPostApi: NewsPostApi
    @Inject
    lateinit var postDao: NewsPostDao

    private var subscription: Disposable = CompositeDisposable()

    private val mutablePostList: MutableLiveData<List<NewsPostVertical>> = MutableLiveData()

    override fun onViewCreated() {
        val postListObserver = Observer<List<NewsPostVertical>> { postList ->
            if (postList != null) view.updateUI(processNewsToTemplate(postList))
        }

        mutablePostList.observe(view, postListObserver)
        loadPosts(true)
    }

    /**
     * Creates data for UI.
     * @param posts : List of all the items
     * @return : returns list of data for ui.
     */
    private fun processNewsToTemplate(posts: List<NewsPostVertical>): List<NewsPostBase>{
        val uiNewsPost = mutableListOf<NewsPostBase>()
        var buildingFeatured = false
        val loop = posts.listIterator()
        while (loop.hasNext()) {
            val list = mutableListOf<NewsPostVertical>().apply {
                add(loop.next())
                if (loop.hasNext()) add(loop.next())
                if (loop.hasNext()) add(loop.next())
            }
            if (buildingFeatured) uiNewsPost.add(NewsPostHorizontal(list)) else uiNewsPost.addAll(list)
            buildingFeatured = buildingFeatured.not()
        }
        return uiNewsPost
    }

    /**
     * Loads the news post from api or database. First it checks values in database, otherwise makes a network call.
     */
    private fun loadPosts(isLoadDataFromDatabase: Boolean) {
        view.showLoading()
        subscription =  Observable.fromCallable { postDao.all }
                .flatMap { postList -> if (isLoadDataFromDatabase && postList.isNotEmpty()) Observable.just(postList) else saveApiTransactionsInDatabase() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe { postList -> mutablePostList.value = postList }
    }

    /**
     * Load the posts from the API and store them in the database.
     * @return an Observable for the list of Post retrieved from API
     */
    private fun saveApiTransactionsInDatabase(): Observable<List<NewsPostVertical>> {

        return newsPostApi.getNewsPost()
                .flatMap {
                    postList -> Observable.fromCallable {
                    //printPost(postList.content)
                    postDao.deleteAll()
                    postDao.insertAll(postList.content)
                    postDao.all
                }
                }
    }

    override fun onViewDestroyed() {
        subscription.dispose()
    }

    override fun reloadData() {
        view.showLoading()
        val postListObserver = Observer<List<NewsPostVertical>> { postList ->
            if (postList != null) view.updateUI(processNewsToTemplate(postList))
        }

        mutablePostList.observe(view, postListObserver)
        loadPosts(false)
    }

    @Deprecated("Use Log.d(list)", level = DeprecationLevel.HIDDEN)
    fun printPost(list :List<NewsPostVertical>) {
        list.forEach {
            item -> Log.d("jaggrat item -", item.images.mainImageThumbnail.url)
        }
    }

}
