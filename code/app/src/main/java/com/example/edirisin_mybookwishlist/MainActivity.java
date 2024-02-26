package com.example.edirisin_mybookwishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EditBookDialogListener {
    private ListView wishList;

    private BookArrayAdapter bookAdapter;
    private ArrayList<Book> bookDataList;

    private Toast bookCount;
    private Toast bookReadCount;


    private Book bookSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        This method is called when the activity is first created. This is where you should do all of your normal static set up: create views, bind data to lists, etc.
        This method also provides you with a Bundle containing the activity's previously frozen state, if there was one. Always followed by onStart().
        Moreover, it creates an array of book objects that are sent to an adapter to get a view for each book object in the array.
        Also has button listeners for adding and deleting books, and for displaying the number of books and the number of books read.

         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wishList = findViewById(R.id.book_list);
        bookDataList = new ArrayList<>();

        bookAdapter = new BookArrayAdapter(this, bookDataList);
        wishList.setAdapter(bookAdapter);

        FloatingActionButton add_button = findViewById(R.id.button_add_book);
        //add button where the fragment is shown and a new book is added
        add_button.setOnClickListener(v -> {
            new EditBookFragment().show(getSupportFragmentManager(), "ADD_BOOK");
        });
        // delete button where u select a item and then click delete button to delete the item
        FloatingActionButton delete_button = findViewById(R.id.button_delete_book);
        wishList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bookSelected = (Book) parent.getItemAtPosition(position);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBook(bookSelected);
            }
        });

        //edit button where if u click an item on the list for a long period, it will pop up a fragment to edit the item
        wishList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                bookSelected = (Book) parent.getItemAtPosition(position);
                new EditBookFragment(bookSelected).show(getSupportFragmentManager(), "CHANGE_BOOK");
                return true;
            }
        });
        //display book count and read book count
        displayBookCount();
        displayBookReadCount();


    }

    public void addBook(Book book) {
        /*
        This method adds a book to the bookDataList and notifies the adapter that the data has changed.
        it is called from the editbookfragment class when a new book is added.
         */
        bookDataList.add(book);
        bookAdapter.notifyDataSetChanged();
    }

    public void deleteBook(Book book) {
        /*
        This method deletes a book from the bookDataList and notifies the adapter that the data has changed.
        this method is called from the editbookfragment class when a book is deleted.
         */
        bookDataList.remove(book);
        bookAdapter.notifyDataSetChanged();
    }

    public void EditBook() {
        /*
        This method notifies the adapter that the data has changed.
        this method is called from the editbookfragment class when a book is edited.
         */
        bookAdapter.notifyDataSetChanged();
    }

    public void displayBookCount() {
        /*
        This method displays the number of books in the bookDataList when the book count button is clicked.
        this method just gets the size of the datalist and include that when creating a toast
         */
        Button bookCountButton = findViewById(R.id.book_count);

        bookCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookCount = Toast.makeText(getApplicationContext(), "Number of books: " + bookDataList.size(), Toast.LENGTH_SHORT);
                bookCount.show();
            }
        });

    }

    public void displayBookReadCount() {
        /*
        This method return the number of books read in the bookDataList when the read count button is clicked.
        this method iterates through the datalist to check if read is the status of the book and then increments the count
        afterwards, the count is included when creating the toast
         */
        Button readCountButton = findViewById(R.id.read_count);
        readCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                for (Book book : bookDataList) {
                    if (book.getStatus()){
                        count++;
                    }
                }
                bookReadCount = Toast.makeText(getApplicationContext(), "Number of books read: " + count, Toast.LENGTH_SHORT);
                bookReadCount.show();
            }
        });


    }

}

