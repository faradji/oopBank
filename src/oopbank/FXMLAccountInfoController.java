/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopbank;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Andreas Vettefors (contact@vettefors.se)
 */
public class FXMLAccountInfoController implements Initializable {

    @FXML
    private Label lblAccountType, lblBalance, lblCredit;
    
    @FXML
    private ListView lvTransactions;
    
    @FXML
    public void btnNewTransactionClicked()
    {
        //Ny transaktion, fönstret för detta är inte skapat än
    }
    
    @FXML
    public void btnBackClicked()
    {
        //Gå tillbaka till FXMLCustomerInfo
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        //lblAccountType ska visa om det är credit eller savings
        //lblBalance visar saldot
        //lblCredit visar krediten, om det är ett credit konto
        //lvTransactions visar en lista på transaktioner
    }    
    
}
