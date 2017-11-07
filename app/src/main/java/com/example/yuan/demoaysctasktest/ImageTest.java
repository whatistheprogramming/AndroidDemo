package com.example.yuan.demoaysctasktest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class ImageTest extends AppCompatActivity
{

    private ImageView imageView;

    private ProgressBar progressBar;

    private static final String URL = "http://www.imooc.com/static/img/index/logo_new.png";

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_test);

        imageView = (ImageView) findViewById(R.id.image_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        //调用AsyncTask的execute()方法就可以开始进行异步操作
        new MyAsyncTask().execute(URL); //这里参数是可变参数
    }

    /**
     * 第一个参数是传入的类型，这里是url
     * 第二个是进度
     * 第三个是返回的类型
     */
    class MyAsyncTask extends AsyncTask<String, Void, Bitmap>
    {

        /**
         * 线程开始前的操作
         * 通常做一些初始化操作
         */
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            //图片加载前显示progressbar
            progressBar.setVisibility(View.VISIBLE);
        }

        /**
         * 这是耗时操作
         * 里面内容都实现在子线程中
         */
        @Override
        protected Bitmap doInBackground(String... params)
        {
            //从params中获取对应URL
            String url = params[0];
            Bitmap bitmap = null;
            URLConnection connection;
            InputStream is;
            try
            {
                Thread.sleep(3000);

                /**
                 * 将url解析成bitmap
                 */
                connection = new URL(url).openConnection();
                //获取输入流
                is = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                //通过decodeStream()方法,将输入流解析成bitmap
                bitmap = BitmapFactory.decodeStream(bis);
                //关闭输入流
                is.close();
                bis.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            //返回bitmap
            return bitmap;
        }

        /**
         * 这是处理完doInBackground后的操作
         * 这里实在主线程操作，所以可以对UI进行操作
         */
        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            super.onPostExecute(bitmap);
            //这里会得到doInBackground返回的bitmap
            //所以这里进行设置UI操作
            imageView.setImageBitmap(bitmap);
            //同时将progressbar隐藏
            progressBar.setVisibility(View.GONE);

        }
    }
}
