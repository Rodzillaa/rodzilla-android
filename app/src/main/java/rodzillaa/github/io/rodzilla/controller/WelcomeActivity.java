package rodzillaa.github.io.rodzilla.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import rodzillaa.github.io.rodzilla.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button loginButton = (Button) findViewById(R.id.Loginbuttonwelcome);
        Button registerButton = (Button) findViewById(R.id.registerbuttonwelcome);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log in the user after entering the username and password
                EditText username = (EditText) findViewById(R.id.usernameEnter);
                EditText password = (EditText) findViewById(R.id.passwordEnter);

                String usernameTest = "username";
                String passwordTest = "password";

                Intent loginIntent = new Intent(WelcomeActivity.this, HomepageActivity.class);
                //Validate that username and password matches a pair in the database

                if (username.toString().equals(usernameTest) &&
                        password.toString().equals(passwordTest)) {
                    startActivity(loginIntent);
                }

            }

        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //route user to register screen.
                Intent registerIntent = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

    }
}
