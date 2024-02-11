package university;

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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;

public  class UpdateStudentController extends AnchorPane {

    protected final Label label;
    protected final Label label0;
    protected final TextField id;
    protected final Button viewbtn;
    protected final TableView tableView;
    protected final TableColumn<StudentDTO, String> idtbl;
     protected final TableColumn<StudentDTO, String> nametbl;
     protected final TableColumn<StudentDTO, String> agetbl;
     protected final TableColumn<StudentDTO, String> citytbl;
     protected final TableColumn<StudentDTO, String> depttbl;
     protected final TableColumn<StudentDTO, String> gpatbl;
     protected final TableColumn<StudentDTO, String> semtbl;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
    protected final Label label4;
    protected final TextField namef;
    protected final TextField agef;
    protected final TextField cityf;
    protected final TextField phonef;
    protected final Label label5;
    protected final Label label6;
    protected final TextField textField;
    protected final Label label7;
    protected final TextField deptf;
    protected final Button updatebtn;
      Socket socket;
    DataInputStream dis;
    PrintStream ps;
    private ObservableList<StudentDTO> studentList = FXCollections.observableArrayList();


    public UpdateStudentController() {

        label = new Label();
        label0 = new Label();
        id = new TextField();
        viewbtn = new Button();
        tableView = new TableView();
        idtbl = new TableColumn();
        nametbl = new TableColumn();
        agetbl = new TableColumn();
        citytbl = new TableColumn();
        gpatbl = new TableColumn();
        depttbl = new TableColumn();
        semtbl = new TableColumn();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        label4 = new Label();
        namef = new TextField();
        agef = new TextField();
        cityf = new TextField();
        phonef = new TextField();
        label5 = new Label();
        label6 = new Label();
        textField = new TextField();
        label7 = new Label();
        deptf = new TextField();
        updatebtn = new Button();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        label.setLayoutX(229.0);
        label.setLayoutY(26.0);
        label.setPrefHeight(27.0);
        label.setPrefWidth(194.0);
        label.setText("Update Student Info");
        label.setFont(new Font("System Bold", 18.0));

        label0.setLayoutX(31.0);
        label0.setLayoutY(61.0);
        label0.setPrefHeight(17.0);
        label0.setPrefWidth(90.0);
        label0.setText("Student_ID");
        label0.setFont(new Font(16.0));

        id.setLayoutX(115.0);
        id.setLayoutY(61.0);
        id.setPrefHeight(25.0);
        id.setPrefWidth(156.0);
idtbl.setCellValueFactory(new PropertyValueFactory<>("id"));
        nametbl.setCellValueFactory(new PropertyValueFactory<>("name"));
        agetbl.setCellValueFactory(new PropertyValueFactory<>("age"));
        citytbl.setCellValueFactory(new PropertyValueFactory<>("city"));
        gpatbl.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        depttbl.setCellValueFactory(new PropertyValueFactory<>("dept"));
        semtbl.setCellValueFactory(new PropertyValueFactory<>("semester"));
        viewbtn.setLayoutX(438.0);
        viewbtn.setLayoutY(59.0);
        viewbtn.setMnemonicParsing(false);
        viewbtn.setText("View");
        viewbtn.setFont(new Font(13.0));

        tableView.setLayoutX(31.0);
        tableView.setLayoutY(100.0);
        tableView.setPrefHeight(59.0);
        tableView.setPrefWidth(526.0);

        idtbl.setPrefWidth(75.0);
        idtbl.setText("ID");

        nametbl.setPrefWidth(75.0);
        nametbl.setText("Name");

        agetbl.setPrefWidth(75.0);
        agetbl.setText("Age");

        citytbl.setPrefWidth(75.0);
        citytbl.setText("City");

        gpatbl.setPrefWidth(75.0);
        gpatbl.setText("GPA");

        depttbl.setPrefWidth(75.0);
        depttbl.setText("Dept ID");

        semtbl.setPrefWidth(75.0);
        semtbl.setText("Semester");

        label1.setLayoutX(31.0);
        label1.setLayoutY(173.0);
        label1.setPrefHeight(17.0);
        label1.setPrefWidth(52.0);
        label1.setText("Name");
        label1.setFont(new Font(16.0));

        label2.setLayoutX(300.0);
        label2.setLayoutY(173.0);
        label2.setPrefHeight(17.0);
        label2.setPrefWidth(44.0);
        label2.setText("City");
        label2.setFont(new Font(16.0));

        label3.setLayoutX(31.0);
        label3.setLayoutY(220.0);
        label3.setPrefHeight(17.0);
        label3.setPrefWidth(52.0);
        label3.setText("Age");
        label3.setFont(new Font(16.0));

        label4.setLayoutX(300.0);
        label4.setLayoutY(220.0);

        namef.setLayoutX(90.0);
        namef.setLayoutY(169.0);

        agef.setLayoutX(90.0);
        agef.setLayoutY(216.0);

        cityf.setLayoutX(357.0);
        cityf.setLayoutY(169.0);

        phonef.setLayoutX(357.0);
        phonef.setLayoutY(216.0);

        label5.setLayoutX(300.0);
        label5.setLayoutY(220.0);
        label5.setPrefHeight(17.0);
        label5.setPrefWidth(52.0);
        label5.setText("Phone");
        label5.setFont(new Font(16.0));

        label6.setLayoutX(28.0);
        label6.setLayoutY(275.0);
        label6.setPrefHeight(19.0);
        label6.setPrefWidth(55.0);
        label6.setText("Semester");
        label6.setFont(new Font(13.0));

        textField.setLayoutX(90.0);
        textField.setLayoutY(271.0);

        label7.setLayoutX(291.0);
        label7.setLayoutY(275.0);
        label7.setPrefHeight(25.0);
        label7.setPrefWidth(55.0);
        label7.setText("Dept ID");
        label7.setFont(new Font(16.0));

        deptf.setLayoutX(353.0);
        deptf.setLayoutY(271.0);

        updatebtn.setLayoutX(264.0);
        updatebtn.setLayoutY(323.0);
        updatebtn.setMnemonicParsing(false);
        updatebtn.setText("Update");
        updatebtn.setFont(new Font(15.0));

        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(id);
        getChildren().add(viewbtn);
        tableView.getColumns().add(idtbl);
        tableView.getColumns().add(nametbl);
        tableView.getColumns().add(agetbl);
        tableView.getColumns().add(citytbl);
        tableView.getColumns().add(gpatbl);
        tableView.getColumns().add(depttbl);
        tableView.getColumns().add(semtbl);
        getChildren().add(tableView);
        getChildren().add(label1);
        getChildren().add(label2);
        getChildren().add(label3);
        getChildren().add(label4);
        getChildren().add(namef);
        getChildren().add(agef);
        getChildren().add(cityf);
        getChildren().add(phonef);
        getChildren().add(label5);
        getChildren().add(label6);
        getChildren().add(textField);
        getChildren().add(label7);
        getChildren().add(deptf);
        getChildren().add(updatebtn);
        try {
            socket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
        } catch (IOException ex) {
            //Logger.getLogger(WishlistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        viewbtn.addEventHandler(ActionEvent.ACTION, event -> {
    if (!id.getText().isEmpty()) {
        try {
            JSONObject searchData = new JSONObject();
            searchData.put("HEADER", "fnSearchStudent");
            searchData.put("ID", id.getText());
            ps.println(searchData.toString());
            String response = dis.readLine();

            if (response != null && !response.isEmpty()) {
                // Clear previous data
                studentList.clear();

                try {
                    JSONObject searchedData = new JSONObject(response);

                    // Extract student information from the JSONObject
                    int studentId = searchedData.optInt("ID");
                    String studentName = searchedData.optString("NAME");
                    int studentAge = searchedData.optInt("AGE");
                    String studentCity = searchedData.optString("CITY");
                    int studentGPA = searchedData.optInt("GPA");
                    int studentSemester = searchedData.optInt("SEM");

                    // Create a Student object and add it to the ObservableList
                    StudentDTO student = new StudentDTO(studentId, studentName, studentAge, studentCity, studentGPA, studentSemester);
                    studentList.add(student);
                    tableView.setItems(studentList);
                        namef.setText(student.getName());
                        agef.setText(String.valueOf(student.getAge()));
                        cityf.setText(student.getCity());
                        textField.setText(String.valueOf(student.getSemester()));
                    

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
  updatebtn.addEventHandler(ActionEvent.ACTION, event -> {
    if (!id.getText().isEmpty()) {
        try {
            JSONObject searchData = new JSONObject();
            searchData.put("HEADER", "fnUpdateStudent");
            searchData.put("ID", id.getText());
            searchData.put("NAME", namef.getText());            
            searchData.put("AGE", agef.getText());
            searchData.put("CITY", cityf.getText());
            searchData.put("PHONE", phonef.getText());
            searchData.put("SEM", textField.getText());
            searchData.put("DEPT", deptf.getText());
            ps.println(searchData.toString());
            String response = dis.readLine();

            if (response.equals("Student Updated successfully")) {
                // Clear previous data
                tableView.getItems().clear();
                // Clear text fields
                namef.clear();
                agef.clear();
                cityf.clear();
                phonef.clear();
                textField.clear();
                deptf.clear();
                JOptionPane.showMessageDialog(null, "Student Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update", "Error", JOptionPane.INFORMATION_MESSAGE);
                //tableView.getItems().clear();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }
});



    }
}
