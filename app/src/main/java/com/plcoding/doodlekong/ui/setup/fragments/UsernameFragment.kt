package com.plcoding.doodlekong.ui.setup.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.plcoding.doodlekong.R
import com.plcoding.doodlekong.databinding.FragmentUsernameBinding
import com.plcoding.doodlekong.ui.setup.SetupViewModel
import com.plcoding.doodlekong.util.snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class UsernameFragment: Fragment() {

    private var _binding: FragmentUsernameBinding? = null
    private val binding: FragmentUsernameBinding
        get() = _binding!!

    private val viewModel: SetupViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUsernameBinding.bind(view)


        listenToEvents()
        binding.btnNext.setOnClickListener {
            viewModel.validateUsernameAndNavigateToSelectRoom(
                binding.etUsername.text.toString()
            )
        }
    }

    private fun listenToEvents() {
        lifecycleScope.launchWhenCreated {
            viewModel.setupEvent.collect { event ->
                when(event) {
                    is SetupViewModel.SetupEvent.NavigateToSelectRoomEvent -> {
                        findNavController().navigate(
                            UsernameFragmentDirections.actionUsernameFragmentToSelectRoomFragment(
                                event.username
                            )
                        )
                    }

                    is SetupViewModel.SetupEvent.InputEmptyError -> {
                        snackbar(R.string.error_field_empty)
                    }
                    is SetupViewModel.SetupEvent.InputTooShortError -> {
                        snackbar(R.string.error_username_too_short)
                    }
                    is SetupViewModel.SetupEvent.InputTooLongError -> {
                        snackbar(R.string.error_username_too_long)
                    }
                    else -> {

                    }
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}