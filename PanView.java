package com.example.zk_01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * data:2018/11/5
 * author: HJL (磊)
 * function:
 */
public class PanView extends View implements View.OnClickListener {

    private int centerX;
    private int centerY;
    private Paint mPaint;
    private Boolean isStart = false;
    private int[] colors = {
            Color.parseColor("#EA2000"),
            Color.parseColor("#0188FB"),
            Color.parseColor("#FFCD40"),
            Color.parseColor("#DD5044"),
            Color.parseColor("#159F5C"),
            Color.parseColor("#FFE793"),
    };
    private String[] count = {
            "一等奖", "二等奖", "三等奖", "四等奖", "五等奖", "没有奖"
    };
    private RotateAnimation animation;

    public PanView(Context context) {
        this(context, null);
    }

    public PanView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public PanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取屏幕信息
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int widthPixels = metrics.widthPixels;//宽
        int heightPixels = metrics.heightPixels;//高

        //中心点
        centerX = widthPixels / 2;
        centerY = heightPixels / 2;

        //初始化画笔
        initPaint();

        initAnim();

        this.setOnClickListener(this);

    }

    //动画
    private void initAnim() {

        animation = new RotateAnimation(0f, 360f, centerX, centerY);
        animation.setRepeatCount(-1);
        animation.setFillAfter(true);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(50);
        mPaint.setAntiAlias(true);
    }

    //测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(1500, 1500);
    }

    //绘制

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //移动画布中心点
        canvas.translate(centerX, centerY);

        //画弧
        RectF rectF = new RectF(-300, -300, 300, 300);
        float start = 60;
        for (int i = 0; i < 6; i++) {
            mPaint.setColor(colors[i]);
            canvas.drawArc(rectF, start * i, 60, true, mPaint);
        }

        //画中间的圆
        mPaint.setColor(Color.RED);
        canvas.drawCircle(0, 0, 100, mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(30);

        //给中间的圆填字
        Rect rect = new Rect();

        mPaint.getTextBounds("start", 0, 5, rect);
        int width = rect.width();
        int height = rect.height();
        canvas.drawText("start", -width / 2, height / 2, mPaint);

        //给圆弧填字
        RectF rectF1 = new RectF(-200, -200, 200, 200);

        for (int i = 0; i < 6; i++) {
            mPaint.setColor(Color.WHITE);
            Path path = new Path();
            path.addArc(rectF1, start * i + 15, 60);
            canvas.drawTextOnPath(count[i], path, 0, 0, mPaint);
        }

    }

    @Override
    public void onClick(View v) {
        if (!isStart) {
            isStart = true;
            animation.setDuration(1000);
            animation.setInterpolator(new LinearInterpolator());
            startAnimation(animation);
        } else {
            isStart = false;
            stopAnim();
        }
    }

    //停止转动
    private void stopAnim() {
        clearAnimation();
    }
}
