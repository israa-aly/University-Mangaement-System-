package university;

import DTO.CourseDTO;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainPageController extends BorderPane {
    protected int adminId;
    protected String facultyName;
    AddStudentController addStudentController;
    DeleteStudentController deleteStudentController;
    UpdateStudentController updateStudentController;
    
    AddProfessorController addProfessorController;
    ManageProfessorsController manageProfessorController;
    AddDeptController addDeptController;
    ManageDeptController manageDeptController;
    AddCourseController addCourseController;
    ManageCoursesController manageCoursesController;
    StudentToCourseController studentToCourseController;
    UpdateGradesController updateGradesController;
    ManageStudentController manageStudentController;
    AddCourseToDeptController addCourseToDeptController;
    HomeController homeController;

    protected final AnchorPane anchorPane;
    protected final ImageView imageView;
    protected final Label label;
    protected final Button homeButton; // Renamed to "Home"
    protected final MenuButton menuButton;
    protected final MenuItem add_student;
    protected final MenuItem view_student;
    protected final MenuButton menuButton0;
    protected final MenuItem add_student11;
    protected final MenuItem view_student1;
    protected final Button course_student;
    protected final MenuButton menuButton1;
    protected final MenuItem add_dept;
    protected final MenuItem manage_dept;
    protected final MenuButton menuButton2;
    protected final MenuItem add_course;
    protected final MenuItem manage_course;
    protected final Button Teacher_course;
    protected final Button UpdateGrades;
    protected final Button logoutbtn;
    Socket socket;
    DataInputStream dis;
    PrintStream ps;

    public MainPageController(int adminId,String facultyName) {
        
this.adminId = adminId;
this.facultyName=facultyName;
        anchorPane = new AnchorPane();
        imageView = new ImageView();
        label = new Label();
        homeButton = new Button(); // Renamed to "Home"
        menuButton = new MenuButton();
        add_student = new MenuItem();
        view_student = new MenuItem();
        menuButton0 = new MenuButton();
        add_student11 = new MenuItem();
        view_student1 = new MenuItem();
        course_student = new Button();
        menuButton1 = new MenuButton();
        add_dept = new MenuItem();
        manage_dept = new MenuItem();
        menuButton2 = new MenuButton();
        add_course = new MenuItem();
        manage_course = new MenuItem();
        UpdateGrades = new Button();
        Teacher_course = new Button();
        logoutbtn = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(650.0);
        setPrefWidth(1100.0);

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setPrefHeight(600.0);
        anchorPane.setPrefWidth(204.0);
        anchorPane.setStyle("-fx-background-color: #98AFC7;");

        imageView.setFitHeight(107.0);
        imageView.setFitWidth(151.0);
        imageView.setLayoutX(58.0);
        imageView.setLayoutY(20.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("admin3.png").toExternalForm()));

        label.setLayoutX(24.0);
        label.setLayoutY(120.0);
        label.setPrefHeight(31.0);
        label.setPrefWidth(156.0);
       // String faculty= getFacultyName(adminId);
       label.setText("Welcome Admin");
        label.setFont(new Font("System Bold", 19.0));

        menuButton.setLayoutX(20.0);
        menuButton.setLayoutY(205.0);
        menuButton.setMnemonicParsing(false);
        menuButton.setPrefHeight(34.0);
        menuButton.setPrefWidth(164.0);
        menuButton.setContentDisplay(ContentDisplay.CENTER); 
        menuButton.setText("Students");

        add_student.setMnemonicParsing(false);
        add_student.setText("Add student");

        view_student.setMnemonicParsing(false);
        view_student.setText("Manage students");

        menuButton.setFont(new Font(14.0));

        menuButton0.setLayoutX(18.0);
        menuButton0.setLayoutY(255.0);
        menuButton0.setMnemonicParsing(false);
        menuButton0.setPrefHeight(35.0);
        menuButton0.setPrefWidth(167.0);
        menuButton0.setText("Professors");

        add_student11.setMnemonicParsing(false);
        add_student11.setText("Add Professor");

        view_student1.setMnemonicParsing(false);
        view_student1.setText("Manage Professors");
        menuButton0.setFont(new Font(15.0));

        course_student.setLayoutX(19.0);
        course_student.setLayoutY(455.0);
        course_student.setMnemonicParsing(false);
        course_student.setPrefHeight(31.0);
        course_student.setPrefWidth(165.0);
        course_student.setText("Add Student to Course");
        course_student.setFont(new Font(13.0));

        menuButton1.setLayoutX(19.0);
        menuButton1.setLayoutY(306.0);
        menuButton1.setMnemonicParsing(false);
        menuButton1.setPrefHeight(35.0);
        menuButton1.setPrefWidth(167.0);
        menuButton1.setText("Departments");

        add_dept.setMnemonicParsing(false);
        add_dept.setText("Add Department");

        manage_dept.setMnemonicParsing(false);
        manage_dept.setText("Manage Departments");
        menuButton1.setFont(new Font(15.0));

        menuButton2.setLayoutX(18.0);
        menuButton2.setLayoutY(358.0);
        menuButton2.setMnemonicParsing(false);
        menuButton2.setPrefHeight(35.0);
        menuButton2.setPrefWidth(167.0);
        menuButton2.setText("Courses");

        add_course.setMnemonicParsing(false);
        add_course.setText("Add Course");

        manage_course.setMnemonicParsing(false);
        manage_course.setText("Manage Course");
        menuButton2.setFont(new Font(15.0));

        UpdateGrades.setLayoutX(19.0);
        UpdateGrades.setLayoutY(410.0);
        UpdateGrades.setMnemonicParsing(false);
        UpdateGrades.setPrefHeight(35.0);
        UpdateGrades.setPrefWidth(167.0);
        UpdateGrades.setText("Grades");

        UpdateGrades.setFont(new Font(15.0));

        Teacher_course.setLayoutX(14.0);
        Teacher_course.setLayoutY(504.0);
        Teacher_course.setMnemonicParsing(false);
        Teacher_course.setPrefHeight(32.0);
        Teacher_course.setPrefWidth(176.0);
        Teacher_course.setText("Add course to Department");
        Teacher_course.setFont(new Font(13.0));

        logoutbtn.setLayoutX(19.0);
        logoutbtn.setLayoutY(520.0);
        logoutbtn.setMnemonicParsing(false);
        logoutbtn.setPrefHeight(33.0);
        logoutbtn.setPrefWidth(165.0);
        logoutbtn.setText("Logout");
        logoutbtn.setFont(new Font(16.0));
        setLeft(anchorPane);

        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(homeButton); // Add the Home button to the anchorPane
        menuButton.getItems().add(add_student);
        menuButton.getItems().add(view_student);
        anchorPane.getChildren().add(menuButton);
        menuButton0.getItems().add(add_student11);
        menuButton0.getItems().add(view_student1);
        anchorPane.getChildren().add(menuButton0);
        anchorPane.getChildren().add(course_student);
        menuButton1.getItems().add(add_dept);
        menuButton1.getItems().add(manage_dept);
        anchorPane.getChildren().add(menuButton1);
        menuButton2.getItems().add(add_course);
        menuButton2.getItems().add(manage_course);
        anchorPane.getChildren().add(menuButton2);
        anchorPane.getChildren().add(UpdateGrades);
      //  anchorPane.getChildren().add(Teacher_course);
        anchorPane.getChildren().add(logoutbtn);
        //getFacultyName(adminId);
        homeButton.setLayoutX(24.0);
        homeButton.setLayoutY(160.0);
        homeButton.setMnemonicParsing(false);
        homeButton.setPrefHeight(30.0);
        homeButton.setPrefWidth(164.0);
        homeButton.setText("Home");
        homeButton.setFont(new Font("System Bold", 15.0));

      try {
            socket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        homeController = new HomeController(adminId,facultyName);
    setCenter(homeController);

        homeButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                homeController = new HomeController(adminId,facultyName);
                    setCenter(homeController);
            
            }
        });

       
            add_student.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    addStudentController = new AddStudentController(adminId);
                } catch (IOException ex) {
                    Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    setCenter(addStudentController);
            }
        }); 
            
              
                  view_student.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    manageStudentController = new ManageStudentController(adminId);
                } catch (IOException ex) {
                    Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    setCenter(manageStudentController);
            }
        }); 
          add_student11.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    addProfessorController = new AddProfessorController(adminId);
                } catch (IOException ex) {
                    Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    setCenter(addProfessorController);
            }
        }); 
           view_student1.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    manageProfessorController = new ManageProfessorsController(adminId);
                } catch (IOException ex) {
                    Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    setCenter(manageProfessorController);
            }
        }); 
               add_dept.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    addDeptController = new AddDeptController(adminId);
                } catch (IOException ex) {
                    Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    setCenter(addDeptController);
            }
        }); 
           manage_dept.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    manageDeptController = new ManageDeptController(adminId);
                } catch (IOException ex) {
                    Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    setCenter(manageDeptController);
            }
        }); 
                add_course.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    addCourseController = new AddCourseController(adminId);
                    setCenter(addCourseController);
            }
        }); 
                
           manage_course.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    manageCoursesController = new ManageCoursesController(adminId);
                    setCenter(manageCoursesController);
            }
        }); 
           
                   course_student.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    studentToCourseController = new StudentToCourseController(adminId);
                    setCenter(studentToCourseController);
            }
        }); 
                   
         UpdateGrades.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    updateGradesController = new UpdateGradesController(adminId);
                    setCenter(updateGradesController);
            }
        }); 
          Teacher_course.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    addCourseToDeptController = new AddCourseToDeptController(adminId);
                    setCenter(addCourseToDeptController);
            }
        }); 
           logoutbtn.addEventHandler(ActionEvent.ACTION,
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {

                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Sign Out Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    JSONObject logoutRequest = new JSONObject();
                    logoutRequest.put("HEADER", "fnLogout");
                    ps.println(logoutRequest.toString());
                    Parent root = new LoginUIController(); // Replace this with your LoginController setup
                    Scene scene = new Scene(root);
                    // Get the stage from the button
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.setMaxHeight(600);
                    stage.setMaxWidth(850);
                    stage.show();
                }
            }
        }
        );


    }

 private void getFacultyName(int adminId) {
    try {
        JSONObject request = new JSONObject();
        request.put("HEADER", "getFacultyName");
        request.put("ADMINID", adminId);

        ps.println(request.toString());
        String response = dis.readLine();
        System.out.println("Response from server: " + response); // Add this line for debugging

        if (response != null && !response.isEmpty()) {
            try {
                label.setText("Admin " + response);
            } catch (JSONException e) {
                System.out.println("Error parsing JSON response: " + response);
            }
        }

    } catch (IOException | JSONException ex) {
        ex.printStackTrace(); // Add this line for debugging
        JOptionPane.showMessageDialog(null, "Error fetching courses", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

}

