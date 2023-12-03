package ru.netology.zverev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.netology.zverev.controller.dto.CustomerDTO;
import ru.netology.zverev.controller.dto.CustomersGetResponse;
import ru.netology.zverev.domain.Customer;
import ru.netology.zverev.service.CustomerService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping()
    public ResponseEntity<Object> getCustomers(){
        List<Customer> customers = customerService.getCustomers();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(customer -> new CustomerDTO(customer.getId(), customer.getName()))
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(customerDTOS);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable int id) {
        Customer customer = customerService.getCustomer(id);

        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }


    @PostMapping(path = "/")
    public ResponseEntity<Object> addEmployee(@RequestBody String name) {
        Customer createdCustomer = customerService.saveCustomer(name);
        CustomerDTO createdCustomerDTO = new CustomerDTO(createdCustomer.getId(), createdCustomer.getName());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCustomer.getId())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(createdCustomerDTO);
    }
}
