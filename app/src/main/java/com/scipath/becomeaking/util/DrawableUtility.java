package com.scipath.becomeaking.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;


public class DrawableUtility {

    public static BitmapDrawable makeBitmapDrawable(Drawable drawable, Context context) {
        if (drawable == null) return null;

        BitmapDrawable bitmapDrawable;
        if (drawable instanceof BitmapDrawable) {
            bitmapDrawable = (BitmapDrawable) drawable;
        } else {
            int width = drawable.getIntrinsicWidth() > 0 ? drawable.getIntrinsicWidth() : 1;
            int height = drawable.getIntrinsicHeight() > 0 ? drawable.getIntrinsicHeight() : 1;
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
        }

        return bitmapDrawable;
    }

    public static GradientDrawable makeGradientDrawable(int backgroundColor, int borderColor, float cornerRadius, Context context) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(backgroundColor);
        if (borderColor != Color.TRANSPARENT) {
            drawable.setStroke(dp(2, context), borderColor);
        }
        drawable.setCornerRadius(cornerRadius);
        return drawable;
    }

    public static Drawable makeDrawableTiled(Drawable drawable, Context context) {
        if (drawable == null) return null;
        if (drawable instanceof ColorDrawable) return drawable;

        BitmapDrawable bitmapDrawable = makeBitmapDrawable(drawable, context);
        bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        return bitmapDrawable;
    }

    public static Drawable applyCornerRadius(Drawable drawable, float radius, Context context) {
        if (drawable == null) return null;
        if (radius == 0f) return drawable;

        // TODO: BUGFIX
        /*
        if (drawable instanceof BitmapDrawable) {
            RoundedBitmapDrawable rounded = RoundedBitmapDrawableFactory
                    .create(context.getResources(), ((BitmapDrawable)drawable).getBitmap());
            rounded.setCornerRadius(radius);
            return rounded;
        }*/
        return drawable;
    }

    public static Drawable mergeLayers(Drawable backgroundDrawable, GradientDrawable gradientDrawable, Context context) {
        Drawable[] layers = new Drawable[] { backgroundDrawable, gradientDrawable };
        return new LayerDrawable(layers);
    }

    private static int dp(float value, Context context) {
        return (int) (value * context.getResources().getDisplayMetrics().density);
    }
}
