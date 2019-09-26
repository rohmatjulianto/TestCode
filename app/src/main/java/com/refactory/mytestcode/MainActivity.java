package com.refactory.mytestcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.refactory.mytestcode.adapter.MainAdapter;
import com.refactory.mytestcode.model.Listusers;
import com.refactory.mytestcode.model.MainViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel model = ViewModelProviders.of(this).get(MainViewModel.class);
        model.getAllData().observe(this, getDataObserver);
        model.getData();

        adapter = new MainAdapter(this);
        adapter.notifyDataSetChanged();

        RecyclerView rvShow = findViewById(R.id.rv_show);
        rvShow.setHasFixedSize(true);
        rvShow.setLayoutManager(new LinearLayoutManager(this));
        rvShow.setAdapter(adapter);

    }

    private Observer<ArrayList<Listusers>> getDataObserver = new Observer<ArrayList<Listusers>>() {
        @Override
        public void onChanged(ArrayList<Listusers> listusers) {
            if (listusers != null){
                adapter.setmDataUsers(listusers);
            }
        }
    };
}
