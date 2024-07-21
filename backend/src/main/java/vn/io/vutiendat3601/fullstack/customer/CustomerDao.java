package vn.io.vutiendat3601.fullstack.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
  List<Customer> selectAllCustomers();

  Optional<Customer> selectCustomerById(Long id);

  void insertCustomer(Customer customer);

  void updateCustomer(Customer customer);

  void deleteCustomerById(Long id);

  boolean existsCustomerById(Long id);

  boolean existsCustomerByEmail(String email);
}
