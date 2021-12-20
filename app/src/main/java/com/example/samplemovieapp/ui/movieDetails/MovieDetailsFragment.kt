package com.example.samplemovieapp.ui.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.samplemovieapp.data.db.remote.TheMovieDatabaseAPI
import com.example.samplemovieapp.data.model.EventObserver
import com.example.samplemovieapp.databinding.FragmentMovieDetailsBinding
import com.example.samplemovieapp.ui.BaseFragment
import com.example.samplemovieapp.util.extension.openUrl
import com.example.samplemovieapp.util.extension.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment(true) {

    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var viewDataBinding: FragmentMovieDetailsBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel.setMovieId(args.movieIdArg)
        viewDataBinding =
                FragmentMovieDetailsBinding.inflate(inflater, container, false)
                        .apply {
                            viewmodel = viewModel
                            lifecycleOwner = this@MovieDetailsFragment.viewLifecycleOwner
                        }
        return viewDataBinding.root
    }

    override fun setupViewModelObservers() {
        viewModel.snackBarText.observe(viewLifecycleOwner, EventObserver { view?.showSnackBar(it) })
        viewModel.goToCastDetailsEvent.observe(
                viewLifecycleOwner,
                EventObserver { navigateToPersonDetails(it.id, it.name) })
        viewModel.goToVideoEvent.observe(
                viewLifecycleOwner,
                EventObserver { openUrl(TheMovieDatabaseAPI.getYoutubeWatchUrl(it.key)) })
    }

    private fun navigateToPersonDetails(personId: Int, personName: String) {
        val action =
                MovieDetailsFragmentDirections.actionMovieDetailsFragmentToPersonDetailsFragment(
                        personId,
                        personName
                )
        findNavController().navigate(action)
    }
}