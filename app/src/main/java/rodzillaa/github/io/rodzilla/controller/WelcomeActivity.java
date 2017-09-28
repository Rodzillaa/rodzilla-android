package rodzillaa.github.io.rodzilla.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

                String usernameTest = "user";
                String passwordTest = "pass";

                Intent loginIntent = new Intent(getBaseContext(), HomepageActivity.class);
                //Validate that username and password matches a pair in the database
                Log.d(TAG, "Inside the loginButton's setOn");
                if (username.getText().toString().equals(usernameTest) &&
                        password.getText().toString().equals(passwordTest)) {
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Username or Password is incorrect!",
                            Toast.LENGTH_SHORT).show();
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
}
