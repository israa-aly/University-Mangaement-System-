package university;

import DTO.CourseDTO;
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

public  class AddCourseToDeptController extends AnchorPane {
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
    protected final TableColumn<CourseDTO, String> hourstbl;
    protected final TableColumn<CourseDTO, String> maxtbl;
    protected final TableColumn<CourseDTO, String> numtbl;
    protected final TableColumn<CourseDTO, String> gpatbl;
 

     Socket socket;
    DataInputStream dis;
    PrintStream ps;
    private ObservableList<CourseDTO> studentList = FXCollections.observableArrayList();
    private ObservableList<CourseDTO> studentListForTableView0 = FXCollections.observableArrayList();
  

    public AddCourseToDeptController(int adminId) {
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
        hourstbl = new TableColumn();
        maxtbl = new TableColumn();
        numtbl = new TableColumn();
        gpatbl = new TableColumn();

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
        anchorPane.setPrefHeight(600.0);
        anchorPane.setPrefWidth(900.0);

        label.setLayoutX(308.0);
        label.setLayoutY(23.0);
        label.setPrefHeight(46.0);
        label.setPrefWidth(448.0);
        label.setText("Add Courses  to Departments");
        label.setFont(new Font("System Bold", 31.0));

        addbtn.setLayoutX(688.0);
        addbtn.setLayoutY(312.0);
        addbtn.setMnemonicParsing(false);
        addbtn.setPrefHeight(37.0);
        addbtn.setPrefWidth(108.0);
        addbtn.setText("Add Course");
        addbtn.setFont(new Font(16.0));

        combo.setLayoutX(667.0);
        combo.setLayoutY(246.0);
        combo.setPrefHeight(37.0);
        combo.setPrefWidth(150.0);
        combo.setPromptText("New Course");

        label0.setLayoutX(658.0);
        label0.setLayoutY(204.0);
        label0.setPrefHeight(26.0);
        label0.setPrefWidth(168.0);
        label0.setText("Select New Course");
        label0.setFont(new Font("System Bold", 17.0));

        label1.setLayoutX(70.0);
        label1.setLayoutY(148.0);
        label1.setPrefHeight(26.0);
        label1.setPrefWidth(168.0);
        label1.setText("Registered Courses");
        label1.setFont(new Font("System Bold", 17.0));

        label2.setLayoutX(160.0);
        label2.setLayoutY(93.0);
        label2.setPrefHeight(25.0);
        label2.setPrefWidth(122.0);
        label2.setText("Department id:");
        label2.setFont(new Font(16.0));

        id.setLayoutX(272.0);
        id.setLayoutY(89.0);
        id.setPrefHeight(33.0);
        id.setPrefWidth(245.0);

        view.setLayoutX(594.0);
        view.setLayoutY(89.0);
        view.setMnemonicParsing(false);
        view.setPrefHeight(33.0);
        view.setPrefWidth(162.0);
        view.setText("View Registered Courses");
        view.setFont(new Font(13.0));

        tableView.setLayoutX(70.0);
        tableView.setLayoutY(182.0);
        tableView.setPrefHeight(381.0);
        tableView.setPrefWidth(574.0);

        idtbl.setPrefWidth(60.0);
        idtbl.setText("Course Id");
        idtbl.setCellValueFactory(new PropertyValueFactory<>("id"));
        nametbl.setCellValueFactory(new PropertyValueFactory<>("name"));
        hourstbl.setCellValueFactory(new PropertyValueFactory<>("hours"));
        maxtbl.setCellValueFactory(new PropertyValueFactory<>("max"));
        numtbl.setCellValueFactory(new PropertyValueFactory<>("num"));
        gpatbl.setCellValueFactory(new PropertyValueFactory<>("gpa"));
       

        nametbl.setPrefWidth(124.0);
        nametbl.setText("Name");

        hourstbl.setPrefWidth(114.0);
        hourstbl.setText("Credit Hours");

        maxtbl.setPrefWidth(65.0);
        maxtbl.setText("Max Mark");

        numtbl.setPrefWidth(116.0);
        numtbl.setText("No of students");

        gpatbl.setPrefWidth(94.0);
        gpatbl.setText("Average GPA");

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
        tableView.getColumns().add(hourstbl);
        tableView.getColumns().add(maxtbl);
        tableView.getColumns().add(numtbl);
        tableView.getColumns().add(gpatbl);
        anchorPane.getChildren().add(tableView);
        getChildren().add(anchorPane);
          try {
            socket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
        } catch (IOException ex) {
            //Logger.getLogger(WishlistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        view.addEventHandler(ActionEvent.ACTION, event -> {
            tableView.getItems().clear();
            populateComboBox();
            

            try {
                JSONObject searchData = new JSONObject();
                searchData.put("HEADER", "fnDeptRegCourses");
                searchData.put("ID", id.getText());

                ps.println(searchData.toString());
                String response = dis.readLine();
                if (response != null && !response.isEmpty()) {

                    try {
                        JSONArray coursesArray = new JSONArray(response);

                        for (int i = 0; i < coursesArray.length(); i++) {
                            JSONObject courseData = coursesArray.getJSONObject(i);
                            int studentId = courseData.optInt("ID");
                            String studentName = courseData.optString("NAME");
                            int hours = courseData.optInt("HOURS");
                            int max = courseData.optInt("MAX");
                            int num = courseData.optInt("NUM");
                            int avggpa = courseData.optInt("GPA");
                            

                            CourseDTO course = new CourseDTO(studentId, studentName, hours, max, num, avggpa);
                            studentList.add(course);

                        }

                        tableView.setItems(studentList);

                    } catch (JSONException e) {
                        System.out.println("Error parsing JSON response: " + response);

                    }
                } else {
                    System.out.println("No results found for courses.");
                    tableView.getItems().clear();
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);
            }

        });
          addbtn.setOnAction(event -> {
            

            if (id.getText().isEmpty() || combo.getValue() == null) {
                JOptionPane.showMessageDialog(null, "Please Choose a course first ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Socket socket = new Socket("127.0.0.1", 5005);
                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    PrintStream ps = new PrintStream(socket.getOutputStream());

                  
                        JSONObject userData = new JSONObject();
                        userData.put("HEADER", "fnAddDeptCourse");
                        
                        String headValue = combo.getValue().toString();
                        userData.put("COURSE", combo.getValue());
                         userData.put("ID", id.getText());
                        ps.println(userData.toString());
                        // Read response from server
                        String response2 = dis.readLine();
                        Platform.runLater(new Runnable() {

                            public void run() {
                                if (response2.equals("Course added successfully")) {
                                    JOptionPane.showMessageDialog(null, "Course added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    populateComboBox();
                                    combo.setValue(null);
                                    populateComboBox();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Registration Failed.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            
                    
                            } 
                });} catch (IOException | JSONException ex) {
                    ex.printStackTrace(); 
                }
            }
        });

    }
    private void populateComboBox() {
        try {
            JSONObject fetchCoursesData = new JSONObject();
            fetchCoursesData.put("HEADER", "fnGetDeptNonCourses");
            fetchCoursesData.put("ID",id.getText());
             fetchCoursesData.put("ADMINID",adminId);
            ps.println(fetchCoursesData.toString());

            String response = dis.readLine();

            if (response != null && !response.isEmpty()) {
                try {
                    JSONArray coursesArray = new JSONArray(response);

                    combo.getItems().clear();

                    for (int i = 0; i < coursesArray.length(); i++) {
                        JSONObject courseData = coursesArray.getJSONObject(i);
                        
                        String courseName = courseData.optString("COURSE_NAME");
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
