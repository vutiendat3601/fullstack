package vn.io.vutiendat3601.fullstack.customer;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerListDataAccessService implements CustomerDao {
  private static List<Customer> customers;

  static {
    customers = new LinkedList<>();
    customers.add(new Customer(1L, "Dat", "vutien.dat.3601@gmail.com", 23));
    customers.add(new Customer(2L, "Messi", "messi@gmail.com", 37));
    customers.add(new Customer(3L, "Ronaldo", "ronaldo@gmail.com", 39));
    customers.add(new Customer(4L, "Yamal", "yamal@gmail.com", 17));
  }

  @Override
  public List<Customer> selectAllCustomers() {
    return customers;
  }

  @Override
  public Optional<Customer> selectCustomerById(Long id) {
    return customers.stream().filter(customer -> customer.getId().equals(id)).findFirst();
  }

  @Override
  public void insertCustomer(Customer customer) {
    customers.add(customer);
  }

  @Override
  public void updateCustomer(Customer customer) {
    customers.stream()
        .filter(c -> c.getId().equals(customer.getId()))
        .findFirst()
        .ifPresent(
            c -> {
              c.setName(customer.getName());
              c.setEmail(customer.getEmail());
              c.setAge(customer.getAge());
            });
  }

  @Override
  public void deleteCustomerById(Long id) {
    customers.removeIf(c -> c.getId().equals(id));
  }

  @Override
  public boolean existsCustomerById(Long id) {
    return customers.stream().anyMatch(customer -> customer.getId().equals(id));
  }

  @Override
  public boolean existsCustomerByEmail(String email) {
    return customers.stream().anyMatch(customer -> customer.getEmail().equals(email));
  }
}
