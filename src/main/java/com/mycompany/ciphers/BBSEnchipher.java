package com.mycompany.ciphers;

import java.util.*;
import javax.swing.JOptionPane;

import com.mycompany.ciphers.annotations.BBSCipher;
import com.mycompany.languages.LANG;

/**
 * <p>Implement algorithm of BBSCipherCipher</p>
 * <p>Uses this implemetation by container and inject dependency into service</p>
 *
 * @author User
 * @version 1.0
 */
@BBSCipher
public class BBSEnchipher implements Cipher {

    /**
     * <p>Uses for setting bloom number<p>
     */
    private Integer bloomNumber = Integer.valueOf(0);
    /**
     * <p>Main params for using any cipher algorithms</p>
     */
    private LANG language;
    private List<Character> text = new ArrayList<>();
    private char[] alph;
    private int K;
    private int A;


    public BBSEnchipher() {
        // TODO Auto-generated constructor stub
    }

    /**
     * <p>Needs for get language of text</p>
     *
     * @return
     */
    public LANG getLanguage() {
        return language;
    }

    /**
     * <p>Needs for set language of text</p>
     *
     * @param language
     */
    public void setLanguage(LANG language) {
        this.language = language;
    }

    /**
     * @return
     */
    public List<Character> getText() {
        return text;
    }

    /**
     * @param text
     */
    public void setText(List<Character> text) {
        this.text = text;
    }

    /**
     * @return
     */
    public char[] getAlph() {
        return alph;
    }

    /**
     * @param alph
     */
    public void setAlph(char[] alph) {
        this.alph = alph;
    }

    /**
     * @return
     */
    public int getK() {
        return K;
    }

    /**
     * @param K
     */
    public void setK(int K) {
        this.K = K;
    }

    /**
     * @return A
     */
    public int getA() {
        return A;
    }

    /**
     * @param A
     */
    public void setA(int A) {
        this.A = A;
    }

    /**
     * @return bloomNumber
     */

    public Integer getBloomNumber() {
        return bloomNumber;
    }

    /**
     * @param bloomNumber
     */
    public void setBloomNumber(Integer bloomNumber) {
        this.bloomNumber = bloomNumber;
    }

    /**
     * <p>Uses for initializing data which needs for encipher</p>
     *
     * @param line
     * @param lang
     * @param A
     * @param K
     */

    @Override
    public void initialize(String line, LANG lang, int A, int K) {
        this.setLanguage(lang);
        this.setAlph(this.language.toString().toCharArray());
        this.setA(A);
        this.setK(K);
        this.getText().clear();
        for (Character i : line.toCharArray()) {
            this.getText().add(i);
        }
    }

    /**
     * <p>Uses for initializing and checking taken numbers</p>
     *
     * @param A
     * @param K
     * @return true, if values are congruence
     */

    @Override
    public Boolean initialize(int A, int K) {
        // TODO Auto-generated method stub
        return ((A % 4 == 3) && (K % 4 == 3));
    }

    /**
     * <p>Uses for seeking a random value, which must be relatively simple with it</p>
     * @param blumNumber
     * @return random value X, which needs for crypting text
     */
    public Integer generateValue(Integer blumNumber){
        Integer value = Integer.valueOf(0);
        do {
            value = new Random().nextInt(blumNumber);
        }while(BBSEnchipher.gcd(blumNumber, value) != 1);
        return value;
    }

    /**
     * Uses for creating relative map between encipher text letters and decipher letters of text
     */
    Map<Character,Character> cipherRelative = new TreeMap<>();
    /**
     * <p>Using for encipher text with BBSCipher algorithm</p>
     *
     * @param line Line for crypting
     * @param lang Selected language
     * @param A    Special number for multipling
     * @param K    Special number for sum
     * @return String, if operation is done, else return null
     */

    @Override
    public String encipher(String line, LANG lang, int A, int K) {
        this.initialize(line, lang, A, K);
        this.cipherRelative.clear();
        if (!initialize(A, K)) {
            JOptionPane.showMessageDialog(null, "Your numbers are not congruence.");
            return "";
        }
        this.setBloomNumber(A*K);
        Integer generatedNumber = this.generateValue(this.getBloomNumber());
        System.out.println("My number:"+generatedNumber);

        //build table of random numbers and then build table with junior bit of every number
        int x0 = (int) (Math.pow(generatedNumber.doubleValue(),2) % this.getBloomNumber());
        byte s0 = (byte) (x0 % 2);

        int[] arrayOfSymbIndexes = new int[this.getAlph().length];
        byte[] arrayOfBits = new byte[this.getAlph().length];

        arrayOfSymbIndexes[0]
                = x0;
        arrayOfBits[0] = s0;

        for (int i = 1; i < arrayOfSymbIndexes.length; i++) {
            arrayOfSymbIndexes[i] = (arrayOfSymbIndexes[i-1] * arrayOfSymbIndexes[i-1]) % this.getBloomNumber();
            arrayOfBits[i] = (byte) (arrayOfSymbIndexes[i] % 2);
            System.out.println(arrayOfBits[i] + " " + arrayOfSymbIndexes[i]);
        }

        return this.text.stream()
                .parallel()
                .map((e) -> {
                    this.cipherRelative.put(this.alph[arrayOfSymbIndexes[language.toString().indexOf(e)] % this.alph.length], e);
                    return this.alph[arrayOfSymbIndexes[language.toString().indexOf(e)] % this.alph.length]; })
                .<String>reduce("", (acc, txt) -> acc + txt, (a1, a2) -> a1 + a2);
    }

    /**
     * <p>Uses for seeking parity bit</p>
     *
     * @param a
     * @param b
     * @return parity bit, if it exist.
     */
    private Byte getParityBit(Integer a, Integer b) {
        return null;

    }

    /**
     * <p>Using for decipher text with BBSCipher algorithm</p>
     * <p>Pass the text, special values A and K and set language</p>
     * <p>Work fine without bugs only on english</p>
     *
     * @param line Line for crypting
     * @param lang Selected language
     * @param A    Special number for multipling
     * @param K    Special number for sum
     * @return String, if operation is done, else return null
     */
    @Override
    public String decipher(String line, LANG lang, int A, int K) {
        this.initialize(line, lang, A, K);
        System.out.println("This is map:" + this.cipherRelative);
        return this.text.stream()
                .parallel()
                .map((e) -> {
                    return cipherRelative.get(e);
                })
                .<String>reduce("", (acc, txt) -> acc + txt, (a1, a2) -> a1 + a2);
    }


    /**
     * <p>Use it for seeking greatest common divisor</p>
     *
     * @param a pass for seeking greatest common divisor
     * @param b pass for seeking greatest common divisor
     * @return the greatest common divisor for a and b
     */
    private static int gcd(int a, int b) {
        while (a > 0 && b > 0) {
            if (a > b) {
                a %= b;
            } else {
                b %= a;
            }
        }
        return a + b;
    }

}
