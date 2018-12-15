package com.rameshcodeworks.instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTxtLoginEmail, edtTxtLoginPassword;
    private Button btnLoginActivity, btnSignUpLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");

        edtTxtLoginEmail = findViewById(R.id.edtTxtLoginEmail);
        edtTxtLoginPassword = findViewById(R.id.edtTxtLoginPassword);

        edtTxtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if (keyCode == KeyEvent.KEYCODE_ENTER &&
                        keyEvent.getAction() == KeyEvent.ACTION_DOWN) {

                    onClick(btnLoginActivity);

                }

                return false;
            }
        });
        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignUpLoginActivity = findViewById(R.id.btnSignUpLoginActivity);

        btnLoginActivity.setOnClickListener(this);
        btnSignUpLoginActivity.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnLoginActivity:

                if (edtTxtLoginEmail.getText().toString().equals("")
                        || edtTxtLoginPassword.getText().toString().equals("")) {

                    FancyToast.makeText(LoginActivity.this, "Email,Password is required",
                            Toast.LENGTH_SHORT, FancyToast.INFO, true).show();

                } else {

                    ParseUser.logInInBackground(edtTxtLoginEmail.getText().toString(),
                            edtTxtLoginPassword.getText().toString(),
                            new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {

                                    if (user != null && e == null) {

                                        FancyToast.makeText(LoginActivity.this, user.getUsername() +
                                                        " logged in successfully", Toast.LENGTH_SHORT, FancyToast.SUCCESS,
                                                true).show();

                                        transitionToSocialMediaActivity();
                                    }
                                }
                            });
                }
                break;

            case R.id.btnSignUpLoginActivity:

                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
                break;

        }
    }

    private void transitionToSocialMediaActivity() {

        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }
}
