package com.refactory.mytestcode.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.refactory.mytestcode.DetailActivity;
import com.refactory.mytestcode.R;
import com.refactory.mytestcode.model.Listusers;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.mainViewHolder> {

    private ArrayList<Listusers> mDataUsers = new ArrayList<>();
    private Context context;

    public MainAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Listusers> gemDataUsers() {
        return mDataUsers;
    }

    public void setmDataUsers(ArrayList<Listusers> items) {
        mDataUsers.clear();
        mDataUsers.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapter.mainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent , false);
        return new mainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.mainViewHolder holder, int position) {
        holder.bind(mDataUsers.get(position));
        holder.itemView.setOnClickListener(new ClickAdapter(position, new ClickAdapter.onCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.Extra, mDataUsers.get(position));
                context.startActivity(intent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return mDataUsers.size();
    }

    class mainViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
         mainViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
        void bind(final Listusers listusers){
            tvName.setText(listusers.getName());
        }
    }
}
