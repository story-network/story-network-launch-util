/*
 * Created on Sun Sep 27 2020
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

package sh.pancake.launch.object;

import com.google.gson.annotations.SerializedName;

public class VersionManifest {

    @SerializedName("versions")
    public Version[] versionList;

    @SerializedName("latest")
    public LatestInfo latest;
    
    public class Version {

        @SerializedName("id")
        public String id;

        @SerializedName("releaseTime")
        public String releaseTime;
        
        @SerializedName("time")
        public String time;

        @SerializedName("url")
        public String url;

    }

    public class LatestInfo {

        @SerializedName("release")
        public String release;

        @SerializedName("snapshot")
        public String snapshot;

    }

}
