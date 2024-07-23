package vn.io.vutiendat3601.fullstack;

import com.github.javafaker.Faker;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers // Manage the containers
public abstract class AbstractTestcontainersTest {
  @Container protected static final PostgreSQLContainer<?> POSTGRES_CONTAINER;

  static {
    POSTGRES_CONTAINER = new PostgreSQLContainer<>("postgres:latest");
    POSTGRES_CONTAINER.withDatabaseName("fullstack-dao-unit-test");
    POSTGRES_CONTAINER.withUsername("fullstack");
    POSTGRES_CONTAINER.withPassword("fullstack");
  }

  @DynamicPropertySource // Set or replace properties at runtime
  static void registerDataSourceProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", POSTGRES_CONTAINER::getJdbcUrl);
    registry.add("spring.datasource.username", POSTGRES_CONTAINER::getUsername);
    registry.add("spring.datasource.password", POSTGRES_CONTAINER::getPassword);
  }

  protected static DataSource getDataSource() {
    return DataSourceBuilder.create()
        .driverClassName(POSTGRES_CONTAINER.getDriverClassName())
        .url(POSTGRES_CONTAINER.getJdbcUrl())
        .username(POSTGRES_CONTAINER.getUsername())
        .password(POSTGRES_CONTAINER.getPassword())
        .build();
  }

  protected static JdbcTemplate getJdbcTemplate() {
    return new JdbcTemplate(getDataSource());
  }

  protected static final Faker FAKER = new Faker();

  @BeforeAll
  static void beforeAll() {
    final Flyway flyway =
        Flyway.configure()
            .dataSource(
                POSTGRES_CONTAINER.getJdbcUrl(),
                POSTGRES_CONTAINER.getUsername(),
                POSTGRES_CONTAINER.getPassword())
            .load();
    flyway.migrate();
  }
}
