/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Request;


import Fetch.FetchController;
import Fetch.LoadFetch;
import Processloan.ProcessloanController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import loanmanagementapp.Javaconnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RequestController implements Initializable {
Connection conn;
PreparedStatement pstmt;
ResultSet rs;
    private AnchorPane imgLogin;
    @FXML
    private TextField fullname;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;
    @FXML
    private JFXButton submit;
    
    /**
     * Initializes the controller class.
     */
    private String gender1;
    @FXML
    ToggleGroup gender;
      String agreement;
    @FXML
    private StackPane stick;
    private TextField staffID;
    @FXML
    private JFXCheckBox agree;
    @FXML
    private ComboBox<String> title;
    @FXML
    private JFXDatePicker dateofbirth;
    @FXML
    private JFXRadioButton single;
    @FXML
    private JFXRadioButton divorce;
    @FXML
    private JFXRadioButton complicated;
    @FXML
    private JFXRadioButton married;
    @FXML
    private TextField email;
    @FXML
    private TextField phonenumber;
    @FXML
    private TextField address;
    @FXML
    private JFXRadioButton five;
    @FXML
    private JFXRadioButton ten;
    @FXML
    private JFXRadioButton twenty;
    @FXML
    private JFXRadioButton above;
    @FXML
    private TextField empname;
    @FXML
    private TextField empoccupation;
    @FXML
    private ComboBox<String> workyear;
    @FXML
    private TextField salary;
    private String status;
    private String living;
   private  String surname;
       ObservableList<String> yearr = FXCollections.observableArrayList("Less The 1 Year","Less than 3 years","Less than 5 years"," 5 years  above");
      ObservableList<String> titlee = FXCollections.observableArrayList("Mr","Miss","Mrs","Miss");
    @FXML
    private ToggleGroup sta;
    @FXML
    private ToggleGroup live;
     private double xOffset;
	private double yOffset;
         private static Stage primaryStageObj;
         private final ObservableList<LoadRequestTable> data = FXCollections.observableArrayList();
    @FXML
    private TableView<LoadRequestTable> table;
    @FXML
    private TableColumn<?, ?> idTable;
    @FXML
    private TableColumn<?, ?> surnameTable;
    @FXML
    private TableColumn<?, ?> phonenumberTable;
    @FXML
    private TableColumn<?, ?> statusTable;
    @FXML
    private TableColumn<?, ?> balanceTable;
    @FXML
    private TableColumn<?, ?> dueTable;
    @FXML
    private TextField searchfield;
    @FXML
    private TextField loanamount;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        title.setItems(titlee);
       workyear.setItems(yearr);
        stick.setVisible(false);
        conn = Javaconnect.ConnecrDB();
        setCellTable1();
        refreshtable1();
                
          ScaleTransition transition = new ScaleTransition(Duration.seconds(5), imgLogin);
        transition.setToX(2);
        transition.setToY(2);
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();
    }    
     public static Stage getPrimaryStage() {
	        return primaryStageObj;
	  }
    
     
     
      public void refreshtable1(){   
    data.clear();
     try {
        pstmt = conn.prepareStatement("SELECT * FROM request");
      //  pstmt.setString(1, surname.getText());
        rs = pstmt.executeQuery();
        while(rs.next()){
           data.add(new LoadRequestTable(rs.getInt(1),rs.getString(3),rs.getString(7),rs.getString(20) ,rs.getString(21),rs.getString(19)));
        }
    }catch (SQLException ex) {
        Logger.getLogger(FetchController.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
            try{
               pstmt.close();
               rs.close();
            //   conn.close();
            }catch(SQLException g){
                
            }}
     table.setItems(data);
    }  
  
       private void setCellTable1(){
        idTable.setCellValueFactory(new PropertyValueFactory<>("id"));
        surnameTable.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        phonenumberTable.setCellValueFactory(new PropertyValueFactory<>("number"));
        statusTable.setCellValueFactory(new PropertyValueFactory<>("status"));
        balanceTable.setCellValueFactory(new PropertyValueFactory<>("balance"));
           dueTable.setCellValueFactory(new PropertyValueFactory<>("duedate"));
        
       }
     
     
    private void RequestLoan(){
      String sqll = "Select STAFFID FROM REQUEST WHERE STAFFID =? ";
      try{
          pstmt = conn.prepareStatement(sqll);
          pstmt.setString(1, staffID.getText());
          rs = pstmt.executeQuery();
          if(rs.next()){
              stick.setVisible(true);
      JFXDialogLayout content =  new JFXDialogLayout();
  content.setHeading(new Text("Error")); // Header of the Dialog
  content.setBody(new Text("You have an OutStanding Load")); // Text in the dialog
  JFXDialog dialog = new JFXDialog(stick, content,JFXDialog.DialogTransition.CENTER);
  JFXButton btn = new JFXButton("Close"); // Button to close Dialog

  btn.setOnAction((ActionEvent event) -> {
      dialog.close();
      stick.setVisible(false);
              });
  content.setActions(btn);
     
     dialog.show();
          }else{
              
          }
      }catch(SQLException e){
          JOptionPane.showMessageDialog(null , e);
      }
      
 }

    private String getFirstName(){
   String surnamE = "";
        String getFullname = fullname.getText().trim();
   //showtopic.setText(getFullname);
    String[] arr = getFullname.split(" ");
    for (String arr1 : arr) {
        surnamE = arr[0];
    }
   return surnamE;
    }
    
    
    
    @FXML
    private void submitAction(ActionEvent event) {
    save();
     // moveToProcess();
    }
    
    private void save(){
         try{
            String sql = "INSERT INTO `request`( `title`, fullname,`dob`, `maritalstatus`, "
                    + "`email`, `phone`, `address`, `livingyears`, `gender`, `employername`, `occupation`, `year`, `salary`, `agree`, surname,loanamount) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1 , title.getSelectionModel().getSelectedItem());
            pstmt.setString(2, fullname.getText());
            pstmt.setString(3 , ((TextField) dateofbirth.getEditor()).getText());
           if (married.isSelected()){
                status = married.getText();
            }
            if(single.isSelected()){
                status  = single.getText();
            }
            if (complicated.isSelected()){
                status = complicated.getText();
            }
            if(divorce.isSelected()){
                status  = divorce.getText();
            }
             pstmt.setString(4, status);
            pstmt.setString(5, email.getText());
            pstmt.setString(6, phonenumber.getText());
           pstmt.setString(7, address.getText());
           
            if (five.isSelected()){
                living = five.getText();
            }
            if(ten.isSelected()){
                living  = ten.getText();
            }
              if (twenty.isSelected()){
                living = twenty.getText();
            }
            if(above.isSelected()){
                living  = above.getText();
            }
          pstmt.setString(8, living);
         
             if (male.isSelected()){
                gender1 = male.getText();
            }
            if(female.isSelected()){
                gender1  = female.getText();
            }
          
          pstmt.setString(9, gender1);
          pstmt.setString(10, empname.getText());
          pstmt.setString(11, empoccupation.getText());
          pstmt.setString(12, workyear.getSelectionModel().getSelectedItem());
          pstmt.setString(13, salary.getText());
        
          if(agree.isSelected()){
              agreement = agree.getText();
          }
          pstmt.setString(14, agreement);
          pstmt.setString(15, getFirstName());
         
          pstmt.setString(16, loanamount.getText());
      
          pstmt.execute();
          
          
          stick.setVisible(true);
      JFXDialogLayout content =  new JFXDialogLayout();
  content.setHeading(new Text("Success")); // Header of the Dialog
  content.setBody(new Text("REQUEST SUBMITTED  \n Please Wait Patiently for Approval")); // Text in the dialog
  JFXDialog dialog = new JFXDialog(stick, content,JFXDialog.DialogTransition.CENTER);
  JFXButton btn = new JFXButton("Close"); // Button to close Dialog

  btn.setOnAction((ActionEvent event1) -> {
      dialog.close();
      stick.setVisible(false);
      moveToProcess();
            });
  content.setActions(btn);
     
     dialog.show();
        }catch(SQLException e){
            System.out.println(e);
        }
    
    
    }
    
    
    private void moveToProcess(){
        
        try{
         Stage primaryStage = new Stage();
          primaryStageObj = primaryStage;
                FXMLLoader Loader = new FXMLLoader();
                 Loader.setLocation(getClass().getResource("/Processloan/Processloan.fxml"));
                 Parent root = Loader.load();
                
               ProcessloanController drawer = (ProcessloanController)Loader.getController();
              drawer.GetSurname(getFirstName());
              drawer.GetOccupation(empoccupation.getText());
              drawer.GetAmount(salary.getText());
              drawer.GetNumber(phonenumber.getText());
             drawer.GetLoanAmount(loanamount.getText());
              
             Scene scene = new Scene(root);
              primaryStageObj.setScene(scene);
              primaryStageObj.initStyle(StageStyle.UNDECORATED);
              primaryStageObj.centerOnScreen();
              primaryStageObj.show(); 
    
        }catch(IOException e){
            
        }
        }
 }
 


    
    
 


 
    

