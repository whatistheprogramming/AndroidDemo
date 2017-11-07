package com.example.yuan.demoaysctasktest;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by yuan on 2017/11/7.
 */

public class MyAsyncTask extends AsyncTask<Void, Void, Void>
{

    private static final String TAG = "MyAsyncTask";
    
    @Override
    protected Void doInBackground(Void... params)
    {
        Log.d(TAG, "doInBackground: ");
        publishProgress();

        return null;
    }

    @Override
    protected void onPreExecute()
    {
        Log.d(TAG, "onPreExecute: ");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        Log.d(TAG, "onPostExecute: ");
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        Log.d(TAG, "onProgressUpdate: ");
        super.onProgressUpdate(values);
    }
}
