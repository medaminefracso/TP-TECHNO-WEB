package com.technowebtp.webapp.repositories;

import com.technowebtp.webapp.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDao {

  @PersistenceContext
  private EntityManager entityManager;

  public void save(User user) {
    // ...
    return;
  }

  public void getById(long id) {
    // ...
    return;
  }

}