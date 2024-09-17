package ru.lonelywh1te.stephenkingapp.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.lonelywh1te.stephenkingapp.databinding.ActivityMainBinding
import ru.lonelywh1te.stephenkingapp.domain.model.Book
import ru.lonelywh1te.stephenkingapp.domain.model.Result
import ru.lonelywh1te.stephenkingapp.presentation.adapter.BookAdapter
import java.util.ArrayList

private const val BOOKS_BUNDLE_KEY = "books"

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bookAdapter: BookAdapter
    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupRecyclerView()

        if (savedInstanceState == null) {
            viewModel.getBooksAfterYear(1990)
        }

        binding.layoutSwipeRefresh.setOnRefreshListener(this)

        viewModel.books.observe(this) { result ->
            when (result) {
                is Result.Success -> bookAdapter.updateList(result.data)
                is Result.Error -> showError(result.e.message.toString())
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            updateLoadingState(isLoading)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(BOOKS_BUNDLE_KEY, ArrayList(bookAdapter.getList()))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val list = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getParcelableArrayList(BOOKS_BUNDLE_KEY, Book::class.java)
        } else {
            savedInstanceState.getParcelableArrayList(BOOKS_BUNDLE_KEY)
        }

        list?.let { bookAdapter.updateList(it) }
    }

    private fun showError(message: String) {
        Snackbar
            .make(binding.root, message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_error))
            .show()
    }

    private fun setupRecyclerView() = binding.rvBooks.apply {
        bookAdapter = BookAdapter()
        layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = bookAdapter
        addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
    }

    override fun onRefresh() {
        viewModel.getBooksAfterYear(1990)
    }

    private fun updateLoadingState(isLoading: Boolean) {
        with(binding) {
            if (!isLoading && layoutSwipeRefresh.isRefreshing) layoutSwipeRefresh.isRefreshing = false

            if (isLoading && !layoutSwipeRefresh.isRefreshing) {
                layoutSwipeRefresh.isEnabled = false
                pbLoadingBooks.visibility = View.VISIBLE
            } else {
                layoutSwipeRefresh.isEnabled = true
                pbLoadingBooks.visibility = View.GONE
            }
        }
    }
}