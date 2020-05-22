package com.dev.hib.service.impl;

import com.dev.hib.dao.BookDao;
import com.dev.hib.lib.Inject;
import com.dev.hib.lib.Service;
import com.dev.hib.model.Author;
import com.dev.hib.model.Book;
import com.dev.hib.model.Genre;
import com.dev.hib.service.BookService;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Inject
    private BookDao bookDao;

    @Override
    public void create(Book book) {
        bookDao.create(book);
    }

    @Override
    public Book getBookByTitle(Book book) {
        return bookDao.getBookByTitle(book);
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        return bookDao.getBooksByAuthor(author);
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        return bookDao.getBooksByGenre(genre);
    }
}
