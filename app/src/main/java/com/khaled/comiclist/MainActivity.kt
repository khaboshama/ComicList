package com.khaled.comiclist

import android.os.Bundle
import android.view.View
import com.khaled.comiclist.common.BaseActivity

class MainActivity : BaseActivity<MainViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override val loadingView: View?
        get() = null

    override fun getCurrentActivity() = this
}