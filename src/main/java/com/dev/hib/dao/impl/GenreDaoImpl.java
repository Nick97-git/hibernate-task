package com.dev.hib.dao.impl;

import com.dev.hib.dao.GenreDao;
import com.dev.hib.lib.Dao;
import com.dev.hib.model.Genre;
import com.dev.hib.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class GenreDaoImpl implements GenreDao {

    @Override
    public Genre create(Genre genre) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(genre);
            transaction.commit();
            return genre;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert Genre entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
