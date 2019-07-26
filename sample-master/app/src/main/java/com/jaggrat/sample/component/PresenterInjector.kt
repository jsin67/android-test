package com.jaggrat.sample.component

import com.jaggrat.sample.database.ContextModule
import com.jaggrat.sample.database.DaoModule
import com.jaggrat.sample.database.NetworkModule
import com.jaggrat.sample.presenter.NewsPostPresenterImp
import com.jaggrat.sample.view.BaseView
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class), (DaoModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param newsPostPresenter in which to inject the dependencies
     */
    fun inject(newsPostPresenter: NewsPostPresenterImp)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder
        fun daoModule(daoModule: DaoModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}