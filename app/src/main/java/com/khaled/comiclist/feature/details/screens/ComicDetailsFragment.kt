package com.khaled.comiclist.feature.details.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.khaled.comiclist.R
import com.khaled.comiclist.common.BaseFragment
import com.khaled.comiclist.databinding.FragmentComicDetailsBinding
import com.khaled.comiclist.feature.details.ComicDetailsViewModel
import com.khaled.comiclist.utils.ShareUtils.shareTextUrl
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
        favoriteImageView.setOnClickListener { viewModel.onFavoriteClicked() }
        shareImageView.setOnClickListener { viewModel.onShareButtonClicked() }
        viewModel.shareLiveData.observe(viewLifecycleOwner) { shareTextUrl(getCurrentActivity(), it!!) }
        viewModel.comicItemLiveData.observe(viewLifecycleOwner) {
            titleTextView.text = it.title
            numberTextView.text = it.number.toString()
            Glide.with(requireContext()).load(it.imageUrl).into(ic_ImageView)
            descriptionTextView.text = it.description
            favoriteImageView.setImageResource(
                if (it.isFavorite) R.drawable.ic_favorite_selected else R.drawable.ic_favorite_unselected
            )
        }
    }

    private fun setupComicItemDataViews() {
        viewModel.setComicItem(args.comicItem)
    }

    override val loadingView: View?
        get() = null

}