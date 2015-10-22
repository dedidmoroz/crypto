/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controllers;

import com.mycompany.ciphers.services.CipherServices;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jboss.logging.Logger;
 

/**
 *
 * @author scarlet_skull
 */
public class MainController implements Initializable{
private final CipherServices services;
    
    private final Stage primaryStage;
    private final FileChooser chooser = new FileChooser();
    
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
    private RadioMenuItem rbGammaMode;
    
    @FXML
    private Label labOffset;
    @FXML
    private Label labK;
    @FXML
    private Label labA;
    
     @FXML
    private TextField txK3;
    @FXML
    private TextField txK1;
    @FXML
    private TextField txK2;

    @FXML
    private Label labK3;
    @FXML
    private Label labK2;
    @FXML
    private Label labK1;
    
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
        taResText.clear();
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
            case "athena": taResText.setText(this.services.getAphineEncipher().decipher( taText.getText().toLowerCase().replaceAll("( *|,|.|!|\\:|_|-|[0-9])","").replaceAll(":", ""),  (LANG) languages.getSelectedToggle().getUserData(), Integer.valueOf(txAVal.getText()),Integer.valueOf(txKVal.getText())));break;
            case "ceaser": taResText.setText(this.services.getCeaserEncipher().decipher(taText.getText().toLowerCase().replaceAll("( *|,|.|!|\\:|_|-|[0-9])","").replaceAll(":", ""), Integer.valueOf(this.txOffset.getText())*(-1), (LANG) languages.getSelectedToggle().getUserData()));break;
            case "bbs": taResText.setText(this.services.getAphineEncipher().decipher( taText.getText().toLowerCase().replaceAll("( *|,|.|!|\\:|_|-|[0-9])","").replaceAll(":", ""),  (LANG) languages.getSelectedToggle().getUserData(), Integer.valueOf(txAVal.getText()),Integer.valueOf(txKVal.getText())));break;
            case "gamma": taResText.setText(this.services.getGammaCipher().decipher(taText.getText().toLowerCase().replaceAll("( *|,|.|!|\\:|_|-|[0-9])","").replaceAll(":", ""), (LANG) languages.getSelectedToggle().getUserData(),Integer.valueOf(this.txK1.getText()),Integer.valueOf(this.txK2.getText()),Integer.valueOf(this.txK3.getText())));
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
            case "athena": taResText.setText(this.services.getAphineEncipher().encipher( taText.getText().toLowerCase().replaceAll("( *|,|.|!|\\:|_|-|[0-9])","").replaceAll(":", ""), (LANG) languages.getSelectedToggle().getUserData(), Integer.valueOf(txAVal.getText()),Integer.valueOf(txKVal.getText())));break;
            case "ceaser": taResText.setText(this.services.getCeaserEncipher().encipher(taText.getText().toLowerCase().replaceAll("( *|,|.|!|\\:|_|-|[0-9])", "").replaceAll(":", ""), Integer.valueOf(this.txOffset.getText()), (LANG) languages.getSelectedToggle().getUserData()));break;
            case "bbs": taResText.setText(this.services.getBbsEncipher().encipher( taText.getText().toLowerCase().replaceAll("( *|,|.|!|\\:|_|-|[0-9])","").replaceAll(":", ""), (LANG) languages.getSelectedToggle().getUserData(), Integer.valueOf(txAVal.getText()),Integer.valueOf(txKVal.getText())));break;
            case "gamma": taResText.setText(this.services.getGammaCipher().encipher(taText.getText().toLowerCase().replaceAll("( *|,|.|!|\\:|_|-|[0-9])","").replaceAll(":", ""), (LANG) languages.getSelectedToggle().getUserData(),Integer.valueOf(this.txK1.getText()),Integer.valueOf(this.txK2.getText()),Integer.valueOf(this.txK3.getText())));
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
            case "athena":taResText.setText(this.services.getAphineEncipher().hack(taText.getText().toLowerCase().replaceAll("( *|,|.|!|\\:|_|-|[0-9])","").replaceAll(":", ""), (LANG) languages.getSelectedToggle().getUserData()));break;
            case "ceaser":taResText.setText(this.services.getCeaserEncipher().hack( taText.getText().toLowerCase().replaceAll("( *|,|.|!|\\:|_|-|[0-9])","").replaceAll(":", ""), (LANG) languages.getSelectedToggle().getUserData()));break;
        }
    }
    
    /**
     * <p>Open About modal</p>
     * <p>Show information about program,manufacturer, developers</p>
     * @param event 
     * @deprecated Dialogs
     */
    @FXML
    void showInfo(ActionEvent event) {
        Alert aboutWindow = new Alert(Alert.AlertType.WARNING);
        aboutWindow.setHeaderText("Universal encipher");
        aboutWindow.setContentText(
                "Program was created for not\n"
                + "commercial using at home,when you\n"
                + "and your children haven't any work,\n"
                + "or watch stuped movies in holidays."
                + "Also program was created for CNU University\n"
                + "\n"
                + "\n"
                + "\tCopyright @ 2015-2016 Pavlo Kuz\n \t\tAll Rights Reserved.");
        aboutWindow.setTitle("About program");
        aboutWindow.setResizable(false);
        aboutWindow.initOwner(primaryStage);
        aboutWindow.setGraphic(ImageViewBuilder.create().image(new Image(getClass().getResourceAsStream("/styles/about.png"))).fitHeight(100).fitWidth(100).build());
        aboutWindow.showAndWait();
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
       //set dynamic control of enabling of crypt and decrypt buttons
        btnEnc.disableProperty().bind(
        			(taText.lengthProperty().lessThan(1).or(txOffset.lengthProperty().lessThan(1))
        		.and(rbAthenaMode.selectedProperty().not()
        				.or(txKVal.lengthProperty().lessThan(1))
        				.or(taText.lengthProperty().lessThan(1))
        				.or(txAVal.lengthProperty().lessThan(1)))
        		.and(rbBSSMode.selectedProperty().not()
                                        .or(txKVal.lengthProperty().lessThan(1))
          				.or(taText.lengthProperty().lessThan(1))
           				.or(txAVal.lengthProperty().lessThan(1)))
                        .and(rbGammaMode.selectedProperty().not()
                                        .or(txK1.lengthProperty().lessThan(1))
                                        .or(txK2.lengthProperty().lessThan(1))
                                        .or(txK3.lengthProperty().lessThan(1)))));
        btnDec.disableProperty().bind(
        			(taText.lengthProperty().lessThan(1).or(txOffset.lengthProperty().lessThan(1))
        		.and(rbAthenaMode.selectedProperty().not()
        				.or(txKVal.lengthProperty().lessThan(1))
        				.or(taText.lengthProperty().lessThan(1))
        				.or(txAVal.lengthProperty().lessThan(1)))
        		.and(rbBSSMode.selectedProperty().not()
           				.or(txKVal.lengthProperty().lessThan(1))
           				.or(taText.lengthProperty().lessThan(1))
           				.or(txAVal.lengthProperty().lessThan(1)))
                        .and(rbGammaMode.selectedProperty().not()
                                        .or(txK1.lengthProperty().lessThan(1))
                                        .or(txK2.lengthProperty().lessThan(1))
                                        .or(txK3.lengthProperty().lessThan(1))) ));
         //set dynamic visibility of components  
        btnHack.disableProperty().bind(taText.lengthProperty().lessThan(1).or(rbBSSMode.selectedProperty()));
        btnHack.visibleProperty().bind(rbBSSMode.selectedProperty().not().and(rbGammaMode.selectedProperty().not()));
        
        
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
   
        labK1.visibleProperty().bind(rbGammaMode.selectedProperty());
        labK2.visibleProperty().bind(rbGammaMode.selectedProperty());
        labK3.visibleProperty().bind(rbGammaMode.selectedProperty());
        txK1.visibleProperty().bind(rbGammaMode.selectedProperty());
        txK2.visibleProperty().bind(rbGammaMode.selectedProperty());
        txK3.visibleProperty().bind(rbGammaMode.selectedProperty());
        //set user data for changing between ciphers
        rbAthenaMode.setUserData("athena");
        rbCeaserMode.setUserData("ceaser");
        rbBSSMode.setUserData("bbs");
        rbGammaMode.setUserData("gamma");
        //set drag-and-drop feature
        taText.setOnDragEntered(e-> {taText.setStyle("-fx-background-color:red;");});
        taText.setOnDragExited(e-> {taText.setStyle("-fx-background-color:teal;");});
        taText.setOnDragOver(e->{ e.acceptTransferModes(TransferMode.ANY); });
        taText.setOnDragDropped(event-> {
        	Dragboard board = event.getDragboard();
        	
        	if(board.hasFiles()){
        		try {
        			taText.setText(Files.readAllLines(board.getFiles().get(0).toPath(),Charset.defaultCharset()).stream().parallel().reduce("", (acc,a)->acc+a,(a1,a2)->a1+a2).replaceAll("( *|,|.|!|\\:|_|-|[0-9])", ""));
        		} catch (Exception e1) {
        			// TODO Auto-generated catch block 
        			Logger.getLogger(MainController.class).log(Logger.Level.ERROR, e1.getMessage(),e1);}
        	} else {
        		JOptionPane.showMessageDialog(null, "Problem with loaded file.");
        	}});
    }
    
}
