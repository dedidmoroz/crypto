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
     * @param line
     * @param lang
     * @param A
     * @param K
     */
    default public Boolean initialize(int A,int K) {return null;};
    
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
     * @return null
     */
    default public String encipher(String line, LANG lang,int A,int K){return null;};
    
    /**
     *
     * @param line
     * @param offset
     * @param lang
     * @return null
     */
    default public String decipher(String line, Integer offset, LANG lang){return null;};

    /**
     *
     * @param line
     * @param lang
     * @param A
     * @param K
     * @return
     */
    default public String decipher(String line, LANG lang,int A,int K){return null;};
    
    /**
     *
     * @param line
     * @param lang
     * @return null
     */
    default public String hack(String line, LANG lang){return null;};
    
}
