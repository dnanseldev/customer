package org.sandbox.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import org.sandbox.dto.CustomerDTO;
import org.sandbox.entity.CustomerEntity;
import org.sandbox.repository.CustomerRepository;

@ApplicationScoped
public class CustomerService {

  @Inject
  private CustomerRepository customerRepository;

  public List<CustomerDTO> findAllCustomers() {
    List<CustomerDTO> customers = new ArrayList<>();

    this.customerRepository
      .findAll()
      .stream()
      .forEach(item -> customers.add(this.mapCustomerEntityToDTO(item)));
    return customers;
  }

  public void createNewCustomer(CustomerDTO cDto) {
      this.customerRepository.persist(this.mapCustomerDtoToEntity(cDto));
  }

  public void changeCustomer(Long id, CustomerDTO cDto) {
    CustomerEntity cEntity = customerRepository.findById(id);

    cEntity.setName((cDto.name()));
    cEntity.setAddress(cDto.address());
    cEntity.setPhone(cDto.phone());
    cEntity.setEmail(cDto.email());
    cEntity.setAge(cDto.age());

    customerRepository.persist(cEntity);
  }

  public void deleteCustomer(Long id) {
    customerRepository.deleteById(id);
  }

  //Mappers
  private CustomerDTO mapCustomerEntityToDTO(CustomerEntity customer) {
    return new CustomerDTO(
      customer.getId(),
      customer.getName(),
      customer.getEmail(),
      customer.getPhone(),
      customer.getDocument(),
      customer.getAddress(),
      customer.getAge()
    );
  }

  private CustomerEntity mapCustomerDtoToEntity(CustomerDTO customer) {
    CustomerEntity customerEntity = new CustomerEntity();

    customerEntity.setAddress(customer.address());
    customerEntity.setAge(customer.age());
    customerEntity.setEmail(customer.email());
    customerEntity.setName(customer.name());
    customerEntity.setPhone(customer.phone());

    return customerEntity;
  }
}
