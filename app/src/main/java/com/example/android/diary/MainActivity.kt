package com.example.android.diary

import android.content.Intent
import android.graphics.Color

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: DiaryViewModel
    lateinit var adapter: HomePageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )
            .get(DiaryViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val layout: View = findViewById(R.id.layout)
        val enterText: TextView = findViewById(R.id.enter)
        val recyclerViewCard: MaterialCardView = findViewById(R.id.cardForRecyclerView)
        val plus: ImageView = findViewById(R.id.plus)
        val sentiment: ImageView = findViewById(R.id.sentiment)
        val delete: ImageView = findViewById(R.id.delete)
        adapter = HomePageAdapter(
            this,
            viewModel,
            plus,
            sentiment,
            delete,
            layout,
            recyclerViewCard,
            enterText
        )

        recyclerView.adapter = adapter


        viewModel.allDiaryEntries.observe(
            this, Observer {
                adapter.update(it)
            }
        )

        plus.setOnClickListener {
            val intent: Intent = Intent(this, AddNewEntry::class.java)
            startActivity(intent)
        }






    }
}

