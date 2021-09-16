package com.example.myfish;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FishDrawable extends Drawable {
    private Path mPath;
    private Paint mPaint;

    //除鱼身外的所有透明度
    private final static int  OTHER_ALPHA = 110;
    //鱼身透明度
    private final static int  BODY_ALPHA = 160;
    //转弯更自然的重心（身体的重心点）
    private PointF middlePoint;
    private PointF headPoint;



    //鱼的主角度
    private float fishMainAngle = 90;



    public final static float HEAD_RADUIS = 100;

    //鱼身的长度
    private  final static float BODY_LENGTH = 3.2f * HEAD_RADUIS;
    //----------鱼鳍------------
    //寻找鱼鳍开始点的线长
    private  final static float FIND_FINS_LENGTH = 0.9f * HEAD_RADUIS;
    //鱼鳍
    //鱼鳍的长度
    private  final static float FINS_LENGTH = 1.3f * HEAD_RADUIS;
    //----------鱼尾------------
    //尾部大圆的半径（圆心就是身体底部的中点）
    private  final static float BIG_CIRCLE_RADIUS = 0.7f * HEAD_RADUIS;

    //尾部中园的半径
    private  final static float MIDDLE_CIRCLE_RADIUS = 0.6f * BIG_CIRCLE_RADIUS;
    //尾部小园的半径
    private  final static float SMALL_CIRCLE_RADIUS = 0.4f * MIDDLE_CIRCLE_RADIUS;

    //寻找尾部中园圆心的线长
    private  final static float FIND_MIDDLE_CIRCLE_LENGTH = BIG_CIRCLE_RADIUS + MIDDLE_CIRCLE_RADIUS;

    //寻找尾部小园圆心的线长
    private  final static float FIND_SMALL_CIRCLE_LENGTH = MIDDLE_CIRCLE_RADIUS * (0.4f + 2.7f);

    //寻找大三角形底边中心点的线长
    private  final static float FIND_TRIANGLE_LENGTH = MIDDLE_CIRCLE_RADIUS * 2.7f;

    private float currentValue = 0;




    public FishDrawable() {
        init();
    }

    private void init() {
        mPath = new Path();//路径
        mPaint = new Paint();//画笔
        mPaint.setStyle(Paint.Style.FILL);//画笔类型
        mPaint.setARGB(OTHER_ALPHA,244,92,71);//设置颜色
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);//防抖

        middlePoint = new PointF(4.19f*HEAD_RADUIS,4.19f*HEAD_RADUIS);

      //  ValueAnimator valueAnimator = ValueAnimator.ofFloat(-1,1);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,360);
        valueAnimator.setDuration(1000);
       // valueAnimator.setRepeatMode(valueAnimator.REVERSE);
        valueAnimator.setRepeatMode(valueAnimator.RESTART);
        valueAnimator.setRepeatCount(valueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

               currentValue = (float) animation.getAnimatedValue();
               invalidateSelf();
            }
        });
        valueAnimator.start();
    }

    public static PointF caculatePoint(PointF startPoint,float length,float angle){
        float deltaX = (float)(Math.cos(Math.toRadians(angle)) * length);
        float deltaY = (float)(Math.sin(Math.toRadians(angle - 180)) * length);

        return new PointF(startPoint.x + deltaX,startPoint.y + deltaY);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        //float fishAngle = fishMainAngle + currentValue * 20;
        float fishAngle = (float) (fishMainAngle + Math.sin(Math.toRadians(currentValue))  * 10);
        //绘制鱼头
        // headPoint = caculatePoint(middlePoint,BODY_LENGTH/2,fishAngle);
        canvas.drawCircle(headPoint.x,headPoint.y,HEAD_RADUIS,mPaint);
        //绘制右鱼鳍
        PointF rightFinspoint =  caculatePoint(headPoint,FIND_FINS_LENGTH,fishAngle - 110);
        makefins(canvas,rightFinspoint,fishAngle,true);
        //绘制左鱼鳍
        PointF leftFinspoint =  caculatePoint(headPoint,FIND_FINS_LENGTH,fishAngle + 110);
        makefins(canvas,leftFinspoint,fishAngle,false);

        //身体底部的中心点
        PointF bodyBottomCenterPoint = caculatePoint(headPoint,BODY_LENGTH,fishAngle -180);
        PointF middleCircleCenterPoint = makeSegment(canvas,bodyBottomCenterPoint,BIG_CIRCLE_RADIUS,MIDDLE_CIRCLE_RADIUS,
                FIND_MIDDLE_CIRCLE_LENGTH,fishAngle,true);

//        PointF middleCircleCenterPoint = caculatePoint(bodyBottomCenterPoint,
//                FIND_MIDDLE_CIRCLE_LENGTH,fishAngle - 180);
        makeSegment(canvas,middleCircleCenterPoint,MIDDLE_CIRCLE_RADIUS,SMALL_CIRCLE_RADIUS,
                FIND_SMALL_CIRCLE_LENGTH,fishAngle,false);

        makeTriangle(canvas,middleCircleCenterPoint,FIND_TRIANGLE_LENGTH,
                BIG_CIRCLE_RADIUS,fishAngle);
        makeTriangle(canvas,middleCircleCenterPoint,FIND_TRIANGLE_LENGTH - 10,
                BIG_CIRCLE_RADIUS -20,fishAngle);

        makeBody(canvas,headPoint,bodyBottomCenterPoint,fishAngle);

    }

    private  void makeTriangle(Canvas canvas,PointF startPoint,
                               float findCenterLength,float findEdgeLength,float fishAngel){

        float TriangleAngle = (float) (fishMainAngle + Math.sin(Math.toRadians(currentValue)*3)  * 30);

        PointF centerPoint = caculatePoint(startPoint,findCenterLength,TriangleAngle -180);
        PointF leftPoint = caculatePoint(centerPoint,findEdgeLength,TriangleAngle + 90);
        PointF rightPoint = caculatePoint(centerPoint,findEdgeLength,TriangleAngle - 90);

        mPath.reset();
        mPath.moveTo(startPoint.x,startPoint.y);
        mPath.lineTo(leftPoint.x,leftPoint.y);
        mPath.lineTo(rightPoint.x,rightPoint.y);
        canvas.drawPath(mPath,mPaint);

    }

    private void makeBody(Canvas canvas,PointF headPoint,PointF bodyBottomCenterPoint,float fisheAnagle){

        PointF topLeftPoint = caculatePoint(headPoint,HEAD_RADUIS,fisheAnagle + 90);
        PointF topRightPoint = caculatePoint(headPoint,HEAD_RADUIS,fisheAnagle - 90);
        PointF bottomLeftPoint = caculatePoint(bodyBottomCenterPoint,BIG_CIRCLE_RADIUS,
                fisheAnagle + 90);
        PointF bottomRightPoint = caculatePoint(bodyBottomCenterPoint,BIG_CIRCLE_RADIUS,
                fisheAnagle - 90);

        //二阶贝塞尔曲线的控制点，决定鱼的胖瘦
        PointF controlLeft = caculatePoint(headPoint,BODY_LENGTH*0.56f,
                fisheAnagle + 130);
        PointF controlRight = caculatePoint(headPoint,BODY_LENGTH*0.56f,
                fisheAnagle - 130);

        mPath.reset();
        mPath.moveTo(topLeftPoint.x,topLeftPoint.y);
        mPath.quadTo(controlLeft.x,controlLeft.y,bottomLeftPoint.x,bottomLeftPoint.y);
        mPath.lineTo(bottomRightPoint.x, bottomRightPoint.y);
        mPath.quadTo(controlRight.x,controlRight.y, topRightPoint.x, topRightPoint.y);
        mPaint.setAlpha(BODY_ALPHA);
        canvas.drawPath(mPath,mPaint);


    }


    private PointF makeSegment(Canvas canvas,PointF bottomCenterPoint,float bigRadius,
                              float smallRaduis,float findSmallCirclelength,float fishAngle,
                              boolean hasBigCircle){
        float segmentAngle;

        if(hasBigCircle){
            segmentAngle = (float) (fishMainAngle + Math.cos(Math.toRadians(currentValue)*2)  * 20);
        }else {
            segmentAngle = (float) (fishMainAngle + Math.sin(Math.toRadians(currentValue)*3)  * 30);
        }

        //梯形上底的中心点
        PointF upperCenterPoint = caculatePoint(bottomCenterPoint,findSmallCirclelength,segmentAngle -180);
        //梯形的四个点
        PointF bottomLeftPoint = caculatePoint(bottomCenterPoint,bigRadius,segmentAngle + 90);
        PointF bottomRightPoint = caculatePoint(bottomCenterPoint,bigRadius,segmentAngle - 90);
        PointF upperLeftPoint = caculatePoint(upperCenterPoint,smallRaduis,segmentAngle + 90);
        PointF upperRightPoint = caculatePoint(upperCenterPoint,smallRaduis,segmentAngle - 90);

        if(hasBigCircle){
            canvas.drawCircle(bottomCenterPoint.x,bottomCenterPoint.y,bigRadius,mPaint);
        }
        canvas.drawCircle(upperCenterPoint.x,upperCenterPoint.y,smallRaduis,mPaint);

        mPath.reset();
        mPath.moveTo(bottomLeftPoint.x, bottomLeftPoint.y);
        mPath.lineTo(upperLeftPoint.x,upperLeftPoint.y);
        mPath.lineTo(upperRightPoint.x,upperRightPoint.y);
        mPath.lineTo(bottomRightPoint.x,bottomRightPoint.y);
        canvas.drawPath(mPath,mPaint);

        return upperCenterPoint;

    }

    private void makefins(Canvas canvas, PointF startPoint, float fishAngle,boolean isrightFin) {

        float controlAngle = 115;
        PointF endPoint = caculatePoint(startPoint,FINS_LENGTH,fishAngle - 180);

        PointF controlPoint =  caculatePoint(startPoint,1.8f*FINS_LENGTH,
                isrightFin ? fishAngle - controlAngle: fishAngle + controlAngle);
        mPath.reset();
        mPath.moveTo(startPoint.x, startPoint.y);
        mPath.quadTo(controlPoint.x,controlPoint.y, endPoint.x, endPoint.y);
        canvas.drawPath(mPath,mPaint);


    }


    @Override
    public void setAlpha(int alpha) {
         mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
         mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicHeight() {
        return (int)(8.38f * HEAD_RADUIS);
    }

    @Override
    public int getIntrinsicWidth() {
        return (int)(8.38f * HEAD_RADUIS);
    }

    public PointF getMiddlePoint() {
        return middlePoint;
    }

    public PointF getHeadPoint() {
        return headPoint;
    }

    public void setFishMainAngle(float fishMainAngle) {
        this.fishMainAngle = fishMainAngle;
    }
}
