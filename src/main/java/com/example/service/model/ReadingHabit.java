package com.example.service.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ReadingHabit {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private int habitID;
    private int pagesRead;
    private String submissionMoment;

    @ManyToOne
    @JoinColumn(name = "bookid")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    public int getPagesRead() {
        return pagesRead;
    }

    public void setPagesRead(int pagesRead) {
        this.pagesRead = pagesRead;
    }

    public String getSubmissionMoment() {
        return submissionMoment;
    }

    public void setSubmissionMoment(String submissionMoment) {
        this.submissionMoment = submissionMoment;
    }

    public int getHabitID() {
        return habitID;
    }

    public void setHabitID(int habitID) {
        this.habitID = habitID;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
