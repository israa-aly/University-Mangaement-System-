package university;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public  class AddDeptController extends AnchorPane {
private int adminId;
    protected final AnchorPane anchorPane;
    protected final TextField name;
    protected final Label label;
    protected final Label label0;
    protected final Button addbtn;

    public AddDeptController(int adminId) throws IOException {
this.adminId= adminId;
        anchorPane = new AnchorPane();
        name = new TextField();
        label = new Label();
        label0 = new Label();
       
        addbtn = new Button();

        setId("AnchorPane");
        setPrefHeight(600.0);
        setPrefWidth(900.0);

        AnchorPane.setBottomAnchor(anchorPane, -6.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setTopAnchor(anchorPane, -2.0);
        anchorPane.setId("AnchorPane");
        anchorPane.setLayoutY(-2.0);
        anchorPane.setPrefHeight(608.0);
        anchorPane.setPrefWidth(900.0);

        name.setLayoutX(360.0);
        name.setLayoutY(199.0);
        name.setPrefHeight(44.0);
        name.setPrefWidth(183.0);

        label.setLayoutX(300.0);
        label.setLayoutY(208.0);
        label.setPrefHeight(17.0);
        label.setPrefWidth(93.0);
        label.setText("Name:");
        label.setFont(new Font("System Bold", 18.0));

        label0.setLayoutX(338.0);
        label0.setLayoutY(72.0);
        label0.setPrefHeight(45.0);
        label0.setPrefWidth(330.0);
        label0.setText("Add New Department");
        label0.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label0.setFont(new Font("System Bold", 30.0));

      

        addbtn.setLayoutX(370.0);
        addbtn.setLayoutY(318.0);
        addbtn.setMnemonicParsing(false);
        addbtn.setPrefHeight(44.0);
        addbtn.setPrefWidth(128.0);
        addbtn.setText("Add");
        addbtn.setFont(new Font(15.0));

        anchorPane.getChildren().add(name);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(label0);
       // anchorPane.getChildren().add(label1);
       // anchorPane.getChildren().add(head);
        anchorPane.getChildren().add(addbtn);
        getChildren().add(anchorPane);
       // populateComboBox();
        addbtn.setOnAction(event -> {
            boolean allFieldsFilled = !name.getText().isEmpty() ;
           
            if (!allFieldsFilled) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Socket socket = new Socket("127.0.0.1", 5005);
                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    PrintStream ps = new PrintStream(socket.getOutputStream());

                    JSONObject checkDeptData = new JSONObject();
                    checkDeptData.put("HEADER", "fnCheckDeptName");
                    checkDeptData.put("NAME", name.getText());
                    checkDeptData.put("ADMINID", adminId);

                    ps.println(checkDeptData.toString());

                    String response = dis.readLine();
                    if (response != null && response.equals("yes")) {
                        JOptionPane.showMessageDialog(null, "This department already exists at our university.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JSONObject userData = new JSONObject();
                        userData.put("HEADER", "fnAddDept");
                        userData.put("ADMINID", adminId);
                        userData.put("NAME", name.getText());
                        
                        //int headValue = Integer.parseInt(head.getValue().toString());
                        //userData.put("HEAD", head.getValue());
                        ps.println(userData.toString());

                        String response2 = dis.readLine();

                        Platform.runLater(new Runnable() {

                            public void run() {
                                if (response2.equals("Dept added successfully")) {
                                    JOptionPane.showMessageDialog(null, "Department added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    
                                    name.clear();
                                   // head.setValue(null);
                                    
                                } else {
                                    JOptionPane.showMessageDialog(null, "Registration Failed.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                    
                        });}
                } catch (IOException | JSONException ex) {
                    ex.printStackTrace(); // Handle exceptions appropriately
                }
            }
        });
    }}
