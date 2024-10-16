package com.example.roomlib;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {
    @Query("select * from Notes")
    public default LiveData<List<Note>> getAllData() {
        return null;
    }

    @Insert
    public void insert(Note note);
    @Update
    public void update(Note note);
    @Delete
    public void delete(Note note);

}
