package com.mycompany.ciphers.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;
/**
 * 
 * @author User
 * @version 1.0
 * <p>Uses for setting BBSCipher algorithm in project</p>
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE,ElementType.METHOD})
@Qualifier
@Documented
public @interface BBSCipher {

}
