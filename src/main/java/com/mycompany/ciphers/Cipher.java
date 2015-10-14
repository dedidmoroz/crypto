/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ciphers;

import com.mycompany.languages.LANG;

/**
 *
 * @author scarlet_skull
 */
public interface Cipher {
    public void initialize(String line,Integer offset,LANG lang);
    public String encipher(String line, Integer offset, LANG lang);
    public String decipher(String line, Integer offset, LANG lang);
    public String hack(String line, Integer offset, LANG lang);
    
}
