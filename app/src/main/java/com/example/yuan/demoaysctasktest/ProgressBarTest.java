package com.example.yuan.demoaysctasktest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProgressBarTest extends AppCompatActivity
{
    private ProgressBar progressBar;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_test);

        progressBar = (ProgressBar) findViewById(R.id.pg);
        textView = (TextView) findViewById(R.id.text_complete);

        //启动异步处理,这是就不需要传递任何参数了
        new MyAsyncTask().execute();
    }


    class MyAsyncTask extends AsyncTask<Void, Integer, Void>
    {


        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            textView.setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            /**
             * 这里用for循环模拟进度条的更新
             */
            for (int i = 0; i < 100; i++)
            {

                /**
                 *
                 * publishProgress()方法可以传入一个Integer
                 * 也就是前面指定的第二个参数，更新进度的类型
                 */
                publishProgress(i);

                try
                {
                    Thread.sleep(100);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            /**
             * 这里的Integer就是前面所指定的
             * 即publishProgress(i)传入的i就到了onProgressUpdate()这里
             */
            //设置进度条的进度
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            textView.setVisibility(View.VISIBLE);
        }
    }
}
