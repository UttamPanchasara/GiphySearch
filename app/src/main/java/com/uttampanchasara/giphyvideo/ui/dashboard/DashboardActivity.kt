package com.uttampanchasara.giphyvideo.ui.dashboard

import android.os.Bundle
import android.view.Menu
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.RouterTransaction
import com.uttampanchasara.giphyvideo.R
import com.uttampanchasara.giphyvideo.di.component.ActivityComponent
import com.uttampanchasara.giphyvideo.ui.base.BaseActivity
import com.uttampanchasara.giphyvideo.ui.dashboard.home.HomeController
import kotlinx.android.synthetic.main.activity_dashboard.*
import javax.inject.Inject


class DashboardActivity : BaseActivity(), DashboardView {
    override fun getLayout(): Int {
        return R.layout.activity_dashboard
    }

    @Inject
    lateinit var mPresenter: DashboardPresenter

    override fun injectComponents(mActivityComponent: ActivityComponent) {
        mActivityComponent.inject(this)
    }

    override fun setUp(savedInstanceState: Bundle?) {
        setToolbar(toolbar, getString(R.string.app_name), false)

        val router = Conductor.attachRouter(this, container, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(HomeController()))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }
}