/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopbank;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author annafock
 */
public class BankLogic {

    private List<Customer> customers;

    BankLogic() {
        customers = new ArrayList<Customer>();
    }

    public void addSavingsAccount(long prnNumber) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getpNr() == prnNumber) {
                customers.get(i).getAccountList().add(new SavingsAccount());
            }
        }
    }
}
