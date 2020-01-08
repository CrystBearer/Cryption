package org.vin.ciphers;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Encrypts the string or text file depending on the algorithm given
 */
public class Encrypt {
    HashMap<String, Algorithm> algorithms;
    public Encrypt(){
        this.algorithms = new HashMap<String, Algorithm>(5);
        this.algorithms.put("caesar", new CaesarCipher());
        this.algorithms.put("bacon", new BaconianCipher());
    }

    /**
     * Encrypts the given File
     * @param input File of the given path from the command line
     * @param name algorithm
     * @return encrypted string
     */
    public String encrypt(File input, String name){
        String encryptedOutput = "";
        if(this.algorithms.containsKey(name)){
            String key = this.getKey(name);
            try {
                BufferedReader br =  new BufferedReader(new FileReader(input));
                String fileContent = "";
                while(br.ready()){
                    fileContent += br.readLine();
                }
                encryptedOutput = this.algorithms.get(name).encrypt(fileContent,key);
                br.close();
            } catch (FileNotFoundException err) {
                System.out.println(err.getMessage() + ": Could not find " + input.getName());
            } catch (IOException err){
                System.out.println(err.getMessage() + ": Could not find " + input.getName());
            }
        } else {
            throw new RuntimeException("\""+input+"\"" + " algorithm does not exist.");
        }
        return encryptedOutput;
    }


    /**
     * Encrypts the given File
     * @param input File of the given path from the command line
     * @param name algorithm name
     * @return encrypted string
     */
    public String encrypt(String input, String name){
        String key = this.getKey(name);
        if(this.algorithms.containsKey(name)){
            String encryptedOutput = this.algorithms.get(name).encrypt(input,key);
            return encryptedOutput;
        } else {
            throw new RuntimeException("\""+input+"\"" + " algorithm does not exist.");
        }
    }

    /**
     * Enters the key from user
     * @param algoName name of the type of cipher
     * @return String key
     */
    public String getKey(String algoName){
        String key = "";
        switch(algoName){
            case "caesar":
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter key: ");
                key = scan.next();
                break;
            case "bacon":
                break;
            default:
                throw new RuntimeException("\""+algoName+"\"" + " algorithm does not exist.");
        }
        return key;
    }

    /**
     * Takes in input from the user
     * [Algorithm] [String/File Path]
     * @param args Contains users input
     */
    public static void main(String[] args){
        short len = (short)args.length;
        if (len < 2){
            throw new RuntimeException("Too few arguments entered.");
        } else {
            Encrypt encryptObject = new Encrypt();
            File unencryptedFile = new File(args[1]);
            if(unencryptedFile.exists() && unencryptedFile.isFile()){
                System.out.println(encryptObject.encrypt(unencryptedFile, args[0].toLowerCase()));
            } else {
                System.out.println(encryptObject.encrypt(args[1], args[0].toLowerCase()));
            }
        }
    }
}
