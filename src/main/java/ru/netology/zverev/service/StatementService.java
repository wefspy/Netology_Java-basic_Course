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
        List<Operation> operations = storage.get(operation.getCustomerID());
        if (operations == null) {
            List<Operation> customerOperations = new ArrayList<>();
            customerOperations.add(operation);
            storage.put(operation.getCustomerID(), customerOperations);
        }
        else {
            operations.add(operation);
        }
    }

    public String getOperations() {
        return storage.toString();
    }
}
