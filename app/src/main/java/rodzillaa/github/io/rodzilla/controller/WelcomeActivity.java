package rodzillaa.github.io.rodzilla.controller;

import android.content.Intent;
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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import rodzillaa.github.io.rodzilla.R;

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
                    post("http://143.215.94.175:9000/checkUser", formBody);
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
}
