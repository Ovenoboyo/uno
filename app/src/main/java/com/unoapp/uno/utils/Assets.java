package com.unoapp.uno.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utils class to hold assets related stuff
 */
public class Assets {
    public static int ASSET_NOT_FOUND_ERR = -2;

    /**
     * Get appropriate assets directory (runtime/bin for windows)
     * @return Path pointing to appropriate assets directory
     */
    public static Path getAssetsDirectory() {
        Path assets = Paths.get("assets");
        if (!Files.exists(assets)) {
            assets = Paths.get("runtime", "bin", "assets");
        }
        return assets;
    }

    /**
     * Get path to asset. Straight up panic and exit if asset isn't found cause why not
     * 
     * @param assetName name of asset to find
     * @return Path as string of asset
     */
    public static String getAsset(String assetName) {
        Path file = Paths.get(getAssetsDirectory().toString(), assetName);
        if (!Files.exists(file)) {
            System.out.println("Asset " + assetName + " not found");
            System.exit(ASSET_NOT_FOUND_ERR);
        }
        return file.toString();
    }
}
