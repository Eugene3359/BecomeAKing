package com.scipath.becomeaking.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;


public class DrawableUtility {

    /**
     * The method makes Drawable repeatable
     *
     * @param drawable  The Drawable that must be repeatable
     * @return          The repeatable Drawable
     */
    public static Drawable createTiledDrawable(Context context, Drawable drawable) {
        if (drawable == null)
            return null;

        // If drawable is color - return drawable
        if (drawable instanceof ColorDrawable)
            return drawable;

        BitmapDrawable bitmapDrawable;
        if (drawable instanceof BitmapDrawable) {
            bitmapDrawable = (BitmapDrawable) drawable;
        } else {
            // Convert any drawable to a bitmap first
            int width = drawable.getIntrinsicWidth() > 0 ? drawable.getIntrinsicWidth() : 1;
            int height = drawable.getIntrinsicHeight() > 0 ? drawable.getIntrinsicHeight() : 1;
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
        }

        // Make bitmapDrawable repeatable
        bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        return bitmapDrawable;
    }


    /**
     * The method creates Drawable with set borderColor and backgroundColor
     *
     * @param borderColor       The int that contains colors id
     * @param backgroundColor   The int that contains colors id
     * @return                  The Drawable with set borderColor and backgroundColor
     */
    public static Drawable createBorderDrawable(Context context, int borderColor, int backgroundColor) {
        // Create a GradientDrawable for the border
        GradientDrawable drawable = new GradientDrawable();
        // Set shape
        drawable.setShape(GradientDrawable.RECTANGLE);
        // Set border
        if (borderColor != 0) {
            // Convert 2dp to px
            int width = (int) (2 * context.getResources().getDisplayMetrics().density);
            // Set 2dp border
            drawable.setStroke(width, borderColor);
        }
        // Set background color
        drawable.setColor(backgroundColor);

        return drawable;
    }


    /**
     * The method creates Drawable with set borderColor, backgroundColor and backgroundDrawable
     *
     * @param borderColor           The int that contains colors id
     * @param backgroundColor       The int that contains color id
     * @param backgroundDrawable    The Drawable that must be used as background
     * @return                      The Drawable with set borderColor, backgroundColor and backgroundDrawable
     */
    public static Drawable createBorderDrawable(Context context, int borderColor, int backgroundColor, Drawable backgroundDrawable) {
        // Create a GradientDrawable with set border color
        GradientDrawable borderDrawable = (GradientDrawable) createBorderDrawable(context, borderColor, backgroundColor);

        // Combine backgroundDrawable and borderDrawable in LayerDrawable
        Drawable[] layers = new Drawable[]{backgroundDrawable, borderDrawable};

        return new LayerDrawable(layers);
    }
}
