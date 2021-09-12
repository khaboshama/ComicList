package com.khaled.comiclist.feature.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.khaled.comiclist.R
import com.khaled.comiclist.common.BaseFragment
import com.khaled.comiclist.common.PagedScrollListener
import com.khaled.comiclist.databinding.FragmentSearchListBinding
import com.khaled.comiclist.feature.comicList.screens.ComicsAdapter
import kotlinx.android.synthetic.main.fragment_search_list.*


class SearchFragment : BaseFragment<SearchViewModel>() {

    private var binding: FragmentSearchListBinding? = null
    private val comicsAdapter = ComicsAdapter {
        if (isDestinationFound(R.id.searchFragment)) {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToComicDetailsFragment(it))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSearchListBinding.inflate(inflater, container, false).also { binding = it }.root

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        icArrowBackImageView.setOnClickListener { activity?.onBackPressed() }
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.onSearchChanged(s.toString())
            }

        })
        setupComicRecyclerView()
        viewModel.comicList.observe(viewLifecycleOwner) {
            comicsAdapter.submitList(it)
            comicsAdapter.notifyDataSetChanged()
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
    }

    override fun onResume() {
        super.onResume()
        showKeyboard()
    }

    override val loadingView: View?
        get() = null

    companion object {
        private const val SPAN_COUNT = 2
    }
}