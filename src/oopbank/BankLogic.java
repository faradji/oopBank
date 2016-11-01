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

    private ArrayList<Customer> customerList; //Ska detta vara en lista med String istället för Customer?
    private ArrayList<String> customerInfo;
    private ArrayList<String> removedCustomerInfo;
    
    //Konstruktor
    public BankLogic()
    {
        this.customerList = new ArrayList();
       
     customerList.add(new Customer("Louise", "Ahokas", 2410051701L));
     customerList.add(new Customer("Alexander", "Aschas", 0505113345L));
     customerList.add(new Customer("Ali", "Faradji", 7308266873L));
     customerList.add(new Customer("Anna", "Fock", 6507129021L));
     customerList.add(new Customer("Henrik", "Rosqvist", 5611045723L));
     customerList.add(new Customer("Andreas", "Vetterfors", 9901018021L));
                                
    }    
    
    public ArrayList<Customer> getCustomerList()
    {
        //Returnerar en ArrayList med string som innehåller en presentation av bankens alla kunder
        return customerList;
    }
    
    public boolean addCustomer(String firstName, String lastName, long pNr)
    {   
        boolean customerCreated = true;

        for (int i = 0; i < customerList.size(); i++)
        {
            if (customerList.get(i).getpNr() == pNr)
            {
                customerCreated = false;
                break;
            }
        }
        if (customerCreated == true)
        {
            customerList.add(new Customer(firstName, lastName, pNr));
        }
        return customerCreated;
    }

    public ArrayList<String> getCustomer(long pNr)
    {
        ArrayList<String> customerInfo = new ArrayList();

        for (int i = 0; i < customerList.size(); i++)
        {
            if (customerList.get(i).getpNr() == pNr)
            //Lägger fört till namn och personnummer på första raden    
            {
                customerInfo.add(customerList.get(i).getFirstName() + customerList.get(i).getLastName() + customerList.get(i).getpNr());

                //Sedan loopar accounts på följande rader
                for (int j = 0; j < customerList.get(i).getAccountList().size(); j++) //AF: För indexet i, hämta accountlistans längd
                {
                    customerInfo.add(customerList.get(i).getAccountList().get(j).toString());
                }
            }

        }
        //Returnerar en ArrayList som innehåller information om kunden inklusive dens konton
        //Första platsen i listan är förslagsvis reserverad för kundens namn och personnummer
        //Sedan följer information om kundens konton
        return customerInfo;

    }

    public boolean changeCustomerName(String firstName, String lastName, long pNr)
    {
        boolean changedName = false;
        
        for (int i = 0; i < customerList.size(); i++)
        {
            if (customerList.get(i).getpNr() == pNr)
            {
                customerList.get(i).setFirstName(firstName);
                customerList.get(i).setLastName(lastName);
                changedName = true;
            }
        }
        return changedName;
        //Byter namn på kund med personnummer pNr till name, returnerar true om namnet
        //ändrades, annars false(om kunden inte fanns)
    }

    //Inte riktigt klar, behöver stämmas av med closeAccount
    public ArrayList<String> removeCustomer(long pNr)
    {
        ArrayList<String> removedCustomerInfo = new ArrayList(); //Borde den här deklareras som fält?

        //AF: Första loopen hämtar customerList och hanterar kundens uppgifter
        for (int i = 0; i < customerList.size(); i++)
        {
            if (customerList.get(i).getpNr() == pNr)
            {
                //AF: 1. Lägger till kundens personinfo i den nya arrayListen - ska den skriva toString???
                removedCustomerInfo.add(customerList.get(i).toString());

                //AF: Andra loopen hämtar kundens konton för att kunna stänga dem
                for (int j = 0; j < customerList.get(i).getAccountList().size(); j++)
                {
                    String closeAccountInfo = closeAccount(customerList.get(i).getpNr(), customerList.get(i).getAccountList().get(j).getAccountNo());
                    //AF: 2. Hämtar in metoden closeAccount och lägger kundens pNr och kontonummerna som inparameter                 
                    removedCustomerInfo.add(closeAccountInfo);

                    //Vad returnerar closeAccount? Hämta in det här och lägg till removedCustomerInfo
                }

                //AF: 3. Tar bort kunden från customerList
                customerList.remove(i);

            }
        }
        return removedCustomerInfo;

        //Tar bort kund med personnummer pNr ur banken, alla kundens eventuella
        //konton tas också bort och resultatet returneras
        //Listan som returneras ska innehålla information om alla konton som togs bort
        //saldot som kunden får tillbaka samt vad räntan blev
    }

    
    public String closeAccount(long pNr, int accountNo)
    {

        return "closed";
    }
}
