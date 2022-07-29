package dao.interfaces.mappers.mapperInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
  T rowMapper(ResultSet resultSet) throws SQLException;
}