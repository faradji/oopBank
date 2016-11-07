/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopbank;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author annafock
 */
public class Transaction
{
    private BankLogic banklogic = BankLogic.getInstance();
    private String date; 
    private boolean transactionType;
    private double amount;
    private double balance;
    
    public Transaction(boolean transactionType, double amount)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        String sDate= sdf.format(date);
        this.date = sDate; //När en transaction skapas så blir Date alltid datum och tid "nu".

        this.transactionType = transactionType; //toString skriver ut deposit om true
        this.amount = amount;

        this.balance = banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(FXMLCustomerInfoController.accountChoice).getBalance();
    }

    public String getDate()
    { 
        return date;
    }

    public boolean isTransactionType()
    {
        return transactionType;
    }

    //AF: True = deposit
    public void setTransactionType(boolean transactionType)
    {
        this.transactionType = transactionType;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    //true = deposit, false = withdrawal
    @Override
    public String toString()
    {
        if (transactionType == true)
        {
            return date + " " +  "In: +" + amount + " Balance: " + balance;
        } else
        {
            return date + " " +  "Out: -" + amount + " Balance: " + balance;
        }
    }

}
