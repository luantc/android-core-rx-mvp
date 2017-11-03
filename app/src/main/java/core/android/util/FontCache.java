package core.android.util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.util.Hashtable;


public class FontCache {

    private static final String TAG = FontCache.class.getSimpleName();

    private static Hashtable<String, Typeface> fontCache = new Hashtable<String, Typeface>();

    public static Typeface get(Context context, String name) {
        Typeface tf = fontCache.get(name);
        if (tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), name);
            } catch (Exception e) {
                Log.e(TAG, "Could not get typeface: " + e.getMessage());
                return null;
            }
            fontCache.put(name, tf);
        }
        return tf;
    }
}
