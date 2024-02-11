package university;

import DTO.CourseDTO;
import DTO.ProfessorDTO;
import DTO.StudentDTO;
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

public class ManageStudentController extends AnchorPane {

    private int adminId;
    protected final AnchorPane anchorPane;
    protected final Label label;
    protected final TextField id;
    protected final Button viewbtn;
    protected final TableView tableView;
    protected final TableColumn<StudentDTO, String> idtbl;
    protected final TableColumn<StudentDTO, String> fnametbl;
    protected final TableColumn<StudentDTO, String> lnametbl;

    protected final TableColumn<StudentDTO, String> emailtbl;
    protected final TableColumn<StudentDTO, String> dobtbl;
    protected final TableColumn<StudentDTO, String> deptidtbl;
    protected final TableColumn<StudentDTO, String> gpatbl;
    protected final TableColumn<StudentDTO, String> phonetbl;
    protected final TableColumn<StudentDTO, String> deptnametbl;
    protected final TableColumn<StudentDTO, String> citytbl;

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
    Socket socket;
    DataInputStream dis;
    PrintStream ps;
    private ObservableList<StudentDTO> studentList = FXCollections.observableArrayList();
    private ObservableList<CourseDTO> courseList = FXCollections.observableArrayList();

    public ManageStudentController(int adminId) throws IOException {
        this.adminId = adminId;
        anchorPane = new AnchorPane();
        label = new Label();
        id = new TextField();
        viewbtn = new Button();
        tableView = new TableView();
        idtbl = new TableColumn();
        fnametbl = new TableColumn();
        lnametbl = new TableColumn();
        emailtbl = new TableColumn();
        dobtbl = new TableColumn();
        citytbl = new TableColumn();
        phonetbl = new TableColumn();
        deptidtbl = new TableColumn();
        deptnametbl = new TableColumn();
        gpatbl = new TableColumn();
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

        setId("AnchorPane");
        setPrefHeight(600.0);
        setPrefWidth(900.0);

        anchorPane.setId("AnchorPane");
        anchorPane.setLayoutX(63.0);
        anchorPane.setLayoutY(1.0);
        anchorPane.setPrefHeight(598.0);
        anchorPane.setPrefWidth(784.0);

        label.setLayoutX(113.0);
        label.setLayoutY(79.0);
        label.setPrefHeight(25.0);
        label.setPrefWidth(97.0);
        label.setText("Student Id:");
        label.setFont(new Font(16.0));

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

        tableView.setLayoutX(-3.0);
        tableView.setLayoutY(146.0);
        tableView.setPrefHeight(250.0);
        tableView.setPrefWidth(748.0);

        idtbl.setPrefWidth(75.0);
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
        gpatbl.setCellValueFactory(new PropertyValueFactory<>("gpa"));

        fnametbl.setPrefWidth(75.0);
        fnametbl.setText("First Name");

        lnametbl.setPrefWidth(75.0);
        lnametbl.setText("Last Name");

        emailtbl.setPrefWidth(75.0);
        emailtbl.setText("Email");

        dobtbl.setPrefWidth(75.0);
        dobtbl.setText("DOB");

        citytbl.setPrefWidth(75.0);
        citytbl.setText("City");

        phonetbl.setPrefWidth(75.0);
        phonetbl.setText("Phone");

        deptidtbl.setPrefWidth(75.0);
        deptidtbl.setText("Dept ID");

        deptnametbl.setPrefWidth(75.0);
        deptnametbl.setText("Dept Name");

        gpatbl.setPrefWidth(75.0);
        gpatbl.setText("GPA");

        label0.setLayoutX(218.0);
        label0.setLayoutY(14.0);
        label0.setPrefHeight(45.0);
        label0.setPrefWidth(382.0);
        label0.setText("View Student Information");
        label0.setFont(new Font("System Bold", 30.0));

        viewAll.setLayoutX(560.0);
        viewAll.setLayoutY(79.0);
        viewAll.setMnemonicParsing(false);
        viewAll.setPrefHeight(33.0);
        viewAll.setPrefWidth(111.0);
        viewAll.setText("View all students");

        fname.setLayoutX(49.0);
        fname.setLayoutY(406.0);
        fname.setPrefHeight(33.0);
        fname.setPrefWidth(220.0);
        fname.setPromptText("First Name");
        fname.setFont(new Font(15.0));

        lname.setLayoutX(289.0);
        lname.setLayoutY(406.0);
        lname.setPrefHeight(33.0);
        lname.setPrefWidth(220.0);
        lname.setPromptText("Last Name");
        lname.setFont(new Font(15.0));

        email.setLayoutX(49.0);
        email.setLayoutY(458.0);
        email.setPrefHeight(33.0);
        email.setPrefWidth(220.0);
        email.setPromptText("Email");
        email.setFont(new Font(15.0));

        pass.setLayoutX(289.0);
        pass.setLayoutY(458.0);
        pass.setPrefHeight(33.0);
        pass.setPrefWidth(220.0);
        pass.setPromptText("Password");
        pass.setFont(new Font(15.0));

        city.setLayoutX(516.0);
        city.setLayoutY(406.0);
        city.setPrefHeight(33.0);
        city.setPrefWidth(220.0);
        city.setPromptText("City");
        city.setFont(new Font(15.0));

        phone.setLayoutX(516.0);
        phone.setLayoutY(458.0);
        phone.setPrefHeight(33.0);
        phone.setPrefWidth(220.0);
        phone.setPromptText("Phone");
        phone.setFont(new Font(15.0));

        dept.setLayoutX(56.0);
        dept.setLayoutY(506.0);
        dept.setOpacity(0.68);
        dept.setPrefHeight(33.0);
        dept.setPrefWidth(212.0);
        dept.setPromptText("Department");

        updatebtn.setLayoutX(340.0);
        updatebtn.setLayoutY(500.0);
        updatebtn.setMnemonicParsing(false);
        updatebtn.setPrefHeight(45.0);
        updatebtn.setPrefWidth(84.0);
        updatebtn.setText("Update");
        updatebtn.setFont(new Font(16.0));

        deletebtn.setLayoutX(474.0);
        deletebtn.setLayoutY(500.0);
        deletebtn.setMnemonicParsing(false);
        deletebtn.setPrefHeight(45.0);
        deletebtn.setPrefWidth(84.0);
        deletebtn.setText("Delete");
        deletebtn.setFont(new Font(16.0));

        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(id);
        anchorPane.getChildren().add(viewbtn);
        tableView.getColumns().add(idtbl);
        tableView.getColumns().add(fnametbl);
        tableView.getColumns().add(lnametbl);
        tableView.getColumns().add(emailtbl);
        tableView.getColumns().add(dobtbl);
        tableView.getColumns().add(citytbl);
        tableView.getColumns().add(phonetbl);
        tableView.getColumns().add(deptidtbl);
        tableView.getColumns().add(deptnametbl);
        tableView.getColumns().add(gpatbl);
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
            tableView.getItems().clear();
            if (!id.getText().isEmpty()) {
                try {
                    JSONObject searchData = new JSONObject();
                    searchData.put("HEADER", "fnSearchStudent");
                    searchData.put("ADMINID", adminId);
                    searchData.put("ID", id.getText());
                    ps.println(searchData.toString());
                    String response = dis.readLine();

                    if (response != null && !response.isEmpty()) {

                        // Clear previous data
                        //studentList.clear();
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
                            String pass = searchedData.optString("PASS");
                            int deptid = searchedData.optInt("DID");
                            String deptname = searchedData.optString("DNAME");
                            String gpa = searchedData.optString("GPA");

                            StudentDTO student = new StudentDTO(stdid, fname, lname, email, phone, deptid, deptname, city, pass, dob);
                            studentList.add(student);
                            tableView.setItems(studentList);

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
            if (newSelection != null && newSelection instanceof StudentDTO) {
                // Cast newSelection to StudentDTO
                StudentDTO selectedStudent = (StudentDTO) newSelection;
                // Display selected row data in text fields
                fname.setText(selectedStudent.getFname());
                lname.setText(selectedStudent.getLname());
                city.setText(selectedStudent.getCity());
                email.setText(selectedStudent.getEmail());
                pass.setText(selectedStudent.getPass());
                phone.setText(selectedStudent.getPhone());
                dept.setValue(selectedStudent.getDeptname());

            }
        });
        viewAll.addEventHandler(ActionEvent.ACTION, event -> {
            viewAll();

        });
        updatebtn.addEventHandler(ActionEvent.ACTION, event -> {
            StudentDTO selectedProfessor = (StudentDTO) tableView.getSelectionModel().getSelectedItem();

            if (selectedProfessor != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Update Confirmation");
                alert.setContentText("Are you sure you want to update the selected professor?");

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            JSONObject userData = new JSONObject();
                            userData.put("HEADER", "fnUpdateStudent");
                            userData.put("ADMINID", adminId);
                            userData.put("ID", selectedProfessor.getId());
                            userData.put("FNAME", fname.getText());
                            userData.put("LNAME", lname.getText());
                            userData.put("EMAIL", email.getText());
                            userData.put("PHONE", phone.getText());
                            userData.put("DEPT", dept.getValue());
                            userData.put("CITY", city.getText());
                            userData.put("PASS", pass.getText());
                            ps.println(userData.toString());
                            String response2 = dis.readLine();

                            if (response2.equals("Student Updated successfully")) {

                                fname.clear();
                                lname.clear();
                                city.clear();
                                phone.clear();
                                email.clear();
                                pass.clear();
                                dept.setValue(null);
                                viewAll();
                                JOptionPane.showMessageDialog(null, "Student updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Failed to update", "Error", JOptionPane.INFORMATION_MESSAGE);
                                //tableView.getItems().clear();
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
                StudentDTO selectedItem = (StudentDTO) tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete This student?", "Delete Student Confirmation", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        // Use Platform.runLater() to perform UI operations on the JavaFX application thread
                        Platform.runLater(() -> {
                            try {
                                int studentId = selectedItem.getId();

                                JSONObject deletedStudent = new JSONObject();
                                deletedStudent.put("HEADER", "fnDeleteStudent");
                                deletedStudent.put("ID", selectedItem.getId());
                                ps.println(deletedStudent.toString());

                                String response = dis.readLine();
                                if (response.equals("Student Deleted")) {
                                    // Update your JavaFX TableView and remove the selected item
                                    tableView.getItems().remove(selectedItem);
                                    JOptionPane.showMessageDialog(null, "Student Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
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

    private void viewAll() {
        tableView.getItems().clear();

        try {
            JSONObject searchData = new JSONObject();
            searchData.put("HEADER", "fnAllStudent");
            searchData.put("ADMINID", adminId);
            ps.println(searchData.toString());
            String response = dis.readLine();
            if (response != null && !response.isEmpty()) {
            

                try {
                    JSONArray coursesArray = new JSONArray(response);

                    for (int i = 0; i < coursesArray.length(); i++) {
                        JSONObject searchedData = coursesArray.getJSONObject(i);
                        int stdid = searchedData.optInt("ID");
                        String fname = searchedData.optString("FNAME");
                        String lname = searchedData.optString("LNAME");
                        String email = searchedData.optString("EMAIL");
                        String dob = searchedData.optString("DOB");
                        String phone = searchedData.optString("PHONE");
                        String city = searchedData.optString("CITY");
                        String pass = searchedData.optString("PASS");
                        int deptid = searchedData.optInt("DID");
                        String deptname = searchedData.optString("DNAME");
                        double gpa = searchedData.optDouble("GPA");

                        StudentDTO student = new StudentDTO(stdid, fname, lname, email, phone, deptid, deptname, city, pass, dob, gpa);
                        studentList.add(student);

                    }
                    tableView.setItems(studentList);

                } catch (JSONException e) {
                    System.out.println("Error parsing JSON response: " + response);
                }
            } else {
                System.out.println("No results found for student courses.");
                tableView.getItems().clear();
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
