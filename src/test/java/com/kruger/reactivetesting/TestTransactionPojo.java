package com.kruger.reactivetesting;

import com.kruger.reactivetesting.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

public class TestTransactionPojo {

    //@Test
    public void testPurchaseWithGoods(){

        Transaction transaction = new Transaction((long) 15, 1144, 0, "123456",
                "876543", "POS3", "John", "54300000021156", "234", "1221" );

        Assertions.assertEquals(transaction.getTransactionId(), 15);
    }
}
