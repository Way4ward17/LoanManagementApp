/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author way4ward
 */
public class AdminController implements Initializable {

    @FXML
    private AnchorPane block1;
    @FXML
    private StackPane stick1;
    @FXML
    private AnchorPane block2;
    @FXML
    private StackPane stick2;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        start();
    }    
    public void start(){
         stick1.setVisible(true);
      JFXDialogLayout content =  new JFXDialogLayout();
  content.setHeading(new Text("Admin Confirm Code")); // Header of the Dialog
 // content.setBody(new Text(body)); // Text in the dialog
  JFXDialog dialog = new JFXDialog(stick1, content,JFXDialog.DialogTransition.CENTER);
  JFXButton btn = new JFXButton("Close"); 
JFXButton btnConfirm = new JFXButton("Submit"); // Button to close Dialog
TextField pinv = new TextField();
pinv.setPromptText("Enter your Admin ID");
pinv.setMinWidth(280);
btn.setStyle("-fx-background-color:red; -fx-text-fill:white; -fx-background-radius:6px; -fx-padding-bottom:26px;");
btnConfirm.setStyle("-fx-background-color:green; -fx-text-fill:white; -fx-background-radius:6px; -fx-font-style:bold;");
  btn.setOnAction((ActionEvent event) -> {
      dialog.close();
      //  this.createPage(pane, "/Admin/Admin.fxml");
      stick1.setVisible(false);
      System.exit(0);
         });
    btnConfirm.setOnAction((ActionEvent event) -> {
        if(pinv.getText().equals("12345")){
            block2.setVisible(false);
            stick1.setVisible(false);
            block1.setVisible(false);
        }else{
            pinv.setStyle("-fx-border-width:4px; -fx-border-color:red;");
        }
         });
  content.setActions(pinv, btn , btnConfirm );
  
     dialog.show();
    }
}
