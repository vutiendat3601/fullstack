package vn.io.vutiendat3601.fullstack.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
  @Id
  @SequenceGenerator(name = "customer_id_sequence", sequenceName = "customer_sequence")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_sequence")
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private Integer age;

  public Customer(String name, String email, Integer age) {
    this.name = name;
    this.email = email;
    this.age = age;
  }
}
