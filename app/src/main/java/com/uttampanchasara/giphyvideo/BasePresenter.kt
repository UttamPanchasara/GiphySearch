package com.uttampanchasara.giphyvideo

import android.arch.lifecycle.ViewModel
import com.uttampanchasara.giphyvideo.data.DataManager
import com.uttampanchasara.giphyvideo.ui.base.BaseView
import com.uttampanchasara.giphyvideo.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Uttam Panchasara on 11-28-2018.
 */
open class BasePresenter(val mDataManager: DataManager,
                         val mSchedulerProvider: SchedulerProvider,
                         val mCompositeDisposable: CompositeDisposable) : ViewModel() {

    var view: BaseView? = null

    open fun isViewAttached(): Boolean {
        return view != null
    }

    open fun onAttachView(view: BaseView) {
        this.view = view
    }

    fun onDetachView() {
        mCompositeDisposable.dispose()
        view = null
    }

    open fun getBaseView(): BaseView? {
        return view
    }
}