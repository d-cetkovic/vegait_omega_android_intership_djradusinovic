package com.example.digitalpass.features.pass.presentation.createAccount

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.digitalpass.R
import com.example.digitalpass.databinding.FragmentCreateAccountBinding

class CreateAccountFragment:Fragment(R.layout.fragment_create_account) {

    private var _binding: FragmentCreateAccountBinding? = null

    //     This property is only valid between onCreateView and
//     onDestroyView.
    private val binding get() = _binding!!

    private var fragmentViewModel: CreateAccountFragmentViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val viewModelFactory = CreateAccountViewModelFactory( requireContext() )
//        viewModel = ViewModelProvider(this, viewModelFactory).get(CreateAccountViewModel::class.java)
        fragmentViewModel = ViewModelProvider(requireActivity()).get(CreateAccountFragmentViewModel::class.java)
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        binding.createAccountButton.setOnClickListener {
            createAccountClicked()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createAccountClicked(){
        fragmentViewModel?.let {
            it.createAccount()
            findNavController().navigate(R.id.action_createAccountFragment_to_passListFragment)
        }
    }
}
