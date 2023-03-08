package com.is.projektbackend.projekt.application.dto;

public class RegisterDto {

    private String nameAndLastName;
    private String email;
    private String password;

    private String accountName;

    public RegisterDto(String nameAndLastName, String email, String password, String accountName) {
        this.nameAndLastName = nameAndLastName;
        this.email = email;
        this.password = password;
        this.accountName = accountName;
    }

    public String getNameAndLastName() {
        return nameAndLastName;
    }

    public void setNameAndLastName(String nameAndLastName) {
        this.nameAndLastName = nameAndLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

}
