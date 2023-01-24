package com.medapp.ui

import android.os.Bundle
import com.medapp.model.Result
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.medapp.R
import com.medapp.data.local.database.MedicineEntity
import com.medapp.databinding.FragmentProfileBinding
import com.medapp.ui.adapter.MedicinesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment(), MedicinesAdapter.OnItemClickListener {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: SharedViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MedicinesAdapter(requireContext(), this)
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.profileUiState.collect {
                    when (it) {
                        is Result.Success -> {
                            adapter.submitList(it.medicines)
                            binding.textView.text = it.username
                            showProgressBar(false)
                        }
                        is Result.Error -> {
                            Toast.makeText(context, it.msg, Toast.LENGTH_SHORT).show()
                            showProgressBar(false)
                        }
                        is Result.Loading -> showProgressBar(true)
                        is Result.NotLogged -> findNavController().navigate(R.id.loginFragment)
                    }
                }
            }
        }
    }

    private fun showProgressBar(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    override fun onItemClicked(item: MedicineEntity) {
        val data = bundleOf("data" to item)
        findNavController().navigate(R.id.action_profileFragment_to_detailsFragment, data)
    }

}