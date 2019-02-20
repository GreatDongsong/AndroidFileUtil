package com.dawson.androidfileutil;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

public class Assets {
    private static final Hashtable<String, Typeface> _typefaceCache = new Hashtable<String, Typeface>();

    public static String readFile(AssetManager mgr, String assetsFile) throws IOException {
        return new String(readData(mgr, assetsFile));
    }

    public static byte[] readData(AssetManager mgr, String assetsFile) throws IOException {
        InputStream is = mgr.open(assetsFile);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return buffer;
    }

    public static long calculateSizeBySpooling(AssetManager mgr, String assetsFile) throws IOException {
        long retval = 0;

        InputStream is = mgr.open(assetsFile);

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) > 0) {
            retval += bytesRead;
        }

        return retval;
    }

    public static Drawable getDrawable(AssetManager mgr, String assetsFile) throws IOException {
        InputStream is = mgr.open(assetsFile);
        return Drawable.createFromStream(is, null);
    }

    public static Typeface getTypeface(AssetManager mgr, String typefaceName) {
        synchronized (_typefaceCache) {
            if (!_typefaceCache.containsKey(typefaceName)) {
                try {
                    Typeface tf = Typeface.createFromAsset(mgr, String.format("fonts/%s.otf", typefaceName));
                    _typefaceCache.put(typefaceName, tf);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            return _typefaceCache.get(typefaceName);
        }
    }

    public static String[] getFilesInPath(AssetManager mgr, String path) {
        try {
            String[] retval = mgr.list(path);
            return (retval.length > 0) ? retval : null;
        } catch (IOException e) {
            return null;
        }
    }
}
