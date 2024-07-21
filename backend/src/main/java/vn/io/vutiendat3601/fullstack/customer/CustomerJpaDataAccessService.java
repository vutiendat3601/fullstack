package vn.io.vutiendat3601.fullstack.customer;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CustomerJpaDataAccessService implements CustomerDao {
  private final CustomerRepository customerRepo;

  @Override
  public List<Customer> selectAllCustomers() {
    return customerRepo.findAll();
  }

  @Override
  public Optional<Customer> selectCustomerById(Long id) {
    return customerRepo.findById(id);
  }

  @Override
  public void insertCustomer(Customer customer) {
    customerRepo.save(customer);
  }

  @Override
  public void updateCustomer(Customer customer) {
    customerRepo.save(customer);
  }

  @Override
  public void deleteCustomerById(Long id) {
    customerRepo.deleteById(id);
  }

  @Override
  public boolean existsCustomerById(Long id) {
    return customerRepo.existsById(id);
  }

  @Override
  public boolean existsCustomerByEmail(String email) {
    return customerRepo.existsCustomerByEmail(email);
  }
}
