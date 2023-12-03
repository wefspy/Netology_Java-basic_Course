package ru.netology.zverev.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.zverev.domain.operation.Operation;

import java.lang.reflect.Field;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AsyncInputOperationServiceTest {
    @Autowired
    AsyncInputOperationService asyncInputOperationService;

    @Test
    void startAsyncOperationProcessing() {
        int starCountThreads = Thread.activeCount();
        for(int i = 0; i < 3; i++)
            asyncInputOperationService.startAsyncOperationProcessing();
        int endCountThreads = Thread.activeCount();

        assertTrue(endCountThreads > starCountThreads);
    }

    @Test
    void offerOperation() throws NoSuchFieldException, IllegalAccessException {
        Field field = asyncInputOperationService.getClass().getDeclaredField("queue");
        field.setAccessible(true);
        Queue<Operation> queue = (Queue<Operation>) field.get(asyncInputOperationService);

        int startSizeQueue = queue.size();

        asyncInputOperationService.offerOperation(new Operation(0, 0, 100, "Rub", "eBay"));

        int endSizeQueue = queue.size();

        assertTrue(endSizeQueue > startSizeQueue);
    }
}