package university;

import DTO.CourseDTO;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeController extends AnchorPane {

    private int adminId;
    private String facultyName;

    protected final Label label;
    protected final Label label0;
    protected final ImageView imagestd;
    protected final Label stdnum;
    protected final Label label1;
    protected final ImageView imgdept;
    protected final Label deptnum;
    protected final Label label2;
    protected final Label label3;
    protected final Label label4;
    protected final ImageView imgcourse;
    protected final Label coursesnum;
    protected final TableView tableView;
    protected final TableColumn<CourseDTO, String> idtbl;
    protected final TableColumn<CourseDTO, String> nametbl;
    protected final TableColumn<CourseDTO, String> hourstbl;
    protected final TableColumn<CourseDTO, String> maxtbl;
    protected final TableColumn<CourseDTO, String> numtbl;
    protected final TableColumn<CourseDTO, String> gpatbl;

    protected final CategoryAxis categoryAxis;
    protected final NumberAxis numberAxis;
    protected final BarChart barChart;
    Socket socket;
    DataInputStream dis;
    PrintStream ps;
    private ObservableList<CourseDTO> studentList = FXCollections.observableArrayList();
    //private ObservableList<CourseDTO> studentListForTableView0 = FXCollections.observableArrayList();

    public HomeController(int adminId, String facultyName) {
        this.adminId = adminId;
        this.facultyName = facultyName;
        label = new Label();
        label0 = new Label();
        imagestd = new ImageView();
        stdnum = new Label();
        label1 = new Label();
        imgdept = new ImageView();
        deptnum = new Label();
        label2 = new Label();
        imgcourse = new ImageView();
        coursesnum = new Label();
        tableView = new TableView();
        idtbl = new TableColumn();
        nametbl = new TableColumn();
        hourstbl = new TableColumn();
        maxtbl = new TableColumn();
        numtbl = new TableColumn();
        gpatbl = new TableColumn();
        label3 = new Label();
        label4 = new Label();

        categoryAxis = new CategoryAxis();
        numberAxis = new NumberAxis();
        barChart = new BarChart(categoryAxis, numberAxis);

        setId("AnchorPane");
        setPrefHeight(600.0);
        setPrefWidth(900.0);

        label.setLayoutX(290.0);
        label.setLayoutY(14.0);
        label.setText("Faculty of " + facultyName);
        label.setFont(new Font("System Bold", 30.0));

        label0.setLayoutX(37.0);
        label0.setLayoutY(74.0);
        label0.setPrefHeight(67.0);
        label0.setPrefWidth(230.0);
        label0.setText("Total Students");
        label0.setFont(new Font(20.0));

        imagestd.setFitHeight(67.0);
        imagestd.setFitWidth(64.0);
        imagestd.setPickOnBounds(true);
        imagestd.setPreserveRatio(true);
        imagestd.setImage(new Image(getClass().getResource("students.png").toExternalForm()));
        label0.setGraphic(imagestd);

        stdnum.setLayoutX(236.0);
        stdnum.setLayoutY(96.0);
        stdnum.setText("13");
        stdnum.setFont(new Font(17.0));

        label1.setLayoutX(292.0);
        label1.setLayoutY(74.0);
        label1.setPrefHeight(67.0);
        label1.setPrefWidth(283.0);
        label1.setText("Total Departments");
        label1.setFont(new Font(20.0));

        imgdept.setFitHeight(67.0);
        imgdept.setFitWidth(64.0);
        imgdept.setPickOnBounds(true);
        imgdept.setPreserveRatio(true);
        imgdept.setImage(new Image(getClass().getResource("depts2.png").toExternalForm()));
        label1.setGraphic(imgdept);

        deptnum.setLayoutX(526.0);
        deptnum.setLayoutY(95.0);
        deptnum.setPrefHeight(26.0);
        deptnum.setPrefWidth(59.0);
        deptnum.setText("13");
        deptnum.setFont(new Font(18.0));

        label2.setLayoutX(599.0);
        label2.setLayoutY(75.0);
        label2.setPrefHeight(67.0);
        label2.setPrefWidth(283.0);
        label2.setText("Total Courses");
        label2.setFont(new Font(20.0));
        label3.setLayoutX(510.0);
        label3.setLayoutY(173.0);
        label3.setText("Students Num");
        label3.setFont(new Font("System ", 13.0));
        label4.setLayoutX(660.0);
        label4.setLayoutY(533.0);
        label4.setText("Departments");
        label4.setFont(new Font("System", 13.0));

        imgcourse.setFitHeight(67.0);
        imgcourse.setFitWidth(64.0);
        imgcourse.setPickOnBounds(true);
        imgcourse.setPreserveRatio(true);
        imgcourse.setImage(new Image(getClass().getResource("courses.png").toExternalForm()));
        label2.setGraphic(imgcourse);

        coursesnum.setLayoutX(799.0);
        coursesnum.setLayoutY(95.0);
        coursesnum.setPrefHeight(26.0);
        coursesnum.setPrefWidth(59.0);
        coursesnum.setText("14");
        coursesnum.setFont(new Font(18.0));

        tableView.setLayoutX(14.0);
        tableView.setLayoutY(185.0);
        tableView.setPrefHeight(309.0);
        tableView.setPrefWidth(486.0);

        idtbl.setPrefWidth(60.0);
        idtbl.setText("Course Id");
        idtbl.setCellValueFactory(new PropertyValueFactory<>("id"));
        nametbl.setCellValueFactory(new PropertyValueFactory<>("name"));
        hourstbl.setCellValueFactory(new PropertyValueFactory<>("hours"));
        maxtbl.setCellValueFactory(new PropertyValueFactory<>("max"));
        numtbl.setCellValueFactory(new PropertyValueFactory<>("num"));
        gpatbl.setCellValueFactory(new PropertyValueFactory<>("gpa"));

        nametbl.setPrefWidth(81.0);
        nametbl.setText("Name");

        hourstbl.setText("Credit Hours");

        maxtbl.setText("Max Mark");

        numtbl.setPrefWidth(93.0);
        numtbl.setText("No of students");

        gpatbl.setPrefWidth(91.0);
        gpatbl.setText("Average GPA");

        categoryAxis.setSide(javafx.geometry.Side.BOTTOM);

        numberAxis.setPrefHeight(313.0);
        numberAxis.setPrefWidth(28.0);
        numberAxis.setSide(javafx.geometry.Side.LEFT);
        barChart.setLayoutX(493.0);
        barChart.setLayoutY(185.0);
        barChart.setPrefHeight(344.0);
        barChart.setPrefWidth(400.0);
        barChart.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(stdnum);
        getChildren().add(label1);
        getChildren().add(deptnum);
        getChildren().add(label2);
        getChildren().add(coursesnum);
        tableView.getColumns().add(idtbl);
        tableView.getColumns().add(nametbl);
        tableView.getColumns().add(hourstbl);
        tableView.getColumns().add(maxtbl);
        tableView.getColumns().add(numtbl);
        tableView.getColumns().add(gpatbl);
        getChildren().add(tableView);
        getChildren().add(barChart);
        getChildren().add(label3);
        getChildren().add(label4);

        try {
            socket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
        } catch (IOException ex) {
            //Logger.getLogger(WishlistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        handleViewCourses();
        handleDepartmentStudentChart();

    }

    private void handleViewCourses() {
        tableView.getItems().clear();

        try {
            JSONObject searchData = new JSONObject();
            searchData.put("HEADER", "fnAllCourses");
            searchData.put("ADMINID", adminId);
            //searchData.put("ADMINID", adminId);
            ps.println(searchData.toString());
            String response = dis.readLine();
            if (response != null && !response.isEmpty()) {
                // Clear previous data

                try {
                    JSONArray coursesArray = new JSONArray(response);

                    for (int i = 0; i < coursesArray.length(); i++) {
                        JSONObject courseData = coursesArray.getJSONObject(i);
                        int courseId = courseData.optInt("ID");
                        String courseName = courseData.optString("NAME");
                        int hours = courseData.optInt("HOURS");
                        int max = courseData.optInt("MAX");
                        int num = courseData.optInt("NUM");
                        int avgGpa = courseData.optInt("GPA");

                        CourseDTO course = new CourseDTO(courseId, courseName, hours, max, num, avgGpa);
                        studentList.add(course);
                    }

                    tableView.setItems(studentList);

                    coursesnum.setText(String.valueOf(coursesArray.length()));

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
    }

    private void handleDepartmentStudentChart() {
        barChart.getData().clear(); // Clear previous data in the bar chart

        try {
            JSONObject searchData = new JSONObject();
            searchData.put("HEADER", "fnDepartmentStudentCount");
            searchData.put("ADMINID", adminId);
            ps.println(searchData.toString());
            String response = dis.readLine();
            if (response != null && !response.isEmpty()) {
                try {
                    JSONArray departmentArray = new JSONArray(response);

                    ObservableList<String> departments = FXCollections.observableArrayList();
                    ObservableList<Number> studentCounts = FXCollections.observableArrayList();

                    for (int i = 0; i < departmentArray.length(); i++) {
                        JSONObject departmentData = departmentArray.getJSONObject(i);
                        String deptName = departmentData.optString("DEPTNAME");
                        int num = departmentData.optInt("STUDENT_COUNT");

                        departments.add(deptName);
                        studentCounts.add(num);
                    }

                    XYChart.Series<String, Number> series = new XYChart.Series<>();
                    for (int i = 0; i < departments.size(); i++) {
                        XYChart.Data<String, Number> data = new XYChart.Data<>(departments.get(i), studentCounts.get(i));
                        series.getData().add(data);

                        Node node = data.getNode();
                        if (node != null) {
                            node.setStyle("-fx-bar-fill: green;");
                        }
                    }
                    barChart.getData().add(series);

                    deptnum.setText(String.valueOf(departmentArray.length()));
                    stdnum.setText(String.valueOf(getTotalStudents(departmentArray)));

                } catch (JSONException e) {
                    System.out.println("Error parsing JSON response: " + response);
                }
            } else {
                // Handle empty response here
                System.out.println("No results found for department student counts.");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private int getTotalStudents(JSONArray departmentArray) {
        int totalStudents = 0;
        for (int i = 0; i < departmentArray.length(); i++) {
            JSONObject departmentData = departmentArray.getJSONObject(i);
            totalStudents += departmentData.optInt("STUDENT_COUNT");
        }
        return totalStudents;
    }

}
