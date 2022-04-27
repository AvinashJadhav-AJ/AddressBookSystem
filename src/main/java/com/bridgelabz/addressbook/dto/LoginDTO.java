package com.bridgelabz.addressbook.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
@Data
public class LoginDTO {

    public String email;

    public  String password;

    public LoginDTO(String emailID, String password) {
        this.email = emailID;
        this.password = password;
    }
}
