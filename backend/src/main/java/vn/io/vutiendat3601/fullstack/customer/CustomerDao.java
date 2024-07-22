package vn.io.vutiendat3601.fullstack.customer;

import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;

public interface CustomerDao {
  @NonNull
  List<Customer> selectAllCustomers();

  @NonNull
  Optional<Customer> selectCustomerById(@NonNull Long id);

  void insertCustomer(@NonNull Customer customer);

  void updateCustomer(@NonNull Customer customer);

  void deleteCustomerById(@NonNull Long id);

  boolean existsCustomerById(@NonNull Long id);

  boolean existsCustomerByEmail(@NonNull String email);
}
