package br.upe.tudu.data.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class LocalStorageConnection {
    private static EntityManagerFactory entityManagerFactory;

    static {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("br.upe.tudu.localstorage");
        }
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }


}
