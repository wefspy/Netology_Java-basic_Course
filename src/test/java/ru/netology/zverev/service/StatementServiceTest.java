package ru.netology.zverev.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.zverev.domain.operation.Operation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StatementServiceTest {
    @Autowired
    StatementService statementService;

    @Test
    void getAllOperationsNotNull() {
        assertNotNull(statementService.getAllOperations());
    }

    @Test
    void saveOperationOfNewCustomer() throws NoSuchFieldException, IllegalAccessException {
        int idOperation = 0;
        int idNewCustomer = 0;
        int sumOperation = 100;
        String currencyOperation = "Euro";
        String merchantOperation = "Amazon";

        statementService.saveOperation(new Operation(idOperation, idNewCustomer, sumOperation, currencyOperation, merchantOperation));

        Field field = statementService.getClass().getDeclaredField("storage");
        field.setAccessible(true);
        Map<Integer, List<Operation>> map = (Map<Integer, List<Operation>>) field.get(statementService);
        Operation savedOperation = map.get(0).get(0);


        assertEquals(idOperation, savedOperation.getOperationID());
        assertEquals(idNewCustomer, savedOperation.getCustomerID());
        assertEquals(sumOperation, savedOperation.getSum());
        assertEquals(currencyOperation, savedOperation.getCurrency());
        assertEquals(merchantOperation, savedOperation.getMerchant());
    }

    @Test
    void getOperationsByCustomerId() throws NoSuchFieldException, IllegalAccessException {
        Field field = statementService.getClass().getDeclaredField("storage");
        field.setAccessible(true);
        Map<Integer, List<Operation>> map = (Map<Integer, List<Operation>>) field.get(statementService);

        int idOperation = 1;
        int idNewCustomer = 1;
        int sumOperation = 100;
        String currencyOperation = "Euro";
        String merchantOperation = "eBay";
        List<Operation> listOperations = new ArrayList<>();
        listOperations.add(new Operation(idOperation, idNewCustomer, sumOperation, currencyOperation, merchantOperation));
        map.put(1, listOperations);

        Operation savedOperation = statementService.getOperationsByCustomerId(idNewCustomer).get(0);


        assertEquals(idOperation, savedOperation.getOperationID());
        assertEquals(idNewCustomer, savedOperation.getCustomerID());
        assertEquals(sumOperation, savedOperation.getSum());
        assertEquals(currencyOperation, savedOperation.getCurrency());
        assertEquals(merchantOperation, savedOperation.getMerchant());
    }

    @Test
    void deleteOperation() throws NoSuchFieldException, IllegalAccessException {
        int idOperation = 0;
        int idCustomer = 0;
        statementService.saveOperation(new Operation(idOperation, idCustomer, 100, "Euro", "Amazon"));

        statementService.deleteOperation(idOperation);

        Field field = statementService.getClass().getDeclaredField("storage");
        field.setAccessible(true);
        Map<Integer, List<Operation>> map = (Map<Integer, List<Operation>>) field.get(statementService);
        List<Operation> operations = map.get(idCustomer);

        assertTrue(operations.isEmpty());
    }
}