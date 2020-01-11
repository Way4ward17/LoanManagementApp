/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Phonenumber;


import ViewJob.*;
import Viewfamily.*;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import loanmanagementapp.Javaconnect;


/**
 * FXML Controller class
 *
 * @author Way4ward
 */
public class PhoneController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private JFXButton close;
  
    @FXML
    private StackPane stick;
    @FXML
    private TableView<LoadPhone> table;
    
Connection conn;
PreparedStatement pstmt;
ResultSet rs;
    /**
     * Initializes the controller class.
     */

private ObservableList<LoadPhone> data = FXCollections.observableArrayList() ;
    private Text nameholder;
    @FXML
    private Text surname;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> number;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private BorderPane borderPane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stick.setVisible(false);
    

    }
    
     public void GetSurname(String name){
conn = Javaconnect.ConnecrDB();
surname.setText(name);
 refreshtable1();
 setCellTable1();
      
    } 
 public void refreshtable1(){   
    data.clear();
     try {
        pstmt = conn.prepareStatement("SELECT * FROM request where phone = ?");
        pstmt.setString(1, surname.getText());
        rs = pstmt.executeQuery();
        while(rs.next()){
           data.add(new LoadPhone(rs.getInt(1),rs.getString(3),rs.getString(7) ,rs.getString(17)));
        }
    }catch (SQLException ex) {
        Logger.getLogger(PhoneController.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
            try{
               pstmt.close();
               rs.close();
            //   conn.close();
            }catch(Exception g){
                
            }}
     table.setItems(data);
    }  
  
       private void setCellTable1(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
       }
    @FXML
    private void closeCustomer(ActionEvent event) {
    Stage stage = (Stage) close.getScene().getWindow();
    stage.close();
    }   

    
}
