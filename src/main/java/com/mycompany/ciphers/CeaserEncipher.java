
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
            
    /**
     *
     */
    public CeaserEncipher() {    }

    /**
     *
     * @param offset
     */
    public void setOffset(Integer offset) { this.offset = offset;}

    /**
     *
     * @return
     */
    public Integer getOffset() { return offset;}

    /**
     *
     * @param language
     */
    public void setLanguage(LANG language) { this.language = language;}

    /**
     *
     * @return
     */
    public LANG getLanguage() { return language;}

    /**
     *
     * @param text
     */
    public void setText(List<Character> text) { this.text = text; }

    /**
     *
     * @return
     */
    public List<Character> getText() { return text; }

    /**
     *
     * @param alph
     */
    public void setAlph(char[] alph) { this.alph = alph; }

    /**
     *
     * @return 
     */
    public char[] getAlph() { return alph; }
   
    /**
     * <p>Uses for initializing data which needs for encipher</p>
     * @param line
     * @param offset
     * @param lang
     */
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
    
    
    /**
     * <p>Using for encipher text with Ceaser algorithm</p>
     * @param line Line for crypting
     * @param lang Selected language
     * @param offset Set offset of symbols in alphabet
     * @return String, if operation is done, else return null
     */
    @Override
    public String encipher(String line, Integer offset, LANG lang) {
        this.initialize(line, offset, lang);
        //gavno code detected, it's not my fault, it's because i must to use both way offset
        return this.getText().stream().parallel().map( (e) -> {
                            return alph[(this.language.toString().indexOf(e) + (this.offset % alph.length + alph.length ) ) % alph.length]; })
                    .<String>reduce("", (acc,b)->{return acc+b;},(a,b)->{ return a+b; });
    }
    
    /**
     * <p>Using for encipher text with Ceaser algorithm</p>
     * <p>Pass text, offset and set a language</p>
     * @param line Line for crypting
     * @param lang Selected language
     * @param offset Set offset of symbols in alphabet
     * @return String, if operation is done, else return null
     */
    @Override
    public String decipher(String line, Integer offset, LANG lang) {
        return encipher(line, offset, lang);
    }
    
    
    /**
     * <p>max  had used for setting up a most frequencies symbol count</p>
     * <p>key had used for setting up most frequencies symbol</p>
     * <p>step had used for setting up a step of hacking</p>
     * @since 1.0
     */
    private long max = 0;
    private char key;
    private int step = -1;
    TreeMap<Character,Long> hacktable = new TreeMap<>((k1,k2) -> {if ((byte)k1.charValue() > (byte)k2.charValue()) return 1; else return -1;});
   
    /**
     * <p>Using for hacking a text</p>
     * <p>Pass text, set a language</p>
     * @param line Line for crypting
     * @param lang Selected language
     * @return String, if operation is done, else return null
     */
    @Override
    public String hack(String line, LANG lang) {
        this.initialize(line, offset, lang);
        this.step++;
        
        text.stream().parallel().forEach((e)->{
            hacktable.put(e, text.stream().filter(i -> Objects.equals(e, i)).count());
        });
        hacktable.entrySet().stream().forEach((entry) -> {
            if(entry.getValue().longValue() > max) {max = entry.getValue().longValue(); key = entry.getKey().charValue();}
        });
        if(step == this.language.getAlphabet().length()) step = 0;
        return this.encipher(line, (byte)lang.getAlphabet().charAt(step) - (byte)key, lang);
    }

   
    
    
    
}
