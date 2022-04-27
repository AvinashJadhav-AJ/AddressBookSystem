package com.bridgelabz.addressbook.dto;

import lombok.Data;

@Data
public class ContactDTO {

    public String firstName;
    public String lastName;
    public String address;
    public String state;
    public String city;
    public String zip;
    public String phone;
    public String email;
    public String password;




    /**
     *
     * @param firstName
     * @param lastName
     * @param address
     * @param state
     * @param city
     * @param zip
     * @param phone
     */
    public ContactDTO(String firstName, String lastName, String address, String state, String city, String zip,
                      String phone, String email, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "ContactDTO [First Name =" + firstName + ", Last Name =" + lastName + ", Address =" + address
                + ", State =" + state + ", City =" + city + ", Zip =" + zip + ", Phone =" + phone + ", email =" + email
                +", password =" +password+"]";
    }

}
