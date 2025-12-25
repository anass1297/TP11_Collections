package com.example.tp5;

import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class Library {
    private List<Book> books = new ArrayList<>();
    private Map<Book, Integer> stock = new HashMap<>();
    private Map<User, Set<Loan>> loans = new HashMap<>(); // Set pour interdire doublons

 
    public void addBook(Book b, int quantity) {
        if (!books.contains(b)) {
            books.add(b);
            stock.put(b, quantity);
        } else {
            stock.put(b, stock.get(b) + quantity);
        }
    }

  
    public boolean lendBook(User u, Book b, int days) {
        Integer qty = stock.getOrDefault(b, 0);
        if (qty <= 0) return false;

    
        Set<Loan> userLoans = loans.computeIfAbsent(u, k -> new HashSet<>());
        boolean alreadyBorrowed = userLoans.stream()
            .anyMatch(loan -> loan.getBook().equals(b));
        if (alreadyBorrowed) return false;

      
        LocalDate today = LocalDate.now();
        Loan loan = new Loan(b, today, today.plusDays(days));
        userLoans.add(loan);
        stock.put(b, qty - 1);
        return true;
    }

   
    public boolean returnBook(User u, Book b) {
        Set<Loan> userLoans = loans.get(u);
        if (userLoans != null) {
            Optional<Loan> loanOpt = userLoans.stream()
                .filter(loan -> loan.getBook().equals(b))
                .findFirst();
            if (loanOpt.isPresent()) {
                userLoans.remove(loanOpt.get());
                stock.put(b, stock.getOrDefault(b, 0) + 1);
                return true;
            }
        }
        return false;
    }

   
    public List<Book> listAvailable() {
        return books.stream()
            .filter(b -> stock.getOrDefault(b, 0) > 0)
            .collect(Collectors.toList());
    }

   
    public Set<Loan> listLoans(User u) {
        return loans.getOrDefault(u, Collections.emptySet());
    }

   
    public List<Book> searchByTitle(String title) {
        return books.stream()
            .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
            .collect(Collectors.toList());
    }

   
    public List<Book> searchByAuthor(String author) {
        return books.stream()
            .filter(b -> b.getAuthor().toLowerCase().contains(author.toLowerCase()))
            .collect(Collectors.toList());
    }
}
