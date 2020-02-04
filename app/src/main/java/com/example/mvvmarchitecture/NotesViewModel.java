package com.example.mvvmarchitecture;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmarchitecture.Note_Repository;
import com.example.mvvmarchitecture.note_data;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    private Note_Repository note_repository;
    private LiveData<List<note_data>> all_notes;
    public NotesViewModel(@NonNull Application application) {
        super(application);

        note_repository = new Note_Repository(application);
        all_notes = note_repository.getAllnotes();
    }

    public void insert(note_data note_data){
        note_repository.insert(note_data);
    }

    public void update(note_data note_data){
        note_repository.update(note_data);
    }

    public void delete(note_data note_data){
        note_repository.delete(note_data);
    }

    public void delete_all(){
        note_repository.deleteALll();
    }

    public LiveData<List<note_data>> getAll_notes() {
        return all_notes;
    }
}
