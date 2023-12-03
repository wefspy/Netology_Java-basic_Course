package ru.netology.zverev.service;

import org.springframework.stereotype.Component;
import ru.netology.zverev.domain.operation.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StatementService {
    private final Map<Integer, List<Operation>> storage = new HashMap<>();

    public void saveOperation(Operation operation) {
        if (!storage.containsKey(operation.getCustomerID()))
            createListInMapForNewCustomer(operation.getCustomerID());
        List<Operation> operations = storage.get(operation.getCustomerID());
        operations.add(operation);
    }

    public String getAllOperations() {
        return storage.toString();
    }

    public List<Operation> getOperationsByCustomerId(int customerID) {
        if (storage.get(customerID) == null)
            return createListInMapForNewCustomer(customerID);
        return storage.get(customerID);
    }

    public void deleteOperation(int operationID) {
        List<Operation> needValue = null;
        for (List<Operation> operations : storage.values()) {
            for (Operation operation : operations) {
                if (operation.getOperationID() == operationID) {
                    needValue = operations;
                    break;
                }
            }
        }
        if (needValue != null ) {
            needValue.remove(operationID);
        }
    }

    private List<Operation> createListInMapForNewCustomer(int customerID) {
        List<Operation> customerOperations = new ArrayList<>();
        storage.put(customerID, customerOperations);
        return customerOperations;
    }
}