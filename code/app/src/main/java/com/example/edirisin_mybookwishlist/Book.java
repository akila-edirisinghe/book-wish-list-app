package com.example.edirisin_mybookwishlist;

public class Book {
    /*
    This class is used to create a book object with the following attributes:
    bookTitle, author, genre, publicYear, and Status.

    This class will contain all the getter, setters for each attribute that will be displayed and a constructor to create instances of the object.

     */
    private String bookTitle;
    private String author;
    private String genre;
    private int publicYear;
    private boolean Status;

    public Book(String bookTitle, String author, String genre, int publicYear, boolean Status){
        /*
        This is the constructor for the book class. It takes in the following parameters:
        bookTitle, author, genre, publicYear, and Status.
         */
        this.bookTitle = bookTitle;
        this.author = author;
        this.genre = genre;
        this.publicYear = publicYear;
        this.Status = Status;
    }

    public String getBookTitle() {
        //This method returns the bookTitle of the book object.
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        //This method sets the bookTitle of the book object.
        this.bookTitle = bookTitle;
    }
    public String getAuthor() {
        //This method returns the author of the book object.
        return author;
    }
    public void setAuthor(String author) {
        //This method sets the author of the book object.
        this.author = author;
    }
    public String getGenre() {
        //This method returns the genre of the book object.
        return genre;
    }
    public void setGenre(String genre) {
        //This method sets the genre of the book object.
        this.genre = genre;
    }
    public int getPublicYear() {
        //This method returns the publicYear of the book object.
        return publicYear;
    }
    public void setPublicYear(int publicYear) {
        //This method sets the publicYear of the book object.
        this.publicYear = publicYear;
    }
    public boolean getStatus() {
        //This method returns the Status of the book object.
        return Status;
    }
    public void setStatus(Boolean status) {
        //This method sets the Status of the book object.
        Status = status;
    }


}
