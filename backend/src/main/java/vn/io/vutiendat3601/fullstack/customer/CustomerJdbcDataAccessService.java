package vn.io.vutiendat3601.fullstack.customer;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CustomerJdbcDataAccessService implements CustomerDao {
  private final JdbcTemplate jdbcTemplate;
  private final CustomerRowMapper customerRowMapper;

  @NonNull
  @Override
  public List<Customer> selectAllCustomers() {
    final String sql =
        """
        SELECT id, name, email, age FROM customer
        """;
    return jdbcTemplate.query(sql, customerRowMapper);
  }

  @NonNull
  @Override
  public Optional<Customer> selectCustomerById(@NonNull Long id) {
    final String sql =
        """
        SELECT id, name, email, age FROM customer WHERE id = ?
        """;
    return jdbcTemplate.query(sql, customerRowMapper, id).stream().findFirst();
  }

  @Override
  public void insertCustomer(@NonNull Customer customer) {
    final String sql =
        """
        INSERT INTO customer (name, email, age)
        VALUES (?, ?, ?)
        """;
    jdbcTemplate.update(sql, customer.getName(), customer.getEmail(), customer.getAge());
  }

  @Override
  public void updateCustomer(@NonNull Customer customer) {
    if (customer.getName() != null) {
      final String sql =
          """
          UPDATE customer
          SET name = ?
          WHERE id = ?
          """;
      jdbcTemplate.update(sql, customer.getName(), customer.getId());
    }
    if (customer.getAge() != null) {
      final String sql =
          """
          UPDATE customer
          SET age = ?
          WHERE id = ?
          """;
      jdbcTemplate.update(sql, customer.getAge(), customer.getId());
    }
    if (customer.getEmail() != null) {
      final String sql =
          """
          UPDATE customer
          SET email = ?
          WHERE id = ?
          """;
      jdbcTemplate.update(sql, customer.getEmail(), customer.getId());
    }
  }

  @Override
  public void deleteCustomerById(@NonNull Long id) {
    final String sql =
        """
        DELETE FROM customer WHERE id = ?
        """;
    jdbcTemplate.update(sql, id);
  }

  @Override
  public boolean existsCustomerById(@NonNull Long id) {
    final String sql =
        """
        SELECT COUNT(id) FROM customer WHERE id = ?
        """;
    final Long count = jdbcTemplate.queryForObject(sql, Long.class, id);
    return count != null && count > 0;
  }

  @Override
  public boolean existsCustomerByEmail(@NonNull String email) {
    final String sql =
        """
        SELECT COUNT(email) FROM customer WHERE email = ?
        """;
    final Long count = jdbcTemplate.queryForObject(sql, Long.class, email);
    return count != null && count > 0;
  }
}
