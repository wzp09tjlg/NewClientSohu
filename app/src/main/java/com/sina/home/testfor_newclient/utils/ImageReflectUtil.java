package com.sina.home.testfor_newclient.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.view.View;

/**
 * Created by Walter on 2016/1/10.
 */
public class ImageReflectUtil {
    //方向 左到右0 右到左1 上到下2 下到上3
    public static final int LEFT2RIGHT = 0;
    public static final int RIGHT2LEFT = 1;
    public static final int TOP2BOTTOM = 2;
    public static final int BOTTTOM2TOP = 3;
    //制作倒影的工具类
    public static Bitmap createReflectedImage(View view) {
        return createReflectedImage(view, 1f);
    }

    public static Bitmap createReflectedImage(View view, float s) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return createReflectedImage(bitmap, (int) (s * view.getHeight()));
    }

    public static Bitmap createHomeReflectedImage(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return createReflectedImage(bitmap, 100);
    }

    public static Bitmap createReflectedImage(Bitmap bitmap, int imageHeight) {
        if (bitmap == null || bitmap.isRecycled())
            return null;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        if (bitmap.isRecycled()) {
            return null;
        }
        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height - imageHeight, width, imageHeight, matrix, false);
        Canvas canvas = new Canvas(reflectionImage);
        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, 0, 0, reflectionImage.getHeight(), 0x33ffffff, 0x00ffffff, Shader.TileMode.MIRROR);
        paint.setShader(shader);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, 0, width, reflectionImage.getHeight(), paint);
        bitmap.recycle();
        return reflectionImage;
    }

    public static Bitmap createReflectBitmap(int width,int height,int directory){
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        LinearGradient shader = null;
        if(directory == LEFT2RIGHT ) //左到右
          shader = new LinearGradient(0, 0, bitmap.getWidth(), 0, 0xffffffff, 0x00ffffff, Shader.TileMode.MIRROR);
        else // 右到左
            shader = new LinearGradient(0, 0, bitmap.getWidth(), 0, 0x00ffffff, 0xffffffff, Shader.TileMode.MIRROR);
        paint.setShader(shader);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, 0, bitmap.getWidth(), bitmap.getHeight(), paint);
        return bitmap;  //返回bitmap
    }
}
