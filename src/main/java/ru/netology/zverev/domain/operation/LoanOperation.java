package ru.netology.zverev.domain.operation;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class LoanOperation extends Operation {
    private int loadId;

    public LoanOperation(int operationID, int customerID, int sum, String currency, String merchant, int loadId) {
        super(operationID, customerID, sum, currency, merchant);
        this.loadId = loadId;
    }

    public void printToConsole() {
        System.out.printf("[loadId = %d]", loadId);
        System.out.println();
    }
}
