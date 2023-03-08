package com.is.projektbackend.projekt.application.dto;

public class CreateMemberDto {

    private String  nameAndLastName;
    private String  email;
    private Integer membershipTypeId;
    private String  accountName;
    private String  password;
    private String  repeatedPassword;

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public String getNameAndLastName() {
        return nameAndLastName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getMembershipTypeId() {
        return membershipTypeId;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "CreateMemberDto{" +
            "nameAndLastName='" + nameAndLastName + '\'' +
            ", email='" + email + '\'' +
            ", membershipTypeId=" + membershipTypeId +
            ", accountName='" + accountName + '\'' +
            ", password='" + password + '\'' +
            ", repeatedPassword='" + repeatedPassword + '\'' +
            '}';
    }

}
