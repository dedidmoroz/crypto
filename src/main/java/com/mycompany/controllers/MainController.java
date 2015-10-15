/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controllers;

import com.mycompany.ciphers.CipherServices;
import com.mycompany.languages.LANG;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;

import java.nio.file.StandardOpenOption;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
 

/**
 *
 * @author scarlet_skull
 */
public class MainController implements Initializable{
private CipherServices services;
    
    private Stage primaryStage;
    private FileChooser chooser = new FileChooser();
    
    /**
     *
     * @param serv
     * @param primaryStage
     */
    public MainController(CipherServices serv,Stage primaryStage) {
        this.services = serv;
        this.primaryStage = primaryStage;
        chooser.getExtensionFilters().addAll(FXCollections.observableArrayList(
                new FileChooser.ExtensionFilter("Text/Plain", "*.txt")
        ));
    }

    /**
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    
    
    @FXML
    private MenuItem btnClose;

    @FXML
    private MenuItem btnLoad;

    @FXML
    private MenuItem btnSave;

    @FXML
    private RadioButton rbUkr;

    @FXML
    private ToggleGroup languages;

    @FXML
    private RadioButton rbEng;

    @FXML
    private RadioButton rbRus;

    @FXML
    private TextArea taText;

    @FXML
    private TextArea taResText;
    
    @FXML
    private TextField txOffset;
    
    @FXML
    private Button btnEnc;

    @FXML
    private Button btnDec;

    @FXML
    private Button btnHack;
   
    @FXML
    private TextField txKVal;
    @FXML
    private TextField txAVal;
    
    @FXML
    private RadioMenuItem rbAthenaMode;
    @FXML
    private RadioMenuItem rbCeaserMode;
    @FXML
    private RadioMenuItem rbBSSMode;
    
    @FXML
    private Label labOffset;
    @FXML
    private Label labK;
    @FXML
    private Label labA;
    
    
    
    @FXML
    private ToggleGroup modes;
    /**
     *<p>Event for menu item Save to file</p>
     *<p>Save to file result text which you crypt</p>
     *  
     * @param event
     * @throws IOException 
     */
    @FXML
    void loadFromFile(ActionEvent event) throws IOException {
        taText.setText(Files.readAllLines(chooser.showOpenDialog(primaryStage).toPath(),Charset.defaultCharset()).stream().parallel().reduce("", (acc,a)->acc+a,(a1,a2)->a1+a2));
    }
   /**
     * <p>Event for menu item Load from file</p>
     * <p>Load from file text which you want to crypt</p>
     * @param event
     * @throws IOException 
     */
    @FXML
    void saveToFile(ActionEvent event) throws IOException {
        StandardOpenOption [] options = {StandardOpenOption.APPEND,StandardOpenOption.WRITE,StandardOpenOption.CREATE};
        Files.write(this.chooser.showSaveDialog(primaryStage).toPath(), taResText.getText().getBytes(),options);
    }
    
    /**
     * <p>Event for button Decipher</p>
     * <p>Take encipher text and then try to decrypt it</p>
     * @param event 
     */
    @FXML
    void makeDecipher(ActionEvent event) {
        switch((String)modes.getSelectedToggle().getUserData()){
            case "athena": taResText.setText(this.services.getAphineEncipher().decipher( taText.getText().toLowerCase().replaceAll(" ",""),  (LANG) languages.getSelectedToggle().getUserData(), Integer.valueOf(txAVal.getText()),Integer.valueOf(txKVal.getText())));break;
            case "ceaser": taResText.setText(this.services.getCeaserEncipher().decipher(taText.getText().toLowerCase().replaceAll(" ",""), Integer.valueOf(this.txOffset.getText())*(-1), (LANG) languages.getSelectedToggle().getUserData()));break;
        }
    }
    /**
     * <p>Event for button Encipher</p>
     * <p>Take text and options, then crypt it</p>
     * @param event 
     */
    @FXML
    void makeEncipher(ActionEvent event) {
        switch((String)modes.getSelectedToggle().getUserData()){
            case "athena": taResText.setText(this.services.getAphineEncipher().encipher( taText.getText().toLowerCase().replaceAll(" ",""), (LANG) languages.getSelectedToggle().getUserData(), Integer.valueOf(txAVal.getText()),Integer.valueOf(txKVal.getText())));break;
            case "ceaser": taResText.setText(this.services.getCeaserEncipher().encipher(taText.getText().toLowerCase().replaceAll(" ", ""), Integer.valueOf(this.txOffset.getText()), (LANG) languages.getSelectedToggle().getUserData()));break;
            case "bbs": taResText.setText(this.services.getBbsEncipher().encipher( taText.getText().toLowerCase().replaceAll(" ",""), (LANG) languages.getSelectedToggle().getUserData(), Integer.valueOf(txAVal.getText()),Integer.valueOf(txKVal.getText())));break;
            
        }
 
    }
    /**
     * <p>Event for button Hacking</p>
     * <p>Take text and try to hack it</p>
     * @param event 
     */
    
    @FXML
    void makeHacking(ActionEvent event) {
        
        switch((String)modes.getSelectedToggle().getUserData()){
            case "athena":taResText.setText(this.services.getAphineEncipher().hack(taText.getText().toLowerCase().replaceAll(" ",""), (LANG) languages.getSelectedToggle().getUserData()));break;
            case "ceaser":taResText.setText(this.services.getCeaserEncipher().hack( taText.getText().toLowerCase().replaceAll(" ",""), (LANG) languages.getSelectedToggle().getUserData()));break;
        }
    }
    

    
    /**
     * <p>Initialize whole application,bind properties for dynamic responce</p>
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSave.disableProperty().bind(taResText.lengthProperty().lessThan(1));
        btnLoad.disableProperty().bind(taText.lengthProperty().greaterThan(1));
       
        btnEnc.disableProperty().bind(
        			(taText.lengthProperty().lessThan(1).or(txOffset.lengthProperty().lessThan(1))
        		.and(rbAthenaMode.selectedProperty().not()
        				.or(txKVal.lengthProperty().lessThan(1))
        				.or(taText.lengthProperty().lessThan(1))
        				.or(txAVal.lengthProperty().lessThan(1)))
        		.and(rbBSSMode.selectedProperty().not()
               			.or(txKVal.lengthProperty().lessThan(1))
          				.or(taText.lengthProperty().lessThan(1))
           				.or(txAVal.lengthProperty().lessThan(1)))));
        btnDec.disableProperty().bind(
        			(taText.lengthProperty().lessThan(1).or(txOffset.lengthProperty().lessThan(1))
        		.and(rbAthenaMode.selectedProperty().not()
        				.or(txKVal.lengthProperty().lessThan(1))
        				.or(taText.lengthProperty().lessThan(1))
        				.or(txAVal.lengthProperty().lessThan(1)))
        		.and(rbBSSMode.selectedProperty().not()
           				.or(txKVal.lengthProperty().lessThan(1))
           				.or(taText.lengthProperty().lessThan(1))
           				.or(txAVal.lengthProperty().lessThan(1)))));
       
        btnHack.disableProperty().bind(taText.lengthProperty().lessThan(1));
        
        
        rbEng.setUserData(LANG.Eng);
        rbRus.setUserData(LANG.Ru);
        rbUkr.setUserData(LANG.Ua);
        btnClose.addEventHandler(ActionEvent.ANY, (e)->{System.exit(0);});
        
        txOffset.visibleProperty().bind(rbCeaserMode.selectedProperty());
        labOffset.visibleProperty().bind(rbCeaserMode.selectedProperty());
        
        txAVal.visibleProperty().bind(rbAthenaMode.selectedProperty().or(rbBSSMode.selectedProperty()));
        txKVal.visibleProperty().bind(rbAthenaMode.selectedProperty().or(rbBSSMode.selectedProperty()));
    
        labA.visibleProperty().bind(rbAthenaMode.selectedProperty().or(rbBSSMode.selectedProperty()));
        labK.visibleProperty().bind(rbAthenaMode.selectedProperty().or(rbBSSMode.selectedProperty()));
        
        rbAthenaMode.setUserData("athena");
        rbCeaserMode.setUserData("ceaser");
        rbBSSMode.setUserData("bbs");
        taText.setOnDragEntered(e-> {taText.setStyle("-fx-background-color:red;");});
        taText.setOnDragExited(e-> {taText.setStyle("-fx-background-color:teal;");});
        taText.setOnDragOver(e->{ e.acceptTransferModes(TransferMode.ANY); });
        taText.setOnDragDropped(event-> {
        	Dragboard board = event.getDragboard();
        	
        	if(board.hasFiles()){
        		try {
        			taText.setText(Files.readAllLines(board.getFiles().get(0).toPath(),Charset.defaultCharset()).stream().parallel().reduce("", (acc,a)->acc+a,(a1,a2)->a1+a2).replaceAll(" *", ""));
        		} catch (Exception e1) {
        			// TODO Auto-generated catch block 
        			e1.printStackTrace();}
        	} else {
        		JOptionPane.showMessageDialog(null, "Problem with loaded file.");
        	}});
    }
    
}
