package com.refactory.mytestcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.refactory.mytestcode.model.Listusers;

public class DetailActivity extends AppCompatActivity {

    public final static String Extra = "extra";
    Listusers listusers;
    TextView tvName, tvEmail, tvPhone, tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvName = findViewById(R.id.name);
        tvEmail = findViewById(R.id.email);
        tvPhone = findViewById(R.id.phone);
        tvAddress = findViewById(R.id.address);

        listusers = getIntent().getParcelableExtra(Extra);
        if (listusers != null){
            tvName.setText(listusers.getName());
            tvEmail.setText(listusers.getEmail());
            tvPhone.setText(listusers.getPhone());
            tvAddress.setText(listusers.getAddress());

            if (getSupportActionBar() != null){
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setTitle(listusers.getName());
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
