package com.mycompany.fxhomeproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.fxhomeproject.model.Doctor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML controller class for the FXMLDoctors scene
 *
 * @author Josh
 */
public class FXMLDoctorsController implements Initializable {
    /**
     * TableView variable for table containing {@code Doctor}.
     */
    @FXML
    private TableView<Doctor> table = new TableView<Doctor>();
    /**
     * TableColunm variable for first names.
     */
    @FXML 
    private TableColumn firstNameCol;
    /**
     * TableColunm variable for last names.
     */
    @FXML
    private TableColumn lastNameCol;
    /**
     * id Label.
     */
    @FXML Label idLabel;
    /**
     * First name Label.
     */
    @FXML Label firstNameLabel;
    /**
     * Last name Label.
     */
    @FXML Label lastNameLabel;
    /**
     * Gender Label.
     */
    @FXML Label genderLabel;
    /**
     * Salary Label.
     */
    @FXML Label salaryLabel;
    /**
     * Address Label.
     */
    @FXML Label addressLabel;
    /**
     * Contact Label.
     */
    @FXML Label contactLabel;
   // private MainApp mainapp = new MainApp();
    /**
     * DataClass instance
     */
    DataClass dat =new DataClass() ;
    /**
     * AlertBox instance.
     */
    AlertBox a = new AlertBox();
    /**
     * Handles the action when the delete button is fired.
     */
    @FXML
    private void newButtonAction() {
       a.deleteDoctor();
    }
    /**
     * Handles the action when the new button is fired.
     */
    @FXML
    private void newButton(){
        a.newDoctor();
    }
    /**
     * Handles when the update button is fired.
     */
    @FXML
    public void updateButton() {
        Doctor d = table.getSelectionModel().getSelectedItem();
        System.out.println(d);
        if(d!=null){
            a.updateDoctor(d.getId());
        }
    }
    /**
     * Handles when the home button is fired.
     * @param event action that occurred
     */
    @FXML
    private void homeButtonAction(ActionEvent event) {         
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
            Scene home_page_return = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(home_page_return);
            stage.show();
            //I try to close it here
            
        } catch (Exception ex) {
            Logger.getLogger(FXMLDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       firstNameCol.setCellValueFactory(
            new PropertyValueFactory<Doctor,String>("firstName")
        );
        lastNameCol.setCellValueFactory(
            new PropertyValueFactory<Doctor,String>("lastName")
        );
           table.setItems(dat.listDoctors());
        
//       // Clear person details.
         showeDoctor(null);

        // Listen for selection changes and show the person details when changed.
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Doctor>(){
           @Override
           public void changed(ObservableValue<? extends Doctor> observable, Doctor oldValue, Doctor newValue) {
               showeDoctor(newValue);
           }
            
        });
      
    }
    
   /**
    * Shows the details of a {@code Doctor}.
    * @param doctor 
    */
   public void showeDoctor(Doctor doctor){
        if (doctor != null) {
        // Fill the labels with info from the person object.
        idLabel.setText(Integer.toString(doctor.getId()));
        firstNameLabel.setText(doctor.getFirstName());
        lastNameLabel.setText(doctor.getLastName()); 
        genderLabel.setText(doctor.getGender());
        salaryLabel.setText(Long.toString(doctor.getSalary()));
        addressLabel.setText(doctor.getAddress());
        contactLabel.setText(Long.toString(doctor.getContact()));

        // TODO: We need a way to convert the birthday into a String! 
        // birthdayLabel.setText(...);
    } else {
        // Person is null, remove all the text.
        idLabel.setText("");
        firstNameLabel.setText("");
        lastNameLabel.setText("");   
        genderLabel.setText("");
        salaryLabel.setText("");
        addressLabel.setText("");
        contactLabel.setText("");
    }
   } 
   
   
}
