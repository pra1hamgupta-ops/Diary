package com.example.android.diary

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_add_new_entry.*
import java.time.Year

class AddNewEntry : AppCompatActivity() {
    lateinit var viewModel: DiaryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_entry)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(DiaryViewModel::class.java)



    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun create(view: View) {
        val dateString = dateForNewEntry.text.toString()
        val text = textForNewEntry.text.toString()
        var containsLetters = false
        for(c in 'a'..'z' ){
            if(dateString.contains(c)){
                containsLetters = true
                break
            }
        }
        if(!containsLetters) {
            for (c in 'A'..'Z') {
                if(dateString.contains(c)){
                    containsLetters = true
                    break
                }
            }
        }
        val year:Int = Year.now().value

        if(!containsLetters
            && dateString.length == 10
            && Integer.parseInt(dateString.substring(0,2)) in 0..31
            && dateString.substring(2,3) == "-"
            && Integer.parseInt(dateString.substring(3,5)) in 0..12
            && dateString.substring(5,6) == "-"
            && Integer.parseInt(dateString.substring(6)) in 1950..year
            && text.isNotEmpty()) {
            val diary = Diary(dateString, text)
            viewModel.insert(diary)
            val intent: Intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        else if(text.isEmpty()){
            Toast.makeText(this, "Enter Text", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Enter Correct Date", Toast.LENGTH_SHORT).show()
        }
    }
}