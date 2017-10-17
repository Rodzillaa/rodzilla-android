package rodzillaa.github.io.rodzilla.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import rodzillaa.github.io.rodzilla.R;
import rodzillaa.github.io.rodzilla.model.RatSighting;
import rodzillaa.github.io.rodzilla.model.RatSightingDatabase;

public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG = "WelcomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button loginButton = (Button) findViewById(R.id.Loginbuttonwelcome);
        Button registerButton = (Button) findViewById(R.id.registerbuttonwelcome);

        Log.d(TAG, "outside login onclicklistener");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log in the user after entering the username and password
                EditText username = (EditText) findViewById(R.id.usernameEnter);
                EditText password = (EditText) findViewById(R.id.passwordEnter);

                //Validate that username and password matches a pair in the database
                RequestBody formBody = new FormBody.Builder()
                        .add("username", username.getText().toString())
                        .add("password", password.getText().toString())
                        .build();
                try {
                    post("http://143.215.91.97:9000/checkUser", formBody);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //route user to register screen.
                Intent registerIntent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

    }

    /**
     * Check if user credentials are correct
     * @param url
     * @param requestBody
     * @throws IOException
     */
    protected void post(String url, RequestBody requestBody) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = requestBody;
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    String resp = responseBody.string();
                    try {
                        JSONObject res = new JSONObject(resp);
                        if (res.getInt("status") == 1) {
                            new RatDataFiller().execute("");
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Intent loginIntent = new Intent(getBaseContext(), HomepageActivity.class);
                                    startActivity(loginIntent);
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Username or Password is incorrect!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private class RatDataFiller extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://143.215.91.97:9000/showRecords")
                    .build();

            String body = null;
            Response response = null;
            try {
                response = client.newCall(request).execute();
                body = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return body;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject obj = new JSONObject(result);
                Iterator<String> itr = obj.keys();
                while (itr.hasNext()) {
                    String docID = itr.next();
                    RatSighting temp = new RatSighting();
                    temp.address = obj.getJSONObject(docID).getString("address");
                    temp.borough = obj.getJSONObject(docID).getString("borough");
                    temp.city = obj.getJSONObject(docID).getString("city");
                    temp.date = obj.getJSONObject(docID).getString("date");
                    temp.key = obj.getJSONObject(docID).getString("key");
                    temp.latitude = obj.getJSONObject(docID).getString("latitude");
                    temp.location_type = obj.getJSONObject(docID).getString("location_type");
                    temp.longitude = obj.getJSONObject(docID).getString("longitude");
                    temp.zip = obj.getJSONObject(docID).getString("zip");
                    RatSightingDatabase.addSighting(temp);
                }
                Log.d(TAG, RatSightingDatabase.getRatSightings().size()+"");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
