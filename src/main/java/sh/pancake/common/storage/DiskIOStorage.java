/*
 * Created on Tue Sep 29 2020
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

package sh.pancake.common.storage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * Store file objects
 */
public class DiskIOStorage extends Storage<byte[]> {

    private File dir;

    public DiskIOStorage(String dir) {
        this.dir = new File(dir);
    }

    public DiskIOStorage(File dir) {
        this.dir = dir;
    }

    public File getDirectory() {
        return dir;
    }

    public File getReference(String name) {
        return new File(dir, name);
    }

    @Override
    public boolean write(String name, byte[] data) throws IOException {
        File file = getReference(name);
        File parent = file.getParentFile();

        if (!parent.exists()) parent.mkdirs();

        try (BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file))) {
            writer.write(data);
        }

        return true;
    }

    @Override
    public byte[] read(String name) throws IOException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            File file = getReference(name);

            try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file))) {
                input.transferTo(output);
            }

            return output.toByteArray();
        }
    }

}