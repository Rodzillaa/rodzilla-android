package rodzillaa.github.io.rodzilla.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import rodzillaa.github.io.rodzilla.R;
import rodzillaa.github.io.rodzilla.utils.APIUtil;

/**
 * Activity class that allows a new user to register
 * for a new account.
 */
public class RegisterActivity extends AppCompatActivity {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerlayout);

        Button registerButton = (Button) findViewById(R.id.registerButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.nameEditText);
                EditText username = (EditText) findViewById(R.id.usernameEditText);
                EditText password = (EditText) findViewById(R.id.passwordEditText);
                CheckBox isAdmin = (CheckBox) findViewById(R.id.adminCheck);
                boolean admin = isAdmin.isChecked();
                RequestBody formBody = new FormBody.Builder()
                    .add("name", name.getText().toString())
                    .add("username", username.getText().toString())
                    .add("password", password.getText().toString())
                    .add("isAdmin", admin ? "Y" : "N")
                    .build();
                try {
                    post(APIUtil.SERVER_URL+"/addUser", formBody);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //Cancel Registration and direct user back to welcome page
                Intent cancelIntent = new Intent(getBaseContext(), WelcomeActivity.class);
                startActivity(cancelIntent);
            }
        });
    }

    /**
     * Send post request to web api to insert user into system
     *
     * @param url the server url that the user's credentials are
     *            checked against
     * @param requestBody RequestBody object that contains the username
     *                    and password
     * @throws IOException  a network fails or when a socket is refused
     */
    protected void post(String url, RequestBody requestBody) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
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
                                    Intent loginIntent = new Intent(getBaseContext(), WelcomeActivity.class);
                                    Toast.makeText(getApplicationContext(), "Registered successfully.",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(loginIntent);
                                }
                            });

                        } else {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Registration error.",
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
