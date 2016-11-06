/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopbank;

import java.io.IOException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Andreas Vettefors (contact@vettefors.se)
 */
public class FXMLAccountInfoController implements Initializable {

    @FXML
    private Label lblAccountType, lblBalance, lblCredit, lblnamn, credit;

    @FXML
    private ListView lvTransactions;

    @FXML
    private Button btnBack;
    @FXML
    private TextField amount;

    @FXML
    private ObservableList<Transaction> transaction;

    @FXML
    public void btnBackClicked() throws IOException {
        Stage tempStage = (Stage) btnBack.getScene().getWindow();

        tempStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("FXMLCustomerInfo.fxml"))));

    }

    @FXML
    public void deposit() {
        try {
            double am;
            long pnr;
            int account;
            am = Double.parseDouble(amount.getText());
            pnr = OopBank.banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getpNr();
            account = OopBank.banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(FXMLCustomerInfoController.accountChoice).getAccountNo();

            OopBank.banklogic.deposit(pnr, account, am);

            //OopBank.transaction.setAmount(am);
            //OopBank.transaction.setTransactionType(true);
            //String temp=OopBank.transaction.toString();
            //BankLogic.getTransactions(pnr,account).add(temp);
            refresh();
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    @FXML
    public void withdraw() {
        try {
            double am;
            long pnr = 0;
            int account = 0;
            am = Double.parseDouble(amount.getText());

            pnr = OopBank.banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getpNr();
            account = OopBank.banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(FXMLCustomerInfoController.accountChoice).getAccountNo();

            double tempBalance = OopBank.banklogic.getCustomerList()
                    .get(FXMLStartController.lvCustomerChoice)
                    .getAccountList()
                    .get(FXMLCustomerInfoController.accountChoice).getBalance();

            if (lblAccountType.getText().equalsIgnoreCase("Credit Account")) {
                tempBalance = OopBank.banklogic.getCustomerList().
                        get(FXMLStartController.lvCustomerChoice)
                        .getAccountList()
                        .get(FXMLCustomerInfoController.accountChoice)
                        .getBalance() - am;
                if (tempBalance >= -5000) {
                    OopBank.banklogic.withdraw(pnr, account, am);
                    refresh();
                } else {
                    //Skriv i label
                    System.out.println("Not enough money");
                }

            } else if (lblAccountType.getText().equalsIgnoreCase("Savings Account")) {
                if (OopBank.banklogic.getCustomerList()
                        .get(FXMLStartController.lvCustomerChoice)
                        .getAccountList()
                        .get(FXMLCustomerInfoController.accountChoice)
                        .getHasWithdrawn() == true) {

                    if ((tempBalance - (am * 1.02)) > 0) {

                        tempBalance -= (am * 0.02);
                        OopBank.banklogic.getCustomerList()
                                .get(FXMLStartController.lvCustomerChoice)
                                .getAccountList()
                                .get(FXMLCustomerInfoController.accountChoice)
                                .setBalance(tempBalance);
                        OopBank.banklogic.withdraw(pnr, account, am);
                        refresh();
                    } else {
                        System.out.println("Not enough money");
                    }
                } else if ((tempBalance - am) > 0) {
                    OopBank.banklogic.getCustomerList()
                            .get(FXMLStartController.lvCustomerChoice)
                            .getAccountList()
                            .get(FXMLCustomerInfoController.accountChoice)
                            .setHasWithdrawn(true);

                    OopBank.banklogic.withdraw(pnr, account, am);
                    refresh();
                } else {
                    System.out.println("Not enough money");
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Måste vara en siffra");
        } catch (Exception e1) {
            System.err.println(e1);
        }

    }

    public void refresh() {
        transaction = FXCollections.observableArrayList(OopBank.banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice
        ).getAccountList().get(FXMLCustomerInfoController.accountChoice).getTransactionList());
        lvTransactions.setItems(transaction);

        String forNamn = OopBank.banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getFirstName();
        String efterNamn = OopBank.banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getLastName();
        lblnamn.setText(forNamn + " " + efterNamn);//hämta för och efternamn

        String accountType = OopBank.banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(FXMLCustomerInfoController.accountChoice).getAccountType();
        lblAccountType.setText(accountType);//lblAccountType ska visa om det är credit eller savings

        String balance = String.valueOf(OopBank.banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice
        ).getAccountList().get(FXMLCustomerInfoController.accountChoice).getBalance());
        lblBalance.setText(balance);//lblBalance visar saldot

        if (lblAccountType.getText().equalsIgnoreCase("Credit Account")) {

            lblCredit.setText("-5000");//lblCredit visar krediten, om det är ett credit konto
        } else {
            lblCredit.setVisible(false);
            credit.setVisible(false);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 

        refresh();

    }

}
