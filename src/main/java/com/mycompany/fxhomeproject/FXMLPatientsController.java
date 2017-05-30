package com.mycompany.fxhomeproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.fxhomeproject.model.Doctor;
import com.mycompany.fxhomeproject.model.Patient;
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
 * FXML Controller class for the {@code Patient} scene.
 *
 * @author Josh
 */
public class FXMLPatientsController implements Initializable {
    /**
     * TableView containing {@code Patient}
     */
    @FXML
    private TableView<Patient> table = new TableView<Patient>();
    /**
     * TableColumn for the first names.
     */
    @FXML 
    private TableColumn firstNameCol;
    /**
     * TableColumn for the last names.
     */
    @FXML
    private TableColumn lastNameCol;
    /**
     * id Label.
     */
    @FXML Label idLabel;
    /**
     * first name Label.
     */
    @FXML Label firstNameLabel;
    /**
     * last name Label.
     */
    @FXML Label lastNameLabel;
    /**
     * diagnosis Label.
     */
    @FXML Label diagnosisLabel;
    /**
     * room Label.
     */
    @FXML Label roomLabel;
    /**
     * contact Label.
     */
    @FXML Label contactLabel;
    /** 
     * bills Label.
     */
    @FXML Label billsLabel;
    /**
     * address Label.
     */
    @FXML Label addressLabel;
    /**
     * date of birth Label.
     */
    @FXML Label ageLabel;
    /**
     * DateClass instance.
     */
    DataClass dat =new DataClass() ;
    /**
     * AlertBox instance.
     */
    AlertBox a = new AlertBox();
    /**
     * Handles event when the home button is fired.
     * @param event action performed.
     */
    @FXML
    private void homeButtonAction(ActionEvent event) {         
        try {
             Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
            Scene home_page_return = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(home_page_return);
            stage.show();
            
        } catch (Exception ex) {
            Logger.getLogger(FXMLDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    /**
     * Handles when the delete button is fired.
     */
    @FXML
    public void deleteButton(){
        a.deletePatient();
    }
    /**
     * Handles when the new button is fired.
     */
    @FXML
    public void newButton(){
        a.newPatient();
    }
    /**
     * Handles when the update button is fired.
     */
    @FXML
    public void updateButton(){
        Patient p = table.getSelectionModel().getSelectedItem();
        if(p!=null){
            a.updatePatient(p.getId());
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
           table.setItems(dat.listPatients());
           
           showePatient(null);
           
           table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Patient>(){
             @Override
             public void changed(ObservableValue<? extends Patient> observable, Patient oldValue, Patient newValue) {
                showePatient(newValue);
             }
           });
    }    
    /**
     * Shows the details of a patient.
     * @param patient {@code Patient} object
     */
    public void showePatient(Patient patient){
        if (patient != null) {
        // Fill the labels with info from the person object.
        idLabel.setText(Integer.toString(patient.getId()));
        firstNameLabel.setText(patient.getFirstName());
        lastNameLabel.setText(patient.getLastName()); 
        diagnosisLabel.setText(patient.getDiagnosis());
        roomLabel.setText(Integer.toString(patient.getRoom()));
        contactLabel.setText(Long.toString(patient.getContact()));
        billsLabel.setText(Long.toString(patient.getBills()));
        addressLabel.setText(patient.getAddress());
        ageLabel.setText(patient.getAge().toString());
        

        // TODO: We need a way to convert the birthday into a String! 
        // birthdayLabel.setText(...);
    } else {
        // Person is null, remove all the text.
        idLabel.setText("");
        firstNameLabel.setText("");
        lastNameLabel.setText("");   
        diagnosisLabel.setText("");
        roomLabel.setText("");
        billsLabel.setText("");
        addressLabel.setText("");
        contactLabel.setText("");
        ageLabel.setText("");
    }
   } 
}
