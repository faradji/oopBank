/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopbank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author annafock
 */
public class OopBank extends Application {

    public static BankLogic banklogic;

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("FXMLStart.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Bank of Newton Administratortool");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        banklogic = new BankLogic();
        
        launch(args);
    }

}
