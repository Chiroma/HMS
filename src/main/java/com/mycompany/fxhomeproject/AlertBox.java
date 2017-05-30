/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fxhomeproject;

import com.mycompany.fxhomeproject.model.Doctor;
import java.time.LocalDate;
import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *The class provides methods that creates dialogs.
 * @author Josh
 */
public class AlertBox {
    /**
     * Logger object to log messages.
     */
    private static Logger	logger = LoggerFactory.getLogger(AlertBox.class);
    /**
     * DataClass object instance.
     */
    private DataClass dat = new DataClass() ;
    /**
     * Method creates dialog to delete a {@code Doctor}.
     */
    public void deleteDoctor(){
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Delete Doctor");
        dialog.setHeaderText("Enter ID of Doctor to delete");
        dialog.setContentText("ID:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            int id = Integer.parseInt(result.get());
            dat.deleteDoctor(id);
        }
        logger.info("Deleted Doctor");
        // The Java 8 way to get the response value (with lambda expression).
        //result.ifPresent(name -> System.out.println("Your name: " + name));
    }
    /**
     * Method creates dialog to delete a {@code Patient}.
     */
    public void deletePatient(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Delete Patient");
        dialog.setHeaderText("Enter ID and Room of Patient to delete");
        
        GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            
            TextField id = new TextField();
            id.setPromptText("ID");
            TextField roomNum = new TextField();
            roomNum.setPromptText("Room Number");
            
            grid.add(new Label("ID:"), 0, 0);
            grid.add(id, 1, 0);
            grid.add(new Label("Room Number:"), 0, 1);
            grid.add(roomNum, 1, 1);
            
            dialog.getDialogPane().setContent(grid);
            Optional<Pair<String, String>> result = dialog.showAndWait();
            if(result.isPresent()){
                int  i=Integer.parseInt(id.getText());
                int r=Integer.parseInt(roomNum.getText());
                dat.deletePatient(i, r);
            }
            logger.info("Deleted Patient");
    }
    /**
     * Creates dialog to add a new {@code Patient}.
     */
    public void newPatient(){
        // Create the custom dialog.
            Dialog<Pair<String, String>> dialog = new Dialog<>();
             final int [] roomBill= new int[]{1000,2000,3000};
             final int [] disBill= new int[]{900,1800,2700};
            dialog.setTitle("Login Dialog");
            dialog.setHeaderText("Look, a Custom Login Dialog");

            // Set the icon (must be included in the project).
            //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

            // Set the button types.
            ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
           
            // Create the username and password labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField id = new TextField();
            id.setPromptText("ID");
            TextField firstName = new TextField();
            firstName.setPromptText("First Name");
            TextField lastName = new TextField();
            lastName.setPromptText("Last Name");
            TextField diagnosis = new TextField();
            diagnosis.setPromptText("Diagnosis");
            ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                "Regular", "Extra", "Luxury")
            );
            TextField contact = new TextField();
            contact.setPromptText("Contact");
            final TextField bills = new TextField();
            
            TextField roomNum = new TextField();
            roomNum.setPromptText("Room number");
            TextField address = new TextField();
            address.setPromptText("Address");
            final TextField age = new TextField();
            age.setPromptText("Date of birth eg: 01-01-2000");
            TextArea availableRooms = new TextArea();
            
            grid.add(new Label("ID:"), 0, 0);
            grid.add(id, 1, 0);
            grid.add(new Label("First Name:"), 0, 1);
            grid.add(firstName, 1, 1);
            grid.add(new Label("Last Name:"), 0, 2);
            grid.add(lastName, 1, 2);
            grid.add(new Label("Diagnosis:"), 0,3);
            grid.add(diagnosis, 1, 3);
            grid.add(new Label("Date of Birth:"), 0, 4);
            grid.add(age, 1, 4);
            grid.add(new Label("Room Number:"), 0, 5);
            grid.add(roomNum, 1, 5);
            grid.add(new Label("Bills:"), 0, 6);
            grid.add(bills, 1, 6);
            grid.add(new Label("Address:"), 0, 7);
            grid.add(address, 1, 7);
            grid.add(new Label("Room type"), 0,8);
            grid.add(cb, 1, 8);
            grid.add(new Label("Contact:"), 0,9);
            grid.add(contact, 1, 9);
            grid.add(availableRooms, 1, 10);

          cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                   if(LocalDate.parse(age.getText()).isAfter(LocalDate.now().minusYears(5))){
                     bills.setText(Integer.toString(disBill[newValue.intValue()]));
                   }else{
                     bills.setText(Integer.toString(roomBill[newValue.intValue()]));
                   }
                }
                
            });
            
           dialog.getDialogPane().setContent(grid);
            Optional<Pair<String, String>> result = dialog.showAndWait();
            int i = Integer.parseInt(id.getText());
            //int r = Integer.parseInt(room.getText());
            long c = Long.parseLong(contact.getText());
            long b = Long.parseLong(bills.getText());
            int r = Integer.parseInt(roomNum.getText());
            dat.addPatient(i, firstName.getText(), lastName.getText(), diagnosis.getText(),r, c, b, address.getText(), age.getText());
            logger.info("Added new Patient");
    }
    /**
     * Method creates a dialog to add a new {@code Doctor}.
     */
    public void newDoctor(){
        // Create the custom dialog.
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("New Doctor");
            dialog.setHeaderText("Enter details of Doctor");

            // Set the button types.
            ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

            // Create the username and password labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField id = new TextField();
            id.setPromptText("ID");
            TextField firstName = new TextField();
            firstName.setPromptText("First Name");
            TextField lastName = new TextField();
            lastName.setPromptText("Last Name");
            TextField gender = new TextField();
            gender.setPromptText("Gender");
            TextField salary = new TextField();
            salary.setPromptText("Salary");
            TextField address = new TextField();
            address.setPromptText("Address");
            TextField contact = new TextField();
            contact.setPromptText("Contact");
            
            

            grid.add(new Label("ID:"), 0, 0);
            grid.add(id, 1, 0);
            grid.add(new Label("First Name:"), 0, 1);
            grid.add(firstName, 1, 1);
            grid.add(new Label("Last Name:"), 0, 2);
            grid.add(lastName, 1, 2);
            grid.add(new Label("Gender:"), 0,3);
            grid.add(gender, 1, 3);
            grid.add(new Label("Salry:"), 0, 4);
            grid.add(salary, 1, 4);
            grid.add(new Label("Address:"), 0, 5);
            grid.add(address, 1, 5);
            grid.add(new Label("Contact:"), 0, 6);
            grid.add(contact, 1, 6);
            
            

              // Do some validation (using the Java 8 lambda syntax).
//            username.textProperty().addListener((observable, oldValue, newValue) -> {
//                loginButton.setDisable(newValue.trim().isEmpty());
//            });
            dialog.getDialogPane().setContent(grid);
            Optional<Pair<String, String>> result = dialog.showAndWait();
            if(result.isPresent()){
                int i = Integer.parseInt(id.getText());
                long c = Long.parseLong(contact.getText());
                long s = Long.parseLong(salary.getText());
                dat.addDoctor(i, firstName.getText(), lastName.getText(), gender.getText(), s, address.getText(), c);
            }
            logger.info("Added new Doctor");
    }
    /**
     * Method creates a dialog to update details of a {@code Doctor}.
     * @param id {@code Doctor} id
     */
    public void updateDoctor(int id){
        // Create the custom dialog.
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Update Doctor");
            dialog.setHeaderText("Update details of Doctor");

            // Set the button types.
            ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

            // Create the username and password labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            
            TextField firstName = new TextField();
            firstName.setPromptText("First Name");
            TextField lastName = new TextField();
            lastName.setPromptText("Last Name");
            TextField gender = new TextField();
            gender.setPromptText("Gender");
            TextField salary = new TextField();
            salary.setPromptText("Salary");
            TextField address = new TextField();
            address.setPromptText("Address");
            TextField contact = new TextField();
            contact.setPromptText("Contact");
            
            

            grid.add(new Label("Update details of Doctor with ID:" +id), 0, 0);
            //grid.add(id, 1, 0);
            grid.add(new Label("First Name:"), 0, 1);
            grid.add(firstName, 1, 1);
            grid.add(new Label("Last Name:"), 0, 2);
            grid.add(lastName, 1, 2);
            grid.add(new Label("Gender:"), 0,3);
            grid.add(gender, 1, 3);
            grid.add(new Label("Salry:"), 0, 4);
            grid.add(salary, 1, 4);
            grid.add(new Label("Address:"), 0, 5);
            grid.add(address, 1, 5);
            grid.add(new Label("Contact:"), 0, 6);
            grid.add(contact, 1, 6);
            
            

              // Do some validation (using the Java 8 lambda syntax).
//            username.textProperty().addListener((observable, oldValue, newValue) -> {
//                loginButton.setDisable(newValue.trim().isEmpty());
//            });
            dialog.getDialogPane().setContent(grid);
            Optional<Pair<String, String>> result = dialog.showAndWait();
            if(result.isPresent()){
               // int i = Integer.parseInt(id.getText());
                long c = Long.parseLong(contact.getText());
                long s = Long.parseLong(salary.getText());
                dat.updateDoctor(id,firstName.getText(), lastName.getText(), gender.getText(), s, address.getText(), c);
            }
    }
    /**
     * Method creates dialog to update details of a {@code Patient}.
     * @param id {@code Patient} id
     */
    public void updatePatient(int id){
        // Create the custom dialog.
            Dialog<Pair<String, String>> dialog = new Dialog<>();
             final int [] roomBill= new int[]{1000,2000,3000};
            dialog.setTitle("Login Dialog");
            dialog.setHeaderText("Look, a Custom Login Dialog");

            // Set the icon (must be included in the project).
            //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

            // Set the button types.
            ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
           
            // Create the username and password labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField firstName = new TextField();
            firstName.setPromptText("First Name");
            TextField lastName = new TextField();
            lastName.setPromptText("Last Name");
            TextField diagnosis = new TextField();
            diagnosis.setPromptText("Diagnosis");
            ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                "Regular", "Extra", "Luxury")
            );
            TextField contact = new TextField();
            contact.setPromptText("Contact");
            final TextField bills = new TextField();
            cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                   bills.setText(Integer.toString(roomBill[newValue.intValue()]));
                }
                
            });
            TextField roomNum = new TextField();
            roomNum.setPromptText("Room number");
            TextField address = new TextField();
            address.setPromptText("Address");
            TextField age = new TextField();
            age.setPromptText("Age");
            TextArea availableRooms = new TextArea();
            

            grid.add(new Label("Update details of Patient with ID:"), 0, 0);
            //grid.add(id, 1, 0);
            grid.add(new Label("First Name:"), 0, 1);
            grid.add(firstName, 1, 1);
            grid.add(new Label("Last Name:"), 0, 2);
            grid.add(lastName, 1, 2);
            grid.add(new Label("Diagnosis:"), 0,3);
            grid.add(diagnosis, 1, 3);
            grid.add(new Label("Room Type:"), 0, 4);
            grid.add(cb, 1, 4);
            grid.add(new Label("Room Number:"), 0, 5);
            grid.add(roomNum, 1, 5);
            grid.add(new Label("Bills:"), 0, 6);
            grid.add(bills, 1, 6);
            grid.add(new Label("Address:"), 0, 7);
            grid.add(address, 1, 7);
            grid.add(new Label("Age:"), 0,8);
            grid.add(age, 1, 8);
            grid.add(new Label("Contact:"), 0,9);
            grid.add(contact, 1, 9);
            grid.add(availableRooms, 1, 10);

          
           dialog.getDialogPane().setContent(grid);
            Optional<Pair<String, String>> result = dialog.showAndWait();
            //int i = Integer.parseInt(id.getText());
            //int r = Integer.parseInt(room.getText());
            long c = Long.parseLong(contact.getText());
            long b = Long.parseLong(bills.getText());
            int r = Integer.parseInt(roomNum.getText());
            dat.updatePatient(id, firstName.getText(), lastName.getText(), diagnosis.getText(),r, c, b, address.getText(), age.getText());

    }
}
