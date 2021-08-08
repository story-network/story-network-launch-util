/*
 * Created on Sun Sep 27 2020
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

package sh.pancake.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DownloadUtil {

    public static InputStream openDownloadStream(URL url) throws IOException {
        return url.openStream();
    }

    public static byte[] fetchData(URL url) throws IOException {
        try (
            InputStream stream = openDownloadStream(url);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
        ) {
            stream.transferTo(output);

            return output.toByteArray();
        }
    }

    public static String fetchString(URL url) throws IOException {
        byte[] data = fetchData(url);

        return new String(data);
    }

}