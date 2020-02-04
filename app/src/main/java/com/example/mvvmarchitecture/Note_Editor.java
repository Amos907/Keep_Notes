package com.example.mvvmarchitecture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class Note_Editor extends AppCompatActivity {

    public static final String EXTRA_PICKER = "com.example.mvvmarchitecture.EXTRA PICKER";
    public static final String EXTRA_TITLE = "com.example.mvvmarchitecture.EXTRA TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.mvvmarchitecture.EXTRA DESCRIPTION";

    private EditText title, description;
    private NumberPicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__editor);

        title = findViewById(R.id.editor_title);
        description = findViewById(R.id.editor_desc);
        picker = findViewById(R.id.number_picker);

        picker.setMaxValue(10);
        picker.setMinValue(1);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add notes");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.editor_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveData();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveData() {
        String note_title = title.getText().toString();
        String note_desc = description.getText().toString();
        int note_priority = picker.getValue();

        if (note_title.trim().isEmpty() || note_desc.trim().isEmpty()){
            Toast.makeText(Note_Editor.this,"Please enter Titile and decsription",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,note_title);
        data.putExtra(EXTRA_DESCRIPTION,note_desc);
        data.putExtra(EXTRA_PICKER,note_priority);

        setResult(RESULT_OK,data);

        finish();
    }
}
