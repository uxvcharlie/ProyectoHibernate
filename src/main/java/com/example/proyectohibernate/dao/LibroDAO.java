package com.example.proyectohibernate.dao;

import com.example.proyectohibernate.database.JPAUtil;
import com.example.proyectohibernate.model.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class LibroDAO {

    public static void guardar(Libro libro) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(libro);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
        }
        finally {
            em.close();
        }
    }

    public static List<Libro> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT l from Libro l order by l.id", Libro.class).getResultList();
        } finally {
            em.close();
        }
    }
}
