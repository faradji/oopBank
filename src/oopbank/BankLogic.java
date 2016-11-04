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
public class BankLogic {

    private static ArrayList<Customer> customerList; //Ska detta vara en lista med String istället för Customer?
    private ArrayList<String> customerInfo;
    private static ArrayList<String> removedCustomerInfo;

    //Konstruktor
    public BankLogic() {
        this.customerList = new ArrayList();
        removedCustomerInfo = new ArrayList();

        customerList.add(new Customer("Louise", "Ahokas", 2410051701L));
        customerList.add(new Customer("Alexander", "Aschas", 0505113345L));
        customerList.add(new Customer("Ali", "Faradji", 7308266873L));
        customerList.add(new Customer("Anna", "Fock", 6507129021L));
        customerList.add(new Customer("Henrik", "Rosqvist", 5611045723L));
        customerList.add(new Customer("Andreas", "Vetterfors", 9901018021L));

        addSavingsAccount(0505113345L, 6000);
        addSavingsAccount(7308266873L, 9000);
        addSavingsAccount(9901018021L, 11000);
        addSavingsAccount(2410051701L, 2000);
        addSavingsAccount(6507129021L, 5000);
        addSavingsAccount(5611045723L, 1000);
   
    }

    public static int addCreditAccount(long prnNumber) {
        int tempaccountnumb = 0;
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getpNr() == prnNumber) {
                CreditAccount tempaccount = new CreditAccount();
                customerList.get(i).getAccountList().add(tempaccount);
                tempaccountnumb = tempaccount.getAccountNo();
                break;
            }
        }
        return tempaccountnumb;
    }

    public static ArrayList getTransactions(long pnrNumber, int accountNo) {
        int arrayfirst = 0;
        int arraysecond = 0;
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getpNr() == pnrNumber) {
                arrayfirst = i;
                for (int j = 0; j < customerList.get(i).getAccountList().size(); j++) {
                    if (customerList.get(i).getAccountList().get(j).getAccountNo() == accountNo) {
                        arraysecond = j;
                        break;
                    }
                    break;
                }
            }
        }
        return customerList.get(arrayfirst).getAccountList().get(arraysecond).getTransactionList();
    }

    public static int addSavingsAccount(long prnNumber, double balance) {
        int tempaccountnumb = 0;
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getpNr() == prnNumber) {
                SavingsAccount tempaccount = new SavingsAccount(balance);
                customerList.get(i).getAccountList().add(tempaccount);
                tempaccountnumb = tempaccount.getAccountNo();
                break;
            }
        }
        return tempaccountnumb;
    }

    public static String getAccount(long prnNumber, int accountNo) {

        int arrayfirst = 0;
        int arraysecond = 0;
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getpNr() == prnNumber) {
                arrayfirst = i;
                for (int j = 0; j < customerList.get(i).getAccountList().size(); j++) {
                    if (customerList.get(i).getAccountList().get(j).getAccountNo() == accountNo) {
                        arraysecond = j;
                        break;
                    }

                }
                break;

            }
        }
        return customerList.get(arrayfirst).getAccountList().get(arraysecond).toString();
    }

    public static ArrayList<Customer> getCustomers() {
        return customerList;
    }

    //public static void setCustomers(ArrayList<Customer> customerList)
    //{
    //  this.customerList = customerList;
    //}
    public static boolean deposit(long prnNumber, int accountNo, double amount) {
        boolean temp = false;

        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getpNr() == prnNumber) {
                for (int j = 0; j < customerList.get(i).getAccountList().size(); j++) {
                    if (customerList.get(i).getAccountList().get(j).getAccountNo() == accountNo) {
                        customerList.get(i).getAccountList().get(j).setBalance(
                                (customerList.get(i).getAccountList().get(j).getBalance()) + amount);

                        temp = true;
                        break;
                    } else {
                        temp = false;
                        break;
                    }

                }
            }

        }
        return temp;
    }

    public static boolean withdraw(long pNr, int accountNo, double amount) {
        boolean temp = false;
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getpNr() == pNr) {
                for (int j = 0; j < customerList.get(i).getAccountList().size(); j++) {
                    if (customerList.get(i).getAccountList().get(j).getAccountNo() == accountNo) {
                        customerList.get(i).getAccountList().get(j).setBalance(
                                (customerList.get(i).getAccountList().get(j).getBalance()) - amount);

                        temp = true;
                        break;
                    } else {
                        temp = false;
                        break;
                    }

                }

            }
        }
        return temp;
    }

    public static String closeAccount(long pNr, int accountNo) {
        String temp = "Something went wrong.";
        double amount = 0.0;
        //

        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getpNr() == pNr) {

                for (int j = 0; j < customerList.get(i).getAccountList().size(); j++) {
                    if (customerList.get(i).getAccountList().get(j).getAccountNo() == accountNo) {
                        customerList.get(i).getAccountList().remove(j);

                        temp = getAccount(pNr, accountNo);
                        if (customerList.get(i).getAccountList().get(j).getAccountType().equalsIgnoreCase("Credit Account")) {
                            amount = customerList.get(i).getAccountList().get(j).getBalance();
                            if (amount <= 0) {
                                amount *= 1.07;
                            } else if (amount >= 0) {
                                amount *= 1.005;
                            }
                        } else if (customerList.get(i).getAccountList().get(j).getAccountType().equalsIgnoreCase("Savings Account")) {
                            amount *= 1.001;
                        }
                        break;
                    }

                }

            }
        }

        return temp;
    }

    //KOmmenterad tills vi har testat Date i transaction
//    public static String getDate()
//    {
//
//        Calendar c = Calendar.getInstance();
//        String d;
//        d = String.valueOf(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
//        return (d);
//    }
    public static ArrayList<Customer> getCustomerList() {
        //Returnerar en ArrayList med string som innehåller en presentation av bankens alla kunder
        return customerList;
    }

    public static boolean addCustomer(String firstName, String lastName, long pNr) {
        boolean customerCreated = true;

        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getpNr() == pNr) {
                customerCreated = false;
                break;
            }
        }
        if (customerCreated == true) {
            customerList.add(new Customer(firstName, lastName, pNr));

        }
        return customerCreated;
    }

    public static ArrayList<String> getCustomer(long pNr) {
        ArrayList<String> customerInfo = new ArrayList();

        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getpNr() == pNr) //Lägger fört till namn och personnummer på första raden    
            {
                customerInfo.add(customerList.get(i).getFirstName() + " " + customerList.get(i).getLastName() + " " + customerList.get(i).getpNr());

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

    public static boolean changeCustomerName(String firstName, String lastName, long pNr) {
        boolean changedName = false;

        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getpNr() == pNr) {
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
    public static ArrayList<String> removeCustomer(long pNr) {
        int tempCustomerIndex = 0;
        int tempAccountListSize = customerList.get(tempCustomerIndex)
                .getAccountList().size();
       
        int[] tempAccountNoArray = new int[customerList.get(tempCustomerIndex)
                .getAccountList().size()];
        
    
        // Kollar vilken cutomer det gäller
        for (int i = 0; i < customerList.size();i++){
            if (customerList.get(i).getpNr() == pNr) {
                tempCustomerIndex = i;
            }
        }
        
        // Sparar accountno i en tempArray
        for(int j = 0;j<customerList.get(tempCustomerIndex)
                .getAccountList().size();j++){
            tempAccountNoArray[j] = customerList.get(tempCustomerIndex)
                .getAccountList().get(j).getAccountNo();
        }
        
        // Stänger konton
        //for(int k = 0; k<tempAccountListSize; k++){
          //  closeAccount(pNr, tempAccountNoArray[k]);
        //}
             
        removedCustomerInfo.add(customerList.get(tempCustomerIndex).toString());
        customerList.remove(tempCustomerIndex);
        
            //Tar bort kund med personnummer pNr ur banken, alla kundens eventuella
            //konton tas också bort och resultatet returneras
            //Listan som returneras ska innehålla information om alla konton som togs bort
            //saldot som kunden får tillbaka samt vad räntan blev
        return removedCustomerInfo;

    }

    public static ArrayList<String> getRemovedCustomerInfo() {
        return removedCustomerInfo;
    }
    
    
}
