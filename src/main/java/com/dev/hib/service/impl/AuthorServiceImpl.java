package com.dev.hib.service.impl;

import com.dev.hib.dao.AuthorDao;
import com.dev.hib.lib.Inject;
import com.dev.hib.lib.Service;
import com.dev.hib.model.Author;
import com.dev.hib.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Inject
    private AuthorDao authorDao;

    @Override
    public Author create(Author author) {
        return authorDao.create(author);
    }
}
