package com.example.android.diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_update_diary_entry.*

class UpdateDiaryEntry : AppCompatActivity() {
    private lateinit var viewModel: DiaryViewModel
    var id:Int? = null
    var finalEntry:String? = null
    var date:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_diary_entry)
        date = intent.getStringExtra("dateToUpdate")
        val entry = intent.getStringExtra("entryToUpdate")
        id = intent.getIntExtra("idToUpdate",0)
        toUpdateText.setText(entry)
        finalEntry = toUpdateText.text.toString()


        toUpdateText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                finalEntry = p0.toString()
            }

        }
        )



        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(DiaryViewModel::class.java)
    }

    fun update(view: View) {
        viewModel.update(finalEntry!!, id!!)
        val intent:Intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        
        startActivity(intent)
        Toast.makeText(this, "Updated: $date",Toast.LENGTH_SHORT).show()
    }


}