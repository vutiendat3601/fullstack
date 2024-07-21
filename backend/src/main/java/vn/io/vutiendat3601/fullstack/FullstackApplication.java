package vn.io.vutiendat3601.fullstack;

import java.util.LinkedList;
import java.util.List;
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
    final List<Customer> customers = new LinkedList<>();
    customers.add(new Customer(1L, "Dat", "vutien.dat.3601@gmail.com", 23));
    customers.add(new Customer(2L, "Messi", "messi@gmail.com", 37));
    customers.add(new Customer(3L, "Ronaldo", "ronaldo@gmail.com", 39));
    customers.add(new Customer(4L, "Yamal", "yamal@gmail.com", 17));
    return args -> {
      customerRepo.saveAll(customers);
    };
  }
}
