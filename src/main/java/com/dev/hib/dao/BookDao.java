package com.dev.hib.dao;

import com.dev.hib.model.Author;
import com.dev.hib.model.Book;
import com.dev.hib.model.Genre;
import java.util.List;

public interface BookDao {

    Book create(Book book);

    Book getBookByTitle(Book book);

    List<Book> getBooksByAuthor(Author author);

    List<Book> getBooksByGenre(Genre genre);
}
