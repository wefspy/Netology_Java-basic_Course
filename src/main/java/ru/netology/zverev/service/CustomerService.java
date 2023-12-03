package ru.netology.zverev.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.netology.zverev.domain.Customer;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerService {
    private final List<Customer> storage = new ArrayList<>();
    @PostConstruct
    public void initStorage() {
        storage.add(new Customer(0, "Spring"));
        storage.add(new Customer(1, "Boot"));
    }

    public Customer saveCustomer(String name) {
        Customer customer = new Customer(generateId(), name);
        storage.add(customer);
        return getCustomer(customer.getId());
    }

    public List<Customer> getCustomers() {
        return storage;
    }

    public Customer getCustomer(int CustomerId) {
        return storage.stream()
                .filter(customer -> customer.getId() == CustomerId)
                .findFirst().orElse(null);
    }

    private int generateId() {
        return storage.size() + 1;
    }
}
