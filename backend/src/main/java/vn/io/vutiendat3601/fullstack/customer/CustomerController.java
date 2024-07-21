package vn.io.vutiendat3601.fullstack.customer;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
@RestController
public class CustomerController {
  private final CustomerService customerService;

  @GetMapping
  public List<Customer> getCustomers() {
    return customerService.getAllCustomers();
  }

  @GetMapping(path = "{id}")
  public Customer getCustomer(@PathVariable Long id) {
    final Customer customer = customerService.getCustomer(id);
    return customer;
  }

  @PostMapping
  public void registerCustomer(
      @RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
    customerService.addCustomer(customerRegistrationRequest);
  }

  @PutMapping(path = "{id}")
  public void updateCustomer(
      @PathVariable Long id, @RequestBody CustomerUpdateRequest customerUpdateRequest) {
    customerService.updateCustomer(id, customerUpdateRequest);
  }

  @DeleteMapping(path = "{id}")
  public void deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomer(id);
  }
}
