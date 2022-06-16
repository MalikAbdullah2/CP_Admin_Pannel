package com.example.cp_admin_pannel;

public class admin {
    public admin(String email, String name, String gender, String contact) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    private String email,name,gender,contact;

    public admin() {
    }
}
