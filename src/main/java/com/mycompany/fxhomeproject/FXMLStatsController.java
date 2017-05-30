package com.mycompany.fxhomeproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.fxhomeproject.model.Patient;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * FXML Controller class for the Statistics scene.
 *
 * @author Josh
 */
public class FXMLStatsController implements Initializable {
    /**
     * DataClass instance.
     */
    DataClass dat= new DataClass();
    /**
     * Barchart to show diagnosis statistics.
     */
    @FXML private BarChart <?,?> barchart;
    /**
     * CategoryAxis of the barchart.
     */
    @FXML private CategoryAxis xAxis;
    
    //private ObservableList<Patient> plist;
    /**
     * Handles the event when the home button is fired.
     * @param event action that took place.
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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
       xAxis.setCategories(dat.diagnosisList());
       
       int si = dat.diagnosisList().size();
       int []ar=new int[si];
       String [] ds=new String[si];
       ds= dat.diagnosisList().toArray(ds);
       for(int j=0;j<si;j++){
           ar[j]=dat.countOf(ds[j]);
       }
       XYChart.Series set = new XYChart.Series<>();
       for(int j=0;j<si;j++){
           set.getData().add(new XYChart.Data(ds[j], ar[j]));
       }

       barchart.getData().add(set);
       
       
            for (Node node : barchart.lookupAll(".default-color.chart-bar")) {
                     node.setStyle("-fx-bar-fill: green;");
                 }
       
       // barchart.setStyle("-fx-bar-fill: green;");
        }catch(Exception e){
            System.out.println("");
        }

    }
    
}
