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
public class CreditAccount extends Account
{
    //7% på eventuellt lånade pengar. När balance är negativt när kontot avslutas dras 7% skuldränta.
    //Om balance är negativt får man 0,5% på sparade pengar.
    //Ska regleras i BankLogik-metoden closeAccount?
    private double debtInterest;  

    public CreditAccount(double debtInterest, int accountNo, String accountType, double balance, double limit, double interest, ArrayList<Transaction> transactionList)
    {
        super(accountNo, accountType, balance, limit, interest, transactionList);
        this.debtInterest = debtInterest;
    }

    public double getDebtInterest()
    {
        return debtInterest;
    }

    public void setDebtInterest(double debtInterest)
    {
        //7% på lånade pengar när kontot avslutas. 
        this.debtInterest = debtInterest;
    }
    
    
    public void setBalance(double balance)
    {
    //När kreditkontot skapas är interest 0 kr. Balance kan aldrig bli lägre än -5000 eftersom det är kreditgränsen. 
    }

    @Override
    public void setLimit(double limit)
    {
    //När kreditkontot skapas är credit limit -5000 kr.
    }
    
    @Override
    public void setInterest(double interest)
    {
    //0,5% på insatta pengar när kontot avslutas. 
    }
    
    @Override
    public String toString()
    {
        return super.toString() + "CreditAccount{" + "debtInterest=" + debtInterest + '}';
    }
    
    
    
    
}
