package com.atob.intentdemo.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.atob.intentdemo.Models.Book
import com.atob.intentdemo.R
import kotlinx.android.synthetic.main.activity_book_list.*


class BookListActivity : AppCompatActivity() {

    var bookList = mutableListOf<Book>()

    companion object{
        const val REQUEST_CODE_EDIT_BOOK = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        btn_add_book.setOnClickListener{
            var addBookIntent = Intent(this, BookDetailsActivity::class.java)
            startActivityForResult(addBookIntent,
                REQUEST_CODE_EDIT_BOOK
            )
        }
    }

    override fun onPostResume() {
        refreshBookList()
        super.onPostResume()
    }

    fun refreshBookList(){
        ll_booklist.removeAllViews()
        var counter = 0
        for (book in bookList){
            ll_booklist.addView(createTextView(book, counter))
        }
    }

    fun createTextView(book:Book, index:Int):TextView{
        var newBookView = TextView(this)
        newBookView.textSize = 24F
        newBookView.id = index
        newBookView.text = book.title

        newBookView.setOnClickListener {
            var tvIntent = Intent(this, BookDetailsActivity::class.java)
            tvIntent.putExtra("book", bookList[newBookView.id])
            bookList.removeAt(newBookView.id)
            startActivityForResult(tvIntent, REQUEST_CODE_EDIT_BOOK)
        }
        return newBookView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_EDIT_BOOK && resultCode == RESULT_OK) {
            val newResultBook = data!!.getSerializableExtra("book") as Book
            if (newResultBook != null) {
                bookList.add(newResultBook)
            }
        }
    }
}
