package com.example.wallet.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wallet.NotesDao

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun getNotesDao(): NotesDao

    companion object{
        // instances of database opening at the
        // same time.
        // volatile means "it is visible for other threads"
        // singleton?
        @Volatile
        private var INSTANCE: NoteDatabase? = null
        fun getDatabase(context:Context): NoteDatabase {
            //if the INSTANCE is not null, then return it,
            //if it is, then create database
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database" ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}