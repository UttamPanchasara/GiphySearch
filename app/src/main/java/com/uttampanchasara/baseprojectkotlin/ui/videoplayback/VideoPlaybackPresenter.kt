package com.uttampanchasara.baseprojectkotlin.ui.videoplayback

import com.uttampanchasara.baseprojectkotlin.BasePresenter
import com.uttampanchasara.baseprojectkotlin.data.DataManager
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseView
import com.uttampanchasara.baseprojectkotlin.utils.rx.SchedulerProvider
import io.objectbox.android.AndroidScheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class VideoPlaybackPresenter
@Inject constructor(mDataManager: DataManager,
                    mSchedulerProvider: SchedulerProvider,
                    mCompositeDisposable: CompositeDisposable) : BasePresenter(mDataManager, mSchedulerProvider, mCompositeDisposable) {

    private var mView: VideoPlaybackView? = null

    override fun onAttachView(view: BaseView) {
        mView = view as VideoPlaybackView
    }

    fun updateVotes(videoId: String, upVote: Int, downVote: Int) {

        val disposable = mDataManager.getUpVote(videoId)
                .doOnError {
                    mDataManager.updateVotes(0, videoId, upVote, downVote).subscribe()
                }
                .map {
                    return@map mDataManager.updateVotes(it.id, videoId, upVote, downVote).subscribe()
                }
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe({
                    mView?.onVotesUpdated()
                }, {
                    it.printStackTrace()
                })

        mCompositeDisposable.add(disposable)
    }

    fun observeVotes(videoId: String) {
        mDataManager.observeVotes(videoId)
                .subscribe()
                .on(AndroidScheduler.mainThread())
                .observer {
                    mView?.onVotesAvailable(it)
                }
    }
}