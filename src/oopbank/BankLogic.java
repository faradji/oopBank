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
    private ArrayList customerList; //Ska detta vara en lista med String istället för Customer?

    public BankLogic(ArrayList<Customer> customerList)
    {
        this.customerList = customerList;
    }

    public ArrayList<String> getCustomerList()
    {
        //Returnerar en ArrayList med string som innehåller en presentation av bankens alla kunder
        return customerList;
    }
    
    public boolean addCustomer(String name, long pNr)
    {
        //Skapar en ny kund med namnet name samt personnummer pNr, kunden skapas endast om det 
        //inte finns någon kund med personnummer pNr. 
        //Returnerar true om en kund skapades, annars false
        return true;
    }
    
    public ArrayList<String> getCustomer(long pNr)
    {
        //Returnerar en ArrayList som innehåller information om kunden inklusive dens konton
        //Första platsen i listan är förslagsvis reserverad för kundens namn och personnummer
        //Sedan följer information om kundens konton
        return customerList;
    
    }
    
    public boolean changeCustomerName(String name, long Pnr)
    {
        //Byter namn på kund med personnummer pNr till name, returnerar true om namnet
        //ändrades, annars false(om kunden inte fanns)
        return true;
    }
    
    public ArrayList<String> removeCustomer(long pNr)
    {
        //Tar bort kund med personnummer pNr ur banken, alla kundens eventuella
        //konton tas också bort och resultatet returneras
        //Listan som returneras ska innehålla information om alla konton som togs bort
        //saldot som kunden får tillbaka samt vad räntan blev
        return customerList;
    }
    
    
    
}
