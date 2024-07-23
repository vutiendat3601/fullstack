package vn.io.vutiendat3601.fullstack.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import vn.io.vutiendat3601.fullstack.AbstractTestcontainersTest;

// @SpringBootTest => Bad practice
@DataJpaTest
@AutoConfigureTestDatabase(
    replace = Replace.NONE) // The Replace.NONE is do not replace the DataSource
@Slf4j
public class CustomerRepositoryTest extends AbstractTestcontainersTest {
  @Autowired private CustomerRepository underTest;

  @BeforeEach
  void setUp(ApplicationContext appContext) {
    log.info("Bean count: {}", appContext.getBeanDefinitionCount());
    log.info("Clean up the database");
    underTest.deleteAll();
  }

  @Test
  void testExistsCustomerByEmail() {
    // Given
    final String email = FAKER.internet().emailAddress();
    final String name = FAKER.name().fullName();
    final Integer age = FAKER.number().numberBetween(1, 100);
    final Customer customer = new Customer(name, email, age);
    underTest.save(customer);

    // When
    final boolean actual = underTest.existsCustomerByEmail(email);

    // Then
    assertTrue(actual);
  }

  @Test
  void willReturnFalseWhenEmailIsNotExists() {
    // Given
    final String email = UUID.randomUUID() + FAKER.internet().emailAddress();

    // When
    final boolean actual = underTest.existsCustomerByEmail(email);

    // Then
    assertFalse(actual);
  }
}
