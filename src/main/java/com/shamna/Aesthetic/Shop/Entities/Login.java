package com.shamna.Aesthetic.Shop.Entities;

import jakarta.persistence.*;


@Entity

public class Login {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userId;
    private String uname;
    private String pass;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    @Version
    private Integer version;

    public Login() {
    }

    public Login(int userId, Integer version, String role, String phone, String firstName, String email, String pass, String uname, String lastName) {
        this.userId = userId;
        this.version = version;
        this.role = role;
        this.phone = phone;
        this.firstName = firstName;
        this.email = email;
        this.pass = pass;
        this.uname = uname;
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}

