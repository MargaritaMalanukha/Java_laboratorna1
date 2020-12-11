package com.LabJUnitTest.customer;

import com.Lab.customer.Customer;
import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

    private final Customer customer = new Customer("Ivan", "Ivanov");

    @Test
    public void Equals_WithCustomersAreEqual_ShouldReturnTrue() {
        Boolean result = customer.equals(new Customer("Ivan", "Ivanov"));

        Assert.assertEquals(true, result);
    }

    @Test
    public void Equals_WithCustomersAreNotEqual_ShouldReturnFalse() {
        Boolean result = customer.equals(new Customer("error", "error"));

        Assert.assertEquals(false, result);
    }

    @Test
    public void GenerateBonus_WithFullPriceOfPurchase_ShouldChangeCustomerBonus() {
        customer.getCard().generateBonus(2000);

        Assert.assertEquals(20, customer.getCard().getBonus(), 0);
    }

}
