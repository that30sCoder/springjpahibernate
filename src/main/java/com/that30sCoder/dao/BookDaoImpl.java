package com.that30sCoder.dao;

import com.that30sCoder.domain.Author;
import com.that30sCoder.domain.Book;
import com.that30sCoder.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class BookDaoImpl implements BookDao{

    private final EntityManagerFactory emf;

    public BookDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Book getById(Long id) {
        EntityManager em = getEntityManager();
        Book book = em.find(Book.class, id);
        em.close();
        return book ;
    }

    @Override
    public Book findBookByTitle(String title) {
        EntityManager em = getEntityManager();
        TypedQuery<Book> query = em.createQuery("SELECT  b FROM  Book b WHERE " +
                "b.title = :title", Book.class);
        query.setParameter("title",title);
        em.close();

        return query.getSingleResult();
    }

    @Override
    public Book saveNewBook(Book book) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.flush();
        em.getTransaction().commit();
        em.close();
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(book);
        em.flush();
        em.clear();
        em.getTransaction().commit();
        em.close();
        return book;

    }

    @Override
    public void deleteBookById(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Book book = em.find(Book.class,id);
        em.remove(book);
        em.flush();
        em.getTransaction().commit();
        em.close();

    }

    public EntityManager getEntityManager(){
        return emf.createEntityManager();


    }
}
