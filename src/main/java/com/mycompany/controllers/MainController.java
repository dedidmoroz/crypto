/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controllers;

import com.mycompany.ciphers.CipherServices;
import com.mycompany.languages.LANG;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
 

/**
 *
 * @author scarlet_skull
 */
public class MainController implements Initializable{
private CipherServices services;
    
    public MainController(CipherServices serv) {
        this.services = serv;
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
    void loadFromFile(ActionEvent event) {

    }

    @FXML
    void saveToFile(ActionEvent event) {
        
    }
    
    
    @FXML
    void makeDecipher(ActionEvent event) {
        taResText.setText(this.services.getCeaserEncipher().encipher(taText.getText().toLowerCase(), Integer.valueOf(this.txOffset.getText()), (LANG) languages.getSelectedToggle().getUserData()));
    }

    @FXML
    void makeEncipher(ActionEvent event) {
        taResText.setText(this.services.getCeaserEncipher().encipher(taText.getText().toLowerCase(), Integer.valueOf(this.txOffset.getText()), (LANG) languages.getSelectedToggle().getUserData()));
    }

    @FXML
    void makeHacking(ActionEvent event) {
        taResText.setText(this.services.getCeaserEncipher().hack(taText.getText().toLowerCase(), Integer.valueOf(this.txOffset.getText()), (LANG) languages.getSelectedToggle().getUserData()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSave.disableProperty().bind(taResText.lengthProperty().lessThan(1));
        btnLoad.disableProperty().bind(taText.lengthProperty().greaterThan(1));
       
        btnEnc.disableProperty().bind(taText.lengthProperty().lessThan(1).or(txOffset.lengthProperty().lessThan(1)));
        btnDec.disableProperty().bind(taText.lengthProperty().lessThan(1).or(txOffset.lengthProperty().lessThan(1)));
        btnHack.disableProperty().bind(taText.lengthProperty().lessThan(1));
        
        rbEng.setUserData(LANG.Eng);
        rbRus.setUserData(LANG.Ru);
        rbUkr.setUserData(LANG.Ua);
        btnClose.addEventHandler(ActionEvent.ANY, (e)->{System.exit(0);});
    }
    
}
