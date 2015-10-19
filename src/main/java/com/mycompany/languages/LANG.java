
package com.mycompany.languages;

/**
 *
 * @author scarlet_skull
 */
public enum LANG {

    /**
   * <p>Inner class of current enum</p>
   * <p>Uses for returning frequency table of each english symbol</p>
   * @see LANG
   */
  
    Eng() { 
        public String getHackTable(){return super.getAlphabet();}
    },

   
   
  /**
   * <p>Inner class of current enum</p>
   * <p>Uses for returning frequency table of each russian symbol</p>
   * @see LANG
   */
    Ru()  { 
        public String getHackTable(){ return super.getAlphabet();}
    },

    
    
  /**
   * <p>Inner class of current enum</p>
   * <p>Uses for returning frequency table of each ukrainian symbol</p>
   * @see LANG
   */
    Ua()  { 
        public String getHackTable(){ return super.getAlphabet();}
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
    
    /**
     * <p>Return frequencies for each laguages </p>
     * @return
     */
    public String getAlphabet(){
         switch (this){
            case Eng:return "etiaonsrhcldpyumfbgwvkxqzj";
            case Ru: return "онаитверіскмдлупязьгчбхцюжйїєфшщґ";
            case Ua :return "онаитверіскмдлупязьгчбхцюжйїєфшщґ";
            default:return null;
        }
    }
   
    
    
}
