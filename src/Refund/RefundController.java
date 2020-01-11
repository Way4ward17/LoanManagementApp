/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Refund;

import Fetch.LoadFetch;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import loanmanagementapp.Javaconnect;

/**
 * FXML Controller class
 *
 * @author way4ward
 */


public class RefundController implements Initializable {

Connection conn;
ResultSet rs;
PreparedStatement pstmt;
    @FXML
    private TextField searchid;
    @FXML
    private Label fullname;
    @FXML
    private Label loanamount;
    @FXML
    private Label phonenumber;
    @FXML
    private Label lastpaydate;
    @FXML
    private Label balance;
    @FXML
    private Label perpay;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> amount;
    @FXML
    private TableColumn<?, ?> depositor;

    /**
     * Initializes the controller class.
     */
    private final ObservableList<LoadRefund> data = FXCollections.observableArrayList();
    @FXML
    private TableView<LoadRefund> table;
    @FXML
    private TextField depositorName;
    @FXML
    private TextField Amount;
    @FXML
    private Label acctNo;
    @FXML
    private JFXDatePicker dateDeposit;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    conn = Javaconnect.ConnecrDB();

    }    
    
    
    
    private void fetch(){
        refreshtable1();
        setCellTable1();
    }
    
    private void download(){
            conn = Javaconnect.ConnecrDB();
        try{
            String sql = "select * from request where acctno = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, searchid.getText());
            rs = pstmt.executeQuery();
            while(rs.next()){
             //   
                acctNo.setText(rs.getString("acctno"));
                fullname.setText(rs.getString("fullname"));
                loanamount.setText(rs.getString("loanamount"));
                phonenumber.setText(rs.getString("phone"));
                lastpaydate.setText(rs.getString("lastpaymentdate"));
                balance.setText(rs.getString("balance"));
                perpay.setText(rs.getString("perpay"));
            }
       
        }catch(Exception e){
            System.out.println(e);
        }
    }
     public void refreshtable1(){   
     data.clear();
     try {
        pstmt = conn.prepareStatement("SELECT * FROM refund where accountno = ?");
       pstmt.setString(1, acctNo.getText());
        rs = pstmt.executeQuery();
        while(rs.next()){
           data.add(new LoadRefund(rs.getInt(1),rs.getString(9),rs.getString(4),rs.getString(8)));
        }
    }catch (SQLException ex) {
        Logger.getLogger(RefundController.class.getName()).log(Level.SEVERE, null, ex);
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
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("name"));
        amount.setCellValueFactory(new PropertyValueFactory<>("number"));
        depositor.setCellValueFactory(new PropertyValueFactory<>("date"));
      
        
       }
    
       private void getBalance(){
           String sql = "Select balance from refund where accountno = ?";
           try{
               
           }catch(Exception e){
             //  System.out
           }
       }
    
    private void insert(){
    String sql = "INSERT INTO `refund`(`accountno`, `fullname`, `amount`, `phone`, `balance`, `perpay`, `depositor`, date) values (?,?,?,?,?,?,?,?)";
    try{
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,acctNo.getText());
        pstmt.setString(2,fullname.getText());
        pstmt.setString(3,Amount.getText());
        pstmt.setString(4,phonenumber.getText());  
        pstmt.setString(5,balance.getText());
        pstmt.setString(6,perpay.getText());
        pstmt.setString(7,depositorName.getText());
     
       pstmt.setString(8,  ((TextField) dateDeposit.getEditor()).getText());
        pstmt.execute();
        System.out.println("Saved");
    }catch(Exception e){
        System.out.println(e);
    }
    
    }

    @FXML
    private void depositAction(ActionEvent event) {
        insert();
    }


    @FXML
    private void clearTable(ActionEvent event) {
    }

    @FXML
    private void searchbutton(ActionEvent event) {
        download();
        fetch();
    }
}
