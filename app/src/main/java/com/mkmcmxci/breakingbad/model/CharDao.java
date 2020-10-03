package com.mkmcmxci.breakingbad.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CharDao {

    @Insert
    List<Long> insertAll(BCharacter... chars);

    @Query("SELECT * FROM bcharacter")
    List<BCharacter> getAll();

    @Query("DELETE FROM bcharacter")
    void deleteAll();

}
