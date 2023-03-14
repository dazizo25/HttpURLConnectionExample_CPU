package com.example.httpurlconnectionexample;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Extract from AsyncTask documentation
 * <p>
 * An asynchronous task is defined by a computation that runs on a background thread and whose
 * result is published on the UI thread. An asynchronous task is defined by 3 generic types,
 * called Params, Progress and Result, and 4 steps, called onPreExecute, doInBackground,
 * onProgressUpdate and onPostExecute.
 * <p>
 * AsyncTask's generic types
 * The three types used by an asynchronous task are the following:
 * Params, the type of the parameters sent to the task upon execution.
 * Progress, the type of the progress units published during the background computation.
 * Result, the type of the result of the background computation.
 * Not all types are always used by an asynchronous task. To mark a type as unused, simply use
 * the type Void:
 * private class MyTask extends AsyncTask<Void, Void, Void> { ... }
 * <p>
 * The 4 steps
 * When an asynchronous task is executed, the task goes through 4 steps:
 * 1. onPreExecute(), invoked on the UI thread before the task is executed. This step is normally used
 * to setup the task, for instance by showing a progress bar in the user interface.
 * 2. doInBackground, invoked on the background thread immediately after onPreExecute() finishes
 * executing. This step is used to perform background computation that can take a long time. The
 * parameters of the asynchronous task are passed to this step. The result of the computation must
 * be returned by this step and will be passed back to the last step. This step can also use
 * publishProgress to publish one or more units of progress. These values are published on the UI
 * thread, in the onProgressUpdate step.
 * 3. onProgressUpdate, invoked on the UI thread after a call to publishProgress. The timing of the
 * execution is undefined. This method is used to display any form of progress in the user interface
 * while the background computation is still executing. For instance, it can be used to animate a
 * progress bar or show logs in a text field.
 * 4. onPostExecute, invoked on the UI thread after the background computation finishes. The result of
 * the background computation is passed to this step as a parameter.
 * <p>
 * Threading rules
 * There are a few threading rules that must be followed for this class to work properly:
 * The AsyncTask class must be loaded on the UI thread. This is done automatically as of
 * Build.VERSION_CODES.JELLY_BEAN.
 * The task instance must be created on the UI thread.
 * execute must be invoked on the UI thread.
 * Do not call onPreExecute(), onPostExecute, doInBackground, onProgressUpdate manually.
 * The task can be executed only once (an exception will be thrown if a second execution is
 * attempted.)
 */
public class URLConnectionPostHandler extends AsyncTask<Object, String, Object> {

    URLConnectionPostHandler.DataDownloadListener dataDownloadListener;

    public void setDataDownloadListener(URLConnectionPostHandler.DataDownloadListener dataDownloadListener) {
        this.dataDownloadListener = dataDownloadListener;
    }

    @Override
    protected Object doInBackground(Object... param) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(param[0].toString());
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            try (OutputStream os = urlConnection.getOutputStream()) {
                os.write(param[1].toString().getBytes());
                os.flush();
                os.close();
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return response.toString();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object results) {
        if (results != null) {
            dataDownloadListener.dataDownloadedSuccessfully(results);
        } else {
            dataDownloadListener.dataDownloadFailed();
        }
    }

    public interface DataDownloadListener {
        void dataDownloadedSuccessfully(Object data);

        void dataDownloadFailed();
    }
}

