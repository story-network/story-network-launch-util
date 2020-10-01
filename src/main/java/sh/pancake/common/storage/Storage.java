/*
 * Created on Tue Sep 29 2020
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

package sh.pancake.common.storage;

import java.io.IOException;

/*
 * Storage API
 * Handle IO
 */
public abstract class Storage<T> {

    public abstract boolean write(String name, T object) throws IOException;

    public abstract T read(String name) throws IOException;
}
