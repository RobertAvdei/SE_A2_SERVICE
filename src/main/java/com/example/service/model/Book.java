package com.example.service.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private int bookID;
    private String bookName;

    @OneToMany( mappedBy="book")
    public Set<ReadingHabit> readingHabits;

    public Set<ReadingHabit> getReadingHabits() {
        return readingHabits;
    }

    public void setReadingHabits(Set<ReadingHabit> readingHabits) {
        this.readingHabits = readingHabits;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public long getBookID() {
        return (int)this.bookID;
    }

    public void setBookID(int id) {
        this.bookID = id;
    }

}
