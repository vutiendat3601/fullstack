package vn.io.vutiendat3601.fullstack.customer;

import static org.mockito.Mockito.verify;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vn.io.vutiendat3601.fullstack.AbstractTestcontainersTest;

@ExtendWith(MockitoExtension.class)
public class CustomerJpaDataAccessServiceTest extends AbstractTestcontainersTest {
  private CustomerJpaDataAccessService underTest;

  @Mock private CustomerRepository customerRepo;

  @BeforeEach
  void setUp() {
    underTest = new CustomerJpaDataAccessService(customerRepo);
  }

  @Test
  void testDeleteCustomerById() {}

  @Test
  void testExistsCustomerByEmail() {}

  @Test
  void testExistsCustomerById() {}

  @Test
  void testInsertCustomer() {
    // Given
    final String email = UUID.randomUUID() + FAKER.internet().emailAddress();
    final String name = FAKER.name().fullName();
    final Integer age = FAKER.number().numberBetween(1, 80);
    final Customer customer = new Customer(email, name, age);

    // When
    underTest.insertCustomer(customer);

    // Then
    verify(customerRepo).save(customer);
  }

  @Test
  void testSelectAllCustomers() {
    // When
    underTest.selectAllCustomers();

    // Then
    verify(customerRepo).findAll();
  }

  @Test
  void testSelectCustomerById() {
    // Given
    final Long id = 1L;

    // When
    underTest.selectCustomerById(id);

    // Then
    verify(customerRepo).findById(id);
  }

  @Test
  void testUpdateCustomer() {
    // Given
    final String email = UUID.randomUUID() + FAKER.internet().emailAddress();
    final String name = FAKER.name().fullName();
    final Integer age = FAKER.number().numberBetween(1, 80);
    final Customer customer = new Customer(email, name, age);

    // When
    underTest.updateCustomer(customer);

    // Then
    verify(customerRepo).save(customer);
  }
}
