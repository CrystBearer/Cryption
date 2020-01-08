package org.vin.ciphers;

/**
 * Algorithm that shifts the character by the key
 */
public class CaesarCipher implements Algorithm {
    /**
     * Encrypts the given string input
     * @param input Plaintext to encrypt
     * @param key used to encrypt by that value
     * @return String of the decrypted string
     */
    public String encrypt(String input, String key){
        String output = "";
        int shift =  Integer.parseInt(key);
        for(char s: input.toCharArray()){
            if(!Character.isAlphabetic(s)){
                output += s;
                continue;
            } else {
                int sum = s + shift;
                char o =(char) (sum);
                if(Character.isAlphabetic(s)) {
                    if (sum > (int) 'z' && !Character.isUpperCase(s) || sum > (int) 'Z' && Character.isUpperCase(s)) {
                        o = (char) (o - 26);
                    }
                }
                output += o;
            }
        }
        return output;
    }

    /**
     * Decrypts the given string input
     * @param input Ciphertext to decrypt
     * @param key used to decrypt by that value
     * @return String of the encrypted string
     */
    public String decrypt(String input, String key){
        String output = "";
        int shift =  Integer.parseInt(key);
        for(char s: input.toCharArray()){
            if(!Character.isAlphabetic(s)){
                output += s;
                continue;
            } else {
                int sum = s - shift;
                char o =(char) (sum);
                if(Character.isAlphabetic(s)){
                    if(sum < (int) 'a' && !Character.isUpperCase(s) || sum < (int) 'A' && Character.isUpperCase(s)) {
                        o = (char)(o + 26);
                    }
                }
                output += o;
            }
        }
        return output;
    }

}
