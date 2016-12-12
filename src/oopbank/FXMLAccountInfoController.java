/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopbank;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oopbank.repository.DBConnection;

/**
 * FXML Controller class
 *
 * @author Andreas Vettefors (contact@vettefors.se)
 */
public class FXMLAccountInfoController implements Initializable {
    
    private BankLogic banklogic = BankLogic.getInstance();
    @FXML
    private Label lblAccountType, lblBalance, lblCredit, lblnamn,
            credit, lblAlert, lblAccountNo;
    
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

    @FXML //deposit knappen
    public void saveToFile() {
        DBConnection db = new DBConnection();
        ArrayList tempTrans = new ArrayList();
        // Initierar vad textfilen ska heta
        String fileName = "kontoUtdrag.txt";
        tempTrans.addAll(db.getTransactionListinfo(FXMLStartController.lvCustomerChoice));
        // Sparar kunderna till en textfil
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(fileName))) {
            for (int i = 0; i < tempTrans.size(); i++) {
                pw.println(tempTrans.get(i));
                pw.close();                
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("Transactions saved to " + fileName);
        alert.showAndWait();
    }

    @FXML //deposit knappen
    public void deposit() {
        try {
            double am;
            long pnr;
            int account;
            //hämta värdena och spara de
            am = Double.parseDouble(amount.getText());
            if (am <= 0) {
                lblAlert.setText("Positive numbers, Please!");
            } else {
                pnr = banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getpNr();
                account = banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(FXMLCustomerInfoController.accountChoice).getAccountNo();
                // göra en deposit
                banklogic.deposit(pnr, account, am);
//ladda om sidan
                refresh();
            }
        } catch (NumberFormatException e) {
            lblAlert.setText("Numbers, please!");
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
            //hämta värdena och spara de
            am = Double.parseDouble(amount.getText());
            if (am <= 0) {
                lblAlert.setText("Positive numbers, Please!");
            } else {
                pnr = banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getpNr();
                account = banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(FXMLCustomerInfoController.accountChoice).getAccountNo();
//hämta balance
                double tempBalance = banklogic.getCustomerList()
                        .get(FXMLStartController.lvCustomerChoice)
                        .getAccountList()
                        .get(FXMLCustomerInfoController.accountChoice).getBalance();
//kolla vilken typ av account det är
                if (lblAccountType.getText().equalsIgnoreCase("Credit Account")) {
                    tempBalance = banklogic.getCustomerList().
                            get(FXMLStartController.lvCustomerChoice)
                            .getAccountList()
                            .get(FXMLCustomerInfoController.accountChoice)
                            .getBalance() - am;
                    //om balance är mer eller lika mycket än -5000
                    if (tempBalance >= -5000) {
                        banklogic.withdraw(pnr, account, am);
                        refresh();
                    } else {
                        //Skriv i label
                        lblAlert.setText("Not enough money!");
                    }
//kolla vilken typ av account det är
                } else if (lblAccountType.getText().equalsIgnoreCase("Savings Account")) {
                    //kolla om uttag har gjorts förr
                    if (banklogic.getCustomerList()
                            .get(FXMLStartController.lvCustomerChoice)
                            .getAccountList()
                            .get(FXMLCustomerInfoController.accountChoice)
                            .getHasWithdrawn() == true) {
// annars lägg på ränta
                        if ((tempBalance - (am * 1.02)) > 0) {
                            
                            tempBalance -= (am * 0.02);
                            banklogic.getCustomerList()
                                    .get(FXMLStartController.lvCustomerChoice)
                                    .getAccountList()
                                    .get(FXMLCustomerInfoController.accountChoice)
                                    .setBalance(tempBalance);
//göra en withdraw
                            banklogic.withdraw(pnr, account, am);
                            refresh();
                        } else {
                            lblAlert.setText("Not enough money!");
                        }
                        // om nya balance är mer än 0
                    } else if ((tempBalance - am) > 0) {
                        //registrera första uttag
                        banklogic.getCustomerList()
                                .get(FXMLStartController.lvCustomerChoice)
                                .getAccountList()
                                .get(FXMLCustomerInfoController.accountChoice)
                                .setHasWithdrawn(true);
//göra en withdraw
                        banklogic.withdraw(pnr, account, am);
                        refresh();
                    } else {
                        lblAlert.setText("Not enough money!");
                    }
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
        
        Collections.reverse(transaction);
        lvTransactions.setItems(transaction);
        
        String forNamn = banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getFirstName();
        String efterNamn = banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getLastName();
        lblnamn.setText(forNamn + " " + efterNamn);//hämta för och efternamn

        String accountNumber = String.valueOf(banklogic.getCustomerList()
                .get(FXMLStartController.lvCustomerChoice)
                .getAccountList().get(FXMLCustomerInfoController.accountChoice)
                .getAccountNo());
        
        lblAccountNo.setText(accountNumber);
        
        String accountType = banklogic.getCustomerList()
                .get(FXMLStartController.lvCustomerChoice)
                .getAccountList()
                .get(FXMLCustomerInfoController.accountChoice)
                .getAccountType();
        
        lblAccountType.setText(accountType);//lblAccountType ska visa om det är credit eller savings

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
