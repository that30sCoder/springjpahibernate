package com.that30sCoder.dao;

import com.that30sCoder.domain.Book;

public interface BookDao {

    Book findByIsbn(String isbn);
    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);
}
