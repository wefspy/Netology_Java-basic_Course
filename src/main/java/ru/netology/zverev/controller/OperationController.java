package ru.netology.zverev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.netology.zverev.domain.operation.Operation;
import ru.netology.zverev.service.StatementService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "api/operations")
@RequiredArgsConstructor
public class OperationController {
    private final StatementService statementService;

    @GetMapping()
    public ResponseEntity<Object> getAllOperations(){
        String operations = statementService.getAllOperations();

        return ResponseEntity.status(HttpStatus.OK).body(operations);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getOperationsByCustomerID(@PathVariable int id){
        List<Operation> operations = statementService.getOperationsByCustomerId(id);

        return ResponseEntity.status(HttpStatus.OK).body(operations);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteOperation(@PathVariable int id) {
        List<Operation> operations = statementService.deleteOperation(id);

        return ResponseEntity.status(HttpStatus.OK).body(operations);
    }

    @PostMapping(path = "/")
    public ResponseEntity<Object> saveOperation(@RequestBody Operation operation) {
        List<Operation> operations = statementService.saveOperation(operation);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(operation.getCustomerID())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(operations);
    }
}
