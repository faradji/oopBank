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
public class Test
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Account a = new SavingsAccount(0);

        Account b = new CreditAccount();

        System.out.println(a.toString());
        System.out.println(b.toString());

    }

}
