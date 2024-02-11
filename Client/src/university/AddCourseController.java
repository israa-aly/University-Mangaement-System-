package university;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public  class AddCourseController extends AnchorPane {
private int adminId;
    protected final AnchorPane anchorPane;
    protected final TextField name;
    protected final Button addbtn;
    protected final Label label;
    protected final TextField hours;
    protected final TextField max;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;

    public AddCourseController(int adminId) {
this.adminId=adminId;
        anchorPane = new AnchorPane();
        name = new TextField();
        addbtn = new Button();
        label = new Label();
        hours = new TextField();
        max = new TextField();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();

        setId("AnchorPane");
        setPrefHeight(600.0);
        setPrefWidth(900.0);

        AnchorPane.setBottomAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setTopAnchor(anchorPane, 0.0);
        anchorPane.setId("AnchorPane");
        anchorPane.setLayoutX(-7.0);
        anchorPane.setPrefHeight(600.0);
        anchorPane.setPrefWidth(900.0);

        name.setLayoutX(452.0);
        name.setLayoutY(164.0);
        name.setPrefHeight(37.0);
        name.setPrefWidth(162.0);

        addbtn.setLayoutX(476.0);
        addbtn.setLayoutY(369.0);
        addbtn.setMnemonicParsing(false);
        addbtn.setPrefHeight(37.0);
        addbtn.setPrefWidth(93.0);
        addbtn.setText("Add");
        addbtn.setFont(new Font(16.0));

        label.setLayoutX(368.0);
        label.setLayoutY(78.0);
        label.setPrefHeight(31.0);
        label.setPrefWidth(246.0);
        label.setText("Add New Course");
        label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label.setFont(new Font("System Bold", 31.0));

        hours.setLayoutX(452.0);
        hours.setLayoutY(222.0);
        hours.setPrefHeight(37.0);
        hours.setPrefWidth(162.0);

        max.setLayoutX(454.0);
        max.setLayoutY(285.0);
        max.setPrefHeight(37.0);
        max.setPrefWidth(162.0);

        label0.setLayoutX(352.0);
        label0.setLayoutY(290.0);
        label0.setPrefHeight(27.0);
        label0.setPrefWidth(115.0);
        label0.setText("Max Mark:");
        label0.setFont(new Font("System Bold", 18.0));

        label1.setLayoutX(324.0);
        label1.setLayoutY(227.0);
        label1.setPrefHeight(27.0);
        label1.setPrefWidth(115.0);
        label1.setText("Credit Hours:");
        label1.setFont(new Font("System Bold", 18.0));

        label2.setLayoutX(382.0);
        label2.setLayoutY(169.0);
        label2.setPrefHeight(27.0);
        label2.setPrefWidth(115.0);
        label2.setText("Name:");
        label2.setFont(new Font("System Bold", 18.0));

        anchorPane.getChildren().add(name);
        anchorPane.getChildren().add(addbtn);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(hours);
        anchorPane.getChildren().add(max);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(label1);
        anchorPane.getChildren().add(label2);
        getChildren().add(anchorPane);
         addbtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                boolean allFieldsFilled = !name.getText().isEmpty()
                        && !hours.getText().isEmpty()
                        && !max.getText().isEmpty();
                                              
                if(!allFieldsFilled){
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
               
                else if(!hours.getText().matches("^\\d+$")){
                    JOptionPane.showMessageDialog(null, "Credit Hours Should be a number", "Input Validation Error", JOptionPane.ERROR_MESSAGE);
                }
                 else if(!max.getText().matches("^\\d+$")){
                    JOptionPane.showMessageDialog(null, "Max Mark Should be a number", "Input Validation Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    try {
                        Socket socket = new Socket("127.0.0.1", 5005);
                        DataInputStream dis = new DataInputStream(socket.getInputStream());
                        PrintStream ps = new PrintStream(socket.getOutputStream());
                         JSONObject checkDeptData = new JSONObject();
                    checkDeptData.put("HEADER", "fnCheckCourseName");
                    checkDeptData.put("NAME", name.getText());
                    checkDeptData.put("ADMINID", adminId);
                    ps.println(checkDeptData.toString());

                    // Read response from server
                    String response2 = dis.readLine();
                    if (response2 != null && response2.equals("yes")) {
                        JOptionPane.showMessageDialog(null, "This Course name already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JSONObject userData = new JSONObject();
                        userData.put("HEADER", "fnAddCourse");
                        userData.put("ADMINID", adminId);
                        userData.put("NAME", name.getText());
                        userData.put("HOURS", hours.getText());
                       userData.put("MAX", max.getText());
                       ps.println(userData.toString());

                        String response = dis.readLine();

                       Platform.runLater(new Runnable() {

                            public void run() {
                                if (response.equals("Course added successfully")) {
                                    JOptionPane.showMessageDialog(null, "Course added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                   name.clear();
                                    hours.clear();
                                    max.clear();
                                    
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
}
