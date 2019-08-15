package com.atob.intentdemo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_EDIT_BOOK && resultCode == RESULT_OK) {
            val newResultBook = data!!.getSerializableExtra("book") as Book
            if (newResultBook != null) {
                bookList.add(newResultBook)
            }
        }
    }
}
