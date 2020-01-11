/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fetch;


import Processloan.ProcessloanController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import loanmanagementapp.Javaconnect;


/**
 * FXML Controller class
 *
 * @author Way4ward
 */
public class FetchController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private JFXButton close;
  
    @FXML
    private StackPane stick;
    @FXML
    private TableView<LoadFetch> table;
    
Connection conn;
PreparedStatement pstmt;
ResultSet rs;
    /**
     * Initializes the controller class.
     */

private final ObservableList<LoadFetch> data = FXCollections.observableArrayList();
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
    @FXML
    private Group anchorPane;
     private double xOffset;
     private double yOffset;
    @FXML
    private TableColumn<?, ?> job;
    @FXML
    private TableColumn<?, ?> salary;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = Javaconnect.ConnecrDB();
        stick.setVisible(false);
        borderPane.setVisible(false);
        moveAnchorPane();
    refreshtable1();
          setCellTable1();
          
          
          
          
           table.setOnMouseClicked(e->{       
LoadFetch load = (LoadFetch) table.getSelectionModel().getSelectedItem();   
String surname, phone, amount, job, salary;
surname = load.getName();
              phone = load.getNumber();
              amount = load.getAmount();
              job = load.getJob();
              salary = load.getSalary();
              System.out.println(surname+" "+phone+" "+amount+" "+job);
              
              
              
                 try{
         Stage primaryStage = new Stage();
          
                FXMLLoader Loader = new FXMLLoader();
                 Loader.setLocation(getClass().getResource("/Processloan/Processloan.fxml"));
                 Parent root = Loader.load();
                
               ProcessloanController drawer = (ProcessloanController)Loader.getController();
              drawer.GetSurname(surname);
              drawer.GetOccupation(job);
              drawer.GetAmount(amount);
              drawer.GetNumber(phone);
              drawer.GetLoanAmount(salary);
              
             Scene scene = new Scene(root);
              primaryStage.setScene(scene);
              primaryStage.initStyle(StageStyle.UNDECORATED);
              primaryStage.centerOnScreen();
              primaryStage.show(); 
    
        }catch(IOException ie){
            
        }
             });
    }
    
    public void moveAnchorPane()
	{
		anchorPane.setOnMousePressed(event -> {
	            xOffset = ProcessloanController.getPrimaryStage().getX() - event.getScreenX();
	            yOffset = ProcessloanController.getPrimaryStage().getY() - event.getScreenY();
	            anchorPane.setCursor(Cursor.CLOSED_HAND);
	        });

		anchorPane.setOnMouseDragged(event -> {
			 ProcessloanController.getPrimaryStage().setX(event.getScreenX() + xOffset);
			 ProcessloanController.getPrimaryStage().setY(event.getScreenY() + yOffset);

	        });
		
		anchorPane.setOnMouseReleased(event -> {
			anchorPane.setCursor(Cursor.DEFAULT);
	        });
	}
   
 public void refreshtable1(){   
    data.clear();
     try {
        pstmt = conn.prepareStatement("SELECT * FROM request");
      //  pstmt.setString(1, surname.getText());
        rs = pstmt.executeQuery();
        while(rs.next()){
           data.add(new LoadFetch(rs.getInt(1),rs.getString(16),rs.getString(7),rs.getString(18) ,rs.getString(12)));
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
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        job.setCellValueFactory(new PropertyValueFactory<>("job"));
           salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        
       }
    @FXML
    private void closeCustomer(ActionEvent event) {
    Stage stage = (Stage) close.getScene().getWindow();
    stage.close();
    }

    @FXML
    private void fetch(ActionEvent event) {
        if(borderPane.isVisible() == true){
            borderPane.setVisible(false);
        }else{
          borderPane.setVisible(true);
    }
    }
    
}
