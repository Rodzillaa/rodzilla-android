package rodzillaa.github.io.rodzilla.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import rodzillaa.github.io.rodzilla.R;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerlayout);

        Button registerButton = (Button) findViewById(R.id.registerButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        Spinner accountTypeSpinner = (Spinner) findViewById(R.id.userAdminspinner);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //REGISTRATION
                //Code to input user info into database
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cancel Registration and direct user back to welcome page
                Intent cancelIntent = new Intent(getBaseContext(), WelcomeActivity.class);
                startActivity(cancelIntent);
            }
        });
        String[] accountTypes = {"User","Admin"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, accountTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);


    }
}
