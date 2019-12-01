package dao;

import connector.Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DaoFactory {

  private static Connection connection;
  private static DaoFactory daoFactory = new DaoFactory(Connector.getConnection());

  private DaoFactory(Connection connectionDB) {
    connection = connectionDB;
  }

  public AuthorDaoImpl authorDao(){
    return new AuthorDaoImpl(connection);
  }

  public BookDaoImpl bookDao(){
    return new BookDaoImpl(connection);
  }

  public UserDao userDao(){
    return new UserDao(connection);
  }

}