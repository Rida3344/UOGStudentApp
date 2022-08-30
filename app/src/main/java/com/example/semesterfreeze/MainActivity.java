package com.example.semesterfreeze;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static List<SemesterFreeze> list = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    public static RecyclerView.Adapter mAdapter;
    public static BDHelper bdHelper;
    Random rnd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rnd.setSeed(1000);
        bdHelper =new BDHelper(this);
        list = bdHelper.readallrecords();

        RecyclerView rv = findViewById(R.id.recyclerview);
        Button btnAdd = findViewById(R.id.btnadd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,freezedetail.class);
                startActivityForResult(intent,4444);
            }
        });
        layoutManager = new LinearLayoutManager(this);
        //layoutManager = new GridLayoutManager(this,2);
        rv.setLayoutManager(layoutManager);

        mAdapter = new Adapter(list);
        rv.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==4444 && resultCode == RESULT_OK){
            String cs,sf,vr;
            cs = data.getStringExtra("CS");
            sf = data.getStringExtra("SF");
            vr = data.getStringExtra("R");

            int ID = rnd.nextInt();

            while(!bdHelper.checkFormId(ID)){
                ID = rnd.nextInt();
            }

            SemesterFreeze semesterFreeze = new SemesterFreeze(ID,cs,sf,vr);
            bdHelper.insert(semesterFreeze);
            list.add(semesterFreeze);
            mAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}