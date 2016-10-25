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
public class SavingsAccount extends Account

{
//Saving account har inga egna variabler, ärver allt från account

    public SavingsAccount(int accountNo, String accountType, double balance, double limit, double interest, ArrayList<Transaction> transactionList)
    {
        super(accountNo, accountType, balance, limit, interest, transactionList);
    }

    @Override
    public void setLimit(double limit)
    {
        //1 fritt uttag per år. Efter det första fria uttaget ska uttaget belastas med en uttagsränta på 2% av uttaget belopp.  
        //Regleras i metoden withDraw i BankLogic, men ska det ocksså skrivas här
        //Limit sätts till !>balance
    }

    @Override
    public void setInterest(double interest)
    {
        //Räntesatsen för SavingsAccount är inte bestämd, den får vi bestämma
    }

    //Osäker på om toString ska hämtas från super, ska testköra detta
    @Override
    public String toString()
    {
        return super.toString() + "SavingsAccount{" + '}';
    }

}
