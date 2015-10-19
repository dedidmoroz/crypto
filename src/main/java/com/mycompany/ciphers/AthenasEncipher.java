/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ciphers;

import com.mycompany.ciphers.annotations.AthenasCipher;
import com.mycompany.languages.LANG;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 * <p>Implement algorithm of AthenasCipher</p>
 * <p>Uses this implemetation by container and inject dependency into service</p>
 * @author User
 * @version 1.0
 */
@AthenasCipher
public class AthenasEncipher implements Cipher{
    
    private LANG language;
    private List<Character> text = new ArrayList<>();
    private char [] alph;
    private int K;
    private int A;
    
    /**
     *
     */
    public AthenasEncipher() {  }


    /**
     * <p>Needs for get language of text</p> 
     * @return
     */
    public LANG getLanguage() { return language;  }

    /**
     * <p>Needs for set language of text</p> 
     * @param language
     */
    public void setLanguage(LANG language) { this.language = language; }

    /**
     *
     * @return
     */
    public List<Character> getText() { return text;  }

    /**
     *
     * @param text
     */
    public void setText(List<Character> text) {  this.text = text; }

    /**
     *
     * @return
     */
    public char[] getAlph() { return alph; }

    /**
     *
     * @param alph
     */
    public void setAlph(char[] alph) {  this.alph = alph;  }

    /**
     *
     * @return
     */
    public int getK() {   return K;   }

    /**
     *
     * @param K
     */
    public void setK(int K) {  this.K = K; }

    /**
     *
     * @return
     */
    public int getA() {  return A;  }

    /**
     *
     * @param A
     */
    public void setA(int A) {  this.A = A;  }

    
    /**
     * <p>Uses for hacking</p>
     * <p>But hack a program, not a cipher</p>
     * @deprecated
     * 
     */
    private Map<Integer,Integer> alphTable = new HashMap<>();
    
    /**
     * <p>Uses for initializing data which needs for encipher</p>
     * @param line
     * @param lang
     * @param A
     * @param K
     * 
     */
    
    @Override
    public void initialize(String line, LANG lang, int A, int K) {
            this.setLanguage(lang);
            this.setAlph(this.language.toString().toCharArray());
            this.setA(A);
            this.setK(K);
            
            this.getText().clear();
            for(Character i: line.toCharArray()){
                this.getText().add(i);
            }
    }
    
    /**
     * <p>Using for encipher text with Athenas algorithm</p>
     * @param line Line for crypting
     * @param lang Selected language
     * @param A Special number for multipling
     * @param K Special number for sum
     * @return String, if operation is done, else return null
     */
    @Override
    public String encipher(String line, LANG lang, int A, int K) {
        this.initialize(line, lang, A, K);
        this.alphTable.clear();
        if(!isGcd(A, K)){
            JOptionPane.showMessageDialog(null, "Is not simpler");
            return "problem";
        }
        return   this.getText()
                .stream()
                .parallel()
                .map((e)->{alphTable.put((this.language.toString().indexOf(e)*A+K) % this.alph.length,this.language.toString().indexOf(e)) ;
                           return alph[(this.language.toString().indexOf(e)*A+K) % this.alph.length];})
                .<String>reduce("", (acc,t)->acc+t,(a1,a2)-> a1+a2);
    }

    /**
     * <p>Using for decipher text with Athenas algorithm</p>
     * <p>Pass the text, special values A and K and set language</p>
     * @param line Line for crypting
     * @param lang Selected language
     * @param A Special number for multipling
     * @param K Special number for sum
     * @return String, if operation is done, else return null
     */
    @Override
    public String decipher(String line, LANG lang, int A, int K) {
        this.initialize(line, lang, A, K);
        return decrypt(line, A, K);

    }
    /**
     * <p>Use it for seeking greatest common divisor</p>
     * @param a pass for seeking greatest common divisor
     * @param b pass for seeking greatest common divisor
     * @return the greatest common divisor for a and b
     */
     private static int gcd(int a, int b) {
        while (a > 0 && b > 0) {
            if (a > b) {
                a %= b;
            } else {
                b %= a;
            }
        }
        return a + b;
    }
     boolean isGcd(int a,int b){
         while(a!=b){
             if(a>b){
                a-=b;
             } else {
                 b-=a;
             }
             
         }
         
         return (a==1);
     }
     /**
     * <p>Using for decipher text with Athenas algorithm</p>
     * <p>Pass the text, special values A and K and set language</p>
     * @param text
     * @param k Special number for sum
     * @param m Special number for multipling,must have only one greatest common divisor with length of alphabet
     * @return String, if operation is done, else return null
     */
    public String decrypt(String text, int m, int k){
        this.initialize(text, this.language, A, K);
        int n = language.toString().length();
        m = m % n;
        k = k % n;
        int reversedM = -1;//m-reversed
        //seek m-reversed
        for (int i = 0; i < n; i++) {
            if ((i * m) % n == 1) {
                reversedM = i;
                break;
            }
        }
        StringBuilder newText = new StringBuilder();
        //block of decipher data
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int index = language.toString().indexOf(c);
            index = (((index - k) * reversedM) % n + n) % n;
            newText.append(language.toString().charAt(index));
        }
        return newText.toString();

    } 

    
    /**
     * <p>Make hacking of Athenas algorithm</p>
     * <p>Takes languages and text which you need to hack</p>
     * @param line Text for hacking
     * @param lang Languages of the text
     * @return a line of hack string if everesing is good
     * @since 0.1
     */
    @Override
    public String hack(String line, LANG lang) {
    	this.initialize(line, lang, A, K);
    	return   this.getText()                                                                      
    			.stream()                                                                            
    			.parallel()                                                                          
    			.map((e)->{return alph[this.alphTable.get(this.language.toString().indexOf(e))]; })  
    			.<String>reduce("", (acc,t) -> acc+t,(a1,a2) -> a1+a2);                              
        
    }
    
}


