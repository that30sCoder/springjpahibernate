package com.that30sCoder.dao;

import com.that30sCoder.domain.Author;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jt on 8/28/21.
 */
@Component
public class AuthorDaoImpl implements AuthorDao {


    private final EntityManagerFactory emf;

    public AuthorDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager(){

        return emf.createEntityManager();
        }

    @Override
    public List<Author> findAll() {
        EntityManager em = getEntityManager();
        try {

            TypedQuery<Author> typedQuery = em.createNamedQuery("author_find_all", Author.class);
            return typedQuery.getResultList();
        } finally {
            em.close();
        }

    }

    @Override
    public List<Author> listAuthorByLastNameLike(String lastName) {
       EntityManager em = getEntityManager();
       try {
           Query query = em.createQuery("SELECT a from Author a where a.lastName like :last_name");
            query.setParameter("last_name",lastName + "%");
          List<Author> authorList=  query.getResultList();

           return authorList ;
       } finally {
           em.close();
       }
    }

    @Override
    public Author getById(Long id) {
        EntityManager em = getEntityManager();
        em.close();

        return getEntityManager().find(Author.class,id);

    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        EntityManager em = getEntityManager();
        TypedQuery<Author> query = getEntityManager().createQuery("SELECT a FROM Author a " +

                "WHERE a.firstName = :first_name and a.lastName = :last_name", Author.class);
            query.setParameter("first_name",firstName);
            query.setParameter("last_name",lastName);
        em.close();
        return query.getSingleResult();
    }

    @Override
    public Author saveNewAuthor(Author author) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(author);
        em.flush();
        em.getTransaction().commit();
        em.close();

        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        EntityManager em = getEntityManager();
        em.joinTransaction();
        em.merge(author);
        em.flush();
        em.clear();
        em.close();
        return em.find(Author.class,author.getId());
    }

    @Override
    public void deleteAuthorById(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Author author = em.find(Author.class,id);
        em.remove(author);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }
}
