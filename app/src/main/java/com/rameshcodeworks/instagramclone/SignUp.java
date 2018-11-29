package com.rameshcodeworks.instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName, edtPunchSpeed,
                     edtPunchPower, edtKickSpeed,
                     edtKickPower;
    private TextView txtGetData;
    private Button btnGetAllData;
    private Button btnTransition;
    private String allKickBoxers;
    //private String allBoxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = findViewById(R.id.edtTxtKick);
        edtPunchSpeed = findViewById(R.id.edtTxtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtTxtPower);
        edtKickSpeed = findViewById(R.id.edtTxtKickSpeed);
        edtKickPower = findViewById(R.id.edtTxtKickPower);
        txtGetData = findViewById(R.id.txtGetData);
        btnGetAllData = findViewById(R.id.btnGetAllData);
        btnTransition = findViewById(R.id.btnNextActivity);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("tmqxgaWHZO", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if (object != null && e == null) {

                            txtGetData.setText(object.get("name") + " - " + "Punch Power: " + object.get("punchPower"));
                        }
                    }
                });
            }
        });

       btnSave = findViewById(R.id.btnSave);
       btnSave.setOnClickListener(SignUp.this);

       btnGetAllData.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               allKickBoxers = "";
               //allBoxers = "";
               ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");

              // queryAll.whereGreaterThan("punchPower", 1500);
               //queryAll.whereGreaterThanOrEqualTo("punchPower", 1500);
               queryAll.setLimit(1);

               queryAll.findInBackground(new FindCallback<ParseObject>() {
                   @Override
                   public void done(List<ParseObject> objects, ParseException e) {

                       if (e == null) {

                           if (objects.size() > 0) {

                               for (ParseObject kickBoxers : objects) {

                                   //for (ParseObject boxers : objects) {

                                       allKickBoxers = allKickBoxers + kickBoxers.get("name") + "\n";
                                      // allBoxers = allBoxers + boxers.get("punch_speed") + "\n";

                                  // }
                               }

                               FancyToast.makeText(SignUp.this, allKickBoxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                               //FancyToast.makeText(SignUp.this, allBoxers , FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                           } else {

                               FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                           }
                       }
                   }
               });
           }
       });

       btnTransition.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(SignUp.this, SignUpLoggingActivity.class);
               startActivity(intent);

           }
       });
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

