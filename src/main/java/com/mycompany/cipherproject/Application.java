/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cipherproject;

import com.mycompany.ciphers.CipherServices;
import com.mycompany.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 *
 * @author scarlet_skull
 */
public class Application extends javafx.application.Application{

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        
        
        
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("/scenes/main.fxml"));
        loader.setControllerFactory(e -> {return new MainController(container.instance().select(CipherServices.class).get(),stage);});
    
        Scene scene = new Scene((Parent) loader.load());
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toString());
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/styles/icon.png")));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Enchipher");
        stage.setOnCloseRequest(e->{System.exit(0);});
        stage.show();
    }
    
}
