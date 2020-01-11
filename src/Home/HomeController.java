/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Home;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author way4ward
 */
public class HomeController implements Initializable {
  @FXML
    private AnchorPane pane;
    @FXML
    private JFXButton logoutApp;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try {
          // TODO
          this.createPage(pane, "/Request/Request.fxml");
      } catch (IOException ex) {
          Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
      }
        logoutApp.setOnAction(e->{
          try {
            Stage stage = new Stage();
               FXMLLoader Loader = new FXMLLoader();
                 Loader.setLocation(getClass().getResource("/Signin/Signin.fxml"));
                 Parent root = Loader.load();
               
                 JFXDecorator decorator=new JFXDecorator(stage, root, false, false, true);
        decorator.setCustomMaximize(false);
     
       decorator.setBorder(Border.EMPTY);
           

            
              Scene scene = new Scene(decorator);
             
              stage.setScene(scene);
              stage.setResizable(false);
              stage.centerOnScreen();
              stage.show(); 
              logoutApp.getScene().getWindow().hide();
         } catch (IOException ex) {
             Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
         }  
        });
    }    
    
     public void setNode(Node node){
        pane.getChildren().clear();
        pane.getChildren().add((Node)node);
     
        
        
        //FADE TRANSITION
       FadeTransition ft = new FadeTransition();
       ft.setDuration(Duration.seconds(1));
       ft.setNode(node);
       ft.setFromValue(0.1);
       ft.setToValue(1);
       ft.setCycleCount(1);
       ft.setAutoReverse(false);
       ft.play();
    }
    public void createPage(AnchorPane pane,String loc) throws IOException{
       pane = FXMLLoader.load(getClass().getResource(loc)); 
       setNode(pane);
    }
    @FXML
    private void Make(ActionEvent event) throws IOException{
         this.createPage(pane, "/Request/Request.fxml");
    }
    @FXML
    private void Refund(ActionEvent event) throws IOException{
      this.createPage(pane,"/Refund/Refund.fxml");
     
       
    }
    @FXML
    private void Admin(ActionEvent event) throws IOException{
         Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Admin/Admin.fxml"));
         JFXDecorator decorator=new JFXDecorator(stage, root, false, false, true);
        decorator.setCustomMaximize(false);
       decorator.setBorder(Border.EMPTY);
       
        Scene scene = new Scene(decorator);
       
        stage.setScene(scene);
        stage.show();
        
    }
   
 
}
