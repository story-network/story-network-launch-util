/*
 * Created on Sun Sep 27 2020
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

package sh.pancake.common.object;

import com.google.gson.annotations.SerializedName;

/* 
 * We omit every fields except "downloads" cause it's completely useless for now.
 */
public class VersionInfo {

    @SerializedName("downloads")
    public VersionDownloads downloads;


    public class VersionDownloads {

        @SerializedName("client")
        public Item client;

        @SerializedName("client_mappings")
        public Item clientMappings;

        @SerializedName("server")
        public Item server;

        @SerializedName("server_mappings")
        public Item serverMappings;

        public class Item {

            @SerializedName("sha1")
            public String sha1;

            @SerializedName("size")
            public int size;

            @SerializedName("url")
            public String url;

        }

    }

}
