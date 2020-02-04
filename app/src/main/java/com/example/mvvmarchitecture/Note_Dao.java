package com.example.mvvmarchitecture;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Note_Dao {

    @Insert()
    void insert(note_data note);

    @Update()
    void update(note_data note);

    @Delete()
    void delete(note_data note);

    @Query("Delete  from notes_table")
    void delete_all();

    @Query("SELECT * FROM notes_table order by priority")
    LiveData<List<note_data>> all_notes();
}
