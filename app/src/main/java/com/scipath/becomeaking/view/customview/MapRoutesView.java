package com.scipath.becomeaking.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import com.scipath.becomeaking.R;

import java.util.ArrayList;
import java.util.List;


public class MapRoutesView extends View {

    // Variables
    private final Paint paint = new Paint();
    private final List<PointF> connections = new ArrayList<>();


    public MapRoutesView(Context context) {
        super(context);
        init();
    }

    public MapRoutesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint.setColor(getContext().getColor(R.color.dark_green));
        paint.setStrokeWidth(8f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    public void addConnection(float startX, float startY, float endX, float endY) {
        connections.add(new PointF(getWidth() * startX, getHeight() * startY));
        connections.add(new PointF(getWidth() * endX, getHeight() * endY));
        invalidate();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < connections.size(); i += 2) {
            PointF start = connections.get(i);
            PointF end = connections.get(i + 1);
            canvas.drawLine(start.x, start.y, end.x, end.y, paint);
        }
    }
}