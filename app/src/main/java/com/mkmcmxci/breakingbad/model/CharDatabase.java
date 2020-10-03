package com.mkmcmxci.breakingbad.model;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BCharacter.class}, version = 1)
public abstract class CharDatabase extends RoomDatabase {

    private static CharDatabase instance;

    public static CharDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), CharDatabase.class, "chardatabase")
                    .build();

        }

        return instance;

    }

    public abstract CharDao charDao();

}
