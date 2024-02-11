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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateGradesController extends AnchorPane {
private int adminId;
    protected final AnchorPane anchorPane;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final RowConstraints rowConstraints;
    protected final AnchorPane anchorPane0;
    protected final Label label;
    protected final TextField id;
    protected final Button studentcourses;
    protected final TableView tableView;

    protected final TableColumn<CourseDTO, String> tableColumn;
    protected final TableColumn<CourseDTO, String> tableColumn0;
    protected final TableColumn<CourseDTO, String> tableColumn1;
    protected final TableColumn<CourseDTO, String> tableColumn2;
    protected final TableColumn<CourseDTO, String> tableColumn3;
    protected final TableColumn<CourseDTO, String> marktbl;
    protected final Label label0;
    protected final TextField textField;
    protected final Button add;
    protected final AnchorPane anchorPane1;
    protected final Label label1;
    protected final TextField id2;
    protected final Button deptcourses;
    protected final Label label2;
    protected final TextField mark2;
    protected final Button add2;
    protected final TableView tableView0;

    protected final TableColumn<CourseDTO, String> idtbl2;
    protected final TableColumn<CourseDTO, String> nametbl2;
    protected final TableColumn<CourseDTO, String> depttbl2;
    protected final TableColumn<CourseDTO, String> graderbl2;
    protected final TableColumn<CourseDTO, String> marktbl2;

    protected final Label label3;
    Socket socket;
    DataInputStream dis;
    PrintStream ps;
    private ObservableList<CourseDTO> courseList2 = FXCollections.observableArrayList();
    private ObservableList<CourseDTO> courseList = FXCollections.observableArrayList();

    public UpdateGradesController(int adminId) {
this.adminId=adminId;
        anchorPane = new AnchorPane();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        anchorPane0 = new AnchorPane();
        label = new Label();
        id = new TextField();
        studentcourses = new Button();
        tableView = new TableView();
        tableColumn = new TableColumn();
        tableColumn0 = new TableColumn();
        tableColumn1 = new TableColumn();
        tableColumn2 = new TableColumn();
        tableColumn3 = new TableColumn();
        marktbl = new TableColumn();
        label0 = new Label();
        textField = new TextField();
        add = new Button();
        anchorPane1 = new AnchorPane();
        label1 = new Label();
        id2 = new TextField();
        deptcourses = new Button();
        label2 = new Label();
        mark2 = new TextField();
        add2 = new Button();
        tableView0 = new TableView();
        idtbl2 = new TableColumn();
        nametbl2 = new TableColumn();
        depttbl2 = new TableColumn();
        graderbl2 = new TableColumn();
        marktbl2 = new TableColumn();
        label3 = new Label();

        setId("AnchorPane");
        setPrefHeight(600.0);
        setPrefWidth(900.0);

        AnchorPane.setBottomAnchor(anchorPane, -3.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setTopAnchor(anchorPane, 3.0);
        anchorPane.setId("AnchorPane");
        anchorPane.setLayoutY(3.0);
        anchorPane.setPrefHeight(600.0);
        anchorPane.setPrefWidth(900.0);

        gridPane.setLayoutX(21.0);
        gridPane.setLayoutY(60.0);
        gridPane.setPrefHeight(525.0);
        gridPane.setPrefWidth(816.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        anchorPane0.setPrefHeight(200.0);
        anchorPane0.setPrefWidth(200.0);

        label.setLayoutY(29.0);
        label.setPrefHeight(28.0);
        label.setPrefWidth(118.0);
        label.setText("Student Id:");
        label.setFont(new Font(16.0));

        id.setLayoutX(82.0);
        id.setLayoutY(30.0);
        id.setPrefWidth(149.0);

        studentcourses.setLayoutX(267.0);
        studentcourses.setLayoutY(30.0);
        studentcourses.setMnemonicParsing(false);
        studentcourses.setText("View Courses");

        tableView.setLayoutY(83.0);
        tableView.setPrefHeight(200.0);
        tableView.setPrefWidth(408.0);

        tableColumn.setPrefWidth(75.0);
        tableColumn.setText("Course ID");

        tableColumn0.setPrefWidth(92.0);
        tableColumn0.setText("Name");
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumn0.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("year"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("semester"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("grade"));
        marktbl.setCellValueFactory(new PropertyValueFactory<>("mark"));
        tableColumn1.setPrefWidth(42.0);
        tableColumn1.setText("Year");

        tableColumn2.setPrefWidth(71.0);
        tableColumn2.setText("Semester");

        tableColumn3.setPrefWidth(56.0);
        tableColumn3.setText("Grade");

        marktbl.setPrefWidth(71.0);
        marktbl.setText("Mark");

        label0.setLayoutX(172.0);
        label0.setLayoutY(292.0);
        label0.setPrefHeight(28.0);
        label0.setPrefWidth(118.0);
        label0.setText("Add Mark");
        label0.setFont(new Font("System Bold", 16.0));

        textField.setLayoutX(130.0);
        textField.setLayoutY(333.0);
        textField.setPrefHeight(36.0);
        textField.setPrefWidth(149.0);
        textField.setPromptText("mark");

        add.setLayoutX(161.0);
        add.setLayoutY(384.0);
        add.setMnemonicParsing(false);
        add.setPrefHeight(36.0);
        add.setPrefWidth(87.0);
        add.setText("Add mark");

        GridPane.setColumnIndex(anchorPane1, 1);
        anchorPane1.setPrefHeight(200.0);
        anchorPane1.setPrefWidth(200.0);

        label1.setLayoutX(28.0);
        label1.setLayoutY(22.0);
        label1.setPrefHeight(28.0);
        label1.setPrefWidth(118.0);
        label1.setText("Course ID:");
        label1.setFont(new Font(16.0));

        id2.setLayoutX(123.0);
        id2.setLayoutY(24.0);

        deptcourses.setLayoutX(285.0);
        deptcourses.setLayoutY(24.0);
        deptcourses.setMnemonicParsing(false);
        deptcourses.setText("View Students");

        label2.setLayoutX(183.0);
        label2.setLayoutY(292.0);
        label2.setPrefHeight(28.0);
        label2.setPrefWidth(118.0);
        label2.setText("Add Mark");
        label2.setFont(new Font("System Bold", 16.0));

        mark2.setLayoutX(136.0);
        mark2.setLayoutY(328.0);
        mark2.setPrefHeight(36.0);
        mark2.setPrefWidth(149.0);
        mark2.setPromptText("mark");

        add2.setLayoutX(183.0);
        add2.setLayoutY(376.0);
        add2.setMnemonicParsing(false);
        add2.setPrefHeight(36.0);
        add2.setPrefWidth(87.0);
        add2.setText("Add mark");

        tableView0.setLayoutX(10.0);
        tableView0.setLayoutY(83.0);
        tableView0.setPrefHeight(200.0);
        tableView0.setPrefWidth(389.0);

        idtbl2.setPrefWidth(65.0);
        idtbl2.setText("Student Id");
        idtbl2.setCellValueFactory(new PropertyValueFactory<>("id"));
        nametbl2.setCellValueFactory(new PropertyValueFactory<>("name"));
        depttbl2.setCellValueFactory(new PropertyValueFactory<>("deptname"));
        graderbl2.setCellValueFactory(new PropertyValueFactory<>("grade"));
        marktbl2.setCellValueFactory(new PropertyValueFactory<>("mark"));

        nametbl2.setPrefWidth(84.0);
        nametbl2.setText("Name");

        depttbl2.setPrefWidth(92.0);
        depttbl2.setText("Dept Name");

        graderbl2.setPrefWidth(51.0);
        graderbl2.setText("Grade");

        marktbl2.setPrefWidth(96.0);
        marktbl2.setText("Mark");

        label3.setLayoutX(339.0);
        label3.setLayoutY(22.0);
        label3.setPrefHeight(46.0);
        label3.setPrefWidth(239.0);
        label3.setText("Manage Grades");
        label3.setFont(new Font("System Bold", 31.0));

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        anchorPane0.getChildren().add(label);
        anchorPane0.getChildren().add(id);
        anchorPane0.getChildren().add(studentcourses);
        tableView.getColumns().add(tableColumn);
        tableView.getColumns().add(tableColumn0);
        tableView.getColumns().add(tableColumn1);
        tableView.getColumns().add(tableColumn2);
        tableView.getColumns().add(tableColumn3);
        tableView.getColumns().add(marktbl);
        anchorPane0.getChildren().add(tableView);
        anchorPane0.getChildren().add(label0);
        anchorPane0.getChildren().add(textField);
        anchorPane0.getChildren().add(add);
        gridPane.getChildren().add(anchorPane0);
        anchorPane1.getChildren().add(label1);
        anchorPane1.getChildren().add(id2);
        anchorPane1.getChildren().add(deptcourses);
        anchorPane1.getChildren().add(label2);
        anchorPane1.getChildren().add(mark2);
        anchorPane1.getChildren().add(add2);
        tableView0.getColumns().add(idtbl2);
        tableView0.getColumns().add(nametbl2);
        tableView0.getColumns().add(depttbl2);
        tableView0.getColumns().add(graderbl2);
        tableView0.getColumns().add(marktbl2);
        anchorPane1.getChildren().add(tableView0);
        gridPane.getChildren().add(anchorPane1);
        anchorPane.getChildren().add(gridPane);
        anchorPane.getChildren().add(label3);
        getChildren().add(anchorPane);
        try {
            socket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
        } catch (IOException ex) {
            // Logger.getLogger(WishlistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        studentcourses.addEventHandler(ActionEvent.ACTION, event -> {
            handleViewCourses();

        });
        deptcourses.addEventHandler(ActionEvent.ACTION, event -> {
            System.out.println("Hello, World!");
            handleViewCourses2();
        });
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null && newSelection instanceof CourseDTO) {
                // Cast newSelection to StudentDTO
                CourseDTO selectedStudent = (CourseDTO) newSelection;
                // Display selected row data in text fields
                textField.setText(String.valueOf(selectedStudent.getMark()));
            }
        });
         tableView0.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null && newSelection instanceof CourseDTO) {
                // Cast newSelection to StudentDTO
                CourseDTO selectedStudent = (CourseDTO) newSelection;
                // Display selected row data in text fields
                mark2.setText(String.valueOf(selectedStudent.getMark()));
            }
        });
           add2.addEventHandler(ActionEvent.ACTION, event -> {
            // Get selected item
            CourseDTO selectedProfessor = (CourseDTO) tableView0.getSelectionModel().getSelectedItem();

            if (selectedProfessor != null) {
                  if(Integer.parseInt(mark2.getText())> selectedProfessor.getMax())
                {
                    JOptionPane.showMessageDialog(null, "This mark is greater than the max mark " +selectedProfessor.getMax() , "Error", JOptionPane.INFORMATION_MESSAGE);
                    
                }
                else{
               
                // Display confirmation dialog
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Update Confirmation");
                alert.setContentText("Are you sure you want to update this mark?");

                // Handle user's choice
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        
                        // User clicked OK, proceed with update
                        try {
                            // Prepare JSON data for update
                            JSONObject updateData = new JSONObject();
                            updateData.put("HEADER", "fnUpdateGrade");
                            updateData.put("ID", selectedProfessor.getId());
                            updateData.put("COURSEID",id2.getText());
                            updateData.put("MARK", mark2.getText());

                            // Send update request to the server
                            ps.println(updateData.toString());
                            String response2 = dis.readLine();

                            if (response2.equals("Grade Updated successfully")) {

                                mark2.clear();

                                handleViewCourses2();

                                JOptionPane.showMessageDialog(null, "Grade Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
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
                });}
            } else {
                // No professor selected, display a warning
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("No Selection");
                alert.setContentText("Please select a Course to update.");
                alert.showAndWait();
            }
        });
        add.addEventHandler(ActionEvent.ACTION, event -> {
            // Get selected item
            CourseDTO selectedProfessor = (CourseDTO) tableView.getSelectionModel().getSelectedItem();

            if (selectedProfessor != null) {
                if(Integer.parseInt(textField.getText())> selectedProfessor.getMax())
                {
                    JOptionPane.showMessageDialog(null, "This mark is greater than the max mark " +selectedProfessor.getMax() , "Error", JOptionPane.INFORMATION_MESSAGE);
                    
                }
                else{
               
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Update Confirmation");
                alert.setContentText("Are you sure you want to update this mark?");

                // Handle user's choice
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        // User clicked OK, proceed with update
                        try {
                            // Prepare JSON data for update
                            JSONObject updateData = new JSONObject();
                            updateData.put("HEADER", "fnUpdateGrade");
                            updateData.put("ID", id.getText());
                            updateData.put("COURSEID", selectedProfessor.getId());
                            updateData.put("MARK", textField.getText());

                            // Send update request to the server
                            ps.println(updateData.toString());
                            String response2 = dis.readLine();

                            if (response2.equals("Grade Updated successfully")) {

                                textField.clear();

                                handleViewCourses();

                                JOptionPane.showMessageDialog(null, "Grade Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
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
                });}
            } else {
                // No professor selected, display a warning
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("No Selection");
                alert.setContentText("Please select a Course to update.");
                alert.showAndWait();
            }
        });

    }

    private void handleViewCourses() {
        tableView.getItems().clear();
        if (!id.getText().isEmpty()) {

            try {
                JSONObject searchData = new JSONObject();
                searchData.put("HEADER", "fnGetStudentCourses");
                searchData.put("ADMINID",adminId);
                searchData.put("ID", id.getText());
                ps.println(searchData.toString());
                String response = dis.readLine();

                if (response != null && !response.isEmpty()) {
                    try {
                        JSONArray coursesArray = new JSONArray(response);

                        // Clear previous data
                        courseList.clear();

                        // Iterate through the array and extract course information
                        for (int i = 0; i < coursesArray.length(); i++) {
                            JSONObject courseData = coursesArray.getJSONObject(i);
                            int courseId = courseData.optInt("COURSE_ID");
                            String courseName = courseData.optString("COURSE_NAME");
                            int year = courseData.optInt("YEAR");
                            int sem = courseData.optInt("SEM");
                            String grade = courseData.optString("GRADE");
                            int mark = courseData.optInt("MARK");
                            int max =courseData.optInt("MAX");

                            // Create a CourseDTO object and add it to the ObservableList
                            CourseDTO course = new CourseDTO(courseId, courseName, year, sem, mark, grade,max);
                            courseList.add(course);
                        }

                        // Update tableView outside the loop
                        tableView.setItems(courseList);
                        System.out.println("Course List: " + courseList);
                    } catch (JSONException e) {
                        System.out.println("Error parsing JSON response: " + response);
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

    private void handleViewCourses2() {
        tableView0.getItems().clear();
                        System.out.println("Response from server: ");

        if (!id2.getText().isEmpty()) {

            try {
                JSONObject searchData = new JSONObject();
                searchData.put("HEADER", "fnGetCourseStudents");
                searchData.put("ID", id2.getText());
                ps.println(searchData.toString());
                String response = dis.readLine();
                
                System.out.println("Response from server: " + response);


                if (response != null && !response.isEmpty()) {
                    try {
                        JSONArray coursesArray = new JSONArray(response);

                        // Clear previous data
                        courseList2.clear();

                        // Iterate through the array and extract course information
                        for (int i = 0; i < coursesArray.length(); i++) {
                            JSONObject courseData = coursesArray.getJSONObject(i);
                            int Id = courseData.optInt("ID");
                            String Name = courseData.optString("NAME");
                            String deptname = courseData.optString("DEPT");
                            int mark = courseData.optInt("MARK");
                            int max = courseData.optInt("MAX");
                             System.out.println("mark " + mark);
                            String grade = courseData.optString("GRADE");

                            // Create a CourseDTO object and add it to the ObservableList
                            CourseDTO course5 = new CourseDTO(Id, Name, deptname, mark, grade,max);
                            courseList2.add(course5);
                        }

                        // Update tableView outside the loop
                        tableView0.setItems(courseList2);
                        System.out.println("Course List: " + courseList);
                    } catch (JSONException e) {
                        System.out.println("Error parsing JSON response: " + response);
                    }
                } else {
                    // Handle empty response here
                    System.out.println("No results found for student courses.");
                    tableView.getItems().clear();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
