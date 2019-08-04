package com.uttampanchasara.giphyvideo.ui.dashboard.home

import com.uttampanchasara.giphyvideo.BasePresenter
import com.uttampanchasara.giphyvideo.R
import com.uttampanchasara.giphyvideo.data.DataManager
import com.uttampanchasara.giphyvideo.ui.base.BaseView
import com.uttampanchasara.giphyvideo.utils.rx.SchedulerProvider
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
        val disposable = mDataManager.getGiphyVideos(query, offset)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe({
                    mView?.onGetVideos(it.data)
                }, {
                    mView?.onGetVideos(emptyList())
                    mView?.onError(R.string.something_wrong)
                })

        mCompositeDisposable.add(disposable)
    }
}