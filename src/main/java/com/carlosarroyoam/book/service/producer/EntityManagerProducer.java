package com.carlosarroyoam.book.service.producer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

  private EntityManagerFactory entityManagerFactory;

  @PostConstruct
  public void init() {
    entityManagerFactory = Persistence
        .createEntityManagerFactory("com.carlosarroyoam.book-service");
  }

  @PreDestroy
  public void destroy() {
    if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
      entityManagerFactory.close();
    }
  }

  @Produces
  @RequestScoped
  public EntityManager createEntityManager() {
    return entityManagerFactory.createEntityManager();
  }

  public void disposeEntityManager(@Disposes EntityManager entityManager) {
    if (entityManager.isOpen()) {
      entityManager.close();
    }
  }
}