package com.employeeapi.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.Valid;

public abstract class GenericDAO<T, I extends Serializable> {

   @PersistenceContext
   protected EntityManager entityManager;

   private Class<T> persistedClass;

   protected GenericDAO() {
   }

   protected GenericDAO(Class<T> persistedClass) {
       this();
       this.persistedClass = persistedClass;
   }

   public T save(@Valid T entity) {
       EntityTransaction t = entityManager.getTransaction();
       t.begin();
       entityManager.persist(entity);
       entityManager.flush();
       t.commit();
       return entity;
   }

   public T update(@Valid T entity) {
       EntityTransaction t = entityManager.getTransaction();
       t.begin();
       entityManager.merge(entity);
       entityManager.flush();
       t.commit();
       return entity;
   }
   
   public void delete(I id) {
       T entity = entityManager.find(persistedClass, id);
       EntityTransaction tx = entityManager.getTransaction();
       tx.begin();
       T mergedEntity = entityManager.merge(entity);
       entityManager.remove(mergedEntity);
       entityManager.flush();
       tx.commit();
   }

   public List<T> getList() {
	   EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
       if (entityManager == null) {
                this.entityManager = factory.createEntityManager();
       }
       CriteriaBuilder builder = entityManager.getCriteriaBuilder();
       CriteriaQuery<T> query = builder.createQuery(persistedClass);
       query.from(persistedClass);
       return entityManager.createQuery(query).getResultList();
   }
}