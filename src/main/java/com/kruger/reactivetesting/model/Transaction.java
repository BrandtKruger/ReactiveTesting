package com.kruger.reactivetesting.model;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("transaction")
@EqualsAndHashCode
@ToString
public class Transaction {

    @Id
    private Long transactionid;
    private long amount;
    private long amountother;
    private String merchantid;
    private String terminalid;
    private String posid;
    private String operatorid;
    private String cardnumber;
    private String cvv;
    private String cardexpdate;
}
