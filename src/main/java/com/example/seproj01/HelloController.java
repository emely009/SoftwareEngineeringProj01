package com.example.seproj01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HelloController {

    @FXML
    private MenuBar menuBar;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, String> idColumn;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, String> departmentColumn;
    @FXML
    private TableColumn<Student, String> majorColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private TextField idTf;
    @FXML
    private TextField firstNameTf;
    @FXML
    private TextField emailTf;
    @FXML
    private TextField lastNameTf;
    @FXML
    private TextField departmentTf;
    @FXML
    private TextField majorTf;

    @FXML
    private ImageView image;

    private Student selectedStudent;
    private ObservableList<Student> myList = FXCollections.observableArrayList();

    public void initialize() {


        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        majorColumn.setCellValueFactory(new PropertyValueFactory<>("major"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableView.setItems(myList);
    }


    public boolean Validation(String first, String last, String email, String id) {
        // Sets a restriction: ID must consist of exactly 5 digits
        boolean isIdValid = id.matches("\\d{5}");


        // sets a restriction first name must start with a capital letter
        boolean isFirstNameValid = first.matches("[A-Z][a-zA-Z]*");

        // sets a restriction last name must start with a capital letter
        boolean isLastNameValid = last.matches("[A-Z][a-zA-Z]*");

        // sets a restriction where email must meet xxx@xxx.xxx
        boolean isEmailValid = email.matches("[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,4}");

        // this array is created to put all error messages together
        List<String> validationErrors = new ArrayList<>();

        // checks if user inputs valid information if information is not valid
        // error message will show on alert window
        if (!isIdValid) {
            validationErrors.add("ID must consist of exactly 5 digits.");
        }

        if (!isFirstNameValid) {
            validationErrors.add("\nFirst Name must start with a capital letter.");
        }

        if (!isLastNameValid) {
            validationErrors.add("\n Last Name must start with a capital letter.");
        }

        if (!isEmailValid) {
            validationErrors.add("\n Email must follow string1@string2.domain format");
        }
        if (!validationErrors.isEmpty()) {
            showErrorAlert("Validation Errors", String.join("\n", validationErrors));
            return false; // Validation failed
        }

        return true; // Validation passed
    }


    @FXML
    private void addHandler(ActionEvent event) {
        String id = idTf.getText();
        String first = firstNameTf.getText();
        String last = lastNameTf.getText();
        String department = departmentTf.getText();
        String major = majorTf.getText();
        String email = emailTf.getText();


        // Validate the input
        if (!Validation(first, last, email, id)) {
            return; // Do not add the student if validation fails
        }
        Student newStudent = new Student(id,first, last,department,major, email);
        myList.add(newStudent);
    }


    @FXML
    private void ClearHandler(ActionEvent event) {
        idTf.clear();
        firstNameTf.clear();
        lastNameTf.clear();
        departmentTf.clear();
        majorTf.clear();
        emailTf.clear();

    }

    @FXML
    private void editHandler(ActionEvent event) {
        if (selectedStudent != null) {
            String id = idTf.getText();
            String first = firstNameTf.getText();
            String last = lastNameTf.getText();
            String dep = departmentTf.getText();
            String major = majorTf.getText();
            String email = emailTf.getText();


            // Validate the input
            if (!Validation(first, last, email, id)) {
                return; // Do not add the student if validation fails
            }

            // Update the selected employee with the new values
            selectedStudent.setId(id);
            selectedStudent.setFirstName(first);
            selectedStudent.setLastName(last);
            selectedStudent.setDepartment(dep);
            selectedStudent.setMajor(major);
            selectedStudent.setEmail(email);


            // Update the TableView by refreshing the items
            tableView.refresh();




        } else {
            System.out.println("Please select an employee to update");
        }
    }

    @FXML
    private void deleteHandler(ActionEvent event) {
        Student selectedStudent= tableView.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            // Remove the selected employee from the ObservableList
            myList.remove(selectedStudent);



        }
    }

    @FXML
    private void tableClicked(MouseEvent event) {
        selectedStudent = tableView.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            // Load selected record into the text fields
            idTf.setText(selectedStudent.getId());
            firstNameTf.setText(selectedStudent.getFirstName());
            lastNameTf.setText(selectedStudent.getLastName());
            departmentTf.setText(selectedStudent.getDepartment());
            majorTf.setText(selectedStudent.getMajor());
            emailTf.setText(selectedStudent.getEmail());

        }
    }




    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
