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

    private Date date; //AF: Behöver ingen setter eftersom den alltid skapas som "nu"
    private boolean transactionType;
    private double amount;
    private double balance;

    //AF: Varje transaktion ska ange om det är en insättning eller uttag och vilket belopp det handlar om
    //AF: Datum sätts som tidpunkten när transaktionen görs
    //AF: Osäker på varför transaction har en balance? Hör inte balansen till kontot? 
    public Transaction(boolean transactionType, double amount, double balance)
    {

        this.date = new Date(); //AF: Om jag har förstått det rätt så är Date alltid datum och tid "nu", alltså när objeketet skapas

        this.transactionType = transactionType; //toString skriver ut deposit om true
        this.amount = amount;

        this.balance = balance;
    }

    public Date getDate()
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

    //AF: testar att göra olika toString beroende på vilken transaktionstyp som är vald
    //AF: true = deposit
    @Override
    public String toString()
    {
        if (transactionType == true)
        {
            return "Transaction{" + "date=" + date + ", transactionType=" + " deposit" + ", amount=" + amount + ", balance=" + balance + '}';
        } else
        {
            return "Transaction{" + "date=" + date + ", transactionType=" + " withdrawal" + ", amount=" + amount + ", balance=" + balance + '}';
        }
    }

}
