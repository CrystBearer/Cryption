package org.vin.ciphers;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Decrypt {
    HashMap<String, Algorithm> algorithms;
    public Decrypt(){
        this.algorithms = new HashMap<String, Algorithm>(5);
        this.algorithms.put("caesar", new CaesarCipher());
        this.algorithms.put("baconian", new BaconianCipher());
    }

    /**
     * Decrypts the given File
     * @param input File of the given path from the command line
     * @param name of the algorithm given
     * @return decrypted string
     */
    public String decrypt(File input, String name){
        name = name.toLowerCase();
        String decryptedOutput = "";
        if(this.algorithms.containsKey(name)){
            try {
                String key = this.getKey(name);
                BufferedReader br =  new BufferedReader(new FileReader(input));
                String fileContent = "";
                while(br.ready()){
                    fileContent += br.readLine();
                }
                decryptedOutput = this.algorithms.get(name).decrypt(fileContent,key);
                br.close();
            } catch (FileNotFoundException err) {
                System.out.println(err.getMessage() + ": Could not find " + input.getName());
            } catch (IOException err){
                System.out.println(err.getMessage() + ": Could not find " + input.getName());
            }
        } else {
            throw new RuntimeException("\""+input+"\"" + " algorithm does not exist.");
        }
        return decryptedOutput;
    }


    /**
     * Decrypts the given File
     * @param input File of the given path from the command line
     * @param name of the algorithm given
     * @return decrypted string
     */
    public String decrypt(String input, String name){
        name = name.toLowerCase();
        if(this.algorithms.containsKey(name)){
            String key = this.getKey(name);
            String decryptedOutput = this.algorithms.get(name).decrypt(input,key);
            return decryptedOutput;
        } else {
            throw new RuntimeException("\""+input+"\"" + " algorithm does not exist.");
        }
    }


    /**
     * Decrypts the given File
     * @param input File of the given path from the command line
     * @param name of the algorithm given
     * @param key of the algorithm given
     * @return decrypted string
     */
    public String decrypt(String input, String name, String key){
        name = name.toLowerCase();
        if(this.algorithms.containsKey(name)){
            String decryptedOutput = this.algorithms.get(name).decrypt(input,key);
            return decryptedOutput;
        } else {
            throw new RuntimeException("\""+input+"\"" + " algorithm does not exist.");
        }
    }

    /**
     * Uses the key given by the user to decrypt
     * @param algoName algorithm name
     * @return String of the key
     */
    public String getKey(String algoName){
        String key = "";
        switch(algoName){
            case "caesar":
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter key: ");
                key = scan.next();
                break;
            case "baconian":
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
            Decrypt decryptObject = new Decrypt();
            File encryptedFile = new File(args[1]);
            if(encryptedFile.exists() && encryptedFile.isFile()){
                System.out.println(decryptObject.decrypt(encryptedFile, args[0].toLowerCase()));
            } else {
                System.out.println(decryptObject.decrypt(args[1], args[0]));
            }
        }
    }
}
