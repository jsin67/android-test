package com.jaggrat.sample.presenter

import com.jaggrat.sample.component.DaggerPresenterInjector
import com.jaggrat.sample.component.PresenterInjector
import com.jaggrat.sample.database.ContextModule
import com.jaggrat.sample.database.DaoModule
import com.jaggrat.sample.database.NetworkModule
import com.jaggrat.sample.view.BaseView

/**
 * Base presenter any presenter of the application must extend. It provides initial injections and
 * required methods.
 * @param V the type of the View the presenter is based on
 * @property view the view the presenter is based on
 * @property injector The injector used to inject required dependencies
 * @constructor Injects the required dependencies
 */
abstract class BasePresenter<out V : BaseView>(protected val view: V) {
    private val injector: PresenterInjector = DaggerPresenterInjector
            .builder()
            .baseView(view)
            .contextModule(ContextModule)
            .networkModule(NetworkModule)
            .daoModule(DaoModule)
            .build()

    init {
        inject()
    }

    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated(){}

    open fun reloadData(){}
    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is NewsPostPresenterImp -> injector.inject(this)
        }
    }
}