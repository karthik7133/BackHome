package com.carcar.backhome;



import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<item> list=new ArrayList<>();
    RecyclerView r;
    FloatingActionButton fb;
    private String channelID="Water remainder";
    private final int NOTIFICATION_ID = 101;

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
        list.add(new item("outline_1k_24","king"));
        list.add(new item("outline_1k_24","kattappa"));
        r=findViewById(R.id.r);

        r.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter adapter=new itemAdapter(this,list);
        r.setAdapter(adapter);
        Log.d("list", String.valueOf(list.size()));


        fb=findViewById(R.id.fb);

        createNotificationChannel();
        fb.setOnClickListener(V->{
            showNotification();
        });

    }

    private void showNotification() {
        NotificationCompat.Builder b=new NotificationCompat.Builder(this,channelID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Water Remainder")
                .setContentText("Water tagu ra badakow").setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat c=NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(this, "Please give notification access", Toast.LENGTH_SHORT).show();
            return;
        }
        c.notify(NOTIFICATION_ID,b.build());
    }

    private void createNotificationChannel() {
        CharSequence name="Water Remainder";
        int importance =NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel=new NotificationChannel(channelID,name,importance);
        NotificationManager manager=getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);
    }


}