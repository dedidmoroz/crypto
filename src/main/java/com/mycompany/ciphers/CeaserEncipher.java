
package com.mycompany.ciphers;

import com.mycompany.ciphers.annotations.CeaserCipher;
import com.mycompany.languages.LANG;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author scarlet_skull
 */
@CeaserCipher
public class CeaserEncipher implements Cipher{
 
    private Integer offset;
    private LANG language;
    private List<Character> text = new ArrayList<>();
    
            
    public CeaserEncipher() {

    }

     
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setLanguage(LANG language) {
        this.language = language;
    }

    public LANG getLanguage() {
        return language;
    }

    public void setText(List<Character> text) {
        this.text = text;
    }

    public List<Character> getText() {
        return text;
    }
   
    
    

    @Override
    public void initialize(String line, Integer offset, LANG lang) {
            this.setLanguage(lang);
            this.setOffset(offset);
            
            this.getText().clear();
            for(Character i: line.toCharArray()){
                this.getText().add(i);
            }
            
    }

    @Override
    public String encipher(String line, Integer offset, LANG lang) {
        this.initialize(line, offset, lang);
        char [] alph = this.language.toString().toCharArray();
        
        System.out.println(alph);
        
        List result =  this.getText().stream().parallel().map( (e) -> {
            return alph[(this.language.toString().indexOf(e) + this.offset) % alph.length];
        }).collect(Collectors.toList());
        
        System.out.println(result);
        
        return null;
    }

    @Override
    public String decipher(String line, Integer offset, LANG lang) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String hack(String line, Integer offset, LANG lang) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
