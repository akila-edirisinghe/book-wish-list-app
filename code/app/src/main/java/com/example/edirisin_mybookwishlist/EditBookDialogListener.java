package com.example.edirisin_mybookwishlist;

public interface EditBookDialogListener {
    /*
    This interface is used to create a edit book dialog listener object with the following methods:
    addBook, deleteBook, and EditBook.

     */
    void addBook( Book book);
    void deleteBook( Book book);
    void EditBook();
}
