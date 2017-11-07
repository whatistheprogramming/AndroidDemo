package com.example.yuan.demoaysctasktest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MyAsyncTask myAsyncTask = new MyAsyncTask();
        //调用execute()即可启动，类似线程的start()方法
//        myAsyncTask.execute();

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, ImageTest.class);
                startActivity(intent);

            }
        });
    }
}
