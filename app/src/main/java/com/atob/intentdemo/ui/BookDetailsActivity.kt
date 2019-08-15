package com.atob.intentdemo.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.atob.intentdemo.Models.Book
import com.atob.intentdemo.R
import kotlinx.android.synthetic.main.activity_book_details.*

class BookDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        btn_confirm_book.setOnClickListener {
           var intentConfirmBook = Intent()
            intentConfirmBook.putExtra("book", createBook())
            setResult(Activity.RESULT_OK, intentConfirmBook)
            finish()
        }

        var bundle: Bundle? = intent.extras
        if(bundle != null) {
            loadBook(bundle!!.getSerializable("book") as Book)
        }
    }

    fun loadBook(book : Book){
        et_book_title.setText(book.title)
        et_book_author.setText(book.author)
        et_book_date.setText(book.publicationDate.toString())
        sw_watched.isChecked = book.isWatched
    }

    fun createBook():Book{
        return Book(et_book_title.text.toString(), et_book_author.text.toString(), et_book_date.text.toString() as Long, sw_watched.isChecked)
    }
}
