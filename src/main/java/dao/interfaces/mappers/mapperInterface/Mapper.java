package dao.interfaces.mappers.mapperInterface;

import java.sql.ResultSet;

public interface Mapper<T> {
  T rowMapper(ResultSet resultSet);
}