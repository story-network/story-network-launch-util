/*
 * Created on Tue Sep 29 2020
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

package sh.pancake.launch.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    private static byte[] hashFrom(MessageDigest md, InputStream stream) throws IOException {
        byte[] buf = new byte[8192];

        int read;
        while ((read = stream.read(buf)) != -1) {
            md.update(buf, 0, read);
        }

        return md.digest();
    }

    public static byte[] sha1From(InputStream stream) throws IOException {
        try {
            return hashFrom(MessageDigest.getInstance("SHA-1"), stream);
        } catch (NoSuchAlgorithmException e) {
            
        }
        
        return null;
    }
    
    public static byte[] sha512From(InputStream stream) throws IOException {
        try {
            return hashFrom(MessageDigest.getInstance("SHA-512"), stream);
        } catch (NoSuchAlgorithmException e) {
            
        }
        
        return null;
    }

    public static byte[] md5From(InputStream stream) throws IOException {
        try {
            return hashFrom(MessageDigest.getInstance("MD5"), stream);
        } catch (NoSuchAlgorithmException e) {
            
        }
        
        return null;
    }

}
