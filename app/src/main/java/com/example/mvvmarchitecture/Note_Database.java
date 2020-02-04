package com.example.mvvmarchitecture;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {note_data.class},version = 2)
public  abstract class Note_Database extends RoomDatabase {

    private static Note_Database note_database;

    public abstract Note_Dao note_dao();

    public  static synchronized Note_Database getInstance(Context context) {
        if (note_database == null){
            note_database = Room.databaseBuilder(context.getApplicationContext(),Note_Database.class,
                    "notes_database")/*.addCallback(callback)*/.fallbackToDestructiveMigration().build();
        }
        return note_database;
    }

   /* public static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new OncreateAssync(note_database).execute();
        }
    };

    public static class OncreateAssync extends AsyncTask<Void,Void,Void>{
        private Note_Dao note_dao;
        public OncreateAssync(Note_Database note_database){
           note_dao = note_database.note_dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            note_dao.insert(new note_data("Title 1","First Title",1));
            note_dao.insert(new note_data("Title 2","sec Title",2));
            note_dao.insert(new note_data("Title 3","Third Title",3));
            return null;
        }
    }*/

}
