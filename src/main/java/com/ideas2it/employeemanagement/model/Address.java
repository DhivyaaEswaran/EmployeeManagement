package com.ideas2it.employeemanagement.model;

import java.sql.Date;
import java.util.List; 

import com.ideas2it.employeemanagement.model.Employee;

/**
 * POJO class containing address details
 * it includes details such as id, door number, street name, district,
 * state, country, pincode
 * @author Dhivyaa Eswaran
 * @version 1.0 29-05-2021
 */
public class Address {
    private int id;
    private int addressId;
    private String doorNumber;
    private String streetName;
    private String district;
    private String state;
    private String country;
    private int pinCode;
    private boolean isDeleted;
    private Employee employee;

    public Address() {
    }

    /**
     * Here we get the Employees address details 
     * @param doorNumber - doorNumber
     * @param streetName - streetName
     * @param district - district
     * @param state - state
     * @param country - country
     * @param pinCode - pinCode
     */
    public Address(String doorNumber, String streetName,
            String district, String state, String country, int pinCode) {       
       this.doorNumber = doorNumber;
       this.streetName = streetName;
       this.district = district;
       this.state = state;
       this.country = country;
       this.pinCode = pinCode;
    }
 
    /**
     * Here we get the Employees address details 
     * @param id - Address id
     * @param doorNumber - doorNumber
     * @param streetName - streetName
     * @param district - district
     * @param state - state
     * @param country - country
     * @param pinCode - pinCode
     */
    public Address(int id, int addressId, String doorNumber, String streetName,
            String district, String state, String country, int pinCode) {       
       this.id = id;
       this.addressId = addressId;
       this.doorNumber = doorNumber;
       this.streetName = streetName;
       this.district = district;
       this.state = state;
       this.country = country;
       this.pinCode = pinCode;
    }
    
    public boolean getIsDeleted() {
        return isDeleted;
    }

    public int getAddressId() {
        return addressId;
    }
  
    public int getId() {
        return id;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getDistrict() {
        return district;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public int getPinCode() {
        return pinCode;
    }    

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
 
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setId(int id) {
        this.id = id;
    }
} 