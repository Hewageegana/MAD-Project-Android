package com.example.progym.admin.Diet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.progym.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DietManagement extends AppCompatActivity {

    Button addDiet;
    ListView dietList;
    DatabaseReference proGym;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_managment);

        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Diet");
        dietList = findViewById(R.id.dietLV);
        addDiet = findViewById(R.id.addDiet);

        FirebaseListOptions<Diet> options = new FirebaseListOptions.Builder<Diet>()
                .setLayout(R.layout.dietlist)
                .setQuery(proGym, Diet.class)
                .build();
        adapter = new FirebaseListAdapter(options) {

            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView Type = v.findViewById(R.id.d_Type);
                TextView key = v.findViewById(R.id.d_ID);
                Diet diet = (Diet) model;
                Type.setText(diet.getTitle());
                key.setText(diet.getDietID());

            }
        };
        dietList.setAdapter(adapter);


        addDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddDiet.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
