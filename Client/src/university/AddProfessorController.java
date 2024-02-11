package university;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddProfessorController extends AnchorPane {
private int adminId;
    protected final AnchorPane anchorPane;
    protected final Label label;
    protected final TextField salary;
    protected final Button addbtn;
    protected final TextField degree;
    protected final TextField fname;
    protected final TextField lname;
    protected final TextField email;
    protected final TextField pass;
    protected final TextField city;
    protected final DatePicker dob;
    protected final TextField phone;
    protected final ComboBox dept;

    public AddProfessorController(int adminId) throws IOException {
this.adminId=adminId;
        anchorPane = new AnchorPane();
        label = new Label();
        salary = new TextField();
        addbtn = new Button();
        degree = new TextField();
        fname = new TextField();
        lname = new TextField();
        email = new TextField();
        pass = new TextField();
        city = new TextField();
        dob = new DatePicker();
        phone = new TextField();
        dept = new ComboBox();

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

        label.setLayoutX(317.0);
        label.setLayoutY(52.0);
        label.setPrefHeight(45.0);
        label.setPrefWidth(390.0);
        label.setText("Professor Registeration");
        label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label.setFont(new Font("System Bold", 30.0));

        salary.setLayoutX(246.0);
        salary.setLayoutY(435.0);
        salary.setPrefHeight(46.0);
        salary.setPrefWidth(220.0);
        salary.setPromptText("Salary");
        salary.setFont(new Font(15.0));

        addbtn.setLayoutX(466.0);
        addbtn.setLayoutY(523.0);
        addbtn.setMnemonicParsing(false);
        addbtn.setPrefHeight(25.0);
        addbtn.setPrefWidth(93.0);
        addbtn.setText("Add");
        addbtn.setFont(new Font(16.0));

        degree.setLayoutX(495.0);
        degree.setLayoutY(435.0);
        degree.setPrefHeight(46.0);
        degree.setPrefWidth(220.0);
        degree.setPromptText("Degree");
        degree.setFont(new Font(15.0));

        fname.setLayoutX(246.0);
        fname.setLayoutY(128.0);
        fname.setPrefHeight(46.0);
        fname.setPrefWidth(220.0);
        fname.setPromptText("First Name");
        fname.setFont(new Font(15.0));

        lname.setLayoutX(492.0);
        lname.setLayoutY(128.0);
        lname.setPrefHeight(46.0);
        lname.setPrefWidth(220.0);
        lname.setPromptText("Last Name");
        lname.setFont(new Font(15.0));

        email.setLayoutX(246.0);
        email.setLayoutY(195.0);
        email.setPrefHeight(46.0);
        email.setPrefWidth(220.0);
        email.setPromptText("Email");
        email.setFont(new Font(15.0));

        pass.setLayoutX(492.0);
        pass.setLayoutY(195.0);
        pass.setPrefHeight(46.0);
        pass.setPrefWidth(220.0);
        pass.setPromptText("Password");
        pass.setFont(new Font(15.0));

        city.setLayoutX(246.0);
        city.setLayoutY(271.0);
        city.setPrefHeight(46.0);
        city.setPrefWidth(220.0);
        city.setPromptText("City");
        city.setFont(new Font(15.0));

        dob.setLayoutX(494.0);
        dob.setLayoutY(271.0);
        dob.setPrefHeight(46.0);
        dob.setPrefWidth(220.0);
        dob.setPromptText("Date Of Birth");
        dob.setStyle("-fx-border-color: #7badec; -fx-border-radius: 5; -fx-background-radius: 5;");

        phone.setLayoutX(246.0);
        phone.setLayoutY(350.0);
        phone.setPrefHeight(46.0);
        phone.setPrefWidth(220.0);
        phone.setPromptText("Phone");
        phone.setFont(new Font(15.0));

        dept.setLayoutX(498.0);
        dept.setLayoutY(350.0);
        dept.setOpacity(0.68);
        dept.setPrefHeight(46.0);
        dept.setPrefWidth(212.0);
        dept.setPromptText("Department");

        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(salary);
        anchorPane.getChildren().add(addbtn);
        anchorPane.getChildren().add(degree);
        anchorPane.getChildren().add(fname);
        anchorPane.getChildren().add(lname);
        anchorPane.getChildren().add(email);
        anchorPane.getChildren().add(pass);
        anchorPane.getChildren().add(city);
        anchorPane.getChildren().add(dob);
        anchorPane.getChildren().add(phone);
        anchorPane.getChildren().add(dept);
        getChildren().add(anchorPane);
        populateComboBox();

        addbtn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                //System.out.println("Hello, World!");
                // System.out.println(dept.getValue());
                String emailPattern = "^[\\w-\\.]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$";
                boolean allFieldsFilled = !fname.getText().isEmpty()
                        && !lname.getText().isEmpty()
                        && !email.getText().isEmpty()
                        && !pass.getText().isEmpty()
                        && !city.getText().isEmpty()
                        && dob.getValue() != null
                        && dept.getValue() != null
                        && !phone.getText().isEmpty()
                        && !salary.getText().isEmpty()
                        && !degree.getText().isEmpty();

                if (!allFieldsFilled) {
                   
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!fname.getText().matches("^[a-zA-Z]+$")) {
                    JOptionPane.showMessageDialog(null, "First Name should contain only letters.", "Input Validation Error", JOptionPane.ERROR_MESSAGE);
                } else if (!lname.getText().matches("^[a-zA-Z]+$")) {
                    JOptionPane.showMessageDialog(null, "Last Name should contain only letters.", "Input Validation Error", JOptionPane.ERROR_MESSAGE);
                } else if (!city.getText().matches("^[a-zA-Z ]+$")) {
                    JOptionPane.showMessageDialog(null, "City should contain only letters and spaces.", "Input Validation Error", JOptionPane.ERROR_MESSAGE);
                } else if (!phone.getText().matches("^\\d+$")) {
                    JOptionPane.showMessageDialog(null, "Phone Should be a number", "Input Validation Error", JOptionPane.ERROR_MESSAGE);
                } else if (!email.getText().matches(emailPattern)) {
                    JOptionPane.showMessageDialog(null, "Email should be entered correctly.", "Input Validation Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Socket socket = new Socket("127.0.0.1", 5005);
                        DataInputStream dis = new DataInputStream(socket.getInputStream());
                        PrintStream ps = new PrintStream(socket.getOutputStream());
                        JSONObject checkDeptData = new JSONObject();
                        checkDeptData.put("HEADER", "fnCheckProfEmail");
                        checkDeptData.put("EMAIL", email.getText());

                        ps.println(checkDeptData.toString());

                        String response = dis.readLine();
                        System.out.println(response);

                        if (response != null && response.equals("yes")) {
                            JOptionPane.showMessageDialog(null, "This email already exists try another one", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {

                            JSONObject userData = new JSONObject();
                            userData.put("HEADER", "fnAddProf");
                            userData.put("ADMINID", adminId);
                            
                            userData.put("FNAME", fname.getText());
                            userData.put("LNAME", lname.getText());
                            userData.put("EMAIL", email.getText());
                            userData.put("PHONE", phone.getText());
                            userData.put("DEPT", dept.getValue());
                            userData.put("CITY", city.getText());
                            userData.put("PASS", pass.getText());
                            userData.put("DOB", dob.getValue().toString());
                            userData.put("SALARY", salary.getText());
                            userData.put("DEGREE", degree.getText());

                            ps.println(userData.toString());
                            // Read response from server
                            String response2 = dis.readLine();
                            Platform.runLater(new Runnable() {

                                public void run() {
                                    if (response2.equals("Professor added successfully")) {
                                        JOptionPane.showMessageDialog(null, "Professor added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        fname.clear();
                                        lname.clear();
                                        city.clear();
                                        phone.clear();
                                        pass.clear();
                                        email.clear();
                                        salary.clear();
                                        degree.clear();
                                        //dept.setValue(null);

                                    } else {
                                        JOptionPane.showMessageDialog(null, "Registration Failed.", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            });
                        }
                    } catch (IOException ex) {
                        // Handle connection failure
                        JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);

                    }
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
}
