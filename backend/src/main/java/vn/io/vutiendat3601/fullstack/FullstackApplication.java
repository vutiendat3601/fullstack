package vn.io.vutiendat3601.fullstack;

import com.github.javafaker.Faker;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.io.vutiendat3601.fullstack.customer.Customer;
import vn.io.vutiendat3601.fullstack.customer.CustomerRepository;

@SpringBootApplication
public class FullstackApplication {
  public static void main(String[] args) {
    SpringApplication.run(FullstackApplication.class, args);
  }

  @Bean
  CommandLineRunner runner(CustomerRepository customerRepo) {
    final Faker faker = new Faker();
    final Random random = new Random();
    final List<Customer> customers = new LinkedList<>();

    final String[] domainNames = new String[500];
    for (int i = 0; i < domainNames.length; i++) {
      domainNames[i] = faker.internet().domainName();
    }
    for (int i = 0; i < 10; i++) {
      final String fullName = faker.name().fullName();

      String email = fullName.replace(". ", "");
      email = email.toLowerCase().replaceFirst(" ", ".");
      email = email.replaceAll(" ", "");
      email += "@" + domainNames[random.nextInt(0, domainNames.length)];
      customers.add(new Customer(fullName, email, random.nextInt(16, 99)));
    }

    return args -> {
      customerRepo.saveAll(customers);
    };
  }
}
