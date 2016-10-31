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

    public ArrayList<Customer> getCustomerList()//LA: Denna kommer Ali att göra.. Det blev lite missförstånd.
    {
        //Returnerar en ArrayList med string som innehåller en presentation av bankens alla kunder
        return customerList;
    }

    public boolean addCustomer(String firstName, String lastName, long pNr)
    {   //LA: känns som att det finns ett snyggare sätt att göra detta på, men jag har gjort så här...
        //AF: Jag tror att det räcker att ta return true/false efter if-satserna, utan att skapa en boolean-variabel
        boolean customerCreated = true;

        for (int i = 0; i < customerList.size(); i++)
        {
            if (customerList.get(i).getpNr() == pNr)
            {
                customerCreated = false;
                break;
            } else
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

    public ArrayList<String> getCustomer(long pNr)
    {
        ArrayList<String> customerInfo = new ArrayList();

        for (int i = 0; i < customerList.size(); i++)
        {
            if (customerList.get(i).getpNr() == pNr)
            //LA: jag kan inte avsluta den här metoden - hur gör man för att listan med konton ska läggas på nästa plats i Arraylistan? 
            //AF: Alternativ 1. Jag prövade så här... och ett alternativ till nedan...     
            {
                customerInfo.add(customerList.get(i).getFirstName() + customerList.get(i).getLastName() + customerList.get(i).getpNr());

                for (int j = 0; j < customerList.get(i).getAccountList().size(); j++) //AF: För indexet i, hämta accountlistans längd
                {
                    customerInfo.add(customerList.get(i).getAccountList().get(j).toString());
                }
            }

            //AF: Alternativ 2. Lite fuskigt alterantiv kanske??
            {
                customerInfo.add(customerList.get(i).toString());
            }
        }

        //LA: jag kan inte avsluta den här metoden - hur gör man för att listan med konton ska läggas på nästa plats i Arraylistan? 
//            {
//                customerInfo.add(customerList.get(i).getFirstName() + customerList.get(i).getLastName() + customerList.get(i).getpNr());
//            }
        //Returnerar en ArrayList som innehåller information om kunden inklusive dens konton
        //Första platsen i listan är förslagsvis reserverad för kundens namn och personnummer
        //Sedan följer information om kundens konton
        return customerInfo;

    }

    public boolean changeCustomerName(String firstName, String lastName, long pNr)
    {
        for (int i = 0; i < customerList.size(); i++)
        {
            if (customerList.get(i).getpNr() == pNr)
            {
                customerList.get(i).setFirstName(firstName);
                customerList.get(i).setLastName(lastName);
                return true;
            }
        }
        return false;
        //Byter namn på kund med personnummer pNr till name, returnerar true om namnet
        //ändrades, annars false(om kunden inte fanns)
    }

    public ArrayList<Customer> removeCustomer(long pNr)
    {
        long closePNr;
        int closeAccountNo;

        ArrayList<Customer> removedCustomers = new ArrayList();

        //AF: Första loopen hämtar customerList och hanterar kundens uppgifter
        for (int i = 0; i < customerList.size(); i++)
        {
            if (customerList.get(i).getpNr() == pNr)
            {
                //AF: 1. Lägger till kunden i den nya arrayListen
                removedCustomers.add(customerList.get(i));

                //AF: Andra loopen hämtar kundens konton för att kunna stänga dem
                for (int j = 0; j < customerList.get(i).getAccountList().size(); j++)
                {
                    //AF: 2. Hämtar in metoden closeAccount och lägger in kundens pNr och kontonummerna                   
                    closeAccount(customerList.get(i).getpNr(), customerList.get(i).getAccountList().get(j).getAccountNo());

                }

                //AF: 3. Tar bort kunden från customerList
                customerList.remove(i);

            }
        }
        //AF: Just nu returnerar den bara en lista på borttagna konton, inte info om ränta. 
        //Måste kolla metoden closeAccounts mer innan jag fixar här
        return removedCustomers;

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
