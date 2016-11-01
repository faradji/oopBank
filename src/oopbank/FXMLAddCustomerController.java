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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Andreas Vettefors (contact@vettefors.se)
 */
public class FXMLAddCustomerController implements Initializable {
    
    
    @FXML
    private TextField txtfieldName;
    
    @FXML
    private TextField txtfieldSSN;
    
    @FXML
    public void btnCancelClicked()
    {
        //Avbryter skapandet av en användare och stänger fönstret
    }
    
    @FXML
    public void btnOKClicked()
    {
        //Skapare en ny användare med information som finns i textfälten
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
