package ru.netology.zverev.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.netology.zverev.domain.Customer;
import ru.netology.zverev.domain.operation.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StatementService {
    private final Map<Integer, List<Operation>> storage = new HashMap<>();

    public List<Operation> saveOperation(Operation operation) {
        if (!storage.containsKey(operation.getCustomerID()))
            createListInMapForNewCustomer(operation.getCustomerID());
        List<Operation> operations = storage.get(operation.getCustomerID());
        operations.add(operation);
        return storage.get(operation.getCustomerID());
    }

    public String getAllOperations() {
        return storage.toString();
    }

    public List<Operation> getOperationsByCustomerId(int customerID) {
        if (storage.get(customerID) == null)
            return createListInMapForNewCustomer(customerID);
        return storage.get(customerID);
    }

    public List<Operation> deleteOperation(int operationID) {

        for (List<Operation> operations : storage.values()) {
            for (Operation operation : operations) {
                if (operation.getOperationID() == operationID) {
                    operations.remove(operationID);
                    return operations;
                }
            }
        }

        return new ArrayList<>();
    }

    private List<Operation> createListInMapForNewCustomer(int customerID) {
        List<Operation> customerOperations = new ArrayList<>();
        storage.put(customerID, customerOperations);
        return customerOperations;
    }
}