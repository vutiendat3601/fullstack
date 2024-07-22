package vn.io.vutiendat3601.fullstack.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {

  @Override
  public Customer mapRow(@NonNull ResultSet rs, int rowCol) throws SQLException {
    return new Customer(
        rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs.getInt("age"));
  }
}
