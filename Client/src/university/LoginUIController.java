package university;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public  class LoginUIController extends AnchorPane {

    protected final TextField txtEmail;
    protected final PasswordField txtPassword;
    protected final Button btnSignIn;
    protected final ImageView imageView;
     Socket socket;
    DataInputStream dis;
    public PrintStream ps;

    public LoginUIController() {

        txtEmail = new TextField();
        txtPassword = new PasswordField();
        btnSignIn = new Button();
        imageView = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(500.0);
        setPrefWidth(500.0);

        txtEmail.setLayoutX(142.0);
        txtEmail.setLayoutY(250.0);
        txtEmail.setPrefHeight(35.0);
        txtEmail.setPrefWidth(233.0);
        txtEmail.setPromptText("Email");

        txtPassword.setLayoutX(142.0);
        txtPassword.setLayoutY(298.0);
        txtPassword.setPrefHeight(35.0);
        txtPassword.setPrefWidth(233.0);
        txtPassword.setPromptText("Password");

        btnSignIn.setLayoutX(200.0);
        btnSignIn.setLayoutY(344.0);
        btnSignIn.setMnemonicParsing(false);
        btnSignIn.setPrefHeight(35.0);
        btnSignIn.setPrefWidth(114.0);
        btnSignIn.setStyle("-fx-background-color: #7badec;");
        btnSignIn.setText("Sign In");
        btnSignIn.setTextFill(javafx.scene.paint.Color.WHITE);
        btnSignIn.setFont(new Font(15.0));

        imageView.setFitHeight(220.0);
        imageView.setFitWidth(220.0);
        imageView.setLayoutX(150.0);
        imageView.setLayoutY(31.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("login2.png").toExternalForm()));

        getChildren().add(txtEmail);
        getChildren().add(txtPassword);
        getChildren().add(btnSignIn);
        getChildren().add(imageView);
         btnSignIn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    socket = new Socket("127.0.0.1", 5005);
                    dis = new DataInputStream(socket.getInputStream());
                    ps = new PrintStream(socket.getOutputStream());

                    // Creating a JSON object for login
                    JSONObject userData = new JSONObject();
                    userData.put("HEADER", "fnLogin");
                    userData.put("USERNAME", txtEmail.getText());
                    userData.put("PASSWORD", txtPassword.getText());
                    ps.println(userData.toString());

                    // Handling server response in a separate thread
                    new Thread() {
                        public void run() {

                            try {
                                String response = dis.readLine();
                               

                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (response.equals("Login Successfully")) {
                                            try {
                                                int id = Integer.parseInt(dis.readLine());
                                                String facultyName =dis.readLine();
                                                JOptionPane.showMessageDialog(null, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                                Parent root = new MainPageController(id,facultyName); 
                                               
                                                Scene scene = new Scene(root);
                                                // Get the stage from the button
                                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                                stage.setTitle("University Management System - Admin: " + id + " - Faculty: " + facultyName);
                                                stage.setScene(scene);
                                                stage.show();
                                                
                                            } catch (IOException ex) {
                                                Logger.getLogger(LoginUIController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                                
                                            
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Login Failed. Please check your Email and password.", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                });
                            } catch (IOException ex) {
                                // Handle disconnection from the server
                                JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);

                                // Close the client application
                              /* Platform.runLater(new Runnable() {

                                    @Override
                                    public void run() {

                                        /*Stage stage = (Stage) getScene().getWindow();
                                        stage.close();*/
                                //    }
                              //  }
                         //      ); 

                            }
                        }

                    }.start();
                } catch (IOException ex) {
                    // Handle connection failure
                    JOptionPane.showMessageDialog(null, "Connection Lost From the Server", "Connection Error", JOptionPane.ERROR_MESSAGE);

                    // Close the client application
                    /*Stage stage = (Stage) getScene().getWindow();
                    stage.close();*/
                }
            }
        });


    }
}
