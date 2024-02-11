package university;

import DTO.CourseDTO;
import DTO.StudentDTO;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
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

public  class StudentToCourseController extends AnchorPane {
    private int adminId;

    protected final AnchorPane anchorPane;
    protected final Label label;
    protected final Button addbtn;
    protected final ComboBox combo;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final TextField id;
    protected final Button view;
    protected final TableView tableView;
   
    protected final TableColumn<CourseDTO, String> idtbl;
     protected final TableColumn<CourseDTO, String> nametbl;
     protected final TableColumn<CourseDTO, String> yeartbl;
     protected final TableColumn<CourseDTO, String> semtbl;
     protected final TableColumn<CourseDTO, String> gradetbl;
  protected final TextField year;
    protected final Label label3;
    protected final TextField sem;
    protected final Label label4;

     Socket socket;
    DataInputStream dis;
    PrintStream ps;
    private ObservableList<StudentDTO> studentList = FXCollections.observableArrayList();
    private ObservableList<CourseDTO> courseList = FXCollections.observableArrayList();

    public StudentToCourseController(int adminId) {
this.adminId=adminId;
        anchorPane = new AnchorPane();
        label = new Label();
        addbtn = new Button();
        combo = new ComboBox();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        id = new TextField();
        view = new Button();
        tableView = new TableView();
        idtbl = new TableColumn();
        nametbl = new TableColumn();
        yeartbl = new TableColumn();
        semtbl = new TableColumn();
        gradetbl = new TableColumn();
year = new TextField();
        label3 = new Label();
        sem = new TextField();
        label4 = new Label();

        setId("AnchorPane");
        setPrefHeight(600.0);
        setPrefWidth(900.0);

        AnchorPane.setBottomAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setTopAnchor(anchorPane, 0.0);
        anchorPane.setId("AnchorPane");
        anchorPane.setPrefHeight(620.0);
        anchorPane.setPrefWidth(920.0);

        label.setLayoutX(308.0);
        label.setLayoutY(23.0);
        label.setPrefHeight(46.0);
        label.setPrefWidth(448.0);
        label.setText("Add Courses  to Students");
        label.setFont(new Font("System Bold", 31.0));

        

        combo.setLayoutX(644.0);
        combo.setLayoutY(246.0);
        combo.setPrefHeight(37.0);
        combo.setPrefWidth(150.0);
        combo.setPromptText("New Course");

        label0.setLayoutX(635.0);
        label0.setLayoutY(204.0);
        label0.setPrefHeight(26.0);
        label0.setPrefWidth(168.0);
        label0.setText("Select New Course");
        label0.setFont(new Font("System Bold", 17.0));

        label1.setLayoutX(113.0);
        label1.setLayoutY(150.0);
        label1.setPrefHeight(26.0);
        label1.setPrefWidth(168.0);
        label1.setText("Registered Courses");
        label1.setFont(new Font("System Bold", 17.0));

        label2.setLayoutX(113.0);
        label2.setLayoutY(93.0);
        label2.setPrefHeight(25.0);
        label2.setPrefWidth(122.0);
        label2.setText("Student id:");
        label2.setFont(new Font(16.0));

        id.setLayoutX(205.0);
        id.setLayoutY(89.0);
        id.setPrefHeight(33.0);
        id.setPrefWidth(385.0);

        view.setLayoutX(622.0);
        view.setLayoutY(89.0);
        view.setMnemonicParsing(false);
        view.setPrefHeight(33.0);
        view.setPrefWidth(162.0);
        view.setText("View Registered Courses");
        view.setFont(new Font(13.0));

        tableView.setLayoutX(113.0);
        tableView.setLayoutY(185.0);
        tableView.setPrefHeight(381.0);
        tableView.setPrefWidth(481.0);

        idtbl.setPrefWidth(60.0);
        idtbl.setText("Course Id");
         idtbl.setCellValueFactory(new PropertyValueFactory<>("id"));
        nametbl.setCellValueFactory(new PropertyValueFactory<>("name"));
        yeartbl.setCellValueFactory(new PropertyValueFactory<>("year"));
         semtbl.setCellValueFactory(new PropertyValueFactory<>("semester"));
        
        gradetbl.setCellValueFactory(new PropertyValueFactory<>("grade"));

        nametbl.setPrefWidth(124.0);
        nametbl.setText("Name");

        yeartbl.setPrefWidth(114.0);
        yeartbl.setText("Year");

        semtbl.setPrefWidth(65.0);
        semtbl.setText("Semester");

        gradetbl.setPrefWidth(116.0);
        gradetbl.setText("Grade");
 year.setLayoutX(644.0);
        year.setLayoutY(316.0);
        year.setPrefHeight(35.0);
        year.setPrefWidth(150.0);

        label3.setLayoutX(642.0);
        label3.setLayoutY(288.0);
        label3.setPrefHeight(25.0);
        label3.setPrefWidth(122.0);
        label3.setText("Year:");
        label3.setFont(new Font(16.0));

        sem.setLayoutX(645.0);
sem.setLayoutY(385.0);
sem.setPrefHeight(33.0);
sem.setPrefWidth(150.0);

label4.setLayoutX(643.0);
label4.setLayoutY(357.0);
label4.setPrefHeight(25.0);
label4.setPrefWidth(122.0);
label4.setText("Semester:");
label4.setFont(new Font(16.0));

// Adjust the layout for the "Add Course" button
addbtn.setLayoutX(665.0);
addbtn.setLayoutY(432.0); // Adjust the Y-coordinate to position it below the Semester TextField
addbtn.setMnemonicParsing(false);
addbtn.setPrefHeight(37.0);
addbtn.setPrefWidth(108.0);
addbtn.setText("Add Course");
addbtn.setFont(new Font(16.0));


        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(addbtn);
        anchorPane.getChildren().add(combo);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(label1);
        anchorPane.getChildren().add(label2);
        anchorPane.getChildren().add(id);
        anchorPane.getChildren().add(view);
        tableView.getColumns().add(idtbl);
        tableView.getColumns().add(nametbl);
        tableView.getColumns().add(yeartbl);
        tableView.getColumns().add(semtbl);
        tableView.getColumns().add(gradetbl);
        anchorPane.getChildren().add(tableView);
anchorPane.getChildren().add(year);
        anchorPane.getChildren().add(label3);
        anchorPane.getChildren().add(sem);
        anchorPane.getChildren().add(label4);
        getChildren().add(anchorPane);
         if (!id.getText().isEmpty()) {
        populateComboBox();}
          try {
            socket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
        } catch (IOException ex) {
           // Logger.getLogger(WishlistController.class.getName()).log(Level.SEVERE, null, ex);
        }
         view.addEventHandler(ActionEvent.ACTION, event -> {
       handleViewCourses() ;

});
          addbtn.addEventHandler(ActionEvent.ACTION, event -> {
    // Check if both student ID and selected course are not empty
    if (!id.getText().isEmpty() && combo.getValue() != null && !year.getText().isEmpty() && !sem.getText().isEmpty()) {
        try {
            // Get the selected course from the combo box
            CourseDTO selectedCourse = (CourseDTO) combo.getValue();

            // Create a JSON object with the necessary information
            JSONObject addCourseData = new JSONObject();
            addCourseData.put("HEADER", "fnAddStudentCourse");
            addCourseData.put("ID", id.getText());
            //addCourseData.put("CourseID", selectedCourse.getId()); // Assuming getId() returns the course ID
            addCourseData.put("NAME", selectedCourse.getName());
            addCourseData.put("YEAR", year.getText());
            addCourseData.put("SEM", sem.getText());
            //addCourseData.put("Grade", selectedCourse.getGrade());

            // Send the JSON object to the server
            ps.println(addCourseData.toString());

            // Handle the server response if needed
            String response = dis.readLine();
                  if (response.equals("Course added successfully")) {
                                    JOptionPane.showMessageDialog(null, "Course added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                  handleViewCourses() ;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Registration Failed.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
            // Add your response handling logic here

        } catch (IOException | JSONException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    } else {
        // Show a message dialog if either student ID or selected course is empty
        JOptionPane.showMessageDialog(null, "Select both student and course first", "Error", JOptionPane.ERROR_MESSAGE);
    }
});
         
         

    }
     private void handleViewCourses() {
    tableView.getItems().clear();
    if (!id.getText().isEmpty()) {
        populateComboBox();
        try {
            JSONObject searchData = new JSONObject();
            searchData.put("HEADER", "fnGetStudentCourses");
            searchData.put("ID", id.getText());
            searchData.put("ADMINID", adminId);
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

                        // Create a CourseDTO object and add it to the ObservableList
                        CourseDTO course = new CourseDTO(courseId, courseName,year,sem, grade);
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
                tableView.getItems().clear();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
     private void populateComboBox() {
        // Fetch all courses from the 'course' table
        try {
            JSONObject fetchCoursesData = new JSONObject();
            fetchCoursesData.put("HEADER", "fnGetAllCourses");
            fetchCoursesData.put("ADMINID",adminId);
            fetchCoursesData.put("ID",id.getText());
            ps.println(fetchCoursesData.toString());

            // Handle the server response if needed
            String response = dis.readLine();
             System.out.print("Hello");
              System.out.print(response);

            if (response != null && !response.isEmpty()) {
                try {
                    JSONArray coursesArray = new JSONArray(response);

                    // Clear previous data in case it was populated before
                    combo.getItems().clear();

                    for (int i = 0; i < coursesArray.length(); i++) {
                        JSONObject courseData = coursesArray.getJSONObject(i);
                        
                        String courseName = courseData.optString("COURSE_NAME");
                         System.out.print(courseName);
                      

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
}
