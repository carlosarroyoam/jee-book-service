package com.carlosarroyoam.library.producers;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * CDI producer bean to manage {@link EntityManager} instances for our
 * persistence unit.
 */
public class EntityManagerProducer {
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	/**
	 * Create an {@link EntityManager} instance for our persistence unit and make it
	 * injectable as a CDI bean.
	 *
	 * @return the instance
	 */
	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	/**
	 * Dispose and close the given {@link EntityManager} instance.
	 *
	 * @param entityManager the instance to close
	 */
	public void disposeEntityManager(@Disposes EntityManager entityManager) {
		entityManager.close();
	}
}
