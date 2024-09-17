package ru.lonelywh1te.stephenkingapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
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
        val diffUtilCallback = BookDiffUtilCallback(this.list, list)
        val result = DiffUtil.calculateDiff(diffUtilCallback)

        this.list = list

        result.dispatchUpdatesTo(this)
    }

    fun getList(): List<Book> = list
}

class BookViewHolder(private val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Book) {
        binding.tvBookTitle.text = item.title
        binding.tvBookYear.text = item.year.toString()
    }
}

class BookDiffUtilCallback(
    private val oldList: List<Book>,
    private val newList: List<Book>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[oldItemPosition]
        return oldItem.title == newItem.title && oldItem.year == newItem.year
    }

}