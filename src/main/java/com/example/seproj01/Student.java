package com.example.seproj01;

import javafx.beans.property.SimpleStringProperty;

public class Student {
    private final SimpleStringProperty IdColumn;
    private final SimpleStringProperty firstNameColumn;
    private final SimpleStringProperty lastNameColumn;
    private final SimpleStringProperty departmentColumn;
    private final SimpleStringProperty majorColumn;
    private final SimpleStringProperty emailColumn;



    public Student(String Id, String firstName, String lastName, String department, String major, String email) {

        this.IdColumn = new SimpleStringProperty(Id);
        this.firstNameColumn = new SimpleStringProperty(firstName);
        this.lastNameColumn = new SimpleStringProperty(lastName);
        this.departmentColumn = new SimpleStringProperty(department);
        this.majorColumn = new SimpleStringProperty(major);
        this.emailColumn = new SimpleStringProperty(email);

    }



    // Getter methods
    public String getId(){
        return IdColumn.get();
    }

    public String getFirstName() {
        return firstNameColumn.get();
    }

    public String getLastName() {
        return lastNameColumn.get();
    }

    public String getDepartment(){
        return departmentColumn.get();
    }

    public String getMajor(){
        return majorColumn.get();
    }

    public String getEmail(){
        return emailColumn.get();
    }



    // Setter methods
    public void setId(String Id) {
        this.IdColumn.set(Id);
    }

    public void setFirstName(String firstName) {
        this.firstNameColumn.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastNameColumn.set(lastName);
    }

    public void setDepartment(String department) {
        this.departmentColumn.set(department);
    }

    public void setMajor(String major) {
        this.majorColumn.set(major);
    }

    public void setEmail (String email){
        this.emailColumn.set(email);
    }



}