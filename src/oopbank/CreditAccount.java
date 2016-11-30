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
    //Om balance är positivt får man 0,5% på sparade pengar.
    private double debtInterest; 
    private double limit;

    public CreditAccount()//balance är alltid 0 när ett creditaccount öppnas
    {
        super("Credit Account", 0, 0.5);//här initierar vi de variablarna som är "fasta" för creditaccount
        
        this.debtInterest = 7.0;//initeras i konstruktorn eftrsom vi vet att räntan är 7%
        this.limit = -5000.0;//initieras i konstruktorn eftersom vi vet att limit är -5000
    }
    
        public CreditAccount(int accountNo, double balance)//balance är alltid 0 när ett creditaccount öppnas
    {
        super(accountNo, "Credit Account", balance, 0.5);//här initierar vi de variablarna som är "fasta" för creditaccount
        
        this.debtInterest = 7.0;//initeras i konstruktorn eftrsom vi vet att räntan är 7%
        this.limit = -5000.0;//initieras i konstruktorn eftersom vi vet att limit är -5000
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
    
    public void setLimit(double limit)
    {
        this.limit = limit;
        //När kreditkontot skapas är credit limit -5000 kr.
    }


    public double getLimit() {
        return limit;
    }
    
}
