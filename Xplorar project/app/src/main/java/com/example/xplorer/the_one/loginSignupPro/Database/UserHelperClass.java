package com.example.xplorer.the_one.loginSignupPro.Database;

public class UserHelperClass {

    String name,email,gender,dob,pass,phone;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String email, String gender, String dob, String pass, String phone) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.pass = pass;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
