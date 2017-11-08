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

    private MyAsyncTask mTast;


    /**
     * AsyncTask注意点：
     * 1.必须在UI线程中创建AsyncTask实例
     * 2.必须在UI线程中调用AsyncTask的execute()方法
     * 3.重写四个方法是系统自动调用的，不应手动调用
     * 4.每个AsyncTask只能被执行一次，多次调用会引发异常
     * 5.只有doInBackground运行在其他线程，其他的方法都运行在主线程，也就可以进行UI的操作
     */

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_test);

        progressBar = (ProgressBar) findViewById(R.id.pg);
        textView = (TextView) findViewById(R.id.text_complete);

        //启动异步处理,这是就不需要传递任何参数了
        mTast = new MyAsyncTask();
        mTast.execute();
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        /**
         * 解决按back结束progressbar
         */
        //当不为空且正在运行时
        if (mTast != null && mTast.getStatus() == AsyncTask.Status.RUNNING)
        {
            //手动取消掉progressbar
            //cancel()方法只是将对应的AsyncTask标记为cancel状态，并不是真正的取消线程的执行
            mTast.cancel(true);
        }
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
                 * 如果标记为了cancel，就跳出循环
                 */
                if (isCancelled())
                {
                    break;
                }

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
            if (isCancelled())
            {
                return;
            }

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
