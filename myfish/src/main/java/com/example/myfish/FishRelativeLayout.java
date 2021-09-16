package com.example.myfish;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;

public class FishRelativeLayout extends RelativeLayout {

    private static final String TAG = "KIN";
    private Paint mPaint;
    private ImageView ivFish;
    private FishDrawable fishDrawable;

    private float touchx = 0;
    private float touchy = 0;


    private float ripple = 0;
    private float alpha = 0;


    public FishRelativeLayout(Context context) {
        this(context,null);
    }

    public FishRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FishRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setWillNotDraw(false);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);

        ivFish = new ImageView(context);

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ivFish.setLayoutParams(layoutParams);
        fishDrawable = new FishDrawable();
        ivFish.setImageDrawable(fishDrawable);
        addView(ivFish);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAlpha((int) alpha);
        canvas.drawCircle(touchx,touchy,ripple * 150,mPaint);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        touchx = event.getX();
        touchy = event.getY();

        ObjectAnimator objectAnimator = ObjectAnimator.
                ofFloat(this,"ripple",0,1f).setDuration(1000);
        objectAnimator.start();

        makeTrail();
        return super.onTouchEvent(event);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void makeTrail() {

        PointF fishRelativeMiddle = fishDrawable.getMiddlePoint();
        PointF fishMiddle = new PointF(ivFish.getX() + fishRelativeMiddle.x,
        ivFish.getY() + fishRelativeMiddle.y);

        PointF fishHead = new PointF(ivFish.getX() + fishDrawable.getHeadPoint().x,
        ivFish.getY() + fishDrawable.getHeadPoint().y);

        PointF touch = new PointF(touchx,touchy);

        float angle = includeAngle(fishMiddle,fishHead,touch);
        float delta = includeAngle(fishMiddle,new PointF(fishMiddle.x + 1,fishMiddle.y),fishHead);
        PointF controlPoint = FishDrawable.caculatePoint(fishMiddle,FishDrawable.HEAD_RADUIS*1.6f,angle + delta);

        Path path = new Path();
        path.moveTo(fishMiddle.x - fishRelativeMiddle.x,fishMiddle.y - fishRelativeMiddle.y);
        path.cubicTo(fishHead.x  - fishRelativeMiddle.x,fishHead.y - fishRelativeMiddle.y,
                controlPoint.x - fishRelativeMiddle.x,controlPoint.y - fishRelativeMiddle.y,
                touchx - fishRelativeMiddle.x,touchy - fishRelativeMiddle.y);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivFish,"x",
                "y",path);
        objectAnimator.setDuration(2000);

        PathMeasure pathMeasure = new PathMeasure(path,false);

        float[] tan = new float[2];
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();

                pathMeasure.getPosTan(pathMeasure.getLength()*fraction,null,tan);
                float angle = (float)Math.toDegrees(Math.atan2(-tan[1],tan[0]));
                fishDrawable.setFishMainAngle(angle);
            }
        });
        objectAnimator.start();











    }

    private static float includeAngle(PointF O,PointF A, PointF B)
    {
        float AOB = (A.x - O.x) * (B.x - O.x) + (A.y - O.y) * (B.y - O.y);

        float OALength = (float)Math.sqrt((A.x - O.x) * (A.x - O.x) + (A.y - O.y) * (A.y - O.y));

        float OBLength = (float)Math.sqrt((B.x - O.x) * (B.x - O.x) + (B.y - O.y) * (B.y - O.y));

        float cosAOB = AOB / (OALength * OBLength);

        float angelAOB = (float) Math.toDegrees(Math.acos(cosAOB));

        float direction = (A.y - B.y)/(A.x - B.x) - (O.y - B.y)/(O.x - B.x);

        if(direction == 0){

            if(AOB > 0){
                return  0;
            }else{
                return 180;
            }

        }else {

            if(direction > 0)
                return -angelAOB;
            else
                return angelAOB;

        }

    }

    public  void setRipple(float ripple){
        alpha = 150 *(1- ripple);

        Log.e(TAG, "setRipple: "+ripple);
        this.ripple = ripple;
        invalidate();
    }
}
