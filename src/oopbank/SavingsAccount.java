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
    private static boolean hasWithdrawn=false;
//Saving account har inga egna variabler, ärver allt från account

    public SavingsAccount(double balance)
    {
        super("Savings Account", balance, 1.0);//vi kan välja en annan ränta om vi vill, det är bara ett förslag för att vi ska kunna se lätt att det blir rätt i uträkningar
        
    }
    public static boolean getHasWithdrawn(){
        
        return hasWithdrawn;
    }
    public static void setHasWithdrawn(boolean hasWithdrawn){
        
        SavingsAccount.hasWithdrawn=hasWithdrawn;
    }

    

}
