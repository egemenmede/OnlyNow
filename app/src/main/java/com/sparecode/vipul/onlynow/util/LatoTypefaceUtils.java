/*
 * Copyright 2014 Evgeny Shishkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sparecode.vipul.onlynow.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.sparecode.vipul.onlynow.R;


/**
 * Utilities for working with roboto text views.
 *
 * @author Evgeny Shishkin
 */
public final class LatoTypefaceUtils {

    private LatoTypefaceUtils() {
    }

    /**
     * Typeface initialization using the attributes. Used in RobotoTextView constructor.
     *
     * @param textView The roboto text view
     * @param context  The context the widget is running in, through which it can
     *                 access the current theme, resources, etc.
     * @param attrs    The attributes of the XML tag that is inflating the widget.
     */
    public static void initView(@NonNull TextView textView, @NonNull Context context, @Nullable AttributeSet attrs) {
        Typeface typeface;

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LatoTextView);
            typeface = typefaceFromAttrs(context, a);
            a.recycle();
        } else {
            typeface = LatoTypefaceManager.obtainTypeface(context, LatoTypefaceManager.Typeface.LATO_REGULAR);
        }

        setUp(textView, typeface);
    }

    @NonNull
    public static Typeface typefaceFromAttrs(@NonNull Context context, @NonNull TypedArray a) {
        Typeface typeface;
        if (a.hasValue(R.styleable.LatoTextView_typeface)) {
            int typefaceValue = a.getInt(R.styleable.LatoTextView_typeface, LatoTypefaceManager.Typeface.LATO_REGULAR);
            typeface = LatoTypefaceManager.obtainTypeface(context, typefaceValue);
        } else {
            int fontFamily = a.getInt(R.styleable.LatoTextView_fontFamily, LatoTypefaceManager.FontFamily.LATO);
            int textWeight = a.getInt(R.styleable.LatoTextView_textWeight, LatoTypefaceManager.TextWeight.NORMAL);
            int textStyle = a.getInt(R.styleable.LatoTextView_textStyle, LatoTypefaceManager.TextStyle.NORMAL);
            typeface = LatoTypefaceManager.obtainTypeface(context, fontFamily, textWeight, textStyle);
        }
        return typeface;
    }

    /**
     * Set up typeface for TextView. Wrapper over {@link TextView#setTypeface(Typeface)}
     * for making the font anti-aliased.
     *
     * @param textView The text view
     * @param typeface The specify typeface
     */
    public static void setUp(@NonNull TextView textView, @NonNull Typeface typeface) {
        textView.setPaintFlags(textView.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        textView.setTypeface(typeface);
    }

    /**
     * Set up typeface for Paint.
     *
     * @param paint    The paint
     * @param typeface The specify typeface
     */
    public static void setUp(@NonNull Paint paint, @NonNull Typeface typeface) {
        paint.setFlags(paint.getFlags() | Paint.SUBPIXEL_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        paint.setTypeface(typeface);
    }
}