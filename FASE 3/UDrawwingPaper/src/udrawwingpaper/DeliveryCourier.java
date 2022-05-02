/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawwingpaper;

import java.math.BigInteger;

/**
 *
 * @author Erwin14k
 */


public class DeliveryCourier {
    private BigInteger dpi;
    private String name;
    private String lastName;
    private String licenseType;
    private String gender;
    private String phoneNumber;
    private String direction;
    
    public DeliveryCourier(BigInteger dpi,String name,String lastName,String licenseType,String gender,String phoneNumber,String direction){
        this.dpi=dpi;
        this.name=name;
        this.lastName=lastName;
        this.licenseType=licenseType;
        this.gender=gender;
        this.phoneNumber=phoneNumber;
        this.direction=direction;
        
    }

    public BigInteger getDpi() {
        return dpi;
    }

    public void setDpi(BigInteger dpi) {
        this.dpi = dpi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
    
    
    
}
