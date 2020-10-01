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
public interface IStorage<T> {

    boolean write(String name, T object) throws IOException;

    boolean exists(String name);

    // should throw Excpetion if not exists
    T read(String name) throws IOException;
}
