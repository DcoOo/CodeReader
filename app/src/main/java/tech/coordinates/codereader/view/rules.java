package tech.coordinates.codereader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by Administrator on 2016/9/16.
 */
public class Rules extends View {
    private float x;
    private float y;
    private Canvas canvas;
    private Paint paint;
    private DisplayMetrics displayMetrics;

    private float screen_width;

    public Rules(Context context, AttributeSet attrs) {
        this(context,attrs,Color.BLACK);
    }
    public Rules(Context context, AttributeSet attrs, int color){
        super(context,attrs);
        this.x = this.getPivotX();
        this.y = this.getPivotY();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStrokeWidth(5);
        displayMetrics = new DisplayMetrics();
        screen_width = displayMetrics.heightPixels;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        drawRules(paint);
    }

    private void drawRules(Paint paint){
        Path path = new Path();
        path.moveTo(x,y);
        path.lineTo(x+screen_width,y);
        path.close();
        canvas.drawPath(path,paint);
    }
}
