package tech.coordinates.codereader.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import tech.coordinates.codereader.R;
import tech.coordinates.codereader.activity.SettingActivity;

/**
 * Created by Administrator on 2016/9/16.
 */
public class Rules extends View {
    private float x;
    private float y;
    private Canvas canvas;
    private Paint paint;
    private DisplayMetrics displayMetrics;
    private int color;
    private float screen_width;

    public void setColor(int color) {
        this.color = color;
    }

    public Rules(Context context, AttributeSet attrs) {
        this(context,attrs,Color.BLACK);
    }
    public Rules(Context context, AttributeSet attrs, int color){
        super(context,attrs);
        this.x = this.getPivotX();
        this.y = this.getPivotY();

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyRules);
        //设置默认值
        color = ta.getColor(R.styleable.MyRules_rules_color,Color.BLACK);
        ta.recycle();
        setColor(color);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStrokeWidth(100);
        canvas.drawCircle(x,y,3000,paint);
//        drawRules(paint);
    }

    private void drawRules(Paint paint){
        Log.d("Debug","X:"+x+"Y:"+y+"Screen:"+screen_width);
    }
}
