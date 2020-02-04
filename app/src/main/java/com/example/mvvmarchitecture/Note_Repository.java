package com.example.mvvmarchitecture;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Note_Repository {
    private Note_Dao note_dao;
    private LiveData<List<note_data>> allnotes;

    public Note_Repository(Application Application){
      Note_Database note_database = Note_Database.getInstance(Application);
      note_dao = note_database.note_dao();
      allnotes = note_dao.all_notes();
    }

    public void insert(note_data note_data){
        new InsertAssync(note_dao).execute(note_data);
    }

    public void update(note_data note_data){
        new UpdateAssync(note_dao).execute(note_data);
    }

    public void delete(note_data note_data){
        new DeleteAssync(note_dao).execute(note_data);
    }

    public void deleteALll(){
        new DeleteAllAssync(note_dao).execute();
    }

    public LiveData<List<note_data>> getAllnotes() {
        return allnotes;
    }

    private static class InsertAssync extends AsyncTask<note_data,Void,Void>{
        private Note_Dao note_dao;

        public InsertAssync(Note_Dao note_dao){
            this.note_dao=note_dao;
        }
        @Override
        protected Void doInBackground(note_data... note_data) {
            note_dao.insert(note_data[0]);
            return null;
        }
    }

    private static class UpdateAssync extends AsyncTask<note_data,Void,Void>{
        private Note_Dao note_dao;

        public UpdateAssync(Note_Dao note_dao){
            this.note_dao=note_dao;
        }
        @Override
        protected Void doInBackground(note_data... note_data) {
            note_dao.update(note_data[0]);
            return null;
        }
    }

    private static class DeleteAssync extends AsyncTask<note_data,Void,Void>{
        private Note_Dao note_dao;

        public DeleteAssync(Note_Dao note_dao){
            this.note_dao=note_dao;
        }
        @Override
        protected Void doInBackground(note_data... note_data) {
            note_dao.delete(note_data[0]);
            return null;
        }
    }

    private static class DeleteAllAssync extends AsyncTask<Void,Void,Void>{
        private Note_Dao note_dao;

        public DeleteAllAssync(Note_Dao note_dao){
            this.note_dao=note_dao;
        }
        @Override
        protected Void doInBackground(Void... Voids) {
            note_dao.delete_all();
            return null;
        }
    }
}

