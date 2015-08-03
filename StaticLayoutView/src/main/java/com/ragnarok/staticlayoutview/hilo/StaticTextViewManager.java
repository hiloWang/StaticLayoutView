package com.ragnarok.staticlayoutview.hilo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.view.WindowManager;

import java.util.List;

/**
 * Created by hilo on 15/8/3.
 * <p/>
 * Drscription:
 */
public class StaticTextViewManager {

    private StaticLayout[] layout;
    private TextPaint textPaint;
    private TextDirectionHeuristic textDir;
    private Layout.Alignment alignment;

    private Canvas dummyCanvas;

    private int screenWidth;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void initLayout(Context context, List<String> charSequence, int size) {

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.density = context.getResources().getDisplayMetrics().density;
        textPaint.setTextSize(textPaint.density * 25); // dp 转 px

        textDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
        alignment = Layout.Alignment.ALIGN_NORMAL;
        screenWidth = getScreenWidth(context);

        dummyCanvas = new Canvas();
        layout = new StaticLayout[size];
        for (int i = 0; i < layout.length; i++) {
            layout[i] = new StaticLayout(charSequence.get(i).toString(), textPaint, screenWidth, alignment, 1.0f, 0f, true);
            layout[i].draw(dummyCanvas);
        }
    }

    public StaticLayout getLayout(int index) {
        return layout[index];
    }

    public static StaticTextViewManager INSTANCE = null;

    public static StaticTextViewManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new StaticTextViewManager();
        return INSTANCE;
    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        Point size = new Point();
        windowManager.getDefaultDisplay().getSize(size);

        return size.x;
    }
}
