package com.atob.intentdemo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atob.intentdemo.Models.Book
import com.atob.intentdemo.R
import com.atob.intentdemo.adapters.BookAdapter
import kotlinx.android.synthetic.main.activity_book_list.*

class BookListActivity : AppCompatActivity() {

    companion object{
        const val REQUEST_CODE_EDIT_BOOK = 2
    }

    var bookList = mutableListOf<Book>()
    val adapter = BookAdapter(bookList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        btn_add_book.setOnClickListener{
            var addBookIntent = Intent(this, BookDetailsActivity::class.java)
            startActivityForResult(addBookIntent,
                REQUEST_CODE_EDIT_BOOK
            )
        }

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_booklist.layoutManager = layoutManager
        rv_booklist.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_EDIT_BOOK && resultCode == RESULT_OK) {
            val newResultBook = data!!.getSerializableExtra("book") as Book
            if (newResultBook != null) {
                bookList.add(newResultBook)
                adapter.notifyDataSetChanged()
            }
        }
    }
}
