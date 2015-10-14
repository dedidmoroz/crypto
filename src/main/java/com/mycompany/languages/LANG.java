
package com.mycompany.languages;

/**
 *
 * @author scarlet_skull
 */
public enum LANG {

    Eng() { 
        public String getHackTable(){ return "etiaonsrhcldpyumfbgwvkxqzj";}
    },
    Ru()  { 
        public String getHackTable(){ return "онаитверіскмдлупязьгчбхцюжйїєфшщґ";}
    },
    Ua()  { 
        public String getHackTable(){ return "онаитверіскмдлупязьгчбхцюжйїєфшщґ";}
    };

    @Override
    public String toString() {
        switch (this){
            case Eng:return "abcdefghijklmnopqrstuvwxyz";
            case Ru: return "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
            case Ua :return "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
            default:return null;
        }
    }
   
    
    
}
