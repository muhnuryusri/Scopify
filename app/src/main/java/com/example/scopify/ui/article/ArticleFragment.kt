package com.example.scopify.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scopify.data.Resource
import com.example.scopify.databinding.FragmentArticleBinding
import com.example.scopify.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment() {
    private var binding: FragmentArticleBinding? = null

    private val searchViewModel: SearchViewModel by viewModels()
    private val articleViewModel: ArticleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articleAdapter = ArticleAdapter()

        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding?.searchView?.clearFocus()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    searchViewModel.searchArticles(query).observe(viewLifecycleOwner) { articles ->
                        if (articles != null) {
                            when (articles) {
                                is Resource.Loading -> {
                                    binding?.relativeLayoutProgressBar?.visibility = View.VISIBLE
                                }
                                is Resource.Success -> {
                                    binding?.relativeLayoutProgressBar?.visibility = View.GONE
                                    articles.data?.let { it1 ->
                                        if (it1.isNotEmpty()) {
                                            binding?.viewEmpty?.root?.visibility = View.GONE
                                            articleAdapter.setData(it1)
                                        } else {
                                            binding?.viewEmpty?.root?.visibility = View.VISIBLE
                                            articleAdapter.clearData()
                                        }
                                    }
                                }
                                is Resource.Error -> {
                                    binding?.relativeLayoutProgressBar?.visibility = View.GONE
                                    Toast.makeText(
                                        context,
                                        "Failed to fetch articles",
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

        articleAdapter.onItemClick = { selectedData ->
            val action = selectedData.url?.let {
                ArticleFragmentDirections.actionArticleFragmentToDetailFragment(
                    it
                )
            }
            if (action != null) {
                findNavController().navigate(action)
            }
        }

        val sourceId = arguments?.getString("source")
        if (sourceId != null) {
            articleViewModel.getArticle(sourceId).observe(viewLifecycleOwner) { articles ->
                if (articles != null) {
                    when (articles) {
                        is Resource.Loading -> {
                            binding?.relativeLayoutProgressBar?.visibility = View.VISIBLE
                            articleAdapter.setData(emptyList())
                        }
                        is Resource.Success -> {
                            binding?.relativeLayoutProgressBar?.visibility = View.GONE
                            articles.data?.let { it1 -> articleAdapter.setData(it1) }
                        }
                        is Resource.Error -> {
                            binding?.relativeLayoutProgressBar?.visibility = View.GONE
                            Toast.makeText(
                                context,
                                "Failed to fetch articles",
                                Toast.LENGTH_SHORT
                            ).show()
                            articleAdapter.setData(emptyList())
                        }
                    }
                }
            }
        }
        with(binding?.rvArticle) {
            this?.layoutManager = LinearLayoutManager(activity)
            this?.setHasFixedSize(true)
            this?.adapter = articleAdapter
        }
    }
}