package com.example.android.diary
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DiaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(diary:Diary)

    @Delete
    suspend fun delete(diary:Diary)

    @Query("Select * from DiaryTable order by day desc")
    fun getAllDiaryEntries():LiveData<List<Diary>>


    @Query("UPDATE DiaryTable SET DiaryEntry = :text WHERE ID = :id")
    suspend fun update(text:String, id:Int)

}