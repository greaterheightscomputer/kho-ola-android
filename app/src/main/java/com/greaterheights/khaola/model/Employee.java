package com.greaterheights.khaola.model;

public class Employee {

    private String emp_id;
    private String fullname;
    private String address;
    private String phone;
    private String email;
    private String gender;
    private String marital_status;
    private String hire_date;
    private String date_of_birth;
    private int dept_id;
    private byte[] picture;
    private String creation_date;

    public Employee() {
    }

    public Employee(String emp_id, String fullname, String address, String phone, String email, String gender, String marital_status, String hire_date, String date_of_birth, int dept_id, byte[] picture, String creation_date) {
        this.emp_id = emp_id;
        this.fullname = fullname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.marital_status = marital_status;
        this.hire_date = hire_date;
        this.date_of_birth = date_of_birth;
        this.dept_id = dept_id;
        this.picture = picture;
        this.creation_date = creation_date;
    }

    public Employee(String fullname, String address, String phone, String email, String gender, String marital_status, String hire_date, String date_of_birth, int dept_id, byte[] picture, String creation_date) {
        this.fullname = fullname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.marital_status = marital_status;
        this.hire_date = hire_date;
        this.date_of_birth = date_of_birth;
        this.dept_id = dept_id;
        this.picture = picture;
        this.creation_date = creation_date;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }
}
