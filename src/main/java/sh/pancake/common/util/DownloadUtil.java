/*
 * Created on Sun Sep 27 2020
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

package sh.pancake.common.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadUtil {

    public static byte[] fetchData(String urlStr) throws IOException {
        URL url = new URL(urlStr);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        connection.setRequestMethod("GET");
        connection.connect();

        if (connection.getResponseCode() != 200) {
            throw new IOException("Server responded with status code " + connection.getResponseCode() + " " + connection.getResponseMessage());
        }

        try (
            BufferedInputStream input = new BufferedInputStream(connection.getInputStream()); 
            ByteArrayOutputStream output = new ByteArrayOutputStream()) {

            input.transferTo(output);

            return output.toByteArray();
        }
    }

    public static String fetchString(String urlStr) throws IOException {
        byte[] data = fetchData(urlStr);

        return new String(data);
    }

}