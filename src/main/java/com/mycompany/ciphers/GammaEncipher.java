/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ciphers;

import com.mycompany.ciphers.annotations.GammaCipher;
import com.mycompany.languages.LANG;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Main implementation of Gamma crypt</p>
 * <p>Can use encipher, decipher functions with this algorithm</p>
 * @see Cipher
 * @author User
 */
@GammaCipher
public class GammaEncipher implements Cipher{
    private Integer iterator = Integer.valueOf("0");
    private Integer K1 = null;
    private Integer K2 = null;
    private Integer K3 = null;
    private LANG language;
    private List<Character> text = new ArrayList<>();
    private char [] alph;

    /**
     *
     */
    public GammaEncipher() {
    }

    /**
     *
     * @return
     */
    public Integer getK1() {
        return K1;
    }

    /**
     *
     * @param K1
     */
    public void setK1(Integer K1) {
        this.K1 = K1;
    }

    /**
     *
     * @return
     */
    public Integer getK2() {
        return K2;
    }

    /**
     *
     * @param K2
     */
    public void setK2(Integer K2) {
        this.K2 = K2;
    }

    /**
     *
     * @return
     */
    public Integer getK3() {
        return K3;
    }

    /**
     *
     * @param K3
     */
    public void setK3(Integer K3) {
        this.K3 = K3;
    }

    /**
     *
     * @return
     */
    public LANG getLanguage() {
        return language;
    }

    /**
     *
     * @param language
     */
    public void setLanguage(LANG language) {
        this.language = language;
    }

    /**
     *
     * @return
     */
    public List<Character> getText() {
        return text;
    }

    /**
     *
     * @param text
     */
    public void setText(List<Character> text) {
        this.text = text;
    }

    /**
     *
     * @return
     */
    public char[] getAlph() {
        return alph;
    }

    /**
     *
     * @param alph
     */
    public void setAlph(char[] alph) {
        this.alph = alph;
    }

    
    
    
    /**
     * <p>Initialize all data which need for using Gamma Algorithms<p>
     * @param line
     * @param lang
     * @param K1
     * @param K2
     * @param K3 
     */
    @Override
    public void initialize(String line, LANG lang, int K1, int K2, int K3) {
        this.setLanguage(lang);
        this.iterator = 0;
        this.setAlph(this.language.toString().toCharArray());
        this.getText().clear();
        for(Character i: line.toCharArray()){
            this.getText().add(i);
        }
        this.setK1(K1);
        this.setK2(K2);
        this.setK3(K3);
    }
    /**
     * 
     * @param line, input text which must be crypted
     * @param lang, selected language which uses in algorithm
     * @param args, keys for gamma generator
     * @return line, if text crypted
     */
    @Override
    public String encipher(String line, LANG lang, Integer... args) {
        this.initialize(line, lang, args[0],args[1],args[2]);
        //make temporary array for gamma values for whole input data
        int [] tempGammaArray = new int[this.getText().size()];
        //make array for gamma crypted values for whole input data
        int [] gammaArray = new int[this.getText().size()];
        //initialize our keys in gamma array
        tempGammaArray[0] = this.getK1();
        tempGammaArray[1] = this.getK2();
        tempGammaArray[2] = this.getK3();
        
        //fill array with gamma values
        for (int i = 3; i < this.getText().size(); i++) {
            tempGammaArray[i] = (tempGammaArray[i-3] + tempGammaArray[i-1]) % this.alph.length;
        }
        
        //fill final gamma array values for input text
        for (int i = 0; i < this.getText().size()-1; i++) {
            gammaArray[i] = (tempGammaArray[i]+tempGammaArray[i+1]) % this.alph.length;
        }
        
        //crypt a text
        return this.getText().stream().parallel().map((item)-> {return alph[(this.language.toString().indexOf(item) + gammaArray[this.iterator++]) % this.alph.length];}).reduce("",(ch,acc)->ch + acc,(ch1,ch2)-> ch1+ch2);
        
    }
    /**
     * <p>Decrypt input crypted message and return result, which displays on screen</p>
     * @param line, input message
     * @param lang, selected language of message
     * @param args, input keys
     * @return decrypted data,if all input options were corecttly entered
     */
    @Override
    public String decipher(String line, LANG lang, Integer... args) {
        this.initialize(line, lang, args[0],args[1],args[2]);
        //make temporary array for gamma values for whole input data
        int [] tempGammaArray = new int[this.getText().size()];
        //make array for gamma crypted values for whole input data
        int [] gammaArray = new int[this.getText().size()];
        //initialize our keys in gamma array
        tempGammaArray[0] = this.getK1();
        tempGammaArray[1] = this.getK2();
        tempGammaArray[2] = this.getK3();
        
        //fill array with gamma values
        for (int i = 3; i < this.getText().size(); i++) {
            tempGammaArray[i] = (tempGammaArray[i-3] + tempGammaArray[i-1]) % this.alph.length;
        }
        
        //fill final gamma array values for input text
        for (int i = 0; i < this.getText().size()-1; i++) {
            gammaArray[i] = (tempGammaArray[i]+tempGammaArray[i+1]) % this.alph.length;
        }
        //decipher input text
        return this.getText().stream().parallel().map((item)-> {return alph[(this.language.toString().indexOf(item)+ (this.alph.length - gammaArray[this.iterator++])) % this.alph.length];}).reduce("",(ch,acc)->ch + acc,(ch1,ch2)-> ch1+ch2);
    }
    
    
    
}
