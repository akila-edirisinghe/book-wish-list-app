package com.example.edirisin_mybookwishlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BookArrayAdapter extends ArrayAdapter<Book> {
    /*
    This class is used to create a book array adapter object with the following attributes:
    bookTitle, author, genre, publicYear, and Status.
     */



    public BookArrayAdapter(Context context,  ArrayList<Book> books) {
        /*
        This is the constructor for the book array adapter class. It takes in the following parameters:
        context, and books.
         */
        super(context,0, books);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /*
        This method is used to get a view for each book object in the array.
        if there is no convert view, it inflates the wish_list xml but if there is, then it is assigned to view
        it gets the view object and assigns to variables where these variables will get the book title, author, genre, public year and status of the book object
        and set them to the text view.
         */

        View view;

        if(convertView == null){
            view = LayoutInflater.from(super.getContext()).inflate(R.layout.wish_list, parent, false);
        }
        else{
            view = convertView;
        }
        Book book = super.getItem(position);
        TextView book_title = view.findViewById(R.id.book_title);
        TextView author = view.findViewById(R.id.author);
        TextView genre = view.findViewById(R.id.genre);
        TextView public_year = view.findViewById(R.id.public_year);
        TextView Status = view.findViewById(R.id.book_status);

        book_title.setText(book.getBookTitle());
        author.setText(book.getAuthor());
        genre.setText(book.getGenre());
        public_year.setVisibility(View.GONE);
        //public_year.setText(String.valueOf(book.getPublicYear()));
        if (book.getStatus()){
            Status.setText("Read");
        }
        else{
            Status.setText("Unread");
        }


        return view;
    }
}

