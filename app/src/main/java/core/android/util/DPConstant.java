package core.android.util;

import android.util.SparseArray;

public class DPConstant {

    public static final int FONT_STYLE_REGULAR = 0;
    public static final int FONT_STYLE_LIGHT = 1;
    public static final int FONT_STYLE_MEDIUM = 2;
    public static final int FONT_STYLE_HEAVY = 3;
    public static final SparseArray<String> FONT_STYLES = new SparseArray<String>() {
        {
            append(FONT_STYLE_REGULAR, "effra-regular.ttf");
            append(FONT_STYLE_LIGHT, "effra-light.ttf");
            append(FONT_STYLE_MEDIUM, "effra-medium.ttf");
            append(FONT_STYLE_HEAVY, "effra-heavy.ttf");
        }
    };
    // No breaking space letter
    public static final String NON_BREAKING_SPACE = "\u00A0";
}
