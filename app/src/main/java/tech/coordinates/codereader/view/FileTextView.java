package tech.coordinates.codereader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/13.
 */
public class FileTextView extends TextView {
    /**
     * 默认画布颜色和画笔填充颜色
     */
    private static final int DEFAULT_CANVAS_COLOR = Color.WHITE;
    private static final int DEFAULT_PAINT_COLOR = Color.BLUE;
    /**
     * 默认半径大小,最好根据当前TextSize的大小来决定
     */
    private Paint paint;
    private Canvas canvas;

    private int fill_color;
    private int paint_color;

    /**
     * 用于记录该文件是第几级目录
     */
    private int stages;
    /**
     * 当前TextView的坐标
     */
    private float tv_posX;
    private float tv_posY;
    /**
     * TextView左边圆半径,根据TextSize来决定
     */
    private float radius;

    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }

    /**
     * current_path用于保存当前路径，便于找到子目录或者打开文件
     */
    private String currentPath = "";

    public FileTextView(Context context, AttributeSet attrs, int stages) {
        this(context, attrs, DEFAULT_CANVAS_COLOR, DEFAULT_PAINT_COLOR, stages);
    }

    public FileTextView(Context context, AttributeSet attrs, int fill_color, int stages) {
        this(context, attrs, fill_color, DEFAULT_PAINT_COLOR, stages);
    }

    public String getCurrentPath() {
        return currentPath;
    }

    public void setPaint_color(int paint_color) {
        this.paint_color = paint_color;
    }

    public FileTextView(Context context, AttributeSet attrs, int fill_color, int paint_color, int stages) {
        super(context, attrs);
        String str_def = "";
        for (int i = 0; i < 4 + 4 * stages; i++) {
            str_def += " ";
        }
        this.setText(str_def);
        this.fill_color = fill_color;
        this.paint_color = paint_color;
        this.tv_posX = this.getX();
        this.tv_posY = this.getY();
        this.stages = stages;
    }

    public void setFill_color(int fill_color) {
        this.fill_color = fill_color;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        paint = new Paint();
        paint.setColor(paint_color);
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        this.radius = (float) (getTextSize() * 0.3);
        canvas.drawCircle(tv_posX + 2 * radius + 3 * stages * radius, tv_posY + 2 * radius, radius, paint);

    }

    public int getFill_color() {
        return fill_color;
    }

    public int getPaint_color() {
        return paint_color;
    }

    public float getRadius() {
        return radius;
    }
}
