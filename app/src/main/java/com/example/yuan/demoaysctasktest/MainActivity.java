package com.example.yuan.demoaysctasktest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        //调用execute()即可启动，类似线程的start()方法
        myAsyncTask.execute();

        Button btnImage = (Button) findViewById(R.id.button_image);
        Button btnProgress = (Button) findViewById(R.id.button_progress);

        btnImage.setOnClickListener(this);
        btnProgress.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.button_image:
                Intent intent1 = new Intent(MainActivity.this, ImageTest.class);
                startActivity(intent1);
                break;
            case R.id.button_progress:
                Intent intent2 = new Intent(MainActivity.this, ProgressBarTest.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}
