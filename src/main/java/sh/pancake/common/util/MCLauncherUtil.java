/*
 * Created on Sun Sep 27 2020
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

package sh.pancake.common.util;

import java.io.IOException;

import com.google.gson.Gson;

import sh.pancake.common.object.VersionInfo;
import sh.pancake.common.object.VersionManifest;

public class MCLauncherUtil {

    public static String fetchRawVersionManifest() {
        try {
            return DownloadUtil.fetchString("https://launchermeta.mojang.com/mc/game/version_manifest.json");
        } catch(IOException e) {
            return null;
        }
    }

    public static VersionManifest getManifestFromJson(String json) {
        return new Gson().fromJson(json, VersionManifest.class);
    }
    
    public static VersionManifest fetchVersionManifest() {
        String rawManifest = fetchRawVersionManifest();

        if (rawManifest == null) return null;

        return getManifestFromJson(rawManifest);
    }

    public static String fetchRawVersionInfo(VersionManifest manifest, String version) {
        if (version == null) return null;

        VersionManifest.Version target = null;

        for (VersionManifest.Version ver : manifest.versionList) {
            if (version.equals(ver.id)) {
                target = ver;
                break;
            }
        }

        if (target == null) return null;

        try {
            return DownloadUtil.fetchString(target.url);
        } catch(IOException e) {
            return null;
        }
    }

    public static String fetchRawVersionInfo(String version) {
        return fetchRawVersionInfo(fetchVersionManifest(), version);
    }

    public static VersionInfo getInfoFromJson(String json) {
        return new Gson().fromJson(json, VersionInfo.class);
    }

    public static VersionInfo fetchVersionInfo(VersionManifest manifest, String version) {
        String rawVersionInfo = fetchRawVersionManifest();

        if (rawVersionInfo == null) return null;
            
        return getInfoFromJson(rawVersionInfo);
    }

    public static VersionInfo fetchVersionInfo(String version) {
        return fetchVersionInfo(fetchVersionManifest(), version);
    }

}
