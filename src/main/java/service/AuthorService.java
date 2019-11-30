package service;

import dao.AuthorDaoImpl;

import java.sql.Connection;

public class AuthorService {

  private final Connection connection;

  public AuthorService(Connection connection) {
    this.connection = connection;
  }

  public double getAvarageAgeOfUserByAuthor(String nameOfAuthor) {
    return new AuthorDaoImpl(connection).getAvarageAgeOfUserByAuthor(nameOfAuthor);
  }
}
