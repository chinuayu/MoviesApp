package com.example.samplemovieapp.ui.personDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.samplemovieapp.data.db.remote.TheMovieDatabaseAPI
import com.example.samplemovieapp.data.model.EventObserver
import com.example.samplemovieapp.databinding.FragmentPersonDetailsBinding
import com.example.samplemovieapp.ui.BaseFragment
import com.example.samplemovieapp.util.extension.openUrl
import com.example.samplemovieapp.util.extension.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonDetailsFragment : BaseFragment(true) {

    private val args: PersonDetailsFragmentArgs by navArgs()
    private val viewModel: PersonDetailsViewModel by viewModels()
    private lateinit var viewDataBinding: FragmentPersonDetailsBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel.setPersonId(args.personIdArg)
        viewDataBinding =
                FragmentPersonDetailsBinding.inflate(inflater, container, false)
                        .apply {
                            viewmodel = viewModel
                            lifecycleOwner = this@PersonDetailsFragment.viewLifecycleOwner
                        }
        return viewDataBinding.root
    }

    override fun setupViewModelObservers() {
        viewModel.snackBarText.observe(viewLifecycleOwner, EventObserver { view?.showSnackBar(it) })
        viewModel.goToImageEvent.observe(
                viewLifecycleOwner,
                EventObserver { openUrl(TheMovieDatabaseAPI.getProfileUrl(it.filePath)) })

        viewModel.goToCreditEvent.observe(viewLifecycleOwner, EventObserver {
            if (it.mediaType == "movie") {
                navigateToMovieDetails(it.id, it.title)
            } else if (it.mediaType == "tv") {
                navigateToTvShowDetails(it.id, it.title)
            }
        })
    }

    private fun navigateToMovieDetails(id: Int, title: String) {
        val action =
                PersonDetailsFragmentDirections.actionPersonDetailsFragmentToMovieDetailsFragment(
                        id,
                        title
                )
        findNavController().navigate(action)
    }

    private fun navigateToTvShowDetails(id: Int, title: String) {
        val action =
                PersonDetailsFragmentDirections.actionPersonDetailsFragmentToTvShowDetailsFragment(
                        id,
                        title
                )
        findNavController().navigate(action)
    }
}