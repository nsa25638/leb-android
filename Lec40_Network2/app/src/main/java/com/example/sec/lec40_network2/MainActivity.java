package com.example.sec.lec40_network2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;

public class MainActivity extends AppCompatActivity {

    private static ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }

    public void downloadImage(View view) {
        //
        String url = "https://i.ytimg.com/vi/8B5SpCGsZ98/maxresdefault.jpg";
        //
        DownloadTask task = new DownloadTask();
        task.execute(url);
    }

    class DownloadTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
           Bitmap bitmap = null;
            URL url = null;
            HttpURLConnection conn = null;
            InputStream in = null;
            BufferedInputStream bin = null;
            try {

                conn.connect();

                int resCode = conn.getResponseCode();
                if (resCode == HTTP_OK) {
                    in = conn.getInputStream();
                    bin = new BufferedInputStream(in);
                    bitmap = BitmapFactory.decodeStream(bin);
                url = new URL(params[0]);
                conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5 * 1000);
                conn.setReadTimeout(5 * 1000);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bin.close();
                    conn.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);

        }
    } // end class DownloadTask
}
