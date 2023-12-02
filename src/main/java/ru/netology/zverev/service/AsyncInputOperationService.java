package ru.netology.zverev.service;

import lombok.RequiredArgsConstructor;
import ru.netology.zverev.domain.operation.Operation;

import java.util.LinkedList;
import java.util.Queue;
@RequiredArgsConstructor
public class AsyncInputOperationService {
    private final Queue<Operation> queue = new LinkedList<>();
    private final StatementService statementService;

    public boolean offerOperation(Operation operation) {
        System.out.println("Operation added for processing" + operation);
        return queue.offer(operation);
    }

    public void startAsyncOperationProcessing() {
        Thread t = new Thread() {
            @Override
            public void run() {
                processQueue();
            }
        };
        t.start();
    }

    private void processQueue() {
        while (true) {
            Operation operation = queue.poll();
            if (operation == null) {
                try {
                    System.out.println("Waiting for next operation in queue");
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Processing operation:" + operation);
                processOperation(operation);
            }
        }
    }

    private void processOperation(Operation operation) { statementService.saveOperation(operation); }
}