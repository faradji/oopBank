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
    //Ska regleras i BankLogik-metoden closeAccount?
    private double debtInterest; 
    private double limit;//LA: eftersom det är helt olika villkor för limit så lägger vi två olika variablar på creditaccount och savingaccount, en är ju en summa och en är 1 uttag

    public CreditAccount()//balance är alltid 0 när ett creditaccount öppnas
    {
        super("Credit Account", 0, 0.5);//LA: här initierar vi de variablarna som är "fasta" för creditaccount
        
        this.debtInterest = 7.0;//LA: initeras i konstruktorn eftrsom vi vet att räntan är 7%
        this.limit = -5000.0;//LA: initieras i konstruktorn eftersom vi vet att limit är -5000
    }

    public double getDebtInterest()
    {
        return debtInterest;
    }

    public void setDebtInterest(double debtInterest)
    {
        //7% på lånade pengar när kontot avslutas. LA: Ifall man skulle behöva ändra räntan i framtiden så är det bra att det finns en setter
        this.debtInterest = debtInterest;
    }
    
    public void setLimit(double limit)
    {
        this.limit = limit;
        //När kreditkontot skapas är credit limit -5000 kr.
    }
       
    
}
