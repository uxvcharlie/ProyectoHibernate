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
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
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

    public boolean modificar(Libro  libro) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        /*Opcion 1*/
        /*em.merge(libro);*/

        Libro LibroBD = em.find(Libro.class, libro.getId());
        if(LibroBD == null){
            tx.rollback();
            return false;
        }

        LibroBD.setTitulo(libro.getTitulo());
        LibroBD.setAutor(libro.getAutor());
        LibroBD.setPrecio(libro.getPrecio());
        LibroBD.setAnio_publicacion(libro.getAnio_publicacion());
        tx.commit();

        return true;
    }

    public boolean eliminar(Long id){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Libro LibroBD = em.find(Libro.class, id);
            if(LibroBD == null){
                tx.rollback();
                return false;
            }
            em.remove(LibroBD);
            tx.commit();
            return true;
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
                throw e;
            }
        } finally {
            em.close();
        }
        return false;
    }
}
