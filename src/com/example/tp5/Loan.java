package com.example.tp5;
import java.time.LocalDate;

public class Loan {
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    public Loan(Book book, LocalDate borrowDate, LocalDate dueDate) {
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public Book getBook() { return book; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }

    public boolean isLate() {
        return LocalDate.now().isAfter(dueDate);
    }

    @Override
    public String toString() {
        return book + " borrowed on " + borrowDate + " due on " + dueDate + (isLate() ? " (LATE!)" : "");
    }
}
