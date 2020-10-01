/*
 * Created on Tue Sep 29 2020
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

package sh.pancake.common.storage;

import java.io.*;
import java.nio.file.Files;
import java.util.UUID;

/*
 * Provide temp storage
 */
public class TempStorage extends DiskIOStorage {

    public TempStorage() throws IOException {
        this(UUID.randomUUID().toString());
    }

    public TempStorage(String prefix) throws IOException {
        super(Files.createTempDirectory(prefix).toFile());
    }

}
