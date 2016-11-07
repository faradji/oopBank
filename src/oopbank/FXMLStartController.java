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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLStartController {

    
    private BankLogic banklogic = BankLogic.getInstance();
    
    @FXML
    private ListView lvCustomer;

    @FXML
    private Label listViewAlert;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtSSN;

    @FXML
    private Label addCustomerAlert;

    @FXML
    private ObservableList<Customer> obsCustomerList;

    static int lvCustomerChoice = 0;

    @FXML
    public void btnAddCustomer() throws IOException {

        try {
            if (txtFirstName.getText().equals("")
                    || txtLastName.getText().equals("")
                    || txtSSN.getText().equals("")) {
                throw new Exception();
            }

            // Hämtar värden från textfälten och skickar till addCustomer
            banklogic.addCustomer(txtFirstName.getText(), txtLastName.getText(), Long.valueOf(txtSSN.getText()));

            // Lägger in den nya i obsCustomerList
            obsCustomerList = FXCollections
                    .observableArrayList(banklogic.getCustomerList());

            lvCustomer.setItems(obsCustomerList);

            // Rensar inputs
            addCustomerAlert.setText("");
            txtFirstName.setText("");
            txtLastName.setText("");
            txtSSN.setText("");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Customer added");
            alert.showAndWait();

        } catch (NumberFormatException e) {
            addCustomerAlert.setText("Needs to be integer");
            txtSSN.requestFocus();
        } catch (Exception e2) {
            addCustomerAlert.setText("Every Field needs a value");
        }

    }

    @FXML
    public void btnGoToCustomer() throws IOException {

        try {
            lvCustomerChoice = lvCustomer.getSelectionModel().getSelectedIndex();

            Stage editCustomerStage = new Stage();
            Scene editCustomerScene
                    = new Scene(FXMLLoader.load(getClass()
                            .getResource("FXMLCustomerInfo.fxml")));

            editCustomerStage.setScene(editCustomerScene);
            editCustomerStage.setResizable(false);
            editCustomerStage.initModality(Modality.APPLICATION_MODAL);
            editCustomerStage.setTitle("Customer information");
            editCustomerStage.show();
        } catch (Exception e) {
            listViewAlert.setText("Please, choose something in the list");
        }

    }

    @FXML
    public void btnRemoveCustomer() {
        String removedCustomer = "";
        
        try{
        // Varnar användaren för att en kund tas bort
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete " + banklogic.getCustomerList()
                .get(lvCustomer.getSelectionModel()
                        .getSelectedIndex()).getFirstName()
                + " " + banklogic.getCustomerList()
                .get(lvCustomer.getSelectionModel()
                        .getSelectedIndex()).getLastName());
        alert.setContentText("Are you sure?");
        ButtonType yes = new ButtonType("Yes", ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yes, no);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == yes) {
            ArrayList<String> tempInfoRemoveCustomer = banklogic.removeCustomer(banklogic.getCustomerList()
                    .get(lvCustomer.getSelectionModel().getSelectedIndex()).getpNr());

            for (int i = 0; i < tempInfoRemoveCustomer.size(); i++) {
                removedCustomer += tempInfoRemoveCustomer.get(i) + "\n";
            }

            System.out.println(removedCustomer);

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Info");
            alert2.setHeaderText(null);
            alert2.setContentText(removedCustomer);
            alert2.showAndWait();

            banklogic.getInstance().getRemovedCustomerInfo().clear();

            refresh();
        } else {

        }
        }
        catch(Exception e){
            listViewAlert.setText("Please, choose something in the list");
        }

    }

    @FXML
    public void btnSaveToFile() {

        String fileName = "customer.txt";

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(fileName))) {
            for (Customer temp : banklogic.getCustomerList()) {
                pw.println(temp.getFirstName() + " " + temp.getLastName() + " "
                        + temp.getpNr());
            }
            pw.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLStartController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("Customers saved to " + fileName);
        alert.showAndWait();

    }

    @FXML
    public void refresh() {
        lvCustomer.refresh();

        obsCustomerList = FXCollections
                .observableArrayList(banklogic.getCustomerList());

        lvCustomer.setItems(obsCustomerList);
        
        listViewAlert.setText("");
    }

    @FXML
    public void initialize() {
        refresh();
    }

}
