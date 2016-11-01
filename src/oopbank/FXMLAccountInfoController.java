/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopbank;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

           // BankLogic.deposit(pnr, account, am);
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

           // BankLogic.withdraw(pnr, account, am);
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
        String namn = "";//hämta för och efternamn
        lblnamn.setText(namn);
        //lblAccountType ska visa om det är credit eller savings
        lblAccountType.setText(namn);
        //lblBalance visar saldot
        lblBalance.setText(namn);
        //lblCredit visar krediten, om det är ett credit konto
        lblCredit.setText(namn);
        //lvTransactions visar en lista på transaktioner

    }

}
