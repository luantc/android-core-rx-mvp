package core.android.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ReplacementSpan;
import android.text.style.ScaleXSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.EditText;

import core.android.R;
import core.android.util.DPConstant;
import core.android.util.DPUtils;
import core.android.util.FontCache;


public class BaseEditText extends EditText {
    private static final String TAG = BaseEditText.class.getSimpleName();
    private static final String XML_NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android";
    // Original text value (before spacing)
    private CharSequence originalText = null;
    // Flag to present text in ALL CAPS
    private boolean textAllCaps = false;
    // Spacing value (dp)
    private float spacing = 0f;
    // Density (dp) scale based on 320 screen resolution
    private float dpScale = 1f;
    // Pixel scale based on 320 screen resolution
    private float pxScale = 1f;
    // Padding start value
    private int paddingStart = 0;

    private float originalTextSize;

    public BaseEditText(Context context) {
        super(context);
    }

    public BaseEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        checkCustomAttributes(context, attrs);
    }

    public BaseEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        checkCustomAttributes(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        checkCustomAttributes(context, attrs);
    }

    private void checkCustomAttributes(Context context, AttributeSet attrs) {
        dpScale = DPUtils.scaleDensity(context);
        pxScale = DPUtils.scalePixel(context);
        paddingStart = ViewCompat.getPaddingStart(this);
        originalTextSize = getTextSize();
        // originalText = getText();
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DPGText);

            int customStyle = a.getInt(R.styleable.DPGText_fontStyle, 0);
            setCustomFont(getContext(), DPConstant.FONT_STYLES.get(customStyle));

            // Font size
            float fontSize = a.getFloat(R.styleable.DPGText_fontSize, 0f);
            setFontSize(fontSize);

            // Hint size
            float fontSizeHint = a.getFloat(R.styleable.DPGText_fontSizeHint, 0f);
            setFontSizeHint(fontSize, fontSizeHint);

            // Check if textAllCaps flag is set
            textAllCaps = attrs.getAttributeBooleanValue(XML_NAMESPACE_ANDROID, "textAllCaps", false);

            // Text spacing
            spacing = a.getFloat(R.styleable.DPGText_fontSpacing, 0f);
            applyLetterSpacing();
            // Check more attributes here

            // Recycle attributes array
            a.recycle();
        }
    }

    public boolean setCustomFont(Context context, String customFont) {
        Typeface tf = FontCache.get(context, "fonts/" + customFont);
        if (tf != null) {
            setTypeface(tf);
            return true;
        } else {
            return false;
        }
    }

    public void setFontSize(float fontSize) {
        if (fontSize > 0f) {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize * dpScale);
        }
    }

    public void setFontSizeHint(final float fontSize, final float fontSizeHint) {
        if (fontSize > 0f && fontSizeHint > 0f) {
            // Initial state
            if (TextUtils.isEmpty(getText())) {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSizeHint * dpScale);
            } else {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize * dpScale);
            }

            setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSizeHint * dpScale);
            addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // do nothing
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // Update text size if changed
                    if (TextUtils.isEmpty(getText())) {
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSizeHint * dpScale);
                    } else {
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize * dpScale);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    // do nothing
                }
            });
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        // originalText = text;
        applyLetterSpacing();
    }

    @Override
    public Editable getText() {
        // return !TextUtils.isEmpty(originalText) ? (Editable) originalText : super.getText();
        return super.getText();
    }

    public void setSpacing(float spacing) {
        this.spacing = spacing;
        applyLetterSpacing();
    }

    private void applyLetterSpacing() {
        if (spacing == 0f) {
            return;
        }

        // Check if current SDK version is after LOLLIPOP (21), then use letter spacing instead
        if (!isInEditMode() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setLetterSpacing(spacing * dpScale / 11); // 'EM' unit: 1 'EM' = 11 dp
            return;
        }

        // Apply letter spacing for device with API before 21
        if (TextUtils.isEmpty(originalText)
                || originalText.length() < 2) {
            return;
        }

        if (this instanceof EditText) {
            return;
        }

        // Make ALL CAPS flag false first
        setAllCaps(false);

        // Create text builder with non breaking space letter between
        StringBuilder builder;
        if (textAllCaps) {
            builder = new StringBuilder(originalText.toString().toUpperCase().replaceAll("(.)", DPConstant.NON_BREAKING_SPACE + "$1"));
        } else {
            builder = new StringBuilder(originalText.toString().replaceAll("(.)", DPConstant.NON_BREAKING_SPACE + "$1"));
        }

        // Calculate scale x value from spacing attribute value
        float nonBreakingSpace = getPaint().measureText(DPConstant.NON_BREAKING_SPACE);
        float scale = spacing * pxScale / nonBreakingSpace;

        // Check if scale x value is in [0.9, 1.1] area, use non breaking space characters and skip scaling them
        if (scale > 0.9 && scale < 1f) {
            super.setText(builder, BufferType.NORMAL);
            return;
        }

        // Scale all non breaking space letters by provided spacing value
        SpannableString finalText = new SpannableString(builder);
        if (builder.length() > 1) {
            String newline = System.getProperty("line.separator");
            for (int i = 0; i < builder.length(); i += 2) {
                // Check if current character is new line character, skip to check next character
                if (builder.substring(i, i + 1).equalsIgnoreCase(newline)) {
                    i--;
                    continue;
                }
                // Make spannable for non breaking character
                if (builder.substring(i, i + 1).equalsIgnoreCase(DPConstant.NON_BREAKING_SPACE)) {
                    finalText.setSpan(new ScaleXSpan(scale),
                            i,
                            i + 1,
                            Spannable.SPAN_INCLUSIVE_INCLUSIVE
                    );
                }
            }
        }
        // Set text with buffer type SPANNABLE
        super.setText(finalText, BufferType.SPANNABLE);
        // Pull text view to the left to hide non breaking space characters in every line
        setPadding(paddingStart - (int) (spacing * pxScale),
                getPaddingTop(),
                getPaddingRight(),
                getPaddingBottom());
    }

    private static class TrackingSpan extends ReplacementSpan {
        private float trackingPx;

        TrackingSpan(float tracking) {
            trackingPx = tracking;
        }

        @Override
        public int getSize(@NonNull Paint paint, CharSequence text,
                           int start, int end, Paint.FontMetricsInt fm) {
            return (int) (paint.measureText(text, start, end)
                    + trackingPx * (end - start - 1));
        }

        @Override
        public void draw(@NonNull Canvas canvas, CharSequence text,
                         int start, int end, float x, int top, int y,
                         int bottom, @NonNull Paint paint) {
            float dx = x;
            for (int i = start; i < end; i++) {
                canvas.drawText(text, i, i + 1, dx, y, paint);
                dx += paint.measureText(text, i, i + 1) + trackingPx;
            }
        }
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
    }

    public float getOriginalTextSize(){
        return originalTextSize;
    }
}
