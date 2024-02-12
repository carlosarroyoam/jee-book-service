package com.carlosarroyoam.book.service.producers;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerProducer {

	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		return Persistence.createEntityManagerFactory("com.carlosarroyoam.book-service").createEntityManager();
	}

	public void disposeEntityManager(@Disposes EntityManager entityManager) {
		if (entityManager.isOpen()) {
			entityManager.close();
		}
	}

}
