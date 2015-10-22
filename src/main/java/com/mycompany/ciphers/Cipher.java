/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ciphers;

import com.mycompany.languages.LANG;

/**
 * <p>Uses for creating abstraction of ciphers</p>
 * @author scarlet_skull
 * @version 1.0
 * @see CipherServices 
 */
public interface Cipher {

    /**
     * <p>initialize Ceaser encipher class</p>
     * @param line
     * @param offset
     * @param lang
     */
    default public void initialize(String line,Integer offset,LANG lang){};
    
    /**
     * <p>initialize Athenas encipher class</p>
     * @param line
     * @param lang
     * @param A
     * @param K
     */
    default public void initialize(String line,LANG lang,int A,int K) {};
    /**
     * <p>initialize Athenas encipher class</p>
     * @param A
     * @param K
     * @return true, if some conditions with values K and A are works.
     */
   
    default public Boolean initialize(int A,int K) {return null;};
   
    
    /**
     * <p>initialize Gamma encipher class</p>
     * @param line
     * @param lang
     * @param K1
     * @param K2
     * @param K3 
     */
    default public void initialize(String line,LANG lang,int K1,int K2,int K3) {};
    
    
    /**
     * <p>Make crypt a text</p>
     * @param line
     * @param offset
     * @param lang
     * @return null
     */
    default public String encipher(String line, Integer offset, LANG lang){return null;};

    /**
     *
     * @param line
     * @param lang
     * @param A
     * @param K
     * @return string, if input had crypted by  cipher
     */
    default public String encipher(String line, LANG lang,int A,int K){return null;};
    
    /**
     * <p>Uses for create a realisation of gamma cipher</p>
     * @param line
     * @param lang
     * @param args
     * @return string, if input had crypted by gamma cipher
     */
    default public String encipher(String line, LANG lang,Integer ... args){return null;};
    
    /**
     *
     * @param line
     * @param offset
     * @param lang
     * @return string, which was decrypted
     */
    default public String decipher(String line, Integer offset, LANG lang){return null;};

    /**
     *
     * @param line
     * @param lang
     * @param A
     * @param K
     * @return string, which was decrypted
     */
    default public String decipher(String line, LANG lang,int A,int K){return null;};
    /**
     * 
     * @param line
     * @param lang
     * @param args
     * @return string, which was decrypted
     */
    default public String decipher(String line, LANG lang,Integer ... args){return null;};
    
    /**
     *
     * @param line
     * @param lang
     * @return string, which was hacked from input line
     */
    default public String hack(String line, LANG lang){return null;};
    
}
