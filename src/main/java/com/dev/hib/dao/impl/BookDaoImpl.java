package com.dev.hib.dao.impl;

import com.dev.hib.dao.BookDao;
import com.dev.hib.lib.Dao;
import com.dev.hib.model.Author;
import com.dev.hib.model.Book;
import com.dev.hib.model.Genre;
import com.dev.hib.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class BookDaoImpl implements BookDao {

    @Override
    public Book create(Book book) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert Book entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Book getBookByTitle(Book book) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Book> query =
                    session.createQuery("from Book b "
                            + "join fetch b.genre Genre "
                            + "join fetch b.authors Author where b.title =: title", Book.class);
            query.setParameter("title", book.getTitle());
            return query.uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException("Can't get book with title " + book.getTitle(), e);
        }
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Book> query =
                    session.createQuery("from Book b "
                            + "join fetch b.genre Genre "
                            + "join fetch b.authors Author "
                            + "where :author member b.authors", Book.class);
            query.setParameter("author", author);
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Can't get book by author " + author.getName(), e);
        }
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Book> query =
                    session.createQuery("from Book b "
                            + "join fetch b.genre Genre "
                            + "join fetch b.authors Author where b.genre =: genre", Book.class);
            query.setParameter("genre", genre);
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Can't get book by genre " + genre.getName(), e);
        }
    }
}
