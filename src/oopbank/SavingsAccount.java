/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopbank;


/**
 *
 * @author annafock
 */
public class SavingsAccount extends Account

        
{
   
//Saving account har inga egna variabler, ärver allt från account

    public SavingsAccount(double balance)
    {
        super("Savings Account", balance, 1.0);
    }
    
        public SavingsAccount(int accountNo, double balance)
    {
        super(accountNo, "Savings Account", balance, 1.0);
    }
   

    

}