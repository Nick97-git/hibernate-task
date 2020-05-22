package com.dev.hib.dao.impl;

import com.dev.hib.dao.BookDao;
import com.dev.hib.lib.Dao;
import com.dev.hib.model.Author;
import com.dev.hib.model.Book;
import com.dev.hib.model.Genre;
import com.dev.hib.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = criteriaBuilder
                    .createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            Predicate predicateTitle = criteriaBuilder.equal(root.get("title"), book.getTitle());
            criteriaQuery.where(predicateTitle);
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException("Can't get book with title " + book.getTitle(), e);
        }
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = criteriaBuilder
                    .createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            Predicate predicateTitle = criteriaBuilder.isMember(author, root.get("authors"));
            criteriaQuery.where(predicateTitle);
            return session.createQuery(criteriaQuery).list();
        } catch (Exception e) {
            throw new RuntimeException("Can't get book by genre " + author.getName(), e);
        }
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = criteriaBuilder
                    .createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            Predicate predicateTitle = criteriaBuilder.equal(root.get("genre"), genre);
            criteriaQuery.where(predicateTitle);
            return session.createQuery(criteriaQuery).list();
        } catch (Exception e) {
            throw new RuntimeException("Can't get book by genre " + genre.getName(), e);
        }
    }
}
