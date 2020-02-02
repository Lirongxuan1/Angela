package com.example.android.smsmessaging;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class ProfilePage extends AppCompatActivity {
    String name;
    String phone;
    String profilePic;
    String gender;

    EditText nameText;
    EditText phoneText;
    EditText profilePicText;
    EditText genderText;

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);



        nameText=  (EditText)findViewById(R.id.nameInput);
        phoneText=  (EditText)findViewById(R.id.phoneInput);
        profilePicText=  (EditText)findViewById(R.id.profilePicInput);
        genderText=  (EditText)findViewById(R.id.genderInput);

        submitButton = (Button) findViewById(R.id.submitButton);



        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                User i = new User();
                i.cuser().setName(nameText.getText().toString());

                i.cuser().setpnum (phoneText.getText().toString());
                i.cuser().setplink (profilePicText.getText().toString());
                i.cuser().setGender (genderText.getText().toString());
            }
        });





        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }


        });*/
    }

}
