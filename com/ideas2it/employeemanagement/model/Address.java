package com.ideas2it.employeemanagement.model;
 
/**
 * POJO class containing address details
 * it includes details such as id, door number, street name, district,
 * state, country, pincode
 * @author Dhivyaa Eswaran
 * @version 1.0 12-04-2021
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
    public Address(int id, String doorNumber, String streetName,
            String district, String state, String country, int pinCode) {       
       this.id = id;
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

    public void setId(int id) {
        this.id = id;
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

    public String toString() {
        return "\nId:" + this.id + "\nDoorNumber:" + this.doorNumber
                + "\nStreetName:" + this.streetName + "\nDistrict:" + this.district
                + "\nState:" + this.state + "\nCountry:" + this.country + "\nPinCode:" + this.pinCode;
    }
} 