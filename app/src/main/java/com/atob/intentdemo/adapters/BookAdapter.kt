package com.atob.intentdemo.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.atob.intentdemo.Models.Book
import com.atob.intentdemo.R
import com.atob.intentdemo.ui.BookDetailsActivity
import com.atob.intentdemo.ui.BookListActivity
import kotlinx.android.synthetic.main.activity_book_details.view.*
import kotlinx.android.synthetic.main.book_list_content.view.*

class BookAdapter(val data: MutableList<Book>, val context: Activity): RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val etTitle :TextView = view.rv_tv_book_title
        val etAuthor :TextView = view.rv_tv_author
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.ViewHolder {
        val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.book_list_content,parent,false)
        return ViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BookAdapter.ViewHolder, position: Int) {
        holder.etAuthor.setText(data[position].author)
        holder.etTitle.setText(data[position].title)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, BookDetailsActivity::class.java)
            intent.putExtra("book", data[position])
            data.removeAt(position)
            context.startActivityForResult(intent, BookListActivity.REQUEST_CODE_EDIT_BOOK)
        }
    }

}