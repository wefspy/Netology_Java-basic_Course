package ru.netology.zverev;


import ru.netology.zverev.service.AsyncInputOperationService;
import ru.netology.zverev.service.StatementService;

public class Main {
    public static void main(String[] args) {
        StatementService statementService = new StatementService();
        AsyncInputOperationService asyncInputOperationService = new AsyncInputOperationService(statementService);
        asyncInputOperationService.startAsyncOperationProcessing();
    }
}