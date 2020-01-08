package org.vin.ciphers;

/**
 * Algorithm that encrypts and decrypts based on Bacon Cipher
 * This cipher implementation does not include punctuation and will classify it as whitespace if encountered
 */
public class BaconianCipher implements Algorithm {
    /**
     * Adds padding to adhere to 5 binary format
     * @param character that needs to be converted
     * @return String with added padding
     */
    public String padding(char character){
        String paddingOutput = "";
        short maxlen = 5;
        short len = (short) Integer.toBinaryString(character-'a').length();
        short spaces =(short) (maxlen - len);
        if(len < 5){
            while(paddingOutput.length() < spaces){
                paddingOutput += 'A';
            }
        }
        return paddingOutput;
    }


    /**
     * Converts the binary character string into Baconian
     * @param charBin current character to convert
     * @return Converted string of the given binary character string
     */ 
    public String convert(char charBin){
        String output = "";
        for(char bin: Integer.toBinaryString(charBin-'a').toCharArray()){
            switch(bin){
                case '0':
                    output += 'A';
                    break;
                case '1':
                    output += 'B';
                    break;
                default:
                    throw new RuntimeException("\""+bin+"\"" + " is not a legal option.");
            }
        }
        return output;
    }


    /**
     * Reverts the baconian character string into binary character
     * @param charBin current character to revert
     * @return binary string of the given baconian character
     */
    public String revert(char charBin){
        String output = "";
        switch(charBin){
            case 'A':
                output += '0';
                break;
            case 'B':
                output += '1';
                break;
            default:
                throw new RuntimeException("\""+charBin+"\"" + " is not a legal option.");
        }
        return output;
    }


    /**
     * Parses the binary string into human ascii code to turn into plaintext
     * @param binaryStr Binary string that needs to be converted back to plaintext
     * @return decrypted plaintext string
     */
    public String parseBin(String binaryStr){
        String output = "";
        String set = "";
        int counter = 0;
        for(char c: binaryStr.toCharArray()){
            if(Character.isDigit(c)){
                set += c;
                counter++;
                if(counter % 5 == 0){
                    output += (char) (Integer.parseInt(set,2) + 'a');
                    set = "";
                    counter = 0;
                }
            } else {
                output += c;
            }
        }
        return output;
    }

    /**
     * Encrypts the given string input into Baconian ciphertext
     * @param input Plaintext to encrypt
     * @param key used to encrypt by that value
     * @return String of the decrypted string
     */
    @Override
    public String encrypt(String input, String key) {
        String output = "";
        input = input.toLowerCase();
        for(char s: input.toCharArray()){
          if(Character.isAlphabetic(s)){
            output += this.padding(s);
            output += this.convert(s);
          } else {
              output += " ";
          }
        }
        return output;
    }


    /**
     * Decrypts the given Baconian string input into plaintext
     * @param input Ciphertext to decrypt
     * @param key used to decrypt by that value
     * @return String of the encrypted string
     */
    @Override
    public String decrypt(String input, String key) {
        String output = "";
        String binary = "";
        for(char s: input.toCharArray()){
            if(Character.isAlphabetic(s)){
                binary += this.revert(s);
            } else {
                binary += s;
            }
        }
        output = this.parseBin(binary);
        return output;
    }
}
