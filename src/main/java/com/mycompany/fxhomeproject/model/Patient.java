/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fxhomeproject.model;

import java.time.LocalDate;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *{@code Patient} Class representing a Patient.
 * @author Josh
 */
public class Patient {
    /**
     * Id of the {@code Patient}.
     */
    private SimpleIntegerProperty id;
    /**
     * First name of {@code Patient}.
     */
    private SimpleStringProperty firstName;
    /**
     * Last name of {@code Patient}.
     */
    private SimpleStringProperty lastName;
    /**
     * Diagnosis of {@code Patient}.
     */
    private SimpleStringProperty diagnosis;
    /**
     * Room of the {@code Patient}.
     */
    private SimpleIntegerProperty room;
    /**
     * Contact of the {@code Patient}.
     */
    private SimpleLongProperty contact;
    /**
     * Bills of the {@code Patient}.
     */
    private SimpleLongProperty bills;
    /**
     * Address of the {@code Patient}.
     */
    private SimpleStringProperty address;
    /**
     * Date of birth of the {@code Patient}.
     */
    private LocalDate age;
    /**
     * Creates a new {@code Patient} with the given parameters.
     * @param id {@code Patient} id.
     * @param firstName {@code Patient} first name.
     * @param lastName {@code Patient} last name.
     * @param diagnosis {@code Patient} diagnosis
     * @param room {@code Patient} room
     * @param contact {@code Patient} contact
     * @param bills {@code Patient} bills
     * @param address {@code Patient} address
     * @param age {@code Patient} date of birth
     * @throws InvalidEnteryException when an invalid entry is made
     */
    public Patient(int id, String firstName, String lastName,String diagnosis,int room,long contact,long bills,String address,LocalDate age) throws InvalidEnteryException{
        if(id<0 || room<0 || bills<0 || contact<0){throw new InvalidEnteryException();}
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.diagnosis = new SimpleStringProperty(diagnosis);
        this.room = new SimpleIntegerProperty(room);
        this.contact = new SimpleLongProperty(contact);
        this.bills = new SimpleLongProperty(bills);
        this.address = new SimpleStringProperty(address);
        this.age =  age;
    }

    /**
     * Gets the Id.
     * @return id of {@code Patient}
     */
    public int getId() {
        return id.get();
    }
    /**
     * Gets first name.
     * @return {@code Patient} first name
     */
    public String getFirstName() {
        return firstName.get();
    }
    /**
     * Gets the last name.
     * @return {@code Patient} last name
     */
    public String getLastName() {
        return lastName.get();
    }
    /**
     * Gets the diagnosis.
     * @return {@code Patient} diagnosis
     */
    public String getDiagnosis() {
        return diagnosis.get();
    }
    /**
     * Gets the Room.
     * @return {@code Patient} room
     */
    public int getRoom() {
        return room.get();
    }
    /**
     * Gets the Contact.
     * @return {@code Patient} contact
     */
    public long getContact() {
        return contact.get();
    }
    /**
     * Gets the Bills.
     * @return {@code Patient} bills
     */
    public long getBills() {
        return bills.get();
    }
    /**
     * Gets the Address.
     * @return {@code Patient} address
     */
    public String getAddress() {
        return address.get();
    }
    /**
     * Gets the Date of birth.
     * @return {@code Patient} date of birth
     */
    public LocalDate getAge() {
        return age;
    }
    
    
}
