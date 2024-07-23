package vn.io.vutiendat3601.fullstack.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vn.io.vutiendat3601.fullstack.AbstractTestcontainersTest;

public class CustomerJdbcDataAccessServiceTest extends AbstractTestcontainersTest {
  private CustomerJdbcDataAccessService testObject;
  private final CustomerRowMapper customerRowMapper = new CustomerRowMapper();

  @BeforeEach
  void setUp() {
    testObject = new CustomerJdbcDataAccessService(getJdbcTemplate(), customerRowMapper);
  }

  @Test
  void testDeleteCustomerById() {
    final String email = FAKER.internet().safeEmailAddress() + UUID.randomUUID();
    final Customer customer = new Customer(FAKER.name().fullName(), email, 20);
    testObject.insertCustomer(customer);
    final Long id =
        testObject.selectAllCustomers().stream()
            .filter(c -> c.getEmail().equals(email))
            .map(Customer::getId)
            .findFirst()
            .orElseThrow();

    testObject.deleteCustomerById(id);

    assertThat(testObject.selectCustomerById(id)).isEmpty();
  }

  @Test
  void testExistsCustomerByEmail() {
    final String email = FAKER.internet().safeEmailAddress() + UUID.randomUUID();
    final Customer customer = new Customer(FAKER.name().fullName(), email, 20);
    testObject.insertCustomer(customer);

    final boolean actual = testObject.existsCustomerByEmail(email);

    assertTrue(actual);
  }

  @Test
  void testExistsCustomerById() {
    final String email = FAKER.internet().safeEmailAddress() + UUID.randomUUID();
    final Customer customer = new Customer(FAKER.name().fullName(), email, 20);
    testObject.insertCustomer(customer);
    final Long id =
        testObject.selectAllCustomers().stream()
            .filter(c -> c.getEmail().equals(email))
            .map(Customer::getId)
            .findFirst()
            .orElseThrow();

    final boolean actual = testObject.existsCustomerById(id);

    assertTrue(actual);
  }

  @Test
  void testInsertCustomer() {
    final String email = FAKER.internet().safeEmailAddress() + UUID.randomUUID();
    final Customer customer = new Customer(FAKER.name().fullName(), email, 20);
    testObject.insertCustomer(customer);
    final Long id =
        testObject.selectAllCustomers().stream()
            .filter(c -> c.getEmail().equals(email))
            .map(Customer::getId)
            .findFirst()
            .orElseThrow();

    final Optional<Customer> actual = testObject.selectCustomerById(id);

    assertThat(actual)
        .isPresent()
        .hasValueSatisfying(
            c -> {
              assertThat(c.getId()).isEqualTo(id);
              assertThat(c.getName()).isEqualTo(customer.getName());
              assertThat(c.getEmail()).isEqualTo(customer.getEmail());
              assertThat(c.getAge()).isEqualTo(customer.getAge());
            });
  }

  @Test
  void testSelectAllCustomers() {
    final String email = FAKER.internet().safeEmailAddress() + UUID.randomUUID();
    final Customer customer = new Customer(FAKER.name().fullName(), email, 20);
    testObject.insertCustomer(customer);

    final List<Customer> actual = testObject.selectAllCustomers();

    assertThat(actual).isNotEmpty();
  }

  @Test
  void testSelectCustomerById() {
    // Given
    final String email = FAKER.internet().safeEmailAddress() + UUID.randomUUID();
    final Customer customer = new Customer(FAKER.name().fullName(), email, 20);
    testObject.insertCustomer(customer);
    final Long id =
        testObject.selectAllCustomers().stream()
            .filter(c -> c.getEmail().equals(email))
            .map(Customer::getId)
            .findFirst()
            .orElseThrow();

    // When
    final Optional<Customer> actual = testObject.selectCustomerById(id);

    // Then
    assertThat(actual)
        .isPresent()
        .hasValueSatisfying(
            c -> {
              assertThat(c.getId()).isEqualTo(id);
              assertThat(c.getName()).isEqualTo(customer.getName());
              assertThat(c.getEmail()).isEqualTo(customer.getEmail());
              assertThat(c.getAge()).isEqualTo(customer.getAge());
            });
  }

  @Test
  void willReturnEmptyOptionalWhenSelectCustomerById() {
    final Long id = -1L;

    final Optional<Customer> actual = testObject.selectCustomerById(id);

    assertThat(actual).isEmpty();
  }

  @Test
  void testUpdateCustomer() {
    // Given
    final String email = FAKER.internet().safeEmailAddress() + UUID.randomUUID();
    final Customer customer = new Customer(FAKER.name().fullName(), email, 20);
    testObject.insertCustomer(customer);
    final Long id =
        testObject.selectAllCustomers().stream()
            .filter(c -> c.getEmail().equals(email))
            .map(Customer::getId)
            .findFirst()
            .orElseThrow();
    final String updatedName = FAKER.name().fullName();
    final String updatedEmail = FAKER.internet().safeEmailAddress() + UUID.randomUUID();
    final Integer updatedAge = 31;
    final Customer updatedCustomer = new Customer(id, updatedName, updatedEmail, updatedAge);

    // When
    testObject.updateCustomer(updatedCustomer);
    final Optional<Customer> actual = testObject.selectCustomerById(id);

    // Then
    assertThat(actual)
        .isPresent()
        .hasValueSatisfying(
            c -> {
              assertThat(c.getId()).isEqualTo(id);
              assertThat(c.getName()).isEqualTo(updatedName);
              assertThat(c.getEmail()).isEqualTo(updatedEmail);
              assertThat(c.getAge()).isEqualTo(updatedAge);
            });
  }
}
