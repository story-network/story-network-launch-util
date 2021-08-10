/*
 * Created on Sun Sep 27 2020
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

package sh.pancake.launch.util;

import java.io.IOException;
import java.net.URL;

import javax.annotation.Nullable;

import com.google.gson.Gson;

import sh.pancake.launch.object.VersionInfo;
import sh.pancake.launch.object.VersionManifest;

public class MCLauncherUtil {

    public static String fetchRawVersionManifest() throws IOException {
        return DownloadUtil.fetchString(new URL("https://launchermeta.mojang.com/mc/game/version_manifest.json"));
    }

    public static VersionManifest getManifestFromJson(String json) {
        return new Gson().fromJson(json, VersionManifest.class);
    }

    @Nullable
    public static VersionManifest fetchVersionManifest() throws IOException {
        String rawManifest = fetchRawVersionManifest();

        if (rawManifest == null) return null;

        return getManifestFromJson(rawManifest);
    }

    @Nullable
    public static String fetchRawVersionInfo(VersionManifest manifest, String version) throws IOException {
        if (version == null) return null;

        VersionManifest.Version target = null;

        for (VersionManifest.Version ver : manifest.versionList) {
            if (version.equals(ver.id)) {
                target = ver;
                break;
            }
        }

        if (target == null) return null;

        return DownloadUtil.fetchString(new URL(target.url));
    }

    @Nullable
    public static String fetchRawVersionInfo(String version) throws IOException {
        return fetchRawVersionInfo(fetchVersionManifest(), version);
    }

    public static VersionInfo getInfoFromJson(String json) {
        return new Gson().fromJson(json, VersionInfo.class);
    }

    @Nullable
    public static VersionInfo fetchVersionInfo(VersionManifest manifest, String version) throws IOException {
        String rawVersionInfo = fetchRawVersionManifest();

        if (rawVersionInfo == null) return null;
            
        return getInfoFromJson(rawVersionInfo);
    }

    @Nullable
    public static VersionInfo fetchVersionInfo(String version) throws IOException {
        return fetchVersionInfo(fetchVersionManifest(), version);
    }

}
