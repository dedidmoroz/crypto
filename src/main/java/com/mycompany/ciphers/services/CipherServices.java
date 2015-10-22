package com.mycompany.ciphers.services;

import com.mycompany.ciphers.Cipher;
import com.mycompany.ciphers.annotations.AthenasCipher;
import com.mycompany.ciphers.annotations.BBSCipher;
import com.mycompany.ciphers.annotations.CeaserCipher;
import com.mycompany.ciphers.annotations.GammaCipher;

import javax.inject.Inject;
 

/**
 *
 * @author scarlet_skull
 */
public class CipherServices {
    @Inject @CeaserCipher private Cipher ceaserEncipher;
    @Inject @AthenasCipher Cipher athenasEncipher;
    @Inject @BBSCipher Cipher bbsEncipher;
    @Inject @GammaCipher Cipher gammaCipher;
    
   /**
     *
     * @return service of crypting data using Ceaser algorithm 
     */
    public Cipher getCeaserEncipher() {
        return ceaserEncipher;
    }

  /**
    *
    * @return service of crypting data using Athenas algorithm 
    */
    public Cipher getAphineEncipher() {
        return athenasEncipher;
    }

  /**
    *
    * @return service of crypting data using BBS algorithm 
    */
    
    public Cipher getBbsEncipher() {
            return bbsEncipher;
    }
    /**
     * 
     * @return service which give access to Gamma algorithm
     */
    public Cipher getGammaCipher() {
        return gammaCipher;
    }

    
}
