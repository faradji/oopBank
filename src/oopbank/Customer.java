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
public class Customer
{
    private String firstName;
    private String lastName;
    private long pNr;
    private ArrayList<Account> accountList;

    public Customer(String firstName, String lastName, long pNr)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pNr = pNr;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public long getpNr()
    {
        return pNr;
    }

    public void setpNr(long pNr)
    {
        this.pNr = pNr;
    }

    public ArrayList<Account> getAccountList()
    {
        return accountList;
    }
   
    //Lägger till ett Account-objekt till accountList
    public void setAccountList(Account a)
    {
    accountList.add(a);
    }

    @Override
    public String toString()
    {
        return "Customer{" + "firstName=" + firstName + ", lastName=" + lastName + ", pNr=" + pNr + ", accountList=" + accountList + '}';
    }
    
    
}
