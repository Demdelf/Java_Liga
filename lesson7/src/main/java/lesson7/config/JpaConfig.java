package lesson7.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaConfig {
  private static EntityManagerFactory entityManagerFactory;

  public static EntityManagerFactory getEntityManagerFactory() {
    if (entityManagerFactory == null) {
      entityManagerFactory = Persistence.createEntityManagerFactory("lesson7");
    }

    return entityManagerFactory;
  }
}
