package university;

import DTO.CourseDTO;
import DTO.StudentDTO;
import DTO.ProfessorDTO;
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

public  class ManageProfessorsController extends AnchorPane {
    private int adminId;

    protected final AnchorPane anchorPane;
    protected final Label label;
    protected final TextField id;
    protected final Button viewbtn;
    protected final TableView tableView;
    protected final TableColumn<ProfessorDTO, String> idtbl;
     protected final TableColumn<ProfessorDTO, String> fnametbl;
          protected final TableColumn<ProfessorDTO, String> lnametbl;

     protected final TableColumn<ProfessorDTO, String> emailtbl;
     protected final TableColumn<ProfessorDTO, String> dobtbl;
     protected final TableColumn<ProfessorDTO, String> deptidtbl;
     
      protected final TableColumn<ProfessorDTO, String> phonetbl;
     protected final TableColumn<ProfessorDTO, String> deptnametbl;
    protected final TableColumn<ProfessorDTO, String> citytbl;
    protected final TableColumn<ProfessorDTO, String> degreetbl;
    protected final TableColumn<ProfessorDTO, String> salarytbl;
    

   
    protected final Label label0;
    protected final Button viewAll;
    protected final TextField fname;
    protected final TextField lname;
    protected final TextField email;
    protected final TextField pass;
    protected final TextField city;
    protected final TextField phone;
    protected final ComboBox dept;
    protected final Button updatebtn;
    protected final Button deletebtn;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
    protected final Label label4;
    protected final Label label5;
    protected final Label label6;
    protected final Label label7;
    protected final TextField degree;
    protected final TextField salary;
    protected final Label label8;
    protected final Label label9;
     Socket socket;
    DataInputStream dis;
    PrintStream ps;
    private ObservableList<ProfessorDTO> studentList = FXCollections.observableArrayList();
    private ObservableList<CourseDTO> courseList = FXCollections.observableArrayList();


    public ManageProfessorsController(int adminId) throws IOException {
this.adminId=adminId;
        anchorPane = new AnchorPane();
        label = new Label();
        id = new TextField();
        viewbtn = new Button();
        tableView = new TableView();
        idtbl = new TableColumn();
        fnametbl = new TableColumn();
        lnametbl = new TableColumn();
        emailtbl = new TableColumn();
        citytbl = new TableColumn();
        dobtbl = new TableColumn();
        phonetbl = new TableColumn();
        deptidtbl = new TableColumn();
        deptnametbl = new TableColumn();
        degreetbl = new TableColumn();
        salarytbl = new TableColumn();
        label0 = new Label();
        viewAll = new Button();
        fname = new TextField();
        lname = new TextField();
        email = new TextField();
        pass = new TextField();
        city = new TextField();
        phone = new TextField();
        dept = new ComboBox();
        updatebtn = new Button();
        deletebtn = new Button();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        label4 = new Label();
        label5 = new Label();
        label6 = new Label();
        label7 = new Label();
        degree = new TextField();
        salary = new TextField();
        label8 = new Label();
        label9 = new Label();

        setId("AnchorPane");
        setPrefHeight(600.0);
        setPrefWidth(900.0);

        AnchorPane.setBottomAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setTopAnchor(anchorPane, 0.0);
        anchorPane.setId("AnchorPane");
        anchorPane.setPrefHeight(609.0);
        anchorPane.setPrefWidth(900.0);

        label.setLayoutX(103.0);
        label.setLayoutY(83.0);
        label.setPrefHeight(25.0);
        label.setPrefWidth(97.0);
        label.setText("Professor id:");
        label.setFont(new Font(15.0));

        id.setLayoutX(197.0);
        id.setLayoutY(79.0);
        id.setPrefHeight(33.0);
        id.setPrefWidth(245.0);

        viewbtn.setLayoutX(476.0);
        viewbtn.setLayoutY(79.0);
        viewbtn.setMnemonicParsing(false);
        viewbtn.setPrefHeight(33.0);
        viewbtn.setPrefWidth(61.0);
        viewbtn.setText("View");

        tableView.setLayoutX(40.0);
        tableView.setLayoutY(148.0);
        tableView.setPrefHeight(183.0);
        tableView.setPrefWidth(748.0);

        idtbl.setPrefWidth(29.0);
        idtbl.setText("ID");
        idtbl.setCellValueFactory(new PropertyValueFactory<>("id"));
        fnametbl.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lnametbl.setCellValueFactory(new PropertyValueFactory<>("lname"));
        emailtbl.setCellValueFactory(new PropertyValueFactory<>("email"));
        dobtbl.setCellValueFactory(new PropertyValueFactory<>("dob"));
        citytbl.setCellValueFactory(new PropertyValueFactory<>("city"));
        phonetbl.setCellValueFactory(new PropertyValueFactory<>("phone"));
        deptidtbl.setCellValueFactory(new PropertyValueFactory<>("deptid"));
        deptnametbl.setCellValueFactory(new PropertyValueFactory<>("deptname"));
        salarytbl.setCellValueFactory(new PropertyValueFactory<>("salary"));
        degreetbl.setCellValueFactory(new PropertyValueFactory<>("degree"));
        

        fnametbl.setPrefWidth(72.0);
        fnametbl.setText("First Name");

        lnametbl.setPrefWidth(72.0);
        lnametbl.setText("Last Name");

        emailtbl.setPrefWidth(75.0);
        emailtbl.setText("Email");

        citytbl.setPrefWidth(61.0);
        citytbl.setText("City");

        dobtbl.setPrefWidth(61.0);
        dobtbl.setText("DOB");

        phonetbl.setPrefWidth(56.0);
        phonetbl.setText("Phone");

        deptidtbl.setPrefWidth(61.0);
        deptidtbl.setText("Dept ID");

        deptnametbl.setPrefWidth(97.0);
        deptnametbl.setText("Dept Name");

        degreetbl.setMinWidth(0.0);
        degreetbl.setPrefWidth(65.0);
        degreetbl.setText("Degree");

        salarytbl.setMaxWidth(116.0);
        salarytbl.setMinWidth(0.0);
        salarytbl.setPrefWidth(75.0);
        salarytbl.setText("Salary");

        label0.setLayoutX(218.0);
        label0.setLayoutY(14.0);
        label0.setPrefHeight(45.0);
        label0.setPrefWidth(464.0);
        label0.setText("Manage Professors Information");
        label0.setFont(new Font("System Bold", 30.0));

        viewAll.setLayoutX(560.0);
        viewAll.setLayoutY(79.0);
        viewAll.setMnemonicParsing(false);
        viewAll.setPrefHeight(33.0);
        viewAll.setPrefWidth(148.0);
        viewAll.setText("View all professors");

        fname.setLayoutX(115.0);
        fname.setLayoutY(368.0);
        fname.setPrefHeight(33.0);
        fname.setPrefWidth(169.0);
        fname.setFont(new Font(15.0));

        lname.setLayoutX(346.0);
        lname.setLayoutY(368.0);
        lname.setPrefHeight(33.0);
        lname.setPrefWidth(169.0);
        lname.setFont(new Font(15.0));

        email.setLayoutX(113.0);
        email.setLayoutY(430.0);
        email.setPrefHeight(33.0);
        email.setPrefWidth(169.0);
        email.setFont(new Font(15.0));

        pass.setLayoutX(346.0);
        pass.setLayoutY(430.0);
        pass.setPrefHeight(33.0);
        pass.setPrefWidth(169.0);
        pass.setFont(new Font(15.0));

        city.setLayoutX(548.0);
        city.setLayoutY(368.0);
        city.setPrefHeight(33.0);
        city.setPrefWidth(169.0);
        city.setFont(new Font(15.0));

        phone.setLayoutX(548.0);
        phone.setLayoutY(430.0);
        phone.setPrefHeight(33.0);
        phone.setPrefWidth(169.0);
        phone.setFont(new Font(15.0));

        dept.setLayoutX(115.0);
        dept.setLayoutY(493.0);
        dept.setOpacity(0.68);
        dept.setPrefHeight(33.0);
        dept.setPrefWidth(169.0);
        dept.setPromptText("Department");

        updatebtn.setLayoutX(353.0);
        updatebtn.setLayoutY(541.0);
        updatebtn.setMnemonicParsing(false);
        updatebtn.setPrefHeight(45.0);
        updatebtn.setPrefWidth(84.0);
        updatebtn.setText("Update");
        updatebtn.setFont(new Font(16.0));

        deletebtn.setLayoutX(484.0);
        deletebtn.setLayoutY(541.0);
        deletebtn.setMnemonicParsing(false);
        deletebtn.setPrefHeight(45.0);
        deletebtn.setPrefWidth(84.0);
        deletebtn.setText("Delete");
        deletebtn.setFont(new Font(16.0));

        label1.setLayoutX(115.0);
        label1.setLayoutY(342.0);
        label1.setPrefHeight(26.0);
        label1.setPrefWidth(97.0);
        label1.setText("First Name");
        label1.setFont(new Font("System Bold", 15.0));

        label2.setLayoutX(346.0);
        label2.setLayoutY(342.0);
        label2.setPrefHeight(26.0);
        label2.setPrefWidth(97.0);
        label2.setText("Last Name");
        label2.setFont(new Font("System Bold", 15.0));

        label3.setLayoutX(548.0);
        label3.setLayoutY(342.0);
        label3.setPrefHeight(26.0);
        label3.setPrefWidth(97.0);
        label3.setText("City");
        label3.setFont(new Font("System Bold", 15.0));

        label4.setLayoutX(113.0);
        label4.setLayoutY(404.0);
        label4.setPrefHeight(26.0);
        label4.setPrefWidth(97.0);
        label4.setText("Email");
        label4.setFont(new Font("System Bold", 15.0));

        label5.setLayoutX(351.0);
        label5.setLayoutY(404.0);
        label5.setPrefHeight(26.0);
        label5.setPrefWidth(97.0);
        label5.setText("Password");
        label5.setFont(new Font("System Bold", 15.0));

        label6.setLayoutX(548.0);
        label6.setLayoutY(404.0);
        label6.setPrefHeight(26.0);
        label6.setPrefWidth(97.0);
        label6.setText("Phone");
        label6.setFont(new Font("System Bold", 15.0));

        label7.setLayoutX(113.0);
        label7.setLayoutY(468.0);
        label7.setPrefHeight(26.0);
        label7.setPrefWidth(97.0);
        label7.setText("Department");
        label7.setFont(new Font("System Bold", 15.0));

        degree.setLayoutX(346.0);
        degree.setLayoutY(490.0);
        degree.setPrefHeight(33.0);
        degree.setPrefWidth(169.0);
        degree.setFont(new Font(15.0));

        salary.setLayoutX(548.0);
        salary.setLayoutY(490.0);
        salary.setPrefHeight(33.0);
        salary.setPrefWidth(169.0);
        salary.setFont(new Font(15.0));

        label8.setLayoutX(351.0);
        label8.setLayoutY(464.0);
        label8.setPrefHeight(26.0);
        label8.setPrefWidth(97.0);
        label8.setText("Degree");
        label8.setFont(new Font("System Bold", 15.0));

        label9.setLayoutX(548.0);
        label9.setLayoutY(464.0);
        label9.setPrefHeight(26.0);
        label9.setPrefWidth(97.0);
        label9.setText("Salary");
        label9.setFont(new Font("System Bold", 15.0));

        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(id);
        anchorPane.getChildren().add(viewbtn);
        tableView.getColumns().add(idtbl);
        tableView.getColumns().add(fnametbl);
        tableView.getColumns().add(lnametbl);
        tableView.getColumns().add(emailtbl);
        tableView.getColumns().add(citytbl);
        tableView.getColumns().add(dobtbl);
        tableView.getColumns().add(phonetbl);
        tableView.getColumns().add(deptidtbl);
        tableView.getColumns().add(deptnametbl);
        tableView.getColumns().add(degreetbl);
        tableView.getColumns().add(salarytbl);
        anchorPane.getChildren().add(tableView);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(viewAll);
        anchorPane.getChildren().add(fname);
        anchorPane.getChildren().add(lname);
        anchorPane.getChildren().add(email);
        anchorPane.getChildren().add(pass);
        anchorPane.getChildren().add(city);
        anchorPane.getChildren().add(phone);
        anchorPane.getChildren().add(dept);
        anchorPane.getChildren().add(updatebtn);
        anchorPane.getChildren().add(deletebtn);
        anchorPane.getChildren().add(label1);
        anchorPane.getChildren().add(label2);
        anchorPane.getChildren().add(label3);
        anchorPane.getChildren().add(label4);
        anchorPane.getChildren().add(label5);
        anchorPane.getChildren().add(label6);
        anchorPane.getChildren().add(label7);
        anchorPane.getChildren().add(degree);
        anchorPane.getChildren().add(salary);
        anchorPane.getChildren().add(label8);
        anchorPane.getChildren().add(label9);
        getChildren().add(anchorPane);
           populateComboBox();

                 try {
            socket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
        } catch (IOException ex) {
            //Logger.getLogger(WishlistController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 viewAll();
         viewbtn.addEventHandler(ActionEvent.ACTION, event -> {
    if (!id.getText().isEmpty()) {
        try {
            JSONObject searchData = new JSONObject();
            searchData.put("HEADER", "fnSearchProfessor");
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
                    int stdid = searchedData.optInt("ID");
                    String fname = searchedData.optString("FNAME");
                    String lname = searchedData.optString("LNAME");
                    String email = searchedData.optString("EMAIL");
                    String dob = searchedData.optString("DOB");
                   String phone = searchedData.optString("PHONE");
                    String city = searchedData.optString("CITY");
                    String pass =searchedData.optString("PASS");
                    int deptid = searchedData.optInt("DID");
                    String deptname = searchedData.optString("DNAME");
                    int salary=searchedData.optInt("SALARY");
                   String degree= searchedData.optString("DEGREE");

                    // Create a Student object and add it to the ObservableList
                    ProfessorDTO student = new ProfessorDTO(stdid,fname, lname, email, phone, deptid,deptname,city,pass,dob,salary,degree);
                    studentList.add(student);
                    tableView.setItems(studentList);
                       /* namef.setText(student.getName());
                        agef.setText(String.valueOf(student.getAge()));
                        cityf.setText(student.getCity());
                        textField.setText(String.valueOf(student.getSemester()));*/
                    

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
          tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null && newSelection instanceof ProfessorDTO) {
                // Cast newSelection to StudentDTO
                ProfessorDTO selectedStudent = (ProfessorDTO) newSelection;
                // Display selected row data in text fields
                fname.setText(selectedStudent.getFname());
                lname.setText(selectedStudent.getLname());
                city.setText(selectedStudent.getCity());
                email.setText(selectedStudent.getEmail());
                pass.setText(selectedStudent.getPass());
                phone.setText(selectedStudent.getPhone());
                salary.setText((String.valueOf(selectedStudent.getSalary())));
                degree.setText(selectedStudent.getDegree());
                
                
            }
        });
           viewAll.addEventHandler(ActionEvent.ACTION, event -> {
               viewAll();
        });
           updatebtn.addEventHandler(ActionEvent.ACTION, event -> {
            // Get selected item
            ProfessorDTO selectedProfessor = (ProfessorDTO) tableView.getSelectionModel().getSelectedItem();

            if (selectedProfessor != null) {
                // Display confirmation dialog
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Update Confirmation");
                alert.setContentText("Are you sure you want to update the selected professor?");

                // Handle user's choice
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        // User clicked OK, proceed with update
                        try {
                            // Prepare JSON data for update
                            JSONObject userData = new JSONObject();
                            userData.put("HEADER", "fnUpdateProfessor");
                            userData.put("ADMINID", adminId);
                            userData.put("ID", selectedProfessor.getId());
                            userData.put("FNAME", fname.getText());
                        userData.put("LNAME", lname.getText());
                        userData.put("EMAIL", email.getText());
                        userData.put("PHONE", phone.getText());
                        userData.put("DEPT", dept.getValue());
                        userData.put("CITY", city.getText());
                        userData.put("PASS", pass.getText());
                        userData.put("SALARY", salary.getText());
                        userData.put("DEGREE", degree.getText());
                            // Send update request to the server
                            ps.println(userData.toString());
                            String response2 = dis.readLine();

                             if (response2.equals("Professor Updated successfully")) {
                                // Clear previous data
                                
                                // Clear text fields
                                fname.clear();
                                lname.clear();
                                city.clear();
                                phone.clear();
                                email.clear();
                                pass.clear();
                                dept.setValue(null);
                                salary.clear();
                                degree.clear();
                                JOptionPane.showMessageDialog(null, "Professor updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Failed to update", "Error", JOptionPane.INFORMATION_MESSAGE);
                                //tableView.getItems().clear();
                            }

                            // Update the TableView with the new data
                            /*selectedProfessor.setName(name.getText());
                            selectedProfessor.setAge(Integer.parseInt(age.getText()));
                            selectedProfessor.setCity(city.getText());
                            selectedProfessor.setPhone(phone.getText());
                            selectedProfessor.setSalary(Integer.parseInt(salary.getText()));
                            selectedProfessor.setDegree(degree.getText());
                            selectedProfessor.setDept(Integer.parseInt(dept.getText()));*/
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
                alert.setContentText("Please select a professor to update.");
                alert.showAndWait();
            }
        });
           deletebtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ProfessorDTO selectedItem = (ProfessorDTO) tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete This Professor?", "Delete Student Confirmation", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        // Use Platform.runLater() to perform UI operations on the JavaFX application thread
                        Platform.runLater(() -> {
                            try {
                                int studentId = selectedItem.getId();

                                JSONObject deletedStudent = new JSONObject();
                                deletedStudent.put("HEADER", "fnDeleteProfessor");
                                deletedStudent.put("ID", selectedItem.getId());
                                ps.println(deletedStudent.toString());

                                String response = dis.readLine();
                                if (response.equals("Professor Deleted")) {
                                    // Update your JavaFX TableView and remove the selected item
                                    tableView.getItems().remove(selectedItem);
                                    JOptionPane.showMessageDialog(null, "Professor Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error couldn't delete", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Choose Professor first", "Error", JOptionPane.ERROR_MESSAGE);
                }
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
            fetchCoursesData.put("HEADER", "fnAllDept");
             fetchCoursesData.put("ADMINID", adminId);
           
            ps.println(fetchCoursesData.toString());

            // Handle the server response if needed
            String response = dis.readLine();

            if (response != null && !response.isEmpty()) {
                try {
                    JSONArray coursesArray = new JSONArray(response);

                    // Clear previous data in case it was populated before
                    dept.getItems().clear();

                    for (int i = 0; i < coursesArray.length(); i++) {
                        JSONObject courseData = coursesArray.getJSONObject(i);
                        
                        String Name = courseData.optString("NAME");
                      

                        // Create a CourseDTO object and add it to the combo box
                        //CourseDTO course = new CourseDTO(courseName);
                        dept.getItems().add(Name);
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
     private void  viewAll(){
           tableView.getItems().clear();

            try {
                JSONObject searchData = new JSONObject();
                searchData.put("HEADER", "fnAllProf");
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
                            JSONObject searchedData = coursesArray.getJSONObject(i);
                    int stdid = searchedData.optInt("ID");
                    String fname = searchedData.optString("FNAME");
                    String lname = searchedData.optString("LNAME");
                    String email = searchedData.optString("EMAIL");
                    String dob = searchedData.optString("DOB");
                   String phone = searchedData.optString("PHONE");
                    String city = searchedData.optString("CITY");
                    String pass =searchedData.optString("PASS");
                    int deptid = searchedData.optInt("DID");
                    String deptname = searchedData.optString("DNAME");
                    String degree = searchedData.optString("DEGREE");
                    int salary = searchedData.optInt("SALARY");

                            // Create a CourseDTO object and add it to the ObservableList
                           ProfessorDTO student = new ProfessorDTO(stdid,fname, lname, email, phone, deptid,deptname,city,pass,dob,salary,degree);
                            studentList.add(student);

                        }
                        //System.out.println(studentList);
                        tableView.setItems(studentList);

                        // Update tableView0 outside the loop
                    } catch (JSONException e) {
                        System.out.println("Error parsing JSON response: " + response);
                        // Handle the case when the response is not a valid JSON
                        // For example, display an error message or log the issue
                    }
                } else {
                    // Handle empty response here
                    //System.out.println("No results found for student courses.");
                    tableView.getItems().clear();
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);
            }
     }
}
