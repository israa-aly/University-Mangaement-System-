package university;

import DTO.CourseDTO;
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
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddStudentController extends AnchorPane {
    private int adminId;

    protected final TextField fname;
    protected final Button addbtn;
    protected final Label label;
    protected final TextField lname;
    protected final TextField email;
    protected final TextField pass;
    protected final TextField city;
    protected final DatePicker dob;
    protected final TextField phone;
    protected final ComboBox dept;
    protected final ComboBox gender;

    public AddStudentController(int adminId) throws IOException {
this.adminId=adminId;
        fname = new TextField();
        addbtn = new Button();
        label = new Label();
        lname = new TextField();
        email = new TextField();
        pass = new TextField();
        city = new TextField();
        dob = new DatePicker();
        phone = new TextField();
        dept = new ComboBox();
        gender = new ComboBox();

        setId("AnchorPane");
        setPrefHeight(600.0);
        setPrefWidth(900.0);

        fname.setLayoutX(230.0);
        fname.setLayoutY(155.0);
        fname.setPrefHeight(46.0);
        fname.setPrefWidth(220.0);
        fname.setPromptText("First Name");
        fname.setFont(new Font(15.0));

        addbtn.setLayoutX(426.0);
        addbtn.setLayoutY(490.0);
        addbtn.setMnemonicParsing(false);
        addbtn.setPrefHeight(25.0);
        addbtn.setPrefWidth(93.0);
        addbtn.setText("Add");
        addbtn.setFont(new Font(16.0));

        label.setLayoutX(359.0);
        label.setLayoutY(78.0);
        label.setPrefHeight(45.0);
        label.setPrefWidth(321.0);
        label.setText("Student Registeration");
        label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label.setFont(new Font("System Bold", 30.0));

        lname.setLayoutX(476.0);
        lname.setLayoutY(155.0);
        lname.setPrefHeight(46.0);
        lname.setPrefWidth(220.0);
        lname.setPromptText("Last Name");
        lname.setFont(new Font(15.0));

        email.setLayoutX(230.0);
        email.setLayoutY(224.0);
        email.setPrefHeight(46.0);
        email.setPrefWidth(220.0);
        email.setPromptText("Email");
        email.setFont(new Font(15.0));

        pass.setLayoutX(476.0);
        pass.setLayoutY(224.0);
        pass.setPrefHeight(46.0);
        pass.setPrefWidth(220.0);
        pass.setPromptText("Password");
        pass.setFont(new Font(15.0));

        city.setLayoutX(230.0);
        city.setLayoutY(295.0);
        city.setPrefHeight(46.0);
        city.setPrefWidth(220.0);
        city.setPromptText("City");
        city.setFont(new Font(15.0));

        dob.setLayoutX(478.0);
        dob.setLayoutY(295.0);
        dob.setPrefHeight(46.0);
        dob.setPrefWidth(220.0);
        dob.setPromptText("Date Of Birth");
        dob.setStyle("-fx-border-color: #7badec; -fx-border-radius: 5; -fx-background-radius: 5;");

        phone.setLayoutX(230.0);
        phone.setLayoutY(372.0);
        phone.setPrefHeight(46.0);
        phone.setPrefWidth(220.0);
        phone.setPromptText("Phone");
        phone.setFont(new Font(15.0));

        dept.setLayoutX(482.0);
        dept.setLayoutY(372.0);
        dept.setOpacity(0.68);
        dept.setPrefHeight(46.0);
        dept.setPrefWidth(212.0);
        dept.setPromptText("Department");

        gender.setLayoutX(350.0);
        gender.setLayoutY(434.0);
        gender.setOpacity(0.68);
        gender.setPrefHeight(46.0);
        gender.setPrefWidth(212.0);
        gender.setPromptText("Gender");
        gender.getItems().addAll("Female", "Male");

        getChildren().add(fname);
        getChildren().add(addbtn);
        getChildren().add(label);
        getChildren().add(lname);
        getChildren().add(email);
        getChildren().add(pass);
        getChildren().add(city);
        getChildren().add(dob);
        getChildren().add(phone);
        getChildren().add(dept);
        getChildren().add(gender);
        populateComboBox();
        addbtn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                String emailPattern = "^[\\w-\\.]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$";
                boolean allFieldsFilled = !fname.getText().isEmpty()
                        && !lname.getText().isEmpty()
                        && !email.getText().isEmpty()
                        && !pass.getText().isEmpty()
                        && !city.getText().isEmpty()
                        && dob.getValue() != null
                        && dept.getValue() != null
                        && gender.getValue()!= null
                        && !phone.getText().isEmpty();

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
                        checkDeptData.put("HEADER", "fnCheckStdEmail");
                        checkDeptData.put("EMAIL", email.getText());


                        ps.println(checkDeptData.toString());

                        String response = dis.readLine();
                                                System.out.println(response);

                        if (response != null && response.equals("yes")) {
                            JOptionPane.showMessageDialog(null, "This email already exists try another one", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {

                            JSONObject userData = new JSONObject();
                            userData.put("HEADER", "fnAddStd");
                            userData.put("ADMINID", adminId);
                            userData.put("FNAME", fname.getText());
                            userData.put("LNAME", lname.getText());
                            userData.put("EMAIL", email.getText());
                            userData.put("PHONE", phone.getText());
                            userData.put("DEPT", dept.getValue());
                            userData.put("CITY", city.getText());
                            userData.put("PASS", pass.getText());
                            userData.put("DOB", dob.getValue().toString());
                            userData.put("GENDER", gender.getValue());

                            ps.println(userData.toString());
                            // Read response from server
                            String response2 = dis.readLine();
                            Platform.runLater(new Runnable() {
                                public void run() {
                                    if (response2.equals("Student added successfully")) {
                                        JOptionPane.showMessageDialog(null, "Student added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        fname.clear();
                                        lname.clear();
                                        phone.clear();
                                        dept.setValue(null);
                                        city.clear();
                                        pass.clear();
                                        dob.setValue(null);
                                        gender.setValue(null);
                                        email.clear();

                                    } else {
                                        JOptionPane.showMessageDialog(null, "Registration Failed.", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            });
                        }
                    } catch (IOException ex) {

                        JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);

                    }
                }

            }
        });

    }

    private void populateComboBox() throws IOException {

        try {
            Socket socket = new Socket("127.0.0.1", 5005);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            PrintStream ps = new PrintStream(socket.getOutputStream());
            JSONObject fetchCoursesData = new JSONObject();
            fetchCoursesData.put("HEADER", "fnAllDept");
            fetchCoursesData.put("ADMINID", adminId);
            ps.println(fetchCoursesData.toString());

            String response = dis.readLine();

            if (response != null && !response.isEmpty()) {
                try {
                    JSONArray coursesArray = new JSONArray(response);
                    dept.getItems().clear();

                    for (int i = 0; i < coursesArray.length(); i++) {
                        JSONObject courseData = coursesArray.getJSONObject(i);

                        String Name = courseData.optString("NAME");
                        dept.getItems().add(Name);
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
