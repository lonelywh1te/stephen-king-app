package ru.lonelywh1te.stephenkingapp.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Orientation
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.lonelywh1te.stephenkingapp.databinding.ActivityMainBinding
import ru.lonelywh1te.stephenkingapp.presentation.adapter.BookAdapter

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bookAdapter: BookAdapter
    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupRecyclerView()

        binding.layoutSwipeRefresh.setOnRefreshListener(this)

        if (savedInstanceState == null) {
            viewModel.getBooksAfterYear(1990)
            binding.pbLoadingBooks.visibility = View.VISIBLE
        }

        viewModel.books.observe(this) {
            bookAdapter.updateList(it)
            updateUI()
        }
    }

    private fun setupRecyclerView() = binding.rvBooks.apply {
        bookAdapter = BookAdapter()
        layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = bookAdapter
        addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
    }

    private fun updateUI() {
        with(binding) {
            if (layoutSwipeRefresh.isRefreshing) layoutSwipeRefresh.isRefreshing = false
            if (pbLoadingBooks.visibility == View.VISIBLE) pbLoadingBooks.visibility = View.GONE
        }
    }

    override fun onRefresh() {
        binding.layoutSwipeRefresh.isRefreshing = true
        viewModel.getBooksAfterYear(1990)
    }
}