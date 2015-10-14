package com.mycompany.ciphers;

import com.mycompany.ciphers.annotations.CeaserCipher;

import javax.inject.Inject;
 

/**
 *
 * @author scarlet_skull
 */
public class CipherServices {
    @Inject @CeaserCipher private Cipher ceaserEncipher;
    //@Inject @AphineCipher Cipher aphineEncipher;
 
    public Cipher getCeaserEncipher() {
        return ceaserEncipher;
    }

    
    
    
}
