/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopbank;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class FXMLStartController{
    
    @FXML
    private ListView lvCustomer;
    
    @FXML
    //private ObservableList<Customer> obsCustomerList = 
      
             
       //FXCollections.observableArrayList(BankLogic.getCustomerList());
    
    
    
    public static int lvCustomerChoice = 0;
    
    @FXML
    public void btnAddCustomer() throws IOException
    {
        Stage addCustomerStage = new Stage();
        Scene addCustomerScene
                = new Scene(FXMLLoader.load(getClass()
                        .getResource("FXMLAddCustomer.fxml")));

        addCustomerStage.setScene(addCustomerScene);
        addCustomerStage.initModality(Modality.APPLICATION_MODAL);
        addCustomerStage.setTitle("Add customer");
        addCustomerStage.show();
    }
    
    @FXML
    public void btnEditCustomer() throws IOException
    {
        //lvCustomerChoice = lvCustomer.getSelectionModel().getSelectedIndex();
        
        Stage editCustomerStage = new Stage();
        Scene editCustomerScene
                = new Scene(FXMLLoader.load(getClass()
                        .getResource("FXMLCustomerInfo.fxml")));

        editCustomerStage.setScene(editCustomerScene);
        editCustomerStage.initModality(Modality.APPLICATION_MODAL);
        editCustomerStage.setTitle("Customerinformation");
        editCustomerStage.show();
    }
    
    @FXML 
    public void btnRemoveCustomer()
    {
          lvCustomer.getSelectionModel().getSelectedIndex();
          // Skicka in uppgifter till removecustomer()
    }
    
    @FXML
    public void initialize() {
        
      
        //lvCustomer.getItems().add(obsCustomerList);
    }    
    
}
