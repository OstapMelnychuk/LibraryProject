package connector;

import models.User;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface AutoClosable {
  void close() throws Exception;
}
