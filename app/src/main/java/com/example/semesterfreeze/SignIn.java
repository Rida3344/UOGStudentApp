package com.example.semesterfreeze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    public static logincreds lc = new logincreds();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        EditText username_et = findViewById(R.id.usernameBoxSI);
        EditText password_et = findViewById(R.id.passwordBoxSI);
        Button btnSignIn = findViewById(R.id.btnSignInSI);

        SignUpDBHelper signUpDBHelper = new SignUpDBHelper(this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username_et.getText().toString();
                String pass = password_et.getText().toString();

                if(user.equals("") || pass.equals(""))
                    Toast.makeText(SignIn.this,"Please Enter All The Fields!", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUserPass = signUpDBHelper.checkUsernamePassword(user, pass);
                    if(checkUserPass == true) {
                        Toast.makeText(SignIn.this,"Signed In Successfully!", Toast.LENGTH_SHORT).show();
                        SignIn.lc.setUname(user);
                        SignIn.lc.setPass(pass);
                        Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignIn.this,"Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}