/*
 * Created on Mon Sep 28 2020
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

package sh.pancake.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {

    public static byte[] readData(File file) throws IOException {
        try (
            InputStream input = new FileInputStream(file);
            ByteArrayOutputStream output = new ByteArrayOutputStream()) {
                
            input.transferTo(output);

            return output.toByteArray();
        }
    }

    public static String readString(File file) throws IOException {
        byte[] data = readData(file);

        return new String(data);
    }

    public static void writeData(File file, byte[] data) throws IOException {
        File parent = file.getParentFile();
        if (!parent.exists()) parent.mkdirs();

        if (!file.exists()) file.createNewFile();

        try (
            ByteArrayInputStream input = new ByteArrayInputStream(data);
            OutputStream fileStream = new FileOutputStream(file)) {
                
            input.transferTo(fileStream);
        }
    }

    public static void writeString(File file, String data) throws IOException {
        writeData(file, data.getBytes("utf-8"));
    }
    
}
