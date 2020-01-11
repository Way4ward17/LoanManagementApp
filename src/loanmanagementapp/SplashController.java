/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loanmanagementapp;

import com.jfoenix.controls.JFXDecorator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author way4ward
 */
public class SplashController implements Initializable {
    
  
  
      @FXML
    private AnchorPane splashAnchorPanee;
   
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         new loading().start();
    }    
     class loading extends Thread{
        public void run(){
                try {
                    Thread.sleep(3000);
                               Platform.runLater(new Runnable(){    
                @Override
                        public void run(){
                              FadeTransition fadeout=new FadeTransition(Duration.seconds(4), splashAnchorPanee);
                            fadeout.setFromValue(1);
                        fadeout.setToValue(0);
                        fadeout.setCycleCount(1);
                        fadeout.play();
                        
                        fadeout.setOnFinished(e ->{ 
                  Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/Signin/Signin.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
                }
        Stage stage = new Stage();
         JFXDecorator decorator=new JFXDecorator(stage, root, false, false, true);
        decorator.setCustomMaximize(false);
       decorator.setBorder(Border.EMPTY);
       
        Scene scene = new Scene(decorator);
       
        stage.setScene(scene);
        stage.show();
        splashAnchorPanee.getScene().getWindow().hide();
                
             });
                        }
                               });
            } catch (InterruptedException ex) {
                Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
}
}
}
}
