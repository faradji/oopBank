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
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import oopbank.repository.DBConnection;

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
    private Button btnClose;

    @FXML
    public static ObservableList<Customer> obsCustomerList;

    static int lvCustomerChoice = 0;

    // Finns för att kunna uppdatera namnet på CustomerInfo-sidan
    private static SimpleStringProperty nameChange = new SimpleStringProperty();

    @FXML
    public void btnAddCustomer() throws IOException {

        try {
            // Kollar om textfälten har något värde.
            // Annars skrivs ett felmeddelande ut.
            if (txtFirstName.getText().equals("")
                    || txtLastName.getText().equals("")
                    || txtSSN.getText().equals("")) {
                throw new Exception();
            }

            // Kollar om förnamn och efternamn innehåller någon siffra. 
            // Om så är fallet så skrivs ett felmeddelande ut.
            char tempChar = 0;
            for (int i = 0; i < txtFirstName.getText().length(); i++) {
                tempChar = txtFirstName.getText().charAt(i);
                if (!Character.isLetter(tempChar)) {
                    throw new InputMismatchException();
                }
            }

            for (int j = 0; j < txtLastName.getText().length(); j++) {
                tempChar = txtLastName.getText().charAt(j);
                if (!Character.isLetter(tempChar)) {
                    throw new InputMismatchException();
                }
            }

            // Kollar om personnumret är 10 siffror
            // Annars skrivs ett felmeddelande ut.
            if (txtSSN.getText().length() != 10) {
                throw new NoSuchFieldException();
            }

            // Kollar om personen är kund i banken
            // Då skrivs ett felmeddelande ut
            for (int k = 0; k < banklogic.getCustomerList().size(); k++) {
                if (Long.valueOf(txtSSN.getText()) == banklogic.getCustomerList().get(k).getpNr()) {
                    throw new IllegalAccessException();
                }
            }

            // Kollar om personen är över 18år
            // Annars skrivs ett felmeddelande ut
            String tempAgeCheck = String.valueOf(txtSSN.getText().charAt(0));
            if (tempAgeCheck.equals("0")) {
                throw new SecurityException();
            }

            tempAgeCheck += String.valueOf(txtSSN.getText().charAt(1));
            if (tempAgeCheck.equals("99")) {
                throw new SecurityException();
            }

            tempAgeCheck = "";
            tempChar = 0;
            // Hämtar värden från textfälten och skickar till addCustomer
            banklogic.addCustomer(txtFirstName.getText(), txtLastName.getText(), Long.valueOf(txtSSN.getText()));

            // Lägger in den nya kunden i obsCustomerList
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
            addCustomerAlert.setText("Numbers, please!");
        } catch (InputMismatchException e2) {
            addCustomerAlert.setText(" Name can't be a number!");
        } catch (NoSuchFieldException e3) {
            addCustomerAlert.setText("Must be ten digits!");
        } catch (SecurityException e4) {
            addCustomerAlert.setText("Customer must be over 18 years old!");
        } catch (IllegalAccessException e5) {
            addCustomerAlert.setText("This person is already a customer!");
        } catch (Exception e6) {
            addCustomerAlert.setText("Every Field needs a value!");
        }

    }

    @FXML
    public void btnGoToCustomer() throws IOException {

        try {
            lvCustomerChoice = lvCustomer.getSelectionModel().getSelectedIndex();
            long pnr;
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

        try {
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

            // Kollar av vilket val användaren valde
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

                banklogic.getRemovedCustomerInfo().clear();

                refresh();
            } else {

            }
        } catch (Exception e) {
            listViewAlert.setText("Please, choose something in the list");
        }

    }

    @FXML
    public void btnSaveToFile() {

        // Initierar vad textfilen ska heta
        String fileName = "customer.txt";

        // Sparar kunderna till en textfil
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

    // Finns för att kunna uppdatera namnet på CustomerInfo - sidan
    public static SimpleStringProperty getNameChange() {
        return nameChange;
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
   public void close(){
       DBConnection db= new DBConnection();
       
       db.closeConn();
       Stage tempStage = (Stage) btnClose.getScene().getWindow();
        tempStage.close();
   }
    @FXML
    public void initialize() {
        refresh();
    }

}
