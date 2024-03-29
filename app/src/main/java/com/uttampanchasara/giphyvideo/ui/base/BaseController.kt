package com.uttampanchasara.giphyvideo.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.bluelinelabs.conductor.Controller
import com.uttampanchasara.giphyvideo.di.component.ActivityComponent

abstract class BaseController : Controller(), BaseView {

    override fun onAttachView(v: BaseView) {
    }

    override fun onDetachView() {
    }

    override fun hideLoading() {
        mActivity?.hideLoading()
    }

    override fun showLoading() {
        mActivity?.showLoading()
    }

    override fun onUnAuthorizedAccess() {
        mActivity?.onUnAuthorizedAccess()
    }

    abstract fun getLayoutId(): Int

    private var mActivity: BaseActivity? = null

    abstract fun setupView(view: View)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        if (getLayoutId() == 0) {
            throw RuntimeException("Invalid Layout ID")
        }
        val view = inflater.inflate(getLayoutId(), container, false)
        ButterKnife.bind(this, view)

        if (activity is BaseActivity) {
            val activity = activity as BaseActivity
            this.mActivity = activity
            activity.onFragmentAttached()
        }

        setupView(view)
        return view
    }

    fun hideKeyboard(view: View?) {
        mActivity?.hideKeyboard(view)
    }

    override fun onError(error: String) {
        mActivity?.onError(error)
    }

    override fun onError(error: Int) {
        mActivity?.onError(error)
    }

    fun getActivityComponent(): ActivityComponent? {
        return mActivity?.getActivityComponent()
    }
}