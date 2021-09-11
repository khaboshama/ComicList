package com.khaled.comiclist.feature.details.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.khaled.comiclist.common.BaseFragment
import com.khaled.comiclist.databinding.FragmentComicDetailsBinding
import com.khaled.comiclist.feature.details.ComicDetailsViewModel
import kotlinx.android.synthetic.main.fragment_comic_details.*

class ComicDetailsFragment : BaseFragment<ComicDetailsViewModel>() {

    private val args by navArgs<ComicDetailsFragmentArgs>()
    private var binding: FragmentComicDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentComicDetailsBinding.inflate(inflater, container, false).also { binding = it }.root

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupComicItemDataViews()
        icArrowBackImageView.setOnClickListener { activity?.onBackPressed() }
    }

    private fun setupComicItemDataViews() {
        titleTextView.text = args.comicItem.title
        numberTextView.text = args.comicItem.number.toString()
        Glide.with(requireContext()).load(args.comicItem.imageUrl).into(ic_ImageView)
        descriptionTextView.text = args.comicItem.description
    }

    override val loadingView: View?
        get() = null

}