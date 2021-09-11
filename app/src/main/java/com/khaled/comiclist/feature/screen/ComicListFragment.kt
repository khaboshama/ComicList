package com.khaled.comiclist.feature.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.khaled.comiclist.R
import com.khaled.comiclist.common.BaseFragment
import com.khaled.comiclist.common.PagedScrollListener
import com.khaled.comiclist.databinding.FragmentComicListBinding
import kotlinx.android.synthetic.main.fragment_comic_list.*

class ComicListFragment : BaseFragment<ComicViewModel>() {

    private var binding: FragmentComicListBinding? = null
    private var comicsSkeleton: SkeletonScreen? = null
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
        viewModel.showProgressBar.observe(viewLifecycleOwner) {
            paginationProgressBar.visibility = if (it == true) View.VISIBLE else View.GONE
        }
        viewModel.comicList.observe(viewLifecycleOwner) {
            comicsAdapter.submitList(it)
            comicsAdapter.notifyDataSetChanged()
            comicsSkeleton?.hide()
        }
    }

    private fun setupComicRecyclerView() {
        comicRecyclerView.setHasFixedSize(true)
        comicRecyclerView?.addOnScrollListener(object : PagedScrollListener(visibleThreshold = SPAN_COUNT) {
            override fun onLoadMore() {
                viewModel.getNextComics()
            }
        })
        comicRecyclerView.adapter = comicsAdapter
        setupSkeleton()
    }

    private fun setupSkeleton() {
        comicsSkeleton = Skeleton.bind(comicRecyclerView)
            .shimmer(true)
            .adapter(comicsAdapter)
            .count(8)
            .color(R.color.skeleton_background)
            .load(R.layout.item_comic_skeleton)
            .show()
    }

    override val loadingView: View?
        get() = null

    companion object {
        private const val SPAN_COUNT = 2
    }
}