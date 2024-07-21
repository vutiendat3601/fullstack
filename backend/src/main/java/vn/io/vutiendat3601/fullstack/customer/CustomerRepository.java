package vn.io.vutiendat3601.fullstack.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  boolean existsCustomerByEmail(String email);
}
