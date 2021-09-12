package com.khaled.comiclist.common

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_search_list.*
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.koin.getViewModel

abstract class BaseFragment<ViewModel : BaseViewModel> : Fragment(), BaseView<ViewModel> {
    override val viewModel by lazy {
        getKoin().getViewModel(
            owner = { ViewModelOwner.from(this) },
            clazz = viewModelClass()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.error.observe(viewLifecycleOwner, { it?.let { showErrorMessage(it) } })
        viewModel.showLoading.observe(viewLifecycleOwner, { it?.let { if (it) showLoading() else hideLoading() } })
    }

    override fun getCurrentActivity(): Activity = requireActivity()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val inputMethodManager: InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    protected fun showKeyboard() {
        searchEditText.requestFocus()
        val inputMethodManager: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT)
    }

    protected fun isDestinationFound(currentFragmentId: Int) =
        findNavController().currentDestination?.id == currentFragmentId

}