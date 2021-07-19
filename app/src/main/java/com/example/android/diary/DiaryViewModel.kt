package com.example.android.diary

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DiaryViewModel(application: Application):AndroidViewModel(application) {

    val allDiaryEntries:LiveData<List<Diary>>
    private val repository: DiaryRepository


    init{
        val dao = DiaryRoomDatabase.getDatabase(application).getDiaryDao()
        repository = DiaryRepository(dao)
        allDiaryEntries = repository.getAllDiary

    }

    fun insert(diary:Diary) = viewModelScope.launch{
        repository.insert(diary)
    }

    fun delete(diary:Diary) = viewModelScope.launch {
        repository.delete(diary)
    }

    fun update(text:String, id:Int) = viewModelScope.launch {
        repository.update(text, id)
    }




}