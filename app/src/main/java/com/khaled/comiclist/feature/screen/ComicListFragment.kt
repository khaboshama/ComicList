package com.khaled.comiclist.feature.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khaled.comiclist.common.BaseFragment
import com.khaled.comiclist.databinding.FragmentComicListBinding
import kotlinx.android.synthetic.main.loading_layout.*

class ComicListFragment : BaseFragment<ComicViewModel>() {

    private var binding: FragmentComicListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getNextComics()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentComicListBinding.inflate(inflater, container, false).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.comicList.observe(viewLifecycleOwner) {

        }
    }

    override val loadingView: View?
        get() = loadingProgressBar
}