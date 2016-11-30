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
        
        this.accountList = new ArrayList();
    }

    public void setAccountList(ArrayList<Account> accountList) {
        this.accountList = accountList;
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
   

    @Override
    public String toString()
    {
        return firstName + " " + lastName + " " + pNr;
    }
    
    
}
