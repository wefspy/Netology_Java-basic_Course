package ru.netology.zverev.service;

import ru.netology.zverev.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private final List<Customer> storage = new ArrayList<>();

    public void addCustomer(int id, String name) {
        Customer customer = new Customer(id, name);
        storage.add(customer);
    }
}
