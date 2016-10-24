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
    private double debtInterest;

    public CreditAccount(double debtInterest, double balance, double limit, double interest, int accountNo, String accountType, ArrayList<Transaction> transactionList)
    {
        super(balance, limit, interest, accountNo, accountType, transactionList);
        this.debtInterest = debtInterest;
    }

    @Override
    public String toString()
    {
        return super.toString() + "CreditAccount{" + "debtInterest=" + debtInterest + '}';
    }
    
    
}
