package vn.io.vutiendat3601.fullstack.customer;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CustomerJpaDataAccessService implements CustomerDao {
  private final CustomerRepository customerRepo;

  @NonNull
  @Override
  public List<Customer> selectAllCustomers() {
    return customerRepo.findAll();
  }

  @NonNull
  @Override
  public Optional<Customer> selectCustomerById(@NonNull Long id) {
    return customerRepo.findById(id);
  }

  @Override
  public void insertCustomer(@NonNull Customer customer) {
    customerRepo.save(customer);
  }

  @Override
  public void updateCustomer(@NonNull Customer customer) {
    customerRepo.save(customer);
  }

  @Override
  public void deleteCustomerById(@NonNull Long id) {
    customerRepo.deleteById(id);
  }

  @Override
  public boolean existsCustomerById(@NonNull Long id) {
    return customerRepo.existsById(id);
  }

  @Override
  public boolean existsCustomerByEmail(@NonNull String email) {
    return customerRepo.existsCustomerByEmail(email);
  }
}
