package com.huawen.huawenface.sdk.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;


import com.huawen.huawenface.sdk.Config;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ZengYinan.
 * Date: 2018/9/7 11:53
 * Email: 498338021@qq.com
 * Desc:
 */
public class ImageUtils {

    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;

    }

    public static Bitmap getPicFromBytes(byte[] bytes,
                                         BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
                        opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        return newBmp;
    }

    /**
     * 把Bitmap转Byte
     */
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 把字节数组保存为一个文件
     */
    public static File getFileFromBytes(byte[] b, String outputFile) {
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }


    /**
     * 保存图片到指定文件夹
     *
     * @param bmp
     * @param filename
     * @return
     */
    public static String saveBitmapTofile(Bitmap bmp, String filename) {
        if (bmp == null || filename == null)
            return null;
        Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
        int quality = 100;
        OutputStream stream = null;
        String path = Config.File.IMAGE_PATH;
        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }
        File imageFile=new File(path+filename);
        if(imageFile.exists()){
            try {
                imageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        try {
            stream = new FileOutputStream(imageFile);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        boolean result=bmp.compress(format, quality, stream);
        return result?imageFile.getAbsolutePath():null;
    }

}
