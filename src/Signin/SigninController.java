
package Signin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import loanmanagementapp.Javaconnect;
import org.controlsfx.control.Notifications;


public class SigninController implements Initializable {

    @FXML
    private TextField staffID;
    @FXML
    private PasswordField pass;
    @FXML
    private JFXButton lgbtn;
    @FXML
    private JFXButton NewUserBtn;
    @FXML
    private AnchorPane signUpPane;
    @FXML
    private TextField signUpUsername;
    @FXML
    private PasswordField signUpPassword;
    @FXML
    private TextField signUpEmail;
    @FXML
    private TextField signUpContact;
    @FXML
    private JFXButton signupBtn;
Connection conn;
PreparedStatement pstmt;
ResultSet rs;
    @FXML
    private StackPane stick;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = Javaconnect.ConnecrDB();
         lgbtn.setOnAction(e-> login(e));
        stick.setVisible(false);
        signUpPane.setVisible(false);
        NewUserBtn.setOnAction(e->{
           if(signUpPane.isVisible() == true){
               signUpPane.setVisible(false);
               NewUserBtn.setText("New User");
           } else{
               signUpPane.setVisible(true);
            NewUserBtn.setText("Login");
           }
        });
    }
    
     private void login(ActionEvent event) {
if(staffID.getText().isEmpty() || pass.getText().isEmpty()){
    showDialog("Error" , "One or more field is \n Empty");
}else{ 
       try{
            String sql = "SELECT * FROM register WHERE username=? AND Password=?";
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1 , staffID.getText());
           pstmt.setString(2 , pass.getText());
           rs = pstmt.executeQuery();
           if(rs.next()){ 
                Stage stage = new Stage();
               FXMLLoader Loader = new FXMLLoader();
                 Loader.setLocation(getClass().getResource("/Home/Home.fxml"));
                 Parent root = Loader.load();
               
                 JFXDecorator decorator=new JFXDecorator(stage, root, false, false, true);
        decorator.setCustomMaximize(false);
     
       decorator.setBorder(Border.EMPTY);
           

            
              Scene scene = new Scene(decorator);
             
              stage.setScene(scene);
              stage.centerOnScreen();
              stage.setResizable(false);
              stage.show(); 
              lgbtn.getScene().getWindow().hide();
              
               Image img = new Image("/images/owk.png");
               Notifications notificationBuilder = Notifications.create()
               .title("LOGIN")
               .text("LOGIN SUCCESFULL")
               .graphic(new ImageView(img))
               .hideAfter(Duration.seconds(3))
               .position(Pos.BOTTOM_RIGHT);
               notificationBuilder.show();
           }else{
               showDialog("Error" , "Invalid StaffID");
           }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null , e);
       }
     }
     }
     @FXML
   private void signup(ActionEvent event){
    if(signUpUsername.getText().isEmpty() || signUpPassword.getText().isEmpty() || signUpEmail.getText().isEmpty() || signUpContact.getText().isEmpty() ){
        showDialog("ERROR" , "One or More Field is \nEmpty");
    }else
       
    try{
        String sql = "INSERT INTO register (username , PASSWORD , phonenumber , email) VALUES (?,?,?,?)";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1,signUpUsername.getText());
      pstmt.setString(2,signUpPassword.getText());
      pstmt.setString(3,signUpContact.getText());
      pstmt.setString(4,signUpEmail.getText());
      pstmt.execute();
      showDialog("SUCCESS" , "ACCT CREATED");
    }catch(Exception e){
        JOptionPane.showMessageDialog(null , e);
    }   
   } 
    
    
   private void showDialog(String heading,String body){
        stick.setVisible(true);
      JFXDialogLayout content =  new JFXDialogLayout();
  content.setHeading(new Text(heading)); // Header of the Dialog
  content.setBody(new Text(body)); // Text in the dialog
  JFXDialog dialog = new JFXDialog(stick, content,JFXDialog.DialogTransition.CENTER);
  JFXButton btn = new JFXButton("Close"); // Button to close Dialog

  btn.setOnAction(new EventHandler <ActionEvent>(){
      @Override
      public void handle(ActionEvent event) {
          dialog.close();
          stick.setVisible(false);
      }
      
  });
  content.setActions(btn);
     
     dialog.show();  
   } 
    
    
    }    
    

