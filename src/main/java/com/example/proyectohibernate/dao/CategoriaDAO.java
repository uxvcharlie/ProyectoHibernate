package com.example.proyectohibernate.dao;

import com.example.proyectohibernate.database.JPAUtil;
import com.example.proyectohibernate.model.Categoria;
import com.example.proyectohibernate.model.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

import java.util.List;

public class CategoriaDAO {

    public static void guardar(Categoria categoria) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(categoria);
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

    public static List<Categoria> listarCategorias() {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            return em.createQuery("SELECT c FROM Categoria c order by c.id", Categoria.class).getResultList();
        } finally {
            em.close();
        }


    }

    public boolean modificar(Categoria categoria) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Categoria categoriaAux = em.find(Categoria.class, categoria.getId());

        if(categoriaAux == null){
            tx.rollback();
            return false;
        }

        categoriaAux.setId(categoria.getId());
        categoriaAux.setNombre(categoria.getNombre());
        categoriaAux.setDescripcion(categoria.getDescripcion());
        tx.commit();

        return true;
    }

    public boolean eliminar(Long id){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Categoria categoriaAux = em.find(Categoria.class, id);
            if(categoriaAux == null){
                tx.rollback();
                return false;
            }
            em.remove(categoriaAux);
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

    public static List<Categoria> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Categoria c ORDER BY c.id", Categoria.class).getResultList();
        } finally {
            em.close();
        }
    }
}
