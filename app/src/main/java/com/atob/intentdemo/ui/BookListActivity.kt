package com.atob.intentdemo.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.atob.intentdemo.Models.Book
import com.atob.intentdemo.R
import com.atob.intentdemo.data.BookRepository
import kotlinx.android.synthetic.main.activity_book_list.*

/*Step 1 - Right click and Refactor the name of the BookListActivity to something more suitable (BookListActivity)
* Step 2 - Right click and Refactor the activity_book_list.xml file to match the newly named Activity (activity_book_list)
* Step 3 - Create a new Activity that that will display a details of the items in the list (BookDetailsActivity)
* Step 4 - Edit the layout files to get a general idea of what you want it to look like (just a rough draft is fine for now)
* Step 5 - Create the Book Model Data Class!
* Go to the Book Model Class!
* Step 8 - Create a List of Book
* Step 9 - Create a function that creates a textView from a Book object
* Step 9.1 - Create a function to refresh the list of books that's added to the scrollView
*          (removes all child Views from the scrollview then loops through the bookList and adds them all back)
* Step 10 - Create a companion object, a EDIT_BOOK_REQUEST_CODE
* Step 11 - Create the CreateTextView method in the List Activity
*          (It creates a onClickListener on the View itself that first removes the book from the list then
*          passes Book object as Serializable extra to the details activity)
* Step 12 - Set up Click listener for the add book button with an Intent that goes to the details activity
* Step 13 - Create Intents in the click listeners to go to details activity (just a blank intent)
* Go to the Details Activity class!
* Step 17 - Override the onActivityResult function
* Step 18 - Add if statements to check if the results are coming from the proper request code and result code
* Step 19 - Add the returned book to the list and run the refresh book function
* Step 20 - PROFIT!!!
* */


class BookListActivity : AppCompatActivity() {

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
        for (book in BookRepository.bookList){
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
            tvIntent.putExtra("book", BookRepository.bookList[newBookView.id])
            BookRepository.bookList.removeAt(newBookView.id)
            startActivityForResult(tvIntent, REQUEST_CODE_EDIT_BOOK)
        }
        return newBookView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_EDIT_BOOK && resultCode == RESULT_OK) {
            val newResultBook = data!!.getSerializableExtra("book") as Book
            if (newResultBook != null) {
                BookRepository.bookList.add(newResultBook)
            }
        }
    }
}
