package ru.netology.zverev.domain.operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.netology.zverev.domain.ConsolePrintable;

@Data
@AllArgsConstructor
public class Operation implements ConsolePrintable {
    private int operationID;
    private int customerID;
    private int sum;
    private String currency;
    private String merchant;

    public void printToConsole() {
        System.out.printf("[operationID = %d, customerID = %d sum = %d currency = %s merchant = %s]", operationID, customerID, sum, currency, merchant);
        System.out.println();
    }
}
