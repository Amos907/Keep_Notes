package com.example.mvvmarchitecture;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Notes_Recycler_Adapter extends RecyclerView.Adapter<Notes_Recycler_Adapter.NoteViewHolder>{

    private List<note_data> note_data = new ArrayList<>();
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View NoteView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new NoteViewHolder(NoteView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        note_data notes = note_data.get(position);
        holder.title.setText(notes.getTitle());
        holder.desc.setText(notes.getDescription());
        holder.priority.setText(String.valueOf(notes.getPriority()));
    }

    @Override
    public int getItemCount() {
        return note_data.size();
    }

    public void set_notes(List<note_data> note_data){
        //This method passes the notes to the adapter...
        this.note_data = note_data;
        notifyDataSetChanged();
    }

    public note_data getNotesAt(int position){
        return note_data.get(position);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
       private TextView title , desc, priority;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.note_title);

            desc = itemView.findViewById(R.id.note_desc);

            priority = itemView.findViewById(R.id.note_priority);
        }
    }
}
