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
public class BankLogic
{
    private ArrayList <Customer> customerList; //Ska detta vara en lista med String istället för Customer?

    
    public ArrayList<Customer> getCustomerList()//LA: Denna kommer Ali att göra.. Det blev lite missförstånd.
    {
        //Returnerar en ArrayList med string som innehåller en presentation av bankens alla kunder
        return customerList;
    }
    
    public boolean addCustomer(String firstName, String lastName, long pNr)
    {   //LA: känns som att det finns ett snyggare sätt att göra detta på, men jag har gjort så här...
        boolean customerCreated = true;
        Customer c;
        
        for(int i = 0; i < customerList.size(); i++)
        {
            if (customerList.get(i).getpNr() == pNr)
            {
                customerCreated = false;
                break; 
            }
            else
            {
                customerCreated = true;
            }      
        }
        if (customerCreated == true)
        {
            customerList.add(new Customer(firstName, lastName, pNr));
        }
        return customerCreated;
    }
        
    
    public ArrayList<Customer> getCustomer(long pNr)
    {
        //Returnerar en ArrayList som innehåller information om kunden inklusive dens konton
        //Första platsen i listan är förslagsvis reserverad för kundens namn och personnummer
        //Sedan följer information om kundens konton
        
        return customerList;
    
    }
    
    public boolean changeCustomerName(String firstName, String lastName, long Pnr)
    {
        //Byter namn på kund med personnummer pNr till name, returnerar true om namnet
        //ändrades, annars false(om kunden inte fanns)
        return true;
    }
    
    public ArrayList<Customer> removeCustomer(long pNr)
    {
        //Tar bort kund med personnummer pNr ur banken, alla kundens eventuella
        //konton tas också bort och resultatet returneras
        //Listan som returneras ska innehålla information om alla konton som togs bort
        //saldot som kunden får tillbaka samt vad räntan blev
        return customerList;
    }
    
    
    
}
