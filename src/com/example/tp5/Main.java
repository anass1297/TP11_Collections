package com.example.tp5;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();

        Book b1 = new Book("978-0134685991","Effective Java","Joshua Bloch");
        Book b2 = new Book("978-0596009205","Head First Java","Kathy Sierra");
        lib.addBook(b1, 2);
        lib.addBook(b2, 1);

        User alice = new User(1, "Alice");
        User bob   = new User(2, "Bob");

        lib.lendBook(alice, b1, 14); 
        lib.lendBook(bob, b1, 7);  

        System.out.println("Disponibles : " + lib.listAvailable());
        System.out.println("Emprunts d'Alice : " + lib.listLoans(alice));
        System.out.println("Emprunts de Bob   : " + lib.listLoans(bob));

       
        System.out.println("Recherche auteur 'Joshua' : " + lib.searchByAuthor("Joshua"));
    }
}
