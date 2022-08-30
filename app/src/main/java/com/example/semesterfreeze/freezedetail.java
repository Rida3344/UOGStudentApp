package com.example.semesterfreeze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class freezedetail extends AppCompatActivity {

    EditText currentsamester;
    EditText samesterfreeze;
    EditText reason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freezedetail);
        Button btn = findViewById(R.id.btnsubmit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentsamester = findViewById(R.id.txtcsamester);
                samesterfreeze = findViewById(R.id.txtsamesterfreeze);
                reason = findViewById(R.id.txtreason);
                Intent intent = new Intent(freezedetail.this,MainActivity.class);
                intent.putExtra("CS",currentsamester.getText().toString());
                intent.putExtra("SF",samesterfreeze.getText().toString());
                intent.putExtra("R",reason.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}