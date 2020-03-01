package com.kruger.reactivetesting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@Table(name = "transaction")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long transactionId;
    private long amount;
    private long amountOther;
    private String merchantId;
    private String terminalId;
    private String posId;
    private String operatorId;
    private String cardNumber;
    private String cvv;
    private String cardExpDate;
}
