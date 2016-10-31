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

    private static int accountCounter = 1001;//LA: statisk variabel som ökar varje gång ett objekt av Account skapas (kolla i konstruktorn/LA
    protected int accountNo;
    protected String accountType;
    protected double balance; 
    protected double interest; //Olika för olika kontotyper   
    protected ArrayList<Transaction> transactionList;

    public Account(String accountType, double balance)
    {
        this.accountNo = accountCounter;//LA: eftersom variablen accountCounter är statisk så ändras den för alla objekt när den ändras. Vi sparar alltså i en ickestatisk variabel för varje objekt.
        this.accountType = accountType;
        setBalance(balance);//LA: ska man göra setbalance här istället? //AF Ja, det tror jag
        this.interest = interest;
        accountCounter++; //AF: Sparar nytt värde i accountCounter varje gång ett nytt objekt av typen Account skapas

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

    //AF: Denna flyttas med till savingsAccount, men overrideas i CreditAccount med en annan setter
    public void setBalance(double balance)
    {
        if (balance < 0)
        {
            System.out.println("");//LA: här ska nånting hända i gränssnittet "uttaget är inte godkänt" tex
        } else
        {
            this.balance = balance;
        }
    }

    public double getInterest()
    {
        return interest;
    }

    //AF: Ändrade från abstract metod till vanlig setter eftersom
    //interest regleras i respektive kontos konstruktorer, men kontonas setters ser likadana ut
    public void setInterest(double interest)
    {
        this.interest = interest;
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
        return "Account{" + "balance=" + balance + ", interest=" + interest + ", accountNo=" + accountNo + ", accountType=" + accountType + ", transactionList=" + transactionList + '}';
    }

}
