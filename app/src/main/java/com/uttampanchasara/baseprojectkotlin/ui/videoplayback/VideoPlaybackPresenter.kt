package com.uttampanchasara.baseprojectkotlin.ui.videoplayback

import com.uttampanchasara.baseprojectkotlin.BasePresenter
import com.uttampanchasara.baseprojectkotlin.data.DataManager
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseView
import com.uttampanchasara.baseprojectkotlin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class VideoPlaybackPresenter
@Inject constructor(mDataManager: DataManager,
                    mSchedulerProvider: SchedulerProvider,
                    mCompositeDisposable: CompositeDisposable) : BasePresenter(mDataManager, mSchedulerProvider, mCompositeDisposable) {
    override fun onAttachView(view: BaseView) {
    }
}