/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processloan;

import Request.RequestController;
import ViewJob.JobController;
import Viewfamily.FamilyController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import loanmanagementapp.Javaconnect;

/**
 * FXML Controller class
 *
 * @author Way4ward
 */
public class ProcessloanController implements Initializable {

    @FXML
    private Text surname;
    @FXML
    private Text number;
    @FXML
    private Text amount;
    @FXML
    private Text occupation;
    @FXML
    private TextField yearToPay;
    @FXML
    private Text output;
Connection conn;
PreparedStatement pstmt;
ResultSet rs;
    @FXML
    private Text warningNumber;
    @FXML
    private Text outStanding;
    @FXML
    private Text developerOL;
    @FXML
    private Text loanStatus;
    @FXML
    private Text a;
    @FXML
    private Text b;
     private double xOffset;
	private double yOffset;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXButton closeButton;
 
         private static Stage primaryStageObj;
    @FXML
    private WebView webView;
    @FXML
    private Text amount1;
    @FXML
    private Text surname1;
    @FXML
    private Text number1;
    @FXML
    private Text occupation1;
    @FXML
    private Text amount2;
    @FXML
    private Text amount11;
    @FXML
    private Label salaryStatus;
    @FXML
    private Label differences;
    @FXML
    private Label convertToYears;
    @FXML
    private JFXDatePicker endDate;
    @FXML
    private StackPane stick;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        conn = Javaconnect.ConnecrDB();
      getPhonenumber();
       getJob();
           getSurname();
        moveAnchorPane();
        stick.setVisible(false);
    }    
      public static Stage getPrimaryStage() {
	        return primaryStageObj;
	  }
     public void GetSurname(String name){
        surname.setText(name);
           getSurname();
    } 
      public void GetOccupation(String name){
        occupation.setText(name);
          getJob();
    } 
       public void GetAmount(String name){
        amount.setText(name);
    }  
       public void GetNumber(String name){
        number.setText(name);
        getPhonenumber();
    } 
    public void GetLoanAmount(String name){
        amount1.setText(name);
    } 
    
public void moveAnchorPane()
	{
		anchorPane.setOnMousePressed(event -> {
	            xOffset = RequestController.getPrimaryStage().getX() - event.getScreenX();
	            yOffset = RequestController.getPrimaryStage().getY() - event.getScreenY();
	            anchorPane.setCursor(Cursor.CLOSED_HAND);
	        });

		anchorPane.setOnMouseDragged(event -> {
			 RequestController.getPrimaryStage().setX(event.getScreenX() + xOffset);
			 RequestController.getPrimaryStage().setY(event.getScreenY() + yOffset);

	        });
		
		anchorPane.setOnMouseReleased(event -> {
			anchorPane.setCursor(Cursor.DEFAULT);
	        });
	}
        
private void getJob(){
String count;
        try{
            String sql = "Select COUNT(occupation) from request where occupation = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, occupation.getText());
            rs = pstmt.executeQuery();
            if(rs.next()){
               count = rs.getString("COUNT(occupation)");
               developerOL.setText(count+" of "+occupation.getText()+" have an outstanding debt");
            }else{           
              developerOL.setText("No "+occupation.getText()+" has an outstanding loan");
            }            
        }catch(Exception e){
            
        }
    }
         
private void getPhonenumber(){
            String count;
        try{
            String sql = "Select COUNT(phone) from request where phone = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, number.getText());
            rs = pstmt.executeQuery();
            if(rs.next()){
                count = rs.getString("COUNT(phone)");
                warningNumber.setText(number.getText());
              a.setText("Phone number");
              b.setText("has been used to make a loan before "+count+" times");
            }else{
              a.setText("");
              b.setText("");
              warningNumber.setText("");
            }
            
        }catch(Exception e){
            
        }
    }
    
    private void getSurname(){
        outStanding.setText("No "+surname.getText()+" Famlily has an outstanding loan");
        String count;
        try{
            String sql = "Select COUNT(SURNAME) from request where surname = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, surname.getText());
            rs = pstmt.executeQuery();
            if(rs.next()){
                count = rs.getString("COUNT(SURNAME)");
                outStanding.setText(count+" of "+surname.getText()+" family have an outstanding debt");
            }else{
                outStanding.setText("No "+surname.getText()+" Famlily has an outstanding loan");
            }
            
        }catch(Exception e){
            
        }
    }

    @FXML
    private void onKeyReleasedPayment(KeyEvent event) {
        if(yearToPay.getText().isEmpty() || yearToPay.getText().isEmpty()){
        salaryStatus.setText("");
        differences.setText("");
            
        }else{
        int salary = Integer.parseInt(amount.getText());
        int amt = Integer.parseInt(amount1.getText());
        int year = Integer.parseInt(yearToPay.getText());
        double total;
        double forty = 40.0/100.0  * salary;
        System.out.println(forty);
        total = amt/year;
        double diff = forty - total;
        String totalHolder = String.format("%.0f", total);
        output.setText(totalHolder);
        int month = year/12;
        int month2 = year % 12;
        convertToYears.setText(month+" year and "+ month2+" months");
        if (salary > total){
            if (forty > total){
            salaryStatus.setText("");
            salaryStatus.setText(surname.getText()+" is fit for loan");
            // salaryStatus.setStyle("-fx-background-color:green");
            }else
            
            if (forty < total){
            salaryStatus.setText("");
            salaryStatus.setText(surname.getText()+" is not fit for loan");  
           // salaryStatus.setStyle("-fx-background-color:green");
          
            differences.setText("Your balance after 40% calculation is: "+diff+"");
            }
        }else{
            salaryStatus.setText("RISKY TO LOAN AMOUNT");
         //   salaryStatus.setStyle("-fx-font-color:red");
        }
       
        }
        
    }

    @FXML
    private void close(ActionEvent event) {
         outStanding.setText("No Famlily has an outstanding loan");
            a.setText("");
              b.setText("");
              warningNumber.setText("");
              Stage stage = (Stage) closeButton.getScene().getWindow();
              stage.close();
              
    }

    @FXML
    private void viewFamily(ActionEvent event) {
           try{
         Stage primaryStage = new Stage();
          primaryStageObj = primaryStage;
                FXMLLoader Loader = new FXMLLoader();
                 Loader.setLocation(getClass().getResource("/Viewfamily/Family.fxml"));
                 Parent root = Loader.load();
                
               FamilyController drawer = (FamilyController)Loader.getController();
              drawer.GetSurname(surname.getText());
            
              
            Scene scene = new Scene(root);
             scene.setFill(new Color(0, 0, 0, 0));
             primaryStageObj.setScene(scene);
            primaryStageObj.initModality(Modality.APPLICATION_MODAL);
            primaryStageObj.initStyle(StageStyle.TRANSPARENT);
             primaryStageObj.centerOnScreen();
             primaryStageObj.show(); 
    
        }catch(IOException e){
            
        }
    }

    @FXML
    private void viewDeveloper(ActionEvent event) {
         try{
         Stage primaryStage = new Stage();
          primaryStageObj = primaryStage;
                 FXMLLoader Loader = new FXMLLoader();
                 Loader.setLocation(getClass().getResource("/ViewJob/Job.fxml"));
                 Parent root = Loader.load();
                
                 JobController drawer = (JobController)Loader.getController();
                 drawer.GetSurname(occupation.getText());
            
              
            Scene scene = new Scene(root);
            scene.setFill(new Color(0, 0, 0, 0));
            primaryStageObj.setScene(scene);
            primaryStageObj.initModality(Modality.APPLICATION_MODAL);
            primaryStageObj.initStyle(StageStyle.TRANSPARENT);
            primaryStageObj.centerOnScreen();
            primaryStageObj.show(); 
    
        }catch(IOException e){
            
        }
    }

    @FXML
    private void grantAction(ActionEvent event) {
        System.out.println(number.getText());
        sendSMS(number.getText().toString(),"Dear "+surname.getText()+", This is to inform you that your loan request at Al-Hayat has been granted.");
        postDate();
    }
    
    private void postDate(){
       String status = "Granted";
       String sql = "UPDATE REQUEST SET duedate ='"+((TextField) endDate.getEditor()).getText()+"',status ='"+status+"',perpay ='"+output.getText()+"', acctno ='"+acctNum()+"' WHERE surname ='"+surname.getText()+"' AND phone ='"+number.getText()+"' AND occupation ='"+occupation.getText()+"' ";
       try{
       pstmt = conn.prepareStatement(sql);
       pstmt.executeUpdate();
       
       stick.setVisible(true);
          JFXDialogLayout content =  new JFXDialogLayout();
  content.setHeading(new Text("Success")); // Header of the Dialog
  content.setBody(new Text("REQUEST SUBMITTED  \n Please Wait Patiently for Approval")); // Text in the dialog
  JFXDialog dialog = new JFXDialog(stick, content,JFXDialog.DialogTransition.CENTER);
  JFXButton btn = new JFXButton("Close"); // Button to close Dialog

  btn.setOnAction((ActionEvent event1) -> {
      dialog.close();
      stick.setVisible(false);
  
            });
  content.setActions(btn);
     
     dialog.show();
       
       }catch(Exception e){
           System.out.println(e);
       }
    }
    
    private int acctNum(){
Random rand = new Random();
int num = rand.nextInt(900000000) + 1000000000;
return num;
    }
   
    private void sendSMS(String rec, String msg){
   WebEngine engine = webView.getEngine();    
   String url = ("http://www.smsmobile24.com/index.php?option=com_spc&comm=spc_api&username"
           + "=Bursary_Alert&password=bursaryalert23&sender=Al-Hayat Foundation&recipient='"+rec+"'&message='"+msg+"'&");
                engine.load(url);
                engine.getLoadWorker().stateProperty().addListener((observable,oldValue,newValue) ->{
      if(Worker.State.SUCCEEDED.equals(newValue)){
     
   
      }
           });
    }
    @FXML
    private void declineAction(ActionEvent event) {
   
    }

    @FXML
    private void fetchAll(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Fetch/Fetch.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(new Color(0, 0, 0, 0));
        Stage stage = new Stage();
          primaryStageObj = stage;
        primaryStageObj.setScene(scene);
        primaryStageObj.initStyle(StageStyle.UNDECORATED);
        primaryStageObj.initModality(Modality.APPLICATION_MODAL);
        primaryStageObj.initStyle(StageStyle.TRANSPARENT);
        primaryStageObj.show();
    }

    @FXML
    private void viewPhoneNumber(ActionEvent event) {
          try{
         Stage primaryStage = new Stage();
          primaryStageObj = primaryStage;
                 FXMLLoader Loader = new FXMLLoader();
                 Loader.setLocation(getClass().getResource("/Phonenumber/Phone.fxml"));
                 Parent root = Loader.load();
                 JobController drawer = (JobController)Loader.getController();
                 drawer.GetSurname(number.getText());
            Scene scene = new Scene(root);
            scene.setFill(new Color(0, 0, 0, 0));
            primaryStageObj.setScene(scene);
            primaryStageObj.initModality(Modality.APPLICATION_MODAL);
            primaryStageObj.initStyle(StageStyle.TRANSPARENT);
            primaryStageObj.centerOnScreen();
            primaryStageObj.show(); 
    
        }catch(IOException e){
            
        }
    }


   
}

