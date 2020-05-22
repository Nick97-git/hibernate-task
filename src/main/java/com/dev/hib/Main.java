package com.dev.hib;

import com.dev.hib.lib.Injector;
import com.dev.hib.model.Author;
import com.dev.hib.model.Book;
import com.dev.hib.model.Genre;
import com.dev.hib.service.BookService;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("com.dev.hib");
    private static final BookService bookService =
            (BookService) INJECTOR.getInstance(BookService.class);

    public static void main(String[] args) {
        Book book = new Book();
        book.setTitle("Lord of The Rings");
        Book book2 = new Book();
        book2.setTitle("Green Mile");
        Genre genre = new Genre();
        genre.setName("Fantasy");
        Genre genre2 = new Genre();
        genre.setName("Fantastic");
        book.setGenre(genre);
        book2.setGenre(genre2);
        List<Author> authors = new ArrayList<>();
        Author author1 = new Author("John", "Tolkien");
        List<Author> authors2 = new ArrayList<>();
        Author author2 = new Author("Stephen", "King");
        authors.add(author1);
        authors2.add(author2);
        book.setAuthors(authors);
        book2.setAuthors(authors2);
        bookService.create(book);
        bookService.create(book2);

        System.out.println("---------FOUND BOOK------------");
        Book foundBook = bookService.getBookByTitle(book2);
        System.out.println(foundBook.toString());

        System.out.println("---------FOUND BOOKS BY GENRE------------");
        List<Book> booksByGenre = bookService.getBooksByGenre(genre);
        booksByGenre.forEach(b -> System.out.println(b.toString()));

        System.out.println("---------FOUND BOOKS BY AUTHOR------------");
        List<Book> booksByAuthor = bookService.getBooksByAuthor(author2);
        booksByAuthor.forEach(b -> System.out.println(b.toString()));
    }
}
