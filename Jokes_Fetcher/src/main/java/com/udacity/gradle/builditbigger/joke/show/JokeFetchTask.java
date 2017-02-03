/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.udacity.gradle.builditbigger.joke.show;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.joke.teller.jokeApi.JokeApi;

import java.io.IOException;

public class JokeFetchTask extends AsyncTask<String, Void, String> {
    private ProgressDialog dialog;
    private OnJokeFetchedListener listener;
    private JokeApi jokeApi;

    public JokeFetchTask(Context context, OnJokeFetchedListener listener) {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Please Wait");
        dialog.setCancelable(true);
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        if (jokeApi == null) {
            jokeApi = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setApplicationName(params[0])
                    .setRootUrl(params[1])
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    })
                    .build();
        }
        try {
            return jokeApi.jokeMe().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        listener.onJokeFetched(joke);
        if (dialog.isShowing()) {
            dialog.cancel();
        }
    }

    public interface OnJokeFetchedListener {
        void onJokeFetched(String joke);
    }
}

