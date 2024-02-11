package university;


import DTO.StudentDTO;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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

public  class DeleteStudentController extends AnchorPane {

    protected final Label label;
    protected final Label label0;
    protected final TextField id;
    protected final Button btnView;
    protected final TableView tableView;
     protected final TableColumn<StudentDTO, String> idtbl;
     protected final TableColumn<StudentDTO, String> nametbl;
     protected final TableColumn<StudentDTO, String> agetbl;
     protected final TableColumn<StudentDTO, String> citytbl;
     protected final TableColumn<StudentDTO, String> gpatbl;
     protected final TableColumn<StudentDTO, String> semtbl;
    protected final Button deletebtn;
       Socket socket;
    DataInputStream dis;
    PrintStream ps;
    private ObservableList<StudentDTO> studentList = FXCollections.observableArrayList();


    public DeleteStudentController() {
      
        label = new Label();
        label0 = new Label();
        id = new TextField();
        btnView = new Button();
        tableView = new TableView();
        idtbl = new TableColumn();
        nametbl = new TableColumn();
        agetbl = new TableColumn();
        citytbl = new TableColumn();
        gpatbl = new TableColumn();
        semtbl = new TableColumn();
        deletebtn = new Button();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        label.setLayoutX(238.0);
        label.setLayoutY(45.0);
        label.setPrefHeight(17.0);
        label.setPrefWidth(166.0);
        label.setText("Delete Student");
        label.setFont(new Font("System Bold", 19.0));

        label0.setLayoutX(79.0);
        label0.setLayoutY(113.0);
        label0.setPrefHeight(21.0);
        label0.setPrefWidth(84.0);
        label0.setText("Student_id:");
        label0.setFont(new Font(15.0));

        id.setLayoutX(163.0);
        id.setLayoutY(111.0);

        btnView.setLayoutX(397.0);
        btnView.setLayoutY(111.0);
        btnView.setMnemonicParsing(false);
        btnView.setText("View");

        tableView.setLayoutX(79.0);
        tableView.setLayoutY(153.0);
        tableView.setPrefHeight(81.0);
        tableView.setPrefWidth(450.0);

        idtbl.setPrefWidth(75.0);
        idtbl.setText("ID");
        idtbl.setCellValueFactory(new PropertyValueFactory<>("id"));
        nametbl.setCellValueFactory(new PropertyValueFactory<>("name"));
        agetbl.setCellValueFactory(new PropertyValueFactory<>("age"));
        citytbl.setCellValueFactory(new PropertyValueFactory<>("city"));
        gpatbl.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        semtbl.setCellValueFactory(new PropertyValueFactory<>("semester"));

        nametbl.setPrefWidth(75.0);
        nametbl.setText("Name");

        agetbl.setPrefWidth(75.0);
        agetbl.setText("Age");

        citytbl.setPrefWidth(75.0);
        citytbl.setText("City");

        gpatbl.setPrefWidth(75.0);
        gpatbl.setText("GPA");

        semtbl.setPrefWidth(75.0);
        semtbl.setText("Semester");

        deletebtn.setLayoutX(286.0);
        deletebtn.setLayoutY(285.0);
        deletebtn.setMnemonicParsing(false);
        deletebtn.setText("Delete");

        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(id);
        getChildren().add(btnView);
        tableView.getColumns().add(idtbl);
        tableView.getColumns().add(nametbl);
        tableView.getColumns().add(agetbl);
        tableView.getColumns().add(citytbl);
        tableView.getColumns().add(gpatbl);
        tableView.getColumns().add(semtbl);
        tableView.setItems(studentList);
        getChildren().add(tableView);
        getChildren().add(deletebtn);
         try {
            socket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
        } catch (IOException ex) {
            //Logger.getLogger(WishlistController.class.getName()).log(Level.SEVERE, null, ex);
        }
          btnView.addEventHandler(ActionEvent.ACTION, event -> {
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

                    int studentId = searchedData.optInt("ID");
                    String studentName = searchedData.optString("NAME");
                    int studentAge = searchedData.optInt("AGE");
                    String studentCity = searchedData.optString("CITY");
                    int studentGPA = searchedData.optInt("GPA");
                    int studentSemester = searchedData.optInt("SEM");

                    StudentDTO student = new StudentDTO(studentId, studentName, studentAge, studentCity, studentGPA, studentSemester);
                    studentList.add(student);
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

       deletebtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        StudentDTO selectedItem = (StudentDTO) tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
           

            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete student?", "Delete Student Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                try {
                    // Retrieve the attributes and print them
                    int studentId = selectedItem.getId();  // Assuming there is a 'getId()' method in your StudentDTO

                    JSONObject deletedStudent = new JSONObject();
                    deletedStudent.put("HEADER", "fnDeleteStudent");
                    deletedStudent.put("ID", studentId);
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
            }
        } else {
            JOptionPane.showMessageDialog(null, "Choose student first", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
});

    }
}
