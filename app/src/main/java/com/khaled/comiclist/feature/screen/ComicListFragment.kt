package com.khaled.comiclist.feature.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khaled.comiclist.common.BaseFragment
import com.khaled.comiclist.databinding.FragmentComicListBinding
import kotlinx.android.synthetic.main.fragment_comic_list.*
import kotlinx.android.synthetic.main.loading_layout.*

class ComicListFragment : BaseFragment<ComicViewModel>() {

    private var binding: FragmentComicListBinding? = null
    private val comicsAdapter = ComicsAdapter {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getNextComics()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentComicListBinding.inflate(inflater, container, false).also { binding = it }.root

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupComicRecyclerView()
        viewModel.comicList.observe(viewLifecycleOwner) {
            comicsAdapter.submitList(it)
            comicsAdapter.notifyDataSetChanged()
        }
    }

    private fun setupComicRecyclerView() {
        comicRecyclerView.setHasFixedSize(true)
        comicRecyclerView.adapter = comicsAdapter
    }

    override val loadingView: View?
        get() = loadingContainer
}