package com.example.semesterfreeze;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> {

    static List<SemesterFreeze> Data;

    public Adapter(List<SemesterFreeze> data)
    {
        Data = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapterlayout,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.Reason.setText(Data.get(position).getReason());
        holder.SF.setText(Data.get(position).getCurrentsamester());
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        TextView SF;
        TextView Reason;
        Button btndelete;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            SF=itemView.findViewById(R.id.txtsf);
            Reason=itemView.findViewById(R.id.txtReason);
            btndelete=itemView.findViewById(R.id.btndel);
            btndelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete(getAdapterPosition());
                }
            });
        }
    }
    static void delete(int pos){
        MainActivity.bdHelper.deleterecord(pos);
        Data.remove(pos);
        MainActivity.mAdapter.notifyDataSetChanged();
    }
}
