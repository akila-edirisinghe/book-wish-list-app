package com.example.edirisin_mybookwishlist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditBookFragment extends DialogFragment {
    /*
    this is creates a dialog fragment that is used to add or edit a book object.
   the fragment is attached to the main activity, and oncreatedialog is used to create the fragment where if no book is passed in
    the fragment is used to add a book, and if a book is passed in the fragment is used to edit a book.
     */

    private EditBookDialogListener listener;
    private Book book;

    public void onAttach(Context context){
        /*
        This method is used to attach the fragment to the main activity.
         */
        super.onAttach(context);
        try{
            listener = (EditBookDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + " must implement AddBookDialogListener");
        }
    }

    public EditBookFragment(){
        /*
        This is the constructor for the edit book fragment class. It takes in no parameters.
        used for adding a new book
         */
        this.book = null;
    }

    public EditBookFragment(Book book){
        /*
        This is the constructor for the edit book fragment class. It takes in the following parameters:
        used for editing a book

         */
        this.book = book;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        /*
        This method is used to create the fragment where if no book is passed in
        the fragment is used to add a book, and if a book is passed in the fragment is used to edit a book.
        it creates a builder object and a view object where the view object is used to get the book title, author, genre, public year and status of the book object
        and set them to the edit text.

        afterwards, checks if a book is passed in, if a book is passed in , the fragment shows the data of the book to be editted later on
        if no book is passed in, the fragment is used to show blank fields to add a new book

        afterwards, if cancel is pressed, the dialog is cancelled, and if add is pressed, the book title, author, genre, public year and status of the book object
        are set to the edit text and the listener is used to add the book or edit the book.

         */
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.add_edit_book_fragment, null);
        String nametag = getTag();

        EditText bookTitle = view.findViewById(R.id.edit_book_title);
        EditText author = view.findViewById(R.id.edit_author);
        EditText genre = view.findViewById(R.id.edit_genre);
        EditText publicYear = view.findViewById(R.id.edit_public_year);
        Switch status = view.findViewById(R.id.Status);
        builder.setTitle(nametag);
        if (book != null){
            //if a book is passed in , the fragment shows the data of the book to be editted later on
            bookTitle.setText(book.getBookTitle());
            author.setText(book.getAuthor());
            genre.setText(book.getGenre());
            //publicYear.setText(String.valueOf(book.getPublicYear()));
            publicYear.setVisibility(View.GONE);
            status.setChecked(book.getStatus());
        }
        else{
            //if no book is passed in, the fragment is used to show blank fields to add a new book
            bookTitle.setText("");
            author.setText("");
            genre.setText("");
            publicYear.setVisibility(View.VISIBLE);
            publicYear.setText("");
            status.setChecked(false);
        }
        builder.setView(view);
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.cancel();
        });
        builder.setPositiveButton("Add", (dialog, which) -> {
            String BookTitle = bookTitle.getText().toString();
            String authorName = author.getText().toString();
            String genreType = genre.getText().toString();

            int  PublicYear = 0;
            if (nametag.equalsIgnoreCase("ADD_BOOK")){
                try {
                    PublicYear = Integer.parseInt(publicYear.getText().toString());
                } catch (NumberFormatException e){
                    PublicYear = 0;
                }
            }
            Boolean bookStatus = status.isChecked();
            if (book == null){
                listener.addBook(new Book(BookTitle, authorName, genreType, PublicYear, bookStatus));
            }
            else{
                book.setBookTitle(BookTitle);
                book.setAuthor(authorName);
                book.setGenre(genreType);
                //book.setPublicYear(PublicYear);
                book.setStatus(bookStatus);
                listener.EditBook();
            }
        });
        return builder.create();
    }





}
