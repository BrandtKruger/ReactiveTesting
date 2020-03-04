package com.kruger.reactivetesting.service;

import com.kruger.reactivetesting.model.Transaction;
import com.kruger.reactivetesting.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Service
public class TransactionServiceImpl implements iTransactionService, Serializable {

    private TransactionRepository transactionRepository;

    @Override
    public Flux<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Mono<Transaction> getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId);
    }

    @Override
    public Flux<Transaction> getTransactionsByCardNumber(String cardNumber) {
        return transactionRepository.findTransactionsByCardNumber(cardNumber);
    }

    @Override
    public Flux<Transaction> getTransactionByMerchantId(String merchantId) {
        return transactionRepository.findTransactionByMerchantId(merchantId);
    }

    @Override
    public Flux<Transaction> getTransactionByTerminalId(String terminalId) {
        return transactionRepository.findTransactionByTerminalId(terminalId);
    }

    @Override
    public Flux<Transaction> getTransactionByPosIdAndOperatorId(String posId, String operatorId) {
        return transactionRepository.findTransactionByPosIdAndOperatorId(posId, operatorId);
    }

    public Mono<Transaction> save(Transaction transaction){
        return transactionRepository.save(transaction);
    }
}
