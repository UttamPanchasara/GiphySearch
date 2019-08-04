package com.uttampanchasara.giphyvideo.ui.dashboard

import com.uttampanchasara.giphyvideo.BasePresenter
import com.uttampanchasara.giphyvideo.data.DataManager
import com.uttampanchasara.giphyvideo.ui.base.BaseView
import com.uttampanchasara.giphyvideo.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 *
 * @author <a href="https://github.com/UttamPanchasara">Uttam Panchasara</a>
 * @since 11/16/2018
 */
class DashboardPresenter
@Inject constructor(mDataManager: DataManager,
                    mSchedulerProvider: SchedulerProvider,
                    mCompositeDisposable: CompositeDisposable) : BasePresenter(mDataManager, mSchedulerProvider, mCompositeDisposable) {
    override fun onAttachView(view: BaseView) {
    }
}