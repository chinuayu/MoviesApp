package com.example.samplemovieapp.ui.showAll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.samplemovieapp.data.model.EventObserver
import com.example.samplemovieapp.databinding.FragmentShowAllBinding
import com.example.samplemovieapp.ui.BaseFragment
import com.example.samplemovieapp.util.extension.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowAllFragment : BaseFragment(true) {

    private val args: ShowAllFragmentArgs by navArgs()
    private val viewModel: ShowAllViewModel by viewModels()
    private lateinit var viewDataBinding: FragmentShowAllBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel.setMovieListType(args.movieListTypeArg)
        viewDataBinding = FragmentShowAllBinding.inflate(inflater, container, false)
                .apply {
                    viewmodel = viewModel
                    lifecycleOwner = this@ShowAllFragment.viewLifecycleOwner
                }
        return viewDataBinding.root
    }

    override fun setupViewModelObservers() {
        viewModel.snackBarText.observe(viewLifecycleOwner, EventObserver { view?.showSnackBar(it) })
        viewModel.goToMovieDetailsEvent.observe(
                viewLifecycleOwner,
                EventObserver { navigateToMovieDetails(it.id, it.title) })
    }

    private fun navigateToMovieDetails(movieId: Int, movieTitle: String) {
        val action = ShowAllFragmentDirections.actionShowAllFragmentToMovieDetailsFragment(
                movieId,
                movieTitle
        )
        findNavController().navigate(action)
    }
}