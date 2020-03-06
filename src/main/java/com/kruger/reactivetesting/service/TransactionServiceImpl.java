package com.kruger.reactivetesting.service;

import com.kruger.reactivetesting.model.Transaction;
import com.kruger.reactivetesting.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    public Flux<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public Mono<Transaction> getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId);
    }

    public Flux<Transaction> getTransactionsByCardNumber(String cardNumber) {
        return transactionRepository.findTransactionsByCardNumber(cardNumber);
    }

    public Flux<Transaction> getTransactionByMerchantId(String merchantId) {
        return transactionRepository.findTransactionByMerchantId(merchantId);
    }

    public Flux<Transaction> getTransactionByTerminalId(String terminalId) {
        return transactionRepository.findTransactionByTerminalId(terminalId);
    }

    public Flux<Transaction> getTransactionByPosIdAndOperatorId(String posId, String operatorId) {
        return transactionRepository.findTransactionByPosIdAndOperatorId(posId, operatorId);
    }

    public Mono<Transaction> save(Transaction transaction){
        return transactionRepository.save(transaction);
    }
}
