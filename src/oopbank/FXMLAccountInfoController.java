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

    private BankLogic banklogic = BankLogic.getInstance();
    @FXML
    private Label lblAccountType, lblBalance, lblCredit, lblnamn, credit, lblAlert;

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
            pnr = banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getpNr();
            account = banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(FXMLCustomerInfoController.accountChoice).getAccountNo();

            banklogic.deposit(pnr, account, am);

            refresh();
        } catch (NumberFormatException e) {
            lblAlert.setText("Integer, please!");
        } catch (Exception e1) {
            System.err.println(e1);
        }

    }

    @FXML
    public void withdraw() {
        try {
            double am;
            long pnr = 0;
            int account = 0;
            am = Double.parseDouble(amount.getText());

            pnr = banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getpNr();
            account = banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(FXMLCustomerInfoController.accountChoice).getAccountNo();

            double tempBalance = banklogic.getCustomerList()
                    .get(FXMLStartController.lvCustomerChoice)
                    .getAccountList()
                    .get(FXMLCustomerInfoController.accountChoice).getBalance();

            if (lblAccountType.getText().equalsIgnoreCase("Credit Account")) {
                tempBalance = banklogic.getCustomerList().
                        get(FXMLStartController.lvCustomerChoice)
                        .getAccountList()
                        .get(FXMLCustomerInfoController.accountChoice)
                        .getBalance() - am;
                if (tempBalance >= -5000) {
                    banklogic.withdraw(pnr, account, am);
                    refresh();
                } else {
                    //Skriv i label
                    lblAlert.setText("Not enough money!");
                }

            } else if (lblAccountType.getText().equalsIgnoreCase("Savings Account")) {
                if (banklogic.getCustomerList()
                        .get(FXMLStartController.lvCustomerChoice)
                        .getAccountList()
                        .get(FXMLCustomerInfoController.accountChoice)
                        .getHasWithdrawn() == true) {

                    if ((tempBalance - (am * 1.02)) > 0) {

                        tempBalance -= (am * 0.02);
                        banklogic.getCustomerList()
                                .get(FXMLStartController.lvCustomerChoice)
                                .getAccountList()
                                .get(FXMLCustomerInfoController.accountChoice)
                                .setBalance(tempBalance);
                        banklogic.withdraw(pnr, account, am);
                        refresh();
                    } else {
                        lblAlert.setText("Not enough money!");
                    }
                } else if ((tempBalance - am) > 0) {
                    banklogic.getCustomerList()
                            .get(FXMLStartController.lvCustomerChoice)
                            .getAccountList()
                            .get(FXMLCustomerInfoController.accountChoice)
                            .setHasWithdrawn(true);

                    banklogic.withdraw(pnr, account, am);
                    refresh();
                } else {
                    lblAlert.setText("Not enough money!");
                }
            }

        } catch (NumberFormatException e) {
            lblAlert.setText("Numbers, please!");
        } catch (Exception e1) {
            System.err.println(e1);
        }

    }

    public void refresh() {
        transaction = FXCollections.observableArrayList(banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice
        ).getAccountList().get(FXMLCustomerInfoController.accountChoice).getTransactionList());
        lvTransactions.setItems(transaction);

        String forNamn = banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getFirstName();
        String efterNamn = banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getLastName();
        lblnamn.setText(forNamn + " " + efterNamn);//hämta för och efternamn

        String account = banklogic.getCustomerList()
                .get(FXMLStartController.lvCustomerChoice)
                .getAccountList().get(FXMLCustomerInfoController.accountChoice)
                .getAccountNo() + " " + banklogic.getCustomerList()
                .get(FXMLStartController.lvCustomerChoice)
                .getAccountList()
                .get(FXMLCustomerInfoController.accountChoice)
                .getAccountType();
        lblAccountType.setText(account);//lblAccountType ska visa om det är credit eller savings

        String balance = String.valueOf(banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice
        ).getAccountList().get(FXMLCustomerInfoController.accountChoice).getBalance());
        lblBalance.setText(balance);//lblBalance visar saldot

        if (lblAccountType.getText().equalsIgnoreCase("Credit Account")) {

            lblCredit.setText("-5000");//lblCredit visar krediten, om det är ett credit konto

        } else {
            lblCredit.setVisible(false);
            credit.setVisible(false);
        }

        lblAlert.setText("");
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
