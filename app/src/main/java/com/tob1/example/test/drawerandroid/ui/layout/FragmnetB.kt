package com.tob1.example.test.drawerandroid.ui.layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tob1.example.test.drawerandroid.databinding.FragmentBBinding
import com.tob1.example.test.drawerandroid.ui.layout.adapter.PeopleAdapter
import com.tob1.example.test.drawerandroid.ui.layout.viewmodels.PeopleLiveDataFactory
import com.tob1.example.test.drawerandroid.ui.layout.viewmodels.PeopleViewModel

//Cho people
class FragmnetB : Fragment() {

    private lateinit var binding : FragmentBBinding
    private lateinit var peopleAdapter : PeopleAdapter
    private val viewModel : PeopleViewModel by viewModels { PeopleLiveDataFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        peopleAdapter = PeopleAdapter()
        viewModel.listPeopleUI.observe(viewLifecycleOwner, {
            peopleAdapter.submitList(it)
        })

        viewModel.btnAdd.observe(viewLifecycleOwner,{btn ->
            binding.btnAddPeople.setOnClickListener {
                btn.onClick()
            }
        })


        binding.listPeople.apply {
            adapter = peopleAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        }
    }

}