package com.uttampanchasara.giphyvideo.ui.splash

import com.uttampanchasara.giphyvideo.BasePresenter
import com.uttampanchasara.giphyvideo.data.DataManager
import com.uttampanchasara.giphyvideo.ui.base.BaseView
import com.uttampanchasara.giphyvideo.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 *
 * @author <a href="https://github.com/UttamPanchasara">Uttam Panchasara</a>
 * @since 11/20/2018
 */
class SplashPresenter
@Inject constructor(mDataManager: DataManager,
                    mSchedulerProvider: SchedulerProvider,
                    mCompositeDisposable: CompositeDisposable) : BasePresenter(mDataManager, mSchedulerProvider, mCompositeDisposable) {

    var mView: SplashView? = null

    override fun onAttachView(view: BaseView) {
        super.onAttachView(view)
        mView = view as SplashView
    }

    fun navigateToDashboard() {
        mView?.navigateToDashboard()
    }
}