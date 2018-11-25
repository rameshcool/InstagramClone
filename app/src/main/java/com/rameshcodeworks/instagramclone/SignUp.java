package com.rameshcodeworks.instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = findViewById(R.id.edtTxtKick);
        edtPunchSpeed = findViewById(R.id.edtTxtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtTxtPower);
        edtKickSpeed = findViewById(R.id.edtTxtKickSpeed);
        edtKickPower = findViewById(R.id.edtTxtKickPower);

       btnSave = findViewById(R.id.btnSave);
       btnSave.setOnClickListener(SignUp.this);
    }

    @Override
    public void onClick(View v) {
        try {
            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("name", edtName.getText().toString());
            kickBoxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            kickBoxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
            kickBoxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
            kickBoxer.put("kickPower", Integer.parseInt(edtKickPower.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {

                        FancyToast.makeText(SignUp.this, kickBoxer.get("name") + " is saved to server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                    } else {

                        FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });

        } catch (Exception e) {

            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }
}

