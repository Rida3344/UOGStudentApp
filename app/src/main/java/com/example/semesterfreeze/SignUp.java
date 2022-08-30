package com.example.semesterfreeze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //getSupportActionBar().setTitle("Welcome");

        SignUpDBHelper signUpDBHelper = new SignUpDBHelper(this);

        EditText username_et = findViewById(R.id.usernameBoxSU);
        EditText password_et = findViewById(R.id.passwordBoxSU);
        EditText repassword_et = findViewById(R.id.repasswordBoxSU);
        Button signUpBtn = findViewById(R.id.btnSignUpSU);
        Button signInBtn = findViewById(R.id.btnSignInSU);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String user = username_et.getText().toString();
            String pass = password_et.getText().toString();
            String repass = repassword_et.getText().toString();

            if (user.equals("") || pass.equals("") || repass.equals(""))
                Toast.makeText(SignUp.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                if (pass.equals(repass)) {
                    Boolean checkUser = signUpDBHelper.checkUsername(user);
                    if (checkUser == false) {
                        Boolean insert = signUpDBHelper.insertData(user, pass);
                        if (insert == true) {
                            Toast.makeText(SignUp.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                            SignIn.lc.setUname(user);
                            SignIn.lc.setPass(pass);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUp.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUp.this, "User Already Exists! Please Sign In!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignUp.this, "Password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
            }
        });
    }
}
