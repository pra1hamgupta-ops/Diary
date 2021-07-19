package com.example.android.diary
import android.content.Context
import androidx.lifecycle.LiveData


class DiaryRepository(private val diaryDao:DiaryDao) {



    val getAllDiary:LiveData<List<Diary>> = diaryDao.getAllDiaryEntries()

    suspend fun insert(diary:Diary){
        diaryDao.insert(diary)
    }

    suspend fun delete(diary:Diary){
        diaryDao.delete(diary)
    }

    suspend fun update(text:String, id:Int){
        diaryDao.update(text,id)
    }




}

