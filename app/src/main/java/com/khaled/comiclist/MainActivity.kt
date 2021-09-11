package com.khaled.comiclist

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.khaled.comiclist.common.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigationGraph()
    }

    private fun setupNavigationGraph() {
        navController = findNavController(R.id.mainNavHostFragment)
        mainBottomNavigationView.setOnNavigationItemReselectedListener {}
        NavigationUI.setupWithNavController(mainBottomNavigationView, navController)
    }

    override val loadingView: View?
        get() = null

    override fun getCurrentActivity() = this
}