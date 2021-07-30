package com.daasuu.gpuv.egl.filter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GlWatermarkFilter extends GlOverlayFilter {

    private Bitmap bitmap;
    private Position position = Position.LEFT_TOP;
    private float margin = 0f;

    public GlWatermarkFilter(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


    public GlWatermarkFilter(Bitmap bitmap, Position position, float margin) {
        this.bitmap = bitmap;
        this.position = position;
        this.margin = margin;
    }

    @Override
    protected void drawCanvas(Canvas canvas, long presentationTimeUs) {
        if (bitmap != null && !bitmap.isRecycled()) {
            switch (position) {
                case LEFT_TOP:
                    canvas.drawBitmap(bitmap, margin, margin, null);
                    break;
                case LEFT_BOTTOM:
                    canvas.drawBitmap(bitmap, margin, canvas.getHeight() - bitmap.getHeight() - margin, null);
                    break;
                case RIGHT_TOP:
                    canvas.drawBitmap(bitmap, canvas.getWidth() - bitmap.getWidth() - margin, margin, null);
                    break;
                case RIGHT_BOTTOM:
                    canvas.drawBitmap(bitmap, canvas.getWidth() - bitmap.getWidth() - margin, canvas.getHeight() - bitmap.getHeight() - margin, null);
                    break;
            }
        }
    }

    public enum Position {
        LEFT_TOP,
        LEFT_BOTTOM,
        RIGHT_TOP,
        RIGHT_BOTTOM
    }
}
