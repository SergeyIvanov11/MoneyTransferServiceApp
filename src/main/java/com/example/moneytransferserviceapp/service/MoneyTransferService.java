package com.example.moneytransferserviceapp.service;

import com.example.moneytransferserviceapp.exception.InputDataException;
import com.example.moneytransferserviceapp.repository.MoneyTransferRepository;
import com.example.moneytransferserviceapp.request.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MoneyTransferService {
    private final MoneyTransferRepository repository;

    public MoneyTransferService(MoneyTransferRepository repository) {
        this.repository = repository;
    }

    public String transfer(MoneyTransferRequest request) {
        String id = repository.tranfer(request);
        repository.putCode(id, "0000");
        return id;
    }

    public String confirmOperation(ConfirmOperationRequest request) {
        if (request == null) {
            throw new InputDataException("Запрос пуст");
        }
        String operationId = request.getOperationId();
        if (request.getCode().equals(repository.getCode(operationId))) {
            return repository.confirmOperation(request);
        } else {
            throw new InputDataException("Код не подходит!");
        }
    }
}
