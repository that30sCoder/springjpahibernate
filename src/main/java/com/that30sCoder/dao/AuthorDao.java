package com.that30sCoder.dao;

import com.that30sCoder.domain.Author;

/**
 * Created by jt on 8/22/21.
 */
public interface AuthorDao {
    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}
