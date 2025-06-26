package com.carcar.backhome;



import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<item> list=new ArrayList<>();
    RecyclerView r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Window window=getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.pink));
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Message").setValue("HI").addOnSuccessListener(aVoid ->{
            Log.d("Firebase", "Data written successfully");
        }).addOnFailureListener(e -> {
            Log.d("Firebase","Data written unsuccessfully!");
        });
        list.add(new item("outline_1k_24","karthik"));
        r=findViewById(R.id.r);

        r.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter adapter=new itemAdapter(list);
        r.setAdapter(adapter);
        Log.d("list", String.valueOf(list.size()));

    }
}