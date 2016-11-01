/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopbank;

import java.io.IOException;
import java.net.URL;
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
    private Label lblAccountType, lblBalance, lblCredit, lblnamn;

    @FXML
    private ListView lvTransactions;

    @FXML
    private Button btnBack;
    @FXML
    private TextField amount;
    
    @FXML
    private ObservableList<BankLogic> transaction;
    
    @FXML
    public void btnBackClicked() throws IOException {
        Stage tempStage = (Stage) btnBack.getScene().getWindow();

        tempStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("FXMLCustomerInfo.fxml"))));
        
    }

    @FXML
    public void deposit() {
        try {
            double am;
            long pnr = 0;
            int account = 0;
            am = Double.parseDouble(amount.getText());
            pnr=BankLogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getpNr();
           
            boolean deposit = BankLogic.deposit(pnr, account, am);
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
            BankLogic.withdraw(pnr, account, am);
        } catch (Exception e) {
            System.err.println(e);
        }
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        //transaction = FXCollections.observableArrayList(BankLogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(FXMLCustomerInfoController.lvCustomerChoice).getTransaction);
        String forNamn = BankLogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getFirstName();//hämta för och efternamn
        String efterNamn = BankLogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getLastName();
        lblnamn.setText(forNamn+" "+efterNamn);//lblAccountType ska visa om det är credit eller savings
        lblAccountType.setText(forNamn);//lblBalance visar saldot
        OopBank.banklogic.getCustomerList().get(FXMLStartController.lvCustomerChoice).getAccountList().get(0);
        lblBalance.setText(forNamn);//lblCredit visar krediten, om det är ett credit konto
        
        lblCredit.setText(forNamn);//lvTransactions visar en lista på transaktioner

    }

}
