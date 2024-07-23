package vn.io.vutiendat3601.fullstack.customer;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import vn.io.vutiendat3601.fullstack.exception.RequestValidationException;
import vn.io.vutiendat3601.fullstack.exception.ResourceDuplicationException;
import vn.io.vutiendat3601.fullstack.exception.ResourceNotFoundException;

@Service
public class CustomerService {
  private final CustomerDao customerDao;

  public CustomerService(@Qualifier("customerJdbcDataAccessService") CustomerDao customerDao) {
    this.customerDao = customerDao;
  }

  public List<Customer> getAllCustomers() {
    return customerDao.selectAllCustomers();
  }

  public Customer getCustomer(Long id) {
    return customerDao
        .selectCustomerById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("Customer with id [%s] not found".formatted(id)));
  }

  public void addCustomer(CustomerRegistrationRequest customerRegReq) {
    // Check if exists email
    if (customerDao.existsCustomerByEmail(customerRegReq.email())) {
      throw new ResourceDuplicationException(
          "Email already taken".formatted(customerRegReq.email()));
    }
    customerDao.insertCustomer(
        new Customer(customerRegReq.name(), customerRegReq.email(), customerRegReq.age()));
  }

  public void updateCustomer(Long id, CustomerUpdateRequest customerUpdateReq) {
    Customer customer = getCustomer(id);
    boolean isChanged = false;

    if (customerUpdateReq.name() != null && !customerUpdateReq.name().equals(customer.getName())) {
      customer.setName(customerUpdateReq.name());
      isChanged = true;
    }

    if (customerUpdateReq.age() != null && !customerUpdateReq.age().equals(customer.getAge())) {
      customer.setAge(customerUpdateReq.age());
      isChanged = true;
    }

    if (customerUpdateReq.email() != null
        && !customerUpdateReq.email().equals(customer.getEmail())) {
      if (customerDao.existsCustomerByEmail(customerUpdateReq.email())) {
        throw new ResourceDuplicationException(
            "Email already taken".formatted(customerUpdateReq.email()));
      }
      customer.setEmail(customerUpdateReq.email());
      isChanged = true;
    }
    if (!isChanged) {
      throw new RequestValidationException("No data changes found");
    }
    customerDao.updateCustomer(customer);
  }

  public void deleteCustomer(Long id) {
    if (!customerDao.existsCustomerById(id)) {
      throw new ResourceNotFoundException("Customer with id [%s] not found".formatted(id));
    }
    customerDao.deleteCustomerById(id);
  }
}
