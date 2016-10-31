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

/**
 * FXML Controller class
 *
 * @author Andreas Vettefors (contact@vettefors.se)
 */
public class FXMLStartController implements Initializable {
    
    
    @FXML
    public void btnAddCustomer()
    {
        //Öppnar FXMLAddCustomer, stänger inte det nuvarande fönstret
    }
    
    @FXML
    public void btnEditCustomer()
    {
        //Öppnar FXMLCustomerInfo, stänger inte det nuvarande fönstret
    }
    
    @FXML 
    public void btnRemoveCustomer()
    {
        //Tar bort den valda kunden ur listan
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
