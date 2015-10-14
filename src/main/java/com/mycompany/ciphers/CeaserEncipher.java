
package com.mycompany.ciphers;

import com.mycompany.ciphers.annotations.CeaserCipher;
import com.mycompany.languages.LANG;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

/**
 *
 * @author scarlet_skull
 */
@CeaserCipher
public class CeaserEncipher implements Cipher{
 
    private Integer offset;
    private LANG language;
    private List<Character> text = new ArrayList<>();
    private char [] alph;
            
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

    public void setAlph(char[] alph) {
        this.alph = alph;
    }

    public char[] getAlph() {
        return alph;
    }
   
    
    

    @Override
    public void initialize(String line, Integer offset, LANG lang) {
            this.setLanguage(lang);
            this.setOffset(offset);
            this.setAlph(this.language.toString().toCharArray());
            this.getText().clear();
            for(Character i: line.toCharArray()){
                this.getText().add(i);
            }
            
    }

    @Override
    public String encipher(String line, Integer offset, LANG lang) {
        this.initialize(line, offset, lang);
        
        return this.getText().stream().parallel().map( (e) -> {
            return alph[((this.language.toString().indexOf(e) + this.offset)+alph.length) % alph.length];
        }).<String>reduce("", (acc,b)->{return acc+b;},(a,b)->{return a+b;});
    }

    @Override
    public String decipher(String line, Integer offset, LANG lang) {
        return encipher(line, offset, lang);
    }
    
    private long max = 0;
    private char key;
    private int step = -1;
    @Override
    public String hack(String line, Integer offset, LANG lang) {
        this.initialize(line, offset, lang);
        this.step++;
        
        TreeMap<Character,Long> hacktable = new TreeMap<>((k1,k2) -> {if ((byte)k1.charValue() > (byte)k2.charValue()) return 1; else return -1;});
        text.stream().parallel().forEach((e)->{
            hacktable.put(e, text.stream().filter(i -> Objects.equals(e, i)).count());
        });
        hacktable.entrySet().stream().forEach((entry) -> {
            if(entry.getValue().longValue() > max) {max = entry.getValue().longValue(); key = entry.getKey().charValue();}
        });
        
        return this.encipher(line, (byte)lang.getAlphabet().charAt(step) - (byte)key, lang);
    }

    
    
    
}
