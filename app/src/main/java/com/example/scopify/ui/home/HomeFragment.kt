package com.example.scopify.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scopify.data.Resource
import com.example.scopify.databinding.FragmentHomeBinding
import com.example.scopify.domain.entity.Category
import com.example.scopify.ui.home.category.CategoryAdapter
import com.example.scopify.ui.home.category.CategoryViewModel
import com.example.scopify.ui.home.source.SourceAdapter
import com.example.scopify.ui.home.source.SourceViewModel
import com.example.scopify.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null

    private val searchViewModel: SearchViewModel by viewModels()
    private val categoryViewModel: CategoryViewModel by viewModels()
    private val sourceViewModel: SourceViewModel by viewModels()
    private var selectedCategory: Category? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter()
        val sourceAdapter = SourceAdapter()

        binding?.viewInitial?.root?.visibility = View.VISIBLE

        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding?.searchView?.clearFocus()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    binding?.viewInitial?.root?.visibility = View.GONE
                    searchViewModel.searchSources(query).observe(viewLifecycleOwner) { sources ->
                        if (sources != null) {
                            when (sources) {
                                is Resource.Loading -> {
                                    binding?.relativeLayoutProgressBar?.visibility = View.VISIBLE
                                }
                                is Resource.Success -> {
                                    binding?.relativeLayoutProgressBar?.visibility = View.GONE
                                    sources.data?.let { it1 ->
                                        if (it1.isNotEmpty()) {
                                            binding?.viewEmpty?.root?.visibility = View.GONE
                                            sourceAdapter.setData(it1)
                                        } else {
                                            binding?.viewEmpty?.root?.visibility = View.VISIBLE
                                            sourceAdapter.clearData()
                                        }
                                    }
                                }
                                is Resource.Error -> {
                                    binding?.relativeLayoutProgressBar?.visibility = View.GONE
                                    Toast.makeText(
                                        context,
                                        "Failed to fetch sources",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
                return true
            }

        })

        categoryAdapter.onItemClick = { selectedCategory ->
            binding?.viewInitial?.root?.visibility = View.GONE
            this.selectedCategory = selectedCategory

            sourceAdapter.onItemClick = { selectedData ->
                val action = selectedData.id?.let {
                    HomeFragmentDirections.actionHomeFragmentToArticleFragment(
                        it
                    )
                }
                if (action != null) {
                    findNavController().navigate(action)
                }
            }

            selectedCategory.id?.let {
                sourceViewModel.getSources(it).observe(viewLifecycleOwner) { sources ->
                    if (sources != null) {
                        when (sources) {
                            is Resource.Loading -> {
                                binding?.relativeLayoutProgressBar?.visibility = View.VISIBLE
                            }
                            is Resource.Success -> {
                                binding?.relativeLayoutProgressBar?.visibility = View.GONE
                                binding?.viewEmpty?.root?.visibility = View.GONE
                                sources.data?.let { it1 -> sourceAdapter.setData(it1) }
                            }
                            is Resource.Error -> {
                                binding?.relativeLayoutProgressBar?.visibility = View.GONE
                                Toast.makeText(
                                    context,
                                    "Failed to fetch sources",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }

        categoryViewModel.category.observe(viewLifecycleOwner) { categories ->
            if (categories != null) {
                categoryViewModel.category.removeObservers(viewLifecycleOwner)

                when (categories) {
                    is Resource.Loading -> return@observe
                    is Resource.Success -> {
                        categories.data?.let { categoryList ->
                            categoryAdapter.setData(categoryList)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            context,
                            "Failed to catch category list",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        with(binding?.rvCategory) {
            this?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = categoryAdapter
        }
        with(binding?.rvSource) {
            this?.layoutManager = GridLayoutManager(activity, 2)
            this?.setHasFixedSize(true)
            this?.adapter = sourceAdapter
        }
    }
}