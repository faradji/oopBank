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
        protected int accountNo;
        protected String accountType;
        protected double balance; //Ska beräknas som interest * interest/100
        protected double limit; //Olika för olika kontotyper. 
        protected double interest; //Olika för olika kontotyper   
        protected ArrayList<Transaction> transactionList;

    public Account(int accountNo, String accountType, double balance, double limit, double interest, ArrayList<Transaction> transactionList)
    {
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.balance = balance;
        this.limit = limit;
        this.interest = interest;
        this.transactionList = transactionList;
    }
     
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
