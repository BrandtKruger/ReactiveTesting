package com.kruger.reactivetesting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import javax.persistence.*;

@Data
@AllArgsConstructor
@Table(name = "transaction")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "transactionId")
    private Long transactionId;
    @Column(name = "amount")
    private long amount;
    @Column(name = "amountOther")
    private long amountOther;
    @Column(name = "merchantId")
    private String merchantId;
    @Column(name = "terminalId")
    private String terminalId;
    @Column(name = "posId")
    private String posId;
    @Column(name = "operatorId")
    private String operatorId;
    @Column(name = "cardNumber")
    private String cardNumber;
    @Column(name = "cvv")
    private String cvv;
    @Column(name = "cardExpDate")
    private String cardExpDate;
}
