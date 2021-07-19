package com.example.android.diary
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity (tableName = "DiaryTable")
class Diary (@ColumnInfo(name = "Date") val date:String,
             @ColumnInfo(name = "DiaryEntry") var diaryEntry: String){

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "ID") var id:Int = 0
    var day = Integer.parseInt(this.date.substring(0,2)) + 200* Integer.parseInt(this.date.substring(3,5)) +  100000* Integer.parseInt(this.date.substring(6))
}


