package com.example.samplemovieapp.ui.shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.samplemovieapp.data.model.EventObserver
import com.example.samplemovieapp.databinding.FragmentTvShowsBinding
import com.example.samplemovieapp.ui.BaseFragment
import com.example.samplemovieapp.util.extension.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowsFragment : BaseFragment(false) {

    private val viewModel: TvShowsViewModel by viewModels()
    private lateinit var viewDataBinding: FragmentTvShowsBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewDataBinding =
                FragmentTvShowsBinding.inflate(inflater, container, false)
                        .apply {
                            viewmodel = viewModel
                            lifecycleOwner = this@TvShowsFragment.viewLifecycleOwner
                        }
        return viewDataBinding.root
    }

    override fun setupViewModelObservers() {
        viewModel.snackBarText.observe(viewLifecycleOwner, EventObserver { view?.showSnackBar(it) })
        viewModel.goToTvShowEvent.observe(
                viewLifecycleOwner,
                EventObserver { navigateToTvShowDetails(it.id, it.title) })
    }

    private fun navigateToTvShowDetails(tvShowId: Int, tvShowTitle: String) {
        val action = TvShowsFragmentDirections.actionNavigationTvShowsToTVShowDetailsFragment(
                tvShowId, tvShowTitle
        )
        findNavController().navigate(action)
    }
}