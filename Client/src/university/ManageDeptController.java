package university;

import DTO.CourseDTO;
import DTO.DeptDTO;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public  class ManageDeptController extends AnchorPane {
    private int adminId;

    protected final AnchorPane anchorPane;
    protected final Label label;
    protected final Label label0;
    protected final TextField id;
    protected final Button btnView;
    protected final TableView tableView;
   
    protected final Button deletebtn;
    protected final Button updatebtn;
    protected final Button viewall;
    protected final TextField name;
    protected final Label label1;
    protected final Label label2;
    protected final ComboBox head1;
    protected final TableView tableView0;
   
    protected final Button addbtn;
    protected final ComboBox combo;
    protected final Label label3;
     protected final TableColumn<DeptDTO, String> idtbl;
    protected final TableColumn<DeptDTO, String> nametbl;
    protected final TableColumn<DeptDTO, String> headtbl;
    protected final TableColumn<DeptDTO, String> nametbl0;
    
     protected final TableColumn<CourseDTO, String> courseid;
    protected final TableColumn<CourseDTO, String> coursename;
    protected final TableColumn<CourseDTO, String> hours;
    protected final TableColumn<CourseDTO, String> max;
Socket socket;
    DataInputStream dis;
    PrintStream ps;
  private ObservableList<DeptDTO> studentList = FXCollections.observableArrayList();
  private ObservableList<CourseDTO> courseList = FXCollections.observableArrayList();

    public ManageDeptController(int adminId) throws IOException {
this.adminId=adminId;
        anchorPane = new AnchorPane();
        label = new Label();
        label0 = new Label();
        id = new TextField();
        btnView = new Button();
        tableView = new TableView();
        idtbl = new TableColumn();
        nametbl = new TableColumn();
        headtbl = new TableColumn();
        nametbl0 = new TableColumn();
        deletebtn = new Button();
        updatebtn = new Button();
        viewall = new Button();
        name = new TextField();
        label1 = new Label();
        label2 = new Label();
        head1 = new ComboBox();
        tableView0 = new TableView();
        courseid = new TableColumn();
        coursename = new TableColumn();
        hours = new TableColumn();
        max = new TableColumn();
        addbtn = new Button();
        combo = new ComboBox();
        label3 = new Label();
        idtbl.setCellValueFactory(new PropertyValueFactory<>("id"));
        nametbl.setCellValueFactory(new PropertyValueFactory<>("name"));
        headtbl.setCellValueFactory(new PropertyValueFactory<>("head"));
        nametbl0.setCellValueFactory(new PropertyValueFactory<>("name2"));
courseid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coursename.setCellValueFactory(new PropertyValueFactory<>("name"));
        hours.setCellValueFactory(new PropertyValueFactory<>("hours"));
        max.setCellValueFactory(new PropertyValueFactory<>("max"));

        setId("AnchorPane");
        setPrefHeight(600.0);
        setPrefWidth(900.0);

        AnchorPane.setBottomAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setTopAnchor(anchorPane, 0.0);
        anchorPane.setId("AnchorPane");
        anchorPane.setPrefHeight(600.0);
        anchorPane.setPrefWidth(900.0);

        label.setLayoutX(327.0);
        label.setLayoutY(14.0);
        label.setPrefHeight(45.0);
        label.setPrefWidth(352.0);
        label.setText("Manage Departments");
        label.setFont(new Font("System Bold", 30.0));

        label0.setLayoutX(207.0);
        label0.setLayoutY(95.0);
        label0.setPrefHeight(21.0);
        label0.setPrefWidth(113.0);
        label0.setText("Department Id:");
        label0.setFont(new Font(15.0));

        id.setLayoutX(320.0);
        id.setLayoutY(88.0);
        id.setPrefHeight(34.0);
        id.setPrefWidth(254.0);

        btnView.setLayoutX(589.0);
        btnView.setLayoutY(88.0);
        btnView.setMnemonicParsing(false);
        btnView.setPrefHeight(34.0);
        btnView.setPrefWidth(57.0);
        btnView.setText("View");

        tableView.setLayoutX(60.0);
        tableView.setLayoutY(143.0);
        tableView.setPrefHeight(217.0);
        tableView.setPrefWidth(361.0);

        idtbl.setPrefWidth(66.0);
        idtbl.setText("ID");

        nametbl.setPrefWidth(91.0);
        nametbl.setText("Name");

        headtbl.setPrefWidth(91.0);
        headtbl.setText("Head ID");

        nametbl0.setPrefWidth(112.0);
        nametbl0.setText("Head Name");

        deletebtn.setLayoutX(264.0);
        deletebtn.setLayoutY(475.0);
        deletebtn.setMnemonicParsing(false);
        deletebtn.setPrefHeight(34.0);
        deletebtn.setPrefWidth(120.0);
        deletebtn.setText("Delete Department");

        updatebtn.setLayoutX(103.0);
        updatebtn.setLayoutY(475.0);
        updatebtn.setMnemonicParsing(false);
        updatebtn.setPrefHeight(34.0);
        updatebtn.setPrefWidth(132.0);
        updatebtn.setText("Update Dapartment");

        viewall.setLayoutX(660.0);
        viewall.setLayoutY(88.0);
        viewall.setMnemonicParsing(false);
        viewall.setPrefHeight(34.0);
        viewall.setPrefWidth(57.0);
        viewall.setText("View all");

        name.setLayoutX(209.0);
        name.setLayoutY(369.0);
        name.setPrefHeight(34.0);
        name.setPrefWidth(162.0);

        label1.setLayoutX(97.0);
        label1.setLayoutY(373.0);
        label1.setPrefHeight(27.0);
        label1.setPrefWidth(113.0);
        label1.setText("Dept Name:");
        label1.setFont(new Font(18.0));

        label2.setLayoutX(97.0);
        label2.setLayoutY(421.0);
        label2.setPrefHeight(17.0);
        label2.setPrefWidth(93.0);
        label2.setText("Head id:");
        label2.setFont(new Font(18.0));

        head1.setLayoutX(209.0);
        head1.setLayoutY(417.0);
        head1.setPrefHeight(34.0);
        head1.setPrefWidth(162.0);
        head1.setPromptText("Head ID");

        tableView0.setLayoutX(449.0);
        tableView0.setLayoutY(143.0);
        tableView0.setPrefHeight(217.0);
        tableView0.setPrefWidth(361.0);

        courseid.setPrefWidth(66.0);
        courseid.setText("Course ID");

        coursename.setPrefWidth(91.0);
        coursename.setText("Course Name");

        hours.setPrefWidth(91.0);
        hours.setText("Credit Hours");

        max.setPrefWidth(112.0);
        max.setText("Max Mark");

        addbtn.setLayoutX(580.0);
        addbtn.setLayoutY(464.0);
        addbtn.setMnemonicParsing(false);
        addbtn.setPrefHeight(8.0);
        addbtn.setPrefWidth(132.0);
        addbtn.setText("Add Course");
        addbtn.setFont(new Font(16.0));

        combo.setLayoutX(571.0);
        combo.setLayoutY(402.0);
        combo.setPrefHeight(37.0);
        combo.setPrefWidth(150.0);
        combo.setPromptText("New Course");

        label3.setLayoutX(503.0);
        label3.setLayoutY(368.0);
        label3.setPrefHeight(26.0);
        label3.setPrefWidth(309.0);
        label3.setText("Add New Course to this Department");
        label3.setFont(new Font("System Bold", 17.0));

        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(id);
        anchorPane.getChildren().add(btnView);
        tableView.getColumns().add(idtbl);
        tableView.getColumns().add(nametbl);
        tableView.getColumns().add(headtbl);
        tableView.getColumns().add(nametbl0);
        anchorPane.getChildren().add(tableView);
        anchorPane.getChildren().add(deletebtn);
        anchorPane.getChildren().add(updatebtn);
        anchorPane.getChildren().add(viewall);
        anchorPane.getChildren().add(name);
        anchorPane.getChildren().add(label1);
        anchorPane.getChildren().add(label2);
        anchorPane.getChildren().add(head1);
        tableView0.getColumns().add(courseid);
        tableView0.getColumns().add(coursename);
        tableView0.getColumns().add(hours);
        tableView0.getColumns().add(max);
        anchorPane.getChildren().add(tableView0);
        anchorPane.getChildren().add(addbtn);
        anchorPane.getChildren().add(combo);
        anchorPane.getChildren().add(label3);
        getChildren().add(anchorPane);
         populateComboBox();
          try {
            socket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
        } catch (IOException ex) {
            //Logger.getLogger(WishlistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnView.addEventHandler(ActionEvent.ACTION, event -> {
             tableView.getItems().clear();
             tableView0.getItems().clear();
            if (!id.getText().isEmpty()) {
                try {
                    JSONObject searchData = new JSONObject();
                    searchData.put("HEADER", "fnSearchDepartment");
                    searchData.put("ID", id.getText());
                    searchData.put("ADMINID", adminId);
                    ps.println(searchData.toString());
                    String response = dis.readLine();

                    if (response != null && !response.isEmpty()) {
                        // Clear previous data
                        studentList.clear();

                        try {
                            JSONObject searchedData = new JSONObject(response);

                            // Extract student information from the JSONObject
                            int id2 = searchedData.optInt("ID");
                            String name2 = searchedData.optString("NAME");
                            int head2 = searchedData.optInt("HEAD");
                            String head3 = searchedData.optString("HNAME");
                            // Create a Student object and add it to the ObservableList
                           DeptDTO professor = new DeptDTO(id2, name2, head2, head3);
                            studentList.add(professor);
                           tableView.setItems(studentList);
                           // name.setText(professor.getName());
                           /* age.setText(String.valueOf(professor.getAge()));
                            city.setText(professor.getCity());
                            phone.setText(professor.getPhone());
                            salary.setText(String.valueOf(professor.getSalary()));
                            dept.setText(String.valueOf(professor.getDept()));
                            degree.setText(professor.getDegree());*/
                        } catch (JSONException e) {
                            System.out.println("Error parsing JSON response: " + response);

                        }
                    } else {
                        // Handle empty response here
                        System.out.println("No results found.");
                        tableView.getItems().clear();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        viewall.addEventHandler(ActionEvent.ACTION, event -> {
             viewAll();

        });
        
          addbtn.setOnAction(event -> {
   
    DeptDTO selectedDepartment = (DeptDTO) tableView.getSelectionModel().getSelectedItem();

    if (selectedDepartment != null && combo.getValue() != null) {
   
        try {
            JSONObject userData = new JSONObject();
            userData.put("HEADER", "fnAddDeptCourse");
            userData.put("COURSE", combo.getValue());
            userData.put("ID", selectedDepartment.getId()); 
            ps.println(userData.toString());

            String response = dis.readLine();
            Platform.runLater(() -> {
                if (response.equals("Course added successfully")) {
                    JOptionPane.showMessageDialog(null, "Course added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    combo.setValue(null);
                    viewAll();
                    populateComboBox2(selectedDepartment.getId());
                     handleRowSelection(selectedDepartment);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Registration Failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        } catch (IOException | JSONException ex) {
            ex.printStackTrace(); // Handle exceptions appropriately
        }
    } else {
        // Display message if any field is empty
        JOptionPane.showMessageDialog(null, "Please choose a course and select a department first", "Error", JOptionPane.ERROR_MESSAGE);
    }
});

tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null && newSelection instanceof DeptDTO) {
        DeptDTO selectedStudent = (DeptDTO) newSelection;
        handleRowSelection(selectedStudent);
    }
});

        deletebtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        DeptDTO selectedItem = (DeptDTO) tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Check if the department has any students
            try {
                JSONObject checkStudentsData = new JSONObject();
                checkStudentsData.put("HEADER", "fnCheckDeptStudents");
                checkStudentsData.put("ID", selectedItem.getId());
                ps.println(checkStudentsData.toString());
                String response = dis.readLine();

                if (response != null && response.equals("yes")) {
                    // Department has students, show alert
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Cannot Delete Department");
                    alert.setContentText("This department has students. Cannot delete.");
                    alert.showAndWait();
                } else {
                    // Department has no students, proceed with deletion
                    int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete This Department?", "Delete Student Confirmation", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        // Use Platform.runLater() to perform UI operations on the JavaFX application thread
                        Platform.runLater(() -> {
                            try {
                                int id2 = selectedItem.getId();

                                JSONObject deletedStudent = new JSONObject();
                                deletedStudent.put("HEADER", "fnDeleteDept");
                                deletedStudent.put("ID", id2);
                                ps.println(deletedStudent.toString());

                                String response2 = dis.readLine();
                                if (response2.equals("Dept Deleted")) {
                                    // Update your JavaFX TableView and remove the selected item
                                    tableView.getItems().remove(selectedItem);
                                    tableView0.getItems().clear();
                                    JOptionPane.showMessageDialog(null, "Department Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error couldn't delete", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Choose Professor first", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
});

       updatebtn.addEventHandler(ActionEvent.ACTION, event -> {
    // Get selected item
    DeptDTO selectedProfessor = (DeptDTO) tableView.getSelectionModel().getSelectedItem();

    if (selectedProfessor != null) {
        // Display confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Update Confirmation");
        alert.setContentText("Are you sure you want to update the selected Dept?");

        // Handle user's choice
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // User clicked OK, proceed with update
                try {
                    JSONObject checkDeptData = new JSONObject();
                    checkDeptData.put("HEADER", "fnCheckDeptName");
                    checkDeptData.put("NAME", name.getText());
                    checkDeptData.put("ADMINID", adminId);
                    

                    ps.println(checkDeptData.toString());

                    // Read response from server
                    String response3 = dis.readLine();
                    if (response3 != null && response.equals("yes")) {
                        JOptionPane.showMessageDialog(null, "This department already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Prepare JSON data for update
                        JSONObject updateData = new JSONObject();
                        updateData.put("HEADER", "fnUpdateDept");
                        updateData.put("ID", selectedProfessor.getId());
                        updateData.put("NAME", name.getText());
                        updateData.put("HEAD", head1.getValue());

                        // Send update request to the server
                        ps.println(updateData.toString());
                        String response2 = dis.readLine();

                        if (response2.equals("Dept Updated successfully")) {
                            populateComboBox();
                            handleRowSelection(selectedProfessor);

                            name.clear();
                            head1.setValue(null);

                            JOptionPane.showMessageDialog(null, "Department Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to update", "Error", JOptionPane.INFORMATION_MESSAGE);
                            //tableView.getItems().clear();
                        }
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    Logger.getLogger(ManageProfessorsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    } else {
        // No professor selected, display a warning
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("No Selection");
        alert.setContentText("Please select a department to update.");
        alert.showAndWait();
    }
});




    }
    private void populateComboBox() throws IOException {
        // Fetch all courses from the 'course' table
        try {
            Socket socket = new Socket("127.0.0.1", 5005);
                        DataInputStream dis = new DataInputStream(socket.getInputStream());
                        PrintStream ps = new PrintStream(socket.getOutputStream());
            JSONObject fetchCoursesData = new JSONObject();
            fetchCoursesData.put("HEADER", "fnhead");
            fetchCoursesData.put("ADMINID", adminId);
           
            ps.println(fetchCoursesData.toString());

            // Handle the server response if needed
            String response = dis.readLine();

            if (response != null && !response.isEmpty()) {
                try {
                    JSONArray coursesArray = new JSONArray(response);

                    // Clear previous data in case it was populated before
                    head1.getItems().clear();

                    for (int i = 0; i < coursesArray.length(); i++) {
                        JSONObject courseData = coursesArray.getJSONObject(i);
                        
                        int id = courseData.optInt("ID");
                      

                        // Create a CourseDTO object and add it to the combo box
                        //CourseDTO course = new CourseDTO(courseName);
                        head1.getItems().add(id);
                         //dept.setConverter(new CourseStringConverter());
                    }
                } catch (JSONException e) {
                    System.out.println("Error parsing JSON response: " + response);
                }
            }

        } catch (JSONException ex) {
            JOptionPane.showMessageDialog(null, "Error fetching courses", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     private void populateComboBox2(int id) {
        // Fetch all courses from the 'course' table
        try {
            JSONObject fetchCoursesData = new JSONObject();
            fetchCoursesData.put("HEADER", "fnGetDeptNonCourses");
            fetchCoursesData.put("ID",id);
             fetchCoursesData.put("ADMINID",adminId);
            ps.println(fetchCoursesData.toString());

            // Handle the server response if needed
            String response = dis.readLine();

            if (response != null && !response.isEmpty()) {
                try {
                    JSONArray coursesArray = new JSONArray(response);

                    // Clear previous data in case it was populated before
                    combo.getItems().clear();

                    for (int i = 0; i < coursesArray.length(); i++) {
                        JSONObject courseData = coursesArray.getJSONObject(i);
                        
                        String courseName = courseData.optString("COURSE_NAME");
                        // Create a CourseDTO object and add it to the combo box
                        CourseDTO course = new CourseDTO(courseName);
                        combo.getItems().add(course);
                         combo.setConverter(new CourseStringConverter());
                    }
                } catch (JSONException e) {
                    System.out.println("Error parsing JSON response: " + response);
                }
            }

        } catch (IOException | JSONException ex) {
            JOptionPane.showMessageDialog(null, "Error fetching courses", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     private void viewAll(){
          tableView.getItems().clear();
             tableView0.getItems().clear();
            try {
                JSONObject searchData = new JSONObject();
                searchData.put("HEADER", "fnAllDept");
                searchData.put("ADMINID", adminId);
                ps.println(searchData.toString());
                String response = dis.readLine();
                if (response != null && !response.isEmpty()) {
                    // Clear previous data

                    try {
                        // Parse the JSON response and populate the courseList
                        JSONArray coursesArray = new JSONArray(response);

                        // Iterate through the array and extract course information
                        for (int i = 0; i < coursesArray.length(); i++) {
                            JSONObject courseData = coursesArray.getJSONObject(i);
                            int studentId = courseData.optInt("ID");
                            String studentName = courseData.optString("NAME");
                            int studentAge = courseData.optInt("HEAD");
                            String studentCity = courseData.optString("HNAME");
                            

                            // Create a CourseDTO object and add it to the ObservableList
                            DeptDTO professor = new DeptDTO(studentId, studentName, studentAge, studentCity);
                           studentList.add(professor);

                        }
                        
                        tableView.setItems(studentList);

                        // Update tableView0 outside the loop
                    } catch (JSONException e) {
                        System.out.println("Error parsing JSON response: " + response);
                        // Handle the case when the response is not a valid JSON
                        // For example, display an error message or log the issue
                    }
                } else {
                    System.out.println("No results found for student courses.");
                    tableView.getItems().clear();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);
            }   
     }
    private void handleRowSelection(DeptDTO selectedStudent) {
    populateComboBox2(selectedStudent.getId());
    name.setText(selectedStudent.getName());
    head1.setValue(selectedStudent.getHead());
    int selectedStudentId = selectedStudent.getId();
    try {
        JSONObject searchData = new JSONObject();
        searchData.put("HEADER", "fnGetDeptCourses");
        searchData.put("ID", String.valueOf(selectedStudentId));
        ps.println(searchData.toString());
        String response = dis.readLine();
        System.out.println("Course List: " + response);

        if (response != null && !response.isEmpty()) {
            // Clear previous data
            courseList.clear();

            try {
                // Parse the JSON response and populate the courseList
                JSONArray coursesArray = new JSONArray(response);

                // Iterate through the array and extract course information
                for (int i = 0; i < coursesArray.length(); i++) {
                    JSONObject courseData = coursesArray.getJSONObject(i);
                    int courseId = courseData.optInt("COURSE_ID");
                    String courseName = courseData.optString("COURSE_NAME");
                    int hours = courseData.optInt("HOURS");
                    int max = courseData.optInt("MAX");

                    // Create a CourseDTO object and add it to the ObservableList
                    CourseDTO course = new CourseDTO(courseId, courseName, hours, max);
                    courseList.add(course);
                }

                // Update tableView0 outside the loop
                tableView0.setItems(courseList);
                System.out.println("Course List: " + courseList);
            } catch (JSONException e) {
                System.out.println("Error parsing JSON response: " + response);
                // Handle the case when the response is not a valid JSON
                // For example, display an error message or log the issue
            }
        } else {
            // Handle empty response here
            System.out.println("No results found for student courses.");
            tableView0.getItems().clear();
        }
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);
    }
}

  
}
