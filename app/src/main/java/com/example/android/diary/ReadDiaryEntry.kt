package com.example.android.diary


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import kotlinx.android.synthetic.main.activity_read_diary_entry.*

class ReadDiaryEntry : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_diary_entry)

        dateInReadDiaryEntry.paint?.isUnderlineText = true
        entryInReadDiaryEntry.movementMethod = ScrollingMovementMethod()

        val date = intent.getStringExtra("date")
        val entry = intent.getStringExtra("entry")
        val id = intent.getIntExtra("id",0)
        dateInReadDiaryEntry.text = date
        entryInReadDiaryEntry.text = entry

        update.setOnClickListener {
            val intent = Intent(this, UpdateDiaryEntry::class.java)
            intent.putExtra("dateToUpdate",date)
            intent.putExtra("entryToUpdate",entry)
            intent.putExtra("idToUpdate", id)
            startActivity(intent)
        }
    }
}