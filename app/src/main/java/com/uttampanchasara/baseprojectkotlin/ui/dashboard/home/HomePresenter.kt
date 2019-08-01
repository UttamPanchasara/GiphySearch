package com.uttampanchasara.baseprojectkotlin.ui.dashboard.home

import android.util.Log
import com.androidnetworking.error.ANError
import com.uttampanchasara.baseprojectkotlin.BasePresenter
import com.uttampanchasara.baseprojectkotlin.R
import com.uttampanchasara.baseprojectkotlin.data.DataManager
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseView
import com.uttampanchasara.baseprojectkotlin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomePresenter
@Inject constructor(mDataManager: DataManager,
                    mSchedulerProvider: SchedulerProvider,
                    mCompositeDisposable: CompositeDisposable) : BasePresenter(mDataManager, mSchedulerProvider, mCompositeDisposable) {
    private var mView: HomeView? = null

    override fun onAttachView(view: BaseView) {
        mView = view as HomeView
    }

    fun getGiphyVideos(query: String, offset: Int) {
        mView?.showLoading()
        val disposable = mDataManager.getGiphyVideos(query, offset)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe({
                    mView?.hideLoading()
                    mView?.onGetVideos(it.data)
                }, {
                    Log.e("HomePresenter", "MSG: " + (it as ANError).errorBody)
                    mView?.hideLoading()
                    mView?.onError(R.string.something_wrong)
                })

        mCompositeDisposable.add(disposable)
    }
}