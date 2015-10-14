package com.mycompany.ciphers;

import com.mycompany.ciphers.annotations.AthenasCipher;
import com.mycompany.ciphers.annotations.CeaserCipher;

import javax.inject.Inject;
 

/**
 *
 * @author scarlet_skull
 */
public class CipherServices {
    @Inject @CeaserCipher private Cipher ceaserEncipher;
    @Inject @AthenasCipher Cipher aphineEncipher;
 
    /**
     *
     * @return
     */
    public Cipher getCeaserEncipher() {
        return ceaserEncipher;
    }

    /**
     *
     * @return
     */
    public Cipher getAphineEncipher() {
        return aphineEncipher;
    }

    
    
    
    
}
