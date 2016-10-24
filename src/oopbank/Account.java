/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopbank;

import java.util.ArrayList;

/**
 *
 * @author annafock
 */
public abstract class Account
{
        protected double balance;
        protected double limit;
        protected double interest;
        protected int accountNo;
        protected String accountType;
        protected ArrayList<Transaction> transactionList;

        
    //Vilka fält ska vi     
    public Account(double balance, double limit, double interest, int accountNo, String accountType, ArrayList<Transaction> transactionList)
    {
        this.balance = balance;
        this.limit = limit;
        this.interest = interest;
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.transactionList = transactionList;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public double getLimit()
    {
        return limit;
    }

    //Abstrakt metod som utförs med olika villkor beroende på konto-typ
    public abstract void setLimit(double limit);

    public double getInterest()
    {
        return interest;
    }
    
    //Abstrakt metod som utförs med olika villkor beroende på konto-typ
    public abstract void setInterest(double interest);

    public int getAccountNo()
    {
        return accountNo;
    }

    public void setAccountNo(int accountNo)
    {
        this.accountNo = accountNo;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }

    public ArrayList<Transaction> getTransactionList()
    {
        return transactionList;
    }

    public void setTransactionList(ArrayList<Transaction> transactionList)
    {
        this.transactionList = transactionList;
    }

    @Override
    public String toString()
    {
        return "Account{" + "balance=" + balance + ", limit=" + limit + ", interest=" + interest + ", accountNo=" + accountNo + ", accountType=" + accountType + ", transactionList=" + transactionList + '}';
    }
        
             
    
}
