package com.example.mvvmarchitecture;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int NOTE_EDITOR_REQUEST = 1;

    private NotesViewModel notesViewModel;
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         floatingActionButton = findViewById(R.id.floating_button);

         floatingActionButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this,Note_Editor.class);
                 startActivityForResult(intent,NOTE_EDITOR_REQUEST);

             }
         });

         RecyclerView recyclerView = findViewById(R.id.note_recycler);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         recyclerView.setHasFixedSize(true);

         final Notes_Recycler_Adapter recycler_adapter = new Notes_Recycler_Adapter();

         recyclerView.setAdapter(recycler_adapter);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        notesViewModel.getAll_notes().observe(this, new Observer<List<note_data>>() {
            @Override
            public void onChanged(List<note_data> note_data) {
                //This defines what happens after the live data changes...
                recycler_adapter.set_notes(note_data);
            }
        });

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Notes");

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                notesViewModel.delete(recycler_adapter.getNotesAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NOTE_EDITOR_REQUEST && resultCode == RESULT_OK){
            String title  = data.getStringExtra(Note_Editor.EXTRA_TITLE);
            String desc  = data.getStringExtra(Note_Editor.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(Note_Editor.EXTRA_PICKER,1);

            note_data note_data = new note_data(title,desc,priority);
            notesViewModel.insert(note_data);

            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }

    }
}
