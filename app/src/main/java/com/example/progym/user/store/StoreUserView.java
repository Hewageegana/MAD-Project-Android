package com.example.progym.user.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.progym.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class StoreUserView extends AppCompatActivity {

    Button pay_now;
    Button add_cart;

    TextView itemTitle, itemDescription, itemPrice;
    ImageView itemImg;

    DatabaseReference proGym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_user_view);

        String key = getIntent().getStringExtra("Title");
        proGym = FirebaseDatabase.getInstance("https://progym-867fb-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Store").child(key);

        pay_now = findViewById(R.id.buyNow);
        itemTitle = findViewById(R.id.StoreuserViewExTitle);
        itemPrice = findViewById(R.id.StoreuserViewPrice);
        itemDescription = findViewById(R.id.StoreUserViewExDes);
        itemImg = findViewById(R.id.StoreuserViewExImg);

        itemTitle.setText(getIntent().getStringExtra("Title"));
        itemPrice.setText(getIntent().getStringExtra("Price"));
        itemDescription.setText(getIntent().getStringExtra("Description"));

        Picasso.get().setLoggingEnabled(true);
        Picasso.get().load(getIntent().getStringExtra("ImgUrl")).into(itemImg);

        String price = itemPrice.getText().toString();

        pay_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkout = new Intent(getApplicationContext(), Checkout.class );
                checkout.putExtra("Price",price);
                startActivity(checkout);
            }
        });
    }
}