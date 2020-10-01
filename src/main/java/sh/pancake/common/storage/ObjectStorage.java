/*
 * Created on Tue Sep 29 2020
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

package sh.pancake.common.storage;

import java.io.File;

import sh.pancake.common.util.Hash;
import sh.pancake.common.util.Hex;

/*
 * Store file objects with hashed name
 */
public class ObjectStorage extends DiskIOStorage {

    public ObjectStorage(File dir) {
        super(dir);
    }

    @Override
    public File getReference(String name) {
        byte[] rawHash = new byte[0];
        try {
            rawHash = Hash.sha1From(name.getBytes("utf-8"));
        } catch (Exception e) {
            // UTF-8 not supported? impossible
        }

        String hash = Hex.byteArrayToHex(rawHash);

        return new File(getDirectory(), hash.substring(0, 2) + File.separator + hash);
    }
    
}
