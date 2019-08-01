package com.uttampanchasara.baseprojectkotlin.ui.splash

import android.content.Intent
import android.os.Bundle
import com.uttampanchasara.baseprojectkotlin.R
import com.uttampanchasara.baseprojectkotlin.di.component.ActivityComponent
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseActivity
import com.uttampanchasara.baseprojectkotlin.ui.dashboard.DashboardActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashView {
    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    @Inject
    lateinit var mPresenter: SplashPresenter

    override fun injectComponents(mActivityComponent: ActivityComponent) {
        mActivityComponent.inject(this)
    }

    override fun setUp(savedInstanceState: Bundle?) {
        mPresenter.onAttachView(this)
        mPresenter.navigateToDashboard()
    }

    override fun navigateToDashboard() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }
}