package com.ragnarok.staticlayoutview.hilo;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hilo on 15/8/3.
 * <p/>
 * Drscription:
 */
public class StaticTextView extends View {

    private Layout layout = null;
    private int width;
    private int height;

    public StaticTextView(Context context) {
        super(context);
    }

    public StaticTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StaticTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
        if (this.layout.getWidth() != width || this.layout.getHeight() != height) {
            width = this.layout.getWidth();
            height = this.layout.getHeight();
            requestLayout();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        if (layout != null)
            layout.draw(canvas, null, null, 0);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (layout != null)
            setMeasuredDimension(layout.getWidth(), layout.getHeight());
        else
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
