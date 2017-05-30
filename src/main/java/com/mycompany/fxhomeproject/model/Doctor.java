package com.mycompany.fxhomeproject.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Represents a {@code Doctor}.
 * @author Josh
 */
public class Doctor {
   /**
    * id of the {@code Doctor}.
    */
    private SimpleIntegerProperty id;
    /**
     * First name of the {@code Doctor}.
     */
    private SimpleStringProperty firstName;
    /**
     * Last name of the {@code Doctor}.
     */
    private SimpleStringProperty lastName;
    /**
     * Gender of the {@code Doctor}.
     */
    private SimpleStringProperty gender;
    /**
     * Salary of {@code Doctor}.
     */
    private SimpleLongProperty salary;
    /**
     * Address of the {@code Doctor}.
     */
    private SimpleStringProperty address;
    /**
     * Contact of the {@code Doctor}.
     */
    private SimpleLongProperty contact;
    /**
     * Creates a new {@code Doctor} with the given parameters.
     * @param id {@code Doctor} id
     * @param firstName {@code Doctor} first name
     * @param lastName {@code Doctor} last name
     * @param gender {@code Doctor} gender
     * @param salary {@code Doctor} salary
     * @param address {@code Doctor} address
     * @param contact {@code Doctor} contact
     * @throws InvalidEnteryException when invalid entry is made
     */
    public Doctor(int id,String firstName, String lastName,String gender, long salary,String address,long contact)throws InvalidEnteryException {
        if(id<0 || salary<0 || contact<0){throw new InvalidEnteryException();}
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.id = new SimpleIntegerProperty(id);
        this.gender = new SimpleStringProperty(gender);
        this.salary = new SimpleLongProperty(salary);
        this.address = new SimpleStringProperty(address);
        this.contact = new SimpleLongProperty(contact);
    }
    /**
     * Gets the Id.
     * @return {@code Doctor} id
     */
    public int getId() {
        return id.get();
    }
    /**
     * Gets the Gender.
     * @return {@code Doctor} gender
     */
    public String getGender() {
        return gender.get();
    }
    /**
     * Gets the salary.
     * @return {@code Doctor} salary
     */
    public long getSalary() {
        return salary.get();
    }
    /**
     * Gets the address.
     * @return {@code Doctor} address
     */
    public String getAddress() {
        return address.get();
    }
    /**
     * Gets the Contact.
     * @return {@code Doctor} contact
     */
    public long getContact() {
        return contact.get();
    }
    /**
     * Gets the First name.
     * @return {@code Doctor} first name
     */
     public String getFirstName() {
        return firstName.get();
    }
    /**
     * Gets the Last name.
     * @return {@code Doctor} last name
     */
    public String getLastName() {
        return lastName.get();
    }

}


