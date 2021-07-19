package com.example.android.diary

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Diary::class], version = 1, exportSchema = false)
abstract class DiaryRoomDatabase: RoomDatabase() {

    abstract fun getDiaryDao():DiaryDao

    companion object{
        private var INSTANCE:DiaryRoomDatabase? = null

        fun getDatabase(context: Context):DiaryRoomDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DiaryRoomDatabase::class.java,
                    "Diary_database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}