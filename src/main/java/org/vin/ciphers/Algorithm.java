package org.vin.ciphers;

public interface Algorithm {
    String encrypt(String input, String key);
    String decrypt(String input, String key);
}
