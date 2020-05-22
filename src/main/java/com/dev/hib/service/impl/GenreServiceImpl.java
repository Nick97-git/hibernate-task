package com.dev.hib.service.impl;

import com.dev.hib.dao.GenreDao;
import com.dev.hib.lib.Inject;
import com.dev.hib.lib.Service;
import com.dev.hib.model.Genre;
import com.dev.hib.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {
    @Inject
    private GenreDao genreDao;

    @Override
    public Genre create(Genre genre) {
        return genreDao.create(genre);
    }
}
