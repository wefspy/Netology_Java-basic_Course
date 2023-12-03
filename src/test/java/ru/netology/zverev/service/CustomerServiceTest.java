package ru.netology.zverev.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.zverev.domain.Customer;
import ru.netology.zverev.domain.operation.Operation;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceTest {
    @Autowired
    CustomerService customerService;
    @Test
    void saveCustomer() throws NoSuchFieldException, IllegalAccessException {
        String name = "Tom";

        Customer savedCustomer = customerService.saveCustomer(name);

        Field field = customerService.getClass().getDeclaredField("storage");
        field.setAccessible(true);
        List<Customer> list = (List<Customer>) field.get(customerService);



        assertEquals(list.size(), savedCustomer.getId());
        assertEquals(name, savedCustomer.getName());
    }

    @Test
    void getCustomers() {
        List<Customer> customers = customerService.getCustomers();

        assertEquals(0, customers.get(0).getId());
        assertEquals("Spring", customers.get(0).getName());

        assertEquals(1, customers.get(1).getId());
        assertEquals("Boot", customers.get(1).getName());
    }

    @Test
    void getCustomer() {
        Customer customer = customerService.getCustomer(0);

        assertEquals(0, customer.getId());
        assertEquals("Spring", customer.getName());
    }
}