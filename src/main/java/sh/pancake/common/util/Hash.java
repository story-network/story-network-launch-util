/*
 * Created on Tue Sep 29 2020
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

package sh.pancake.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public static byte[] sha1From(byte[] input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            md.update(input);

            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            
        }
        
        return null;
    }
    
    public static byte[] sha512From(byte[] input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            md.update(input);

            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            
        }
        
        return null;
    }

    public static byte[] md5From(byte[] input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(input);

            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            
        }
        
        return null;
    }

}
