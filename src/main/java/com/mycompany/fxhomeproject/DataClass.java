/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fxhomeproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.mycompany.fxhomeproject.model.Doctor;
import com.mycompany.fxhomeproject.model.Patient;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import org.slf4j.LoggerFactory;

/**
 *Class which can connect to a database to get data from the it.
 * @author Josh
 */
public class DataClass {
    /**
     * Logger object to log messages.
     */
    private static org.slf4j.Logger	logger = LoggerFactory.getLogger(DataClass.class);
    /**
     * An ObservableList of {@code Doctor}.
     */
    private ObservableList<Doctor> list = FXCollections.observableArrayList();
    /**
     * An ObservableList of diagnosis.
     */
    private ObservableList<String> diagnosislist = FXCollections.observableArrayList();
    /**
     * An ObservableList of {@code Patient}.
     */
    private ObservableList<Patient> patientlist = FXCollections.observableArrayList();
    
    /**
     * A method to get all the {@code Doctor} currently in the database.
     * @return ObservableList of {@code Doctor} objects
     */
    public ObservableList<Doctor> listDoctors(){
        
        try {
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@codd.inf.unideb.hu:1521:ora12c","u_a55ypw","Josh15//");
            Statement stm = conn.createStatement();
            //System.out.println("Connection Succesful");
            ResultSet res = stm.executeQuery("select * from doctors");
            while(res.next()){
                list.add(new Doctor(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getLong(5),res.getString(6),res.getLong(7)));
            }
//            for(Doctor d: list){
//                System.out.println(d);
//            }
            res.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Something wrong with getting the Doctors");
        }
        
        return list;
    }
    
    /**
     * The method deletes a {@code Doctor} with the specified {@code id} from the database.
     * @param id of {@code Doctor} to delete
     */
    public void deleteDoctor(int id){
        int row;
         try {
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@codd.inf.unideb.hu:1521:ora12c","u_a55ypw","Josh15//");
            Statement stm = conn.createStatement();
            row = stm.executeUpdate("delete from doctors where id= "+id);
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Something wrong with deleting Doctor");
        }
    }
    /**
     * The method adds a new {@code Doctor} to the database.
     * @param id {@code Doctor} id
     * @param firstName {@code Doctor} first name
     * @param lastName {@code Doctor} last name
     * @param gender  {@code Doctor} gender
     * @param salary {@code Doctor} salary
     * @param address {@code Doctor} address
     * @param contact {@code Doctor} contact
     */
    public void addDoctor(int id, String firstName, String lastName,String gender,long salary,String address,long contact){
        int row;
         try {
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@codd.inf.unideb.hu:1521:ora12c","u_a55ypw","Josh15//");
            String query = "insert into doctors values (?,?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareStatement(query);
            //row=stm.executeUpdate("insert into doctors values ("+id+","+firstName+","+lastName+","+gender+","+salary+","+address+","+contact+")");
            stm.setInt(1, id);
            stm.setString(2, firstName);
            stm.setString(3, lastName);
            stm.setString(4, gender);
            stm.setLong(5, salary);
            stm.setString(6, address);
            stm.setLong(7, contact);
            
            stm.execute();
            System.out.println("1 row added...");
            stm.close(); 
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Having problem adding doctor");
        }
    }
    
    /**
     * Method to update details of a {@code Doctor}.
     * @param id {@code Doctor} id
     * @param firstName {@code Doctor} first name
     * @param lastName {@code Doctor} last name
     * @param gender {@code Doctor} gender
     * @param salary {@code Doctor} salary
     * @param address {@code Doctor} address
     * @param contact {@code Doctor} contact
     */
    public void updateDoctor(int id, String firstName, String lastName,String gender,long salary,String address,long contact){
        int row;
         try {
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@codd.inf.unideb.hu:1521:ora12c","u_a55ypw","Josh15//");
            String query = "update doctors set first_name=?,last_name=?,gender=?,salary=?,address=?,contact=? where id="+id;
            PreparedStatement stm = conn.prepareStatement(query);
            //row=stm.executeUpdate("insert into doctors values ("+id+","+firstName+","+lastName+","+gender+","+salary+","+address+","+contact+")");
            //stm.setInt(1, id);
            stm.setString(1, firstName);
            stm.setString(2, lastName);
            stm.setString(3, gender);
            stm.setLong(4, salary);
            stm.setString(5, address);
            stm.setLong(6, contact);
            //stm.setInt(7, id);
            
            stm.execute();
            System.out.println("1 row updated...");
            stm.close(); 
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * The method returns a list of various disease cases currently in the {@code Patient} table.
     * @return ObservableList of {@code Patient} diagnosis
     */
    public ObservableList<String> diagnosisList(){
        try {
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@codd.inf.unideb.hu:1521:ora12c","u_a55ypw","Josh15//");
            Statement stm = conn.createStatement();
            //System.out.println("Connection Succesful");
            ResultSet res = stm.executeQuery("select distinct diagnosis from patients");
            while(res.next()){
                diagnosislist.add(res.getString(1));
            }
//            for(String d: diagnosislist){
//                System.out.println(d);
//            }
            res.close();
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return diagnosislist;
    }
    /**
     * The method gets all the {@code Patient} in the database.
     * @return ObservableList of {@code Patiient} object
     */
    public ObservableList<Patient> listPatients(){
        
        try {
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@codd.inf.unideb.hu:1521:ora12c","u_a55ypw","Josh15//");
            Statement stm = conn.createStatement();
            //System.out.println("Connection Succesful");
            ResultSet res = stm.executeQuery("select * from patients");
            while(res.next()){
                patientlist.add(new Patient(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getInt(5),res.getLong(6),res.getLong(7),res.getString(8),res.getDate(9).toLocalDate()));
            }
//            for(Doctor d: list){
//                System.out.println(d);
//            }
            res.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return patientlist;
    }
    /**
     * Method counts {@code Patient} with diagnosis {@code s}.
     * @param s diagnosis
     * @return number of {@code Patient} with diagnosis {@code s}
     */
    public int countOf(String s){
        int c=0;
        try {
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@codd.inf.unideb.hu:1521:ora12c","u_a55ypw","Josh15//");
            Statement stm = conn.createStatement();
            //System.out.println("Connection Succesful");
            ResultSet res = stm.executeQuery("select * from patients where diagnosis='"+s+"'");
            while(res.next()){ c++;}
            res.close();
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    /**
     * Method adds {@code Patient} to the database.
     * @param id {@code Patient} id
     * @param firstName {@code Patient} first name
     * @param lastName {@code Patient} last name
     * @param diagnosis {@code Patient} diagnosis
     * @param room {@code Patient} room
     * @param contact {@code Patient} contact
     * @param bills {@code Patient} bills
     * @param address {@code Patient} address
     * @param age {@code Patient} date of birth
     */
    public void addPatient(int id, String firstName, String lastName,String diagnosis,int room,long contact,long bills,String address,String age){
        int row;
         try {
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@codd.inf.unideb.hu:1521:ora12c","u_a55ypw","Josh15//");
            String query = "insert into patients values (?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'))";
            String upRoom="update rooms set p_id=? where id=?";
            PreparedStatement stm = conn.prepareStatement(query);
            PreparedStatement pstm = conn.prepareStatement(upRoom);
            
            //row=stm.executeUpdate("insert into doctors values ("+id+","+firstName+","+lastName+","+gender+","+salary+","+address+","+contact+")");
            stm.setInt(1, id);
            stm.setString(2, firstName);
            stm.setString(3, lastName);
            stm.setString(4, diagnosis);
            stm.setInt(5, room);
            stm.setLong(6, contact);
            stm.setLong(7, bills);
            stm.setString(8, address);
            stm.setString(9, age);
            
            pstm.setInt(1, id);
            pstm.setInt(2,room);
            
            pstm.execute();
            stm.execute();
            System.out.println("1 row added...");
            pstm.close();
            stm.close(); 
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Method deletes a {@code Patient} with specified {@code id} and removes the {@code Patient} from the room he/she occupies.
     * @param id {@code Patient} id
     * @param rId room id
     */
    public void deletePatient(int id,int rId){
        int row;
        int room;
         try {
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@codd.inf.unideb.hu:1521:ora12c","u_a55ypw","Josh15//");
            Statement stm = conn.createStatement();
            row = stm.executeUpdate("delete from patients where id= "+id);
            room=stm.executeUpdate("update rooms set p_id=null where id="+rId);
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Method updates {@code Patient} details in the database.
     * @param id {@code Patient} id
     * @param firstName {@code Patient} first name
     * @param lastName {@code Patient} last name
     * @param diagnosis {@code Patient} diagnosis
     * @param room {@code Patient} room
     * @param contact {@code Patient} contact
     * @param bills {@code Patient} bills
     * @param address {@code Patient} address
     * @param age {@code Patient} date of birth
     */
    public void updatePatient(int id, String firstName, String lastName,String diagnosis,int room,long contact,long bills,String address,String age){
        int row;
         try {
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@codd.inf.unideb.hu:1521:ora12c","u_a55ypw","Josh15//");
            String query = "update patients set first_name=?,last_name=?,diagnosis=?,room=?,contact=?,bills=?,address=?,age=to_date(?,'yyyy-mm-dd') where id="+id;
            PreparedStatement stm = conn.prepareStatement(query);
            //row=stm.executeUpdate("insert into doctors values ("+id+","+firstName+","+lastName+","+gender+","+salary+","+address+","+contact+")");
            //stm.setInt(1, id);
            stm.setString(1, firstName);
            stm.setString(2, lastName);
            stm.setString(3, diagnosis);
            stm.setInt(4, room);
            stm.setLong(5, contact);
            stm.setLong(6, bills);
            stm.setString(7, address);
            stm.setString(8,age);
            
            stm.execute();
            System.out.println("1 row updated...");
            stm.close(); 
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
