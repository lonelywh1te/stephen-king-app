package ru.lonelywh1te.stephenkingapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.lonelywh1te.stephenkingapp.databinding.ItemBookBinding
import ru.lonelywh1te.stephenkingapp.domain.model.Book

class BookAdapter: RecyclerView.Adapter<BookViewHolder>() {
    private var list: List<Book> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBookBinding.inflate(inflater, parent, false)

        return BookViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
       val item = list[position]
        holder.bind(item)
    }

    fun updateList(list: List<Book>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun getList(): List<Book> = list
}

class BookViewHolder(private val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Book) {
        binding.tvBookTitle.text = item.title
        binding.tvBookYear.text = item.year.toString()
    }
}