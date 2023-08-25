package com.library.models;

import java.sql.Date;

public class Books {
    private String bookId;
    private String title;
    private String author;
    private String genre;

    private Date publicationDate;
    private boolean isAvailable;
    private int count;

    public Books(String bookId, String title, String author, String genre, Date publicationDate,
            boolean isAvailable, int count) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.isAvailable = isAvailable;
        this.count = count;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Books{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", publicationDate=" + publicationDate +
                ", isAvailable=" + isAvailable +
                ", count=" + count +
                '}';
    }
}
