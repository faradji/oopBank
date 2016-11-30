/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopbank;

import java.util.ArrayList;
import oopbank.repository.DBConnection;

/**
 *
 * @author annafock
 */
public class BankLogic {

    private static BankLogic banklogic = null;
    private ArrayList<Customer> customerList;
    private ArrayList<String> customerInfo;
    private ArrayList<String> removedCustomerInfo;
    private final DBConnection db;
    //Konstruktor
    private BankLogic() {
        db=new DBConnection();
        customerList = db.getCustomerInfo();
        customerInfo = new ArrayList();
        removedCustomerInfo = new ArrayList();
    }

    public static BankLogic getInstance() {
        if (banklogic == null) {
            banklogic = new BankLogic();
        }
        return banklogic;
    }
//Lägger till ett kreditkonto till det personnummer som angivits
    public int addCreditAccount(long prnNumber) {
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
//Returnerar en arraylista av transaktioner tillhörande ett specifikt konto
    public ArrayList getTransactions(long pnrNumber, int accountNo) {
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
//Lägger till ett sparkonto till angivet personnummer med en först insättning
    public int addSavingsAccount(long prnNumber, double balance) {
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

//Returnerar en sträng med information angående ett specificerat konto
    public String getAccount(long prnNumber, int accountNo) {
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

    public ArrayList<Customer> getCustomers() {
        return customerList;
    }

    public boolean deposit(long prnNumber, int accountNo, double amount) {
        boolean temp = true;
            //hämta rätt customer
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getpNr() == prnNumber) {
                    //hämta konto
                for (int j = 0; j < customerList.get(i).getAccountList().size(); j++) {
                    if (customerList.get(i).getAccountList().get(j).getAccountNo() == accountNo) {
                      //spara balance
                        double tempBalance = customerList.get(i).getAccountList().get(j).getBalance();
                        //spara nya balance
                        tempBalance += amount;
                        //uppdatera balance i list
                        customerList.get(i).getAccountList().get(j).setBalance(tempBalance);
                        //skapa en transaktion
                        customerList.get(i).getAccountList().get(j).getTransactionList().add(new Transaction(temp, amount));
                        break;
                    }

                }
            }

        }
        return temp;
    }

    public boolean withdraw(long pNr, int accountNo, double amount) {
        boolean temp = false;
        //hämta customer
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getpNr() == pNr) {
                //hämta konto
                for (int j = 0; j < customerList.get(i).getAccountList().size(); j++) {
                    if (customerList.get(i).getAccountList().get(j).getAccountNo() == accountNo) {
                        //spara balance
                        double tempBalance = customerList.get(i).getAccountList().get(j).getBalance();
                        //spara nya balance
                        tempBalance -= amount;
                        //uppdatera balance i list
                        customerList.get(i).getAccountList().get(j).setBalance(tempBalance);
                        //skapa transaktion
                        customerList.get(i).getAccountList().get(j).getTransactionList().add(new Transaction(temp, amount));

                        break;
                    }

                }

            }
        }
        return temp;
    }

    public String closeAccount(long pNr, int accountNo) {
        int tempCustomer = 0;
        int tempAccount = 0;
        String removedAccount = "";
        double tempBalance;
        CreditAccount tempDebt;  

        // Vilken kund
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getpNr() == pNr) {
                tempCustomer = i;

                // Vilket konto
                for (int j = 0; j < customerList.get(i).getAccountList().size(); j++) {
                    if (customerList.get(i).getAccountList().get(j).getAccountNo() == accountNo) {
                        tempAccount = j;

                        // Räknar ut slutgiltigt saldo beroende på vilket sorts konto det är
                        tempBalance = customerList.get(i).getAccountList().get(j).getBalance();
                        if (customerList.get(i).getAccountList().get(j).getAccountType().equalsIgnoreCase("Credit Account")) {
                            if (tempBalance < 0) {
                                tempBalance *= 1.07;
                                customerList.get(i).getAccountList().get(j).setBalance(tempBalance);
                                tempDebt =  (CreditAccount)customerList.get(tempCustomer).getAccountList().get(tempAccount);

                                removedAccount = "\nAccount is closed" + "\n"
                                        + customerList.get(tempCustomer).getAccountList()
                                        .get(tempAccount).getAccountNo() + " " + customerList.get(tempCustomer).getAccountList()
                                        .get(tempAccount).getAccountType()
                                        + "\nDebtfee: " + tempDebt.getDebtInterest()
                       
                                        + "\nBalance: " + " " + customerList.get(tempCustomer).getAccountList()
                                        .get(tempAccount).getBalance();

                            } else if (tempBalance >= 0) {
                                tempBalance *= 1.005;
                                customerList.get(i).getAccountList().get(j).setBalance(tempBalance);
                                removedAccount = "\nAccount is closed" + "\n"
                                        + customerList.get(tempCustomer).getAccountList()
                                        .get(tempAccount).getAccountNo() + " " + customerList.get(tempCustomer).getAccountList()
                                        .get(tempAccount).getAccountType()
                                        + "\nInterest: " + customerList.get(tempCustomer).getAccountList()
                                        .get(tempAccount).getInterest()
                                        + "\nBalance: " + " " + customerList.get(tempCustomer).getAccountList()
                                        .get(tempAccount).getBalance();
                            }
                        } else if (customerList.get(i).getAccountList().get(j).getAccountType().equalsIgnoreCase("Savings Account")) {
                            tempBalance *= 1.001;
                            customerList.get(i).getAccountList().get(j).setBalance(tempBalance);
                            removedAccount = "\nAccount is closed" + "\n"
                                    + customerList.get(tempCustomer).getAccountList()
                                    .get(tempAccount).getAccountNo() + " " + customerList.get(tempCustomer).getAccountList()
                                    .get(tempAccount).getAccountType()
                                    + "\nInterest: " + customerList.get(tempCustomer).getAccountList()
                                    .get(tempAccount).getInterest()
                                    + "\nBalance: " + " " + customerList.get(tempCustomer).getAccountList()
                                    .get(tempAccount).getBalance();
                        }

                    }

                }
            }
        }

        customerList.get(tempCustomer).getAccountList().remove(tempAccount);

        return removedAccount;
    }

    public ArrayList<Customer> getCustomerList() {
        //Returnerar en ArrayList med string som innehåller en presentation av bankens alla kunder
        return customerList;
    }

    public boolean addCustomer(String firstName, String lastName, long pNr) {
        boolean customerCreated = true;
        //Går igenom customerlist och kollar om kunden redan finns.
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getpNr() == pNr) {
                customerCreated = false;
                break;
            }
        }
        //lägger till kunden om den inte redan finns
        if (customerCreated == true) {
            customerList.add(new Customer(firstName, lastName, pNr));

        }
        return customerCreated;
    }

    public ArrayList<String> getCustomer(long pNr) {

        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getpNr() == pNr) //Lägger först till namn och personnummer på första raden    
            {
                customerInfo.add(customerList.get(i).getFirstName() + " " + customerList.get(i).getLastName() + " " + customerList.get(i).getpNr());

                //Sedan loopar accounts på följande rader
                for (int j = 0; j < customerList.get(i).getAccountList().size(); j++)
                {
                    customerInfo.add(customerList.get(i).getAccountList().get(j).toString());
                }
            }

        }
        
        return customerInfo;

    }

    public boolean changeCustomerName(String firstName, String lastName, long pNr) {
        //Byter namn på kund med personnummer pNr till name, returnerar true om namnet
        //ändrades, annars false(om kunden inte fanns)
        boolean changedName = false;

        //går igenom customerlist för att hitta kunden
        for (int i = 0; i < customerList.size(); i++) {
            
            //om kunden hittas så ändras namnen
            if (customerList.get(i).getpNr() == pNr) {
                customerList.get(i).setFirstName(firstName);
                customerList.get(i).setLastName(lastName);
                changedName = true;
            }
        }
        return changedName;
        
    }

    public ArrayList<String> removeCustomer(long pNr) {
        int tempCustomerIndex = 0;

        int[] tempAccountNoArray = new int[customerList.get(tempCustomerIndex)
                .getAccountList().size()];

        // Kollar vilken kund det gäller
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getpNr() == pNr) {
                tempCustomerIndex = i;
            }
        }
        int tempAccountListSize = customerList.get(tempCustomerIndex)
                .getAccountList().size();

        //Sparar info om kunden i arrayen
        removedCustomerInfo.add("Customer removed\n" + customerList.get(tempCustomerIndex).toString());

        // Sparar kontonummer i en tempArray
        for (int j = 0; j < customerList.get(tempCustomerIndex)
                .getAccountList().size(); j++) {
            tempAccountNoArray[j] = customerList.get(tempCustomerIndex)
                    .getAccountList().get(j).getAccountNo();
        }

        // Stänger konton
        for (int k = 0; k < tempAccountListSize; k++) {
            removedCustomerInfo.add(closeAccount(pNr, tempAccountNoArray[k]));
        }

        // Tar bort kunden
        customerList.remove(tempCustomerIndex);

        // Returnerar en array med all information som behövs
        return removedCustomerInfo;

    }

    public ArrayList<String> getRemovedCustomerInfo() {
        return removedCustomerInfo;
    }

}
