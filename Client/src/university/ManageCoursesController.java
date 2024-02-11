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

public class ManageCoursesController extends AnchorPane {
    private int adminId;

    protected final AnchorPane anchorPane;
    protected final Label label;
    protected final TextField id;
    protected final Button viewbtn;
    protected final TableView tableView;

    protected final TableColumn<CourseDTO, String> idtbl;
    protected final TableColumn<CourseDTO, String> nametbl;
    protected final TableColumn<CourseDTO, String> hourstbl;
    protected final TableColumn<CourseDTO, String> maxtbl;
    protected final TableColumn<CourseDTO, String> numtbl;
    protected final TableColumn<CourseDTO, String> gpatbl;
    protected final TableColumn<CourseDTO, String> depttbl;

    protected final Label label0;
    protected final Button viewAll;
    protected final TextField name;
    protected final TextField max;
    protected final TextField hours;
    protected final Button updatebtn;
    protected final Button deletebtn;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
    protected final TableView tableView0;

    protected final TableColumn<CourseDTO, String> stdid;
    protected final TableColumn<CourseDTO, String> stdname;
    protected final TableColumn<CourseDTO, String> stdsem;
    protected final TableColumn<CourseDTO, String> stdyear;
    protected final TableColumn<CourseDTO, String> stdgrade;

    Socket socket;
    DataInputStream dis;
    PrintStream ps;
    private ObservableList<CourseDTO> studentList = FXCollections.observableArrayList();
    private ObservableList<CourseDTO> studentListForTableView0 = FXCollections.observableArrayList();

    public ManageCoursesController(int adminId) {
this.adminId=adminId;
        anchorPane = new AnchorPane();
        label = new Label();
        id = new TextField();
        viewbtn = new Button();
        tableView = new TableView();
        idtbl = new TableColumn();
        nametbl = new TableColumn();
        hourstbl = new TableColumn();
        maxtbl = new TableColumn();
        numtbl = new TableColumn();
        gpatbl = new TableColumn();
        depttbl = new TableColumn();
        label0 = new Label();
        viewAll = new Button();
        name = new TextField();
        max = new TextField();
        hours = new TextField();
        updatebtn = new Button();
        deletebtn = new Button();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        tableView0 = new TableView();
        stdid = new TableColumn();
        stdname = new TableColumn();
        stdsem = new TableColumn();
        stdyear = new TableColumn();
        stdgrade = new TableColumn();

        setId("AnchorPane");
        setPrefHeight(600.0);
        setPrefWidth(900.0);

        AnchorPane.setBottomAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setTopAnchor(anchorPane, 0.0);
        anchorPane.setId("AnchorPane");
        anchorPane.setLayoutX(10.0);
        anchorPane.setLayoutY(10.0);
        anchorPane.setPrefHeight(619.0);
        anchorPane.setPrefWidth(910.0);

        label.setLayoutX(125.0);
        label.setLayoutY(83.0);
        label.setPrefHeight(25.0);
        label.setPrefWidth(97.0);
        label.setText("Course Id:");
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

        tableView.setLayoutX(14.0);
        tableView.setLayoutY(135.0);
        tableView.setPrefHeight(183.0);
        tableView.setPrefWidth(497.0);

        idtbl.setPrefWidth(60.0);
        idtbl.setText("Course Id");
        idtbl.setCellValueFactory(new PropertyValueFactory<>("id"));
        nametbl.setCellValueFactory(new PropertyValueFactory<>("name"));
        hourstbl.setCellValueFactory(new PropertyValueFactory<>("hours"));
        maxtbl.setCellValueFactory(new PropertyValueFactory<>("max"));
        numtbl.setCellValueFactory(new PropertyValueFactory<>("num"));
        gpatbl.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        depttbl.setCellValueFactory(new PropertyValueFactory<>("deptname"));

        nametbl.setPrefWidth(49.0);
        nametbl.setText("Name");

        hourstbl.setPrefWidth(77.0);
        hourstbl.setText("Credit Hours");

        maxtbl.setPrefWidth(64.0);
        maxtbl.setText("Max Mark");

        numtbl.setPrefWidth(92.0);
        numtbl.setText("No of students");

        gpatbl.setPrefWidth(79.0);
        gpatbl.setText("Average GPA");

        depttbl.setText("Dept Name");

        label0.setLayoutX(372.0);
        label0.setLayoutY(14.0);
        label0.setPrefHeight(45.0);
        label0.setPrefWidth(245.0);
        label0.setText("Manage Courses ");
        label0.setFont(new Font("System Bold", 30.0));

        viewAll.setLayoutX(560.0);
        viewAll.setLayoutY(79.0);
        viewAll.setMnemonicParsing(false);
        viewAll.setPrefHeight(33.0);
        viewAll.setPrefWidth(148.0);
        viewAll.setText("View All Courses");

        name.setLayoutX(381.0);
        name.setLayoutY(359.0);
        name.setPrefHeight(33.0);
        name.setPrefWidth(169.0);
        name.setFont(new Font(15.0));

        max.setLayoutX(379.0);
        max.setLayoutY(487.0);
        max.setPrefHeight(33.0);
        max.setPrefWidth(169.0);
        max.setFont(new Font(15.0));

        hours.setLayoutX(379.0);
        hours.setLayoutY(421.0);
        hours.setPrefHeight(33.0);
        hours.setPrefWidth(169.0);
        hours.setFont(new Font(15.0));

        updatebtn.setLayoutX(366.0);
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

        label1.setLayoutX(446.0);
        label1.setLayoutY(333.0);
        label1.setPrefHeight(26.0);
        label1.setPrefWidth(97.0);
        label1.setText("Name");
        label1.setFont(new Font("System Bold", 15.0));

        label2.setLayoutX(428.0);
        label2.setLayoutY(454.0);
        label2.setPrefHeight(26.0);
        label2.setPrefWidth(97.0);
        label2.setText("Max Mark");
        label2.setFont(new Font("System Bold", 15.0));

        label3.setLayoutX(417.0);
        label3.setLayoutY(392.0);
        label3.setPrefHeight(26.0);
        label3.setPrefWidth(97.0);
        label3.setText("Credit Hours");
        label3.setFont(new Font("System Bold", 15.0));

        tableView0.setLayoutX(526.0);
        tableView0.setLayoutY(135.0);
        tableView0.setPrefHeight(183.0);
        tableView0.setPrefWidth(323.0);

        stdid.setPrefWidth(71.0);
        stdid.setText("Student id");
        stdid.setCellValueFactory(new PropertyValueFactory<>("id"));
        stdname.setCellValueFactory(new PropertyValueFactory<>("name"));
        stdsem.setCellValueFactory(new PropertyValueFactory<>("Semester"));
        stdyear.setCellValueFactory(new PropertyValueFactory<>("year"));
        stdgrade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        stdname.setPrefWidth(53.0);
        stdname.setText("Name");

        stdsem.setPrefWidth(64.0);
        stdsem.setText("Semester");

        stdyear.setPrefWidth(55.0);
        stdyear.setText("Year");

        stdgrade.setPrefWidth(79.0);
        stdgrade.setText("Grade");

        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(id);
        anchorPane.getChildren().add(viewbtn);
        tableView.getColumns().add(idtbl);
        tableView.getColumns().add(nametbl);
        tableView.getColumns().add(hourstbl);
        tableView.getColumns().add(maxtbl);
        tableView.getColumns().add(numtbl);
        tableView.getColumns().add(gpatbl);
      //  tableView.getColumns().add(depttbl);
        anchorPane.getChildren().add(tableView);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(viewAll);
        anchorPane.getChildren().add(name);
        anchorPane.getChildren().add(max);
        anchorPane.getChildren().add(hours);
        anchorPane.getChildren().add(updatebtn);
        anchorPane.getChildren().add(deletebtn);
        anchorPane.getChildren().add(label1);
        anchorPane.getChildren().add(label2);
        anchorPane.getChildren().add(label3);
        tableView0.getColumns().add(stdid);
        tableView0.getColumns().add(stdname);
        tableView0.getColumns().add(stdsem);
        tableView0.getColumns().add(stdyear);
        tableView0.getColumns().add(stdgrade);
        anchorPane.getChildren().add(tableView0);
        getChildren().add(anchorPane);
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
                    searchData.put("HEADER", "fnSearchCourse");
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
                            int hours = searchedData.optInt("HOURS");
                            int max = searchedData.optInt("MAX");
                            int num = searchedData.optInt("NUM");
                            int avggpa = searchedData.optInt("GPA");
                            String deptName = searchedData.optString("DEPTNAME");

                            // Create a Student object and add it to the ObservableList
                            CourseDTO course = new CourseDTO(id2, name2, hours, max, num, avggpa, deptName);
                            studentList.add(course);
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
        viewAll.addEventHandler(ActionEvent.ACTION, event -> {
            viewAll();
           
        });
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null && newSelection instanceof CourseDTO) {
                tableView0.getItems().clear();
                // Cast newSelection to StudentDTO
                CourseDTO selectedStudent = (CourseDTO) newSelection;
                //System.out.println("Course List: " + selectedStudent.getName());
                name.setText(selectedStudent.getName());
                max.setText(String.valueOf(selectedStudent.getMax()));
                hours.setText(String.valueOf(selectedStudent.getHours()));

                // Now you can use getId() or any other methods from StudentDTO
                int selectedStudentId = selectedStudent.getId();

                // Fetch additional information for the selected student using the ID
                try {
                    JSONObject searchData = new JSONObject();
                    searchData.put("HEADER", "fnGetCourseStudents");
                    searchData.put("ID", String.valueOf(selectedStudentId));
                    ps.println(searchData.toString());
                    String response = dis.readLine();
                    System.out.println("Course List: " + response);

                    if (response != null && !response.isEmpty()) {
                        // Clear previous data

                        try {
                            // Parse the JSON response and populate the courseList
                            JSONArray coursesArray = new JSONArray(response);

                            // Iterate through the array and extract course information
                            for (int i = 0; i < coursesArray.length(); i++) {
                                JSONObject courseData = coursesArray.getJSONObject(i);
                                int Id = courseData.optInt("ID");
                                String Name = courseData.optString("NAME");
                                int year = courseData.optInt("YEAR");
                                int sem = courseData.optInt("SEM");
                                String grade = courseData.optString("GRADE");

                                // Create a CourseDTO object and add it to the ObservableList
                                CourseDTO course5 = new CourseDTO(Id, Name, year, sem, grade);
                                studentListForTableView0.add(course5);
                            }

                            // Update tableView0 outside the loop
                            tableView0.setItems(studentListForTableView0);
                            System.out.println("Course List: " + studentList);
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
        });
        deletebtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CourseDTO selectedItem = (CourseDTO) tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    // Check if the department has any students
                    try {
                        JSONObject checkStudentsData = new JSONObject();
                        checkStudentsData.put("HEADER", "fnCheckCoursetudents");
                        checkStudentsData.put("ID", selectedItem.getId());
                        ps.println(checkStudentsData.toString());
                        String response = dis.readLine();

                        if (response != null && response.equals("yes")) {
                            // Department has students, show alert
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning");
                            alert.setHeaderText("Cannot Delete Course");
                            alert.setContentText("This course has students. Cannot delete.");
                            alert.showAndWait();
                        } else {
                            // Department has no students, proceed with deletion
                            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete This Course?", "Delete Student Confirmation", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION) {
                                // Use Platform.runLater() to perform UI operations on the JavaFX application thread
                                Platform.runLater(() -> {
                                    try {
                                        int id2 = selectedItem.getId();

                                        JSONObject deletedStudent = new JSONObject();
                                        deletedStudent.put("HEADER", "fnDeleteCourse");
                                        deletedStudent.put("ID", id2);
                                        ps.println(deletedStudent.toString());

                                        String response2 = dis.readLine();
                                        if (response2.equals("Course Deleted")) {
                                            // Update your JavaFX TableView and remove the selected item
                                            tableView.getItems().remove(selectedItem);
                                            tableView0.getItems().clear();
                                            JOptionPane.showMessageDialog(null, "Course Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
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
                    JOptionPane.showMessageDialog(null, "Choose Course first", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
            updatebtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CourseDTO selectedItem = (CourseDTO) tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    // Check if the department has any students
                    try {
                        JSONObject checkStudentsData = new JSONObject();
                        checkStudentsData.put("HEADER", "fnCheckCoursetudents");
                        checkStudentsData.put("ID", selectedItem.getId());
                        ps.println(checkStudentsData.toString());
                        String response = dis.readLine();

                        if (response != null && response.equals("yes")) {
                            // Department has students, show alert
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning");
                            alert.setHeaderText("Cannot Update Course");
                            alert.setContentText("This course has students. Cannot update.");
                            alert.showAndWait();
                        } else {
                            // Department has no students, proceed with deletion
                            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to update This Course?", "Delete Student Confirmation", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION) {
                                // Use Platform.runLater() to perform UI operations on the JavaFX application thread
                                Platform.runLater(() -> {
                                    try {
                                        int id2 = selectedItem.getId();

                                        JSONObject updateData = new JSONObject();
                                          updateData.put("HEADER", "fnUpdateCourse");
                        updateData.put("ID", selectedItem.getId());
                        updateData.put("NAME", name.getText());
                        updateData.put("HOURS", Integer.parseInt(hours.getText()));
                        updateData.put("MAX", Integer.parseInt(max.getText()));
                                        ps.println(updateData.toString());

                                        String response2 = dis.readLine();
                                        if (response2.equals("Course Updated successfully")) {
                                             name.clear();
                            hours.clear();
                            max.clear();
                            tableView.refresh();
                                            JOptionPane.showMessageDialog(null, "Course Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Error couldn't update", "Error", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(null, "Choose Course first", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
   
    }
    private  void viewAll(){
         tableView.getItems().clear();

            try {
                JSONObject searchData = new JSONObject();
                searchData.put("HEADER", "fnAllCourses");
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
                            int hours = courseData.optInt("HOURS");
                            int max = courseData.optInt("MAX");
                            int num = courseData.optInt("NUM");
                            int avggpa = courseData.optInt("GPA");
                            //String deptName = courseData.optString("DEPTNAME");

                            // Create a Student object and add it to the ObservableList
                            CourseDTO course = new CourseDTO(studentId, studentName, hours, max, num, avggpa);
                            studentList.add(course);
                            tableView.setItems(studentList);

                        }

                        tableView.setItems(studentList);

                        // Update tableView0 outside the loop
                    } catch (JSONException e) {
                        System.out.println("Error parsing JSON response: " + response);

                    }
                } else {
                    // Handle empty response here
                    System.out.println("No results found for courses.");
                    tableView.getItems().clear();
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);
            }

    }
}
