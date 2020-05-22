package com.dev.hib.dao.impl;

import com.dev.hib.dao.AuthorDao;
import com.dev.hib.lib.Dao;
import com.dev.hib.model.Author;
import com.dev.hib.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class AuthorDaoImpl implements AuthorDao {

    @Override
    public Author create(Author author) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
            return author;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert Author entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
