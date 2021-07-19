package com.example.android.diary

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView




class HomePageAdapter(
    private val context: Context,
    private val viewModel: DiaryViewModel,
    private val plus: ImageView,
    private val sentiment: ImageView,
    private val delete: ImageView,
    private val layout: View,
    private val recyclerViewCard: MaterialCardView,
    private val enterText: TextView
) : RecyclerView.Adapter<HomePageViewHolder>() {

    private var dataset: ArrayList<Diary> = ArrayList<Diary>()
    private var redButton:View? = null

    fun update(data: List<Diary>) {
        dataset.clear()
        dataset.addAll(data)
        notifyDataSetChanged()
        if (dataset.size == 0) {
            enterText.visibility = View.VISIBLE
            recyclerViewCard.visibility = View.GONE
        } else {
            enterText.visibility = View.GONE
            recyclerViewCard.visibility = View.VISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.specific_entries, parent, false)
        return HomePageViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomePageViewHolder, position: Int) {
        holder.button.text = dataset[position].date


        holder.button.setOnClickListener {
            this.redButton()?.setBackgroundColor(Color.WHITE)
            plus.visibility = View.VISIBLE
            sentiment.visibility = View.GONE
            delete.visibility = View.GONE
            val intent = Intent(context, ReadDiaryEntry::class.java)
            intent.putExtra("date", dataset[position].date)
            intent.putExtra("entry", dataset[position].diaryEntry)
            intent.putExtra("id", dataset[position].id)
            startActivity(context, intent,null )
        }


        holder.button.setOnLongClickListener { button ->
            this.redButton()?.setBackgroundColor(Color.WHITE)
            redButton = button
            plus.visibility = View.GONE
            sentiment.visibility = View.VISIBLE
            delete.visibility = View.VISIBLE
            button.setBackgroundColor(Color.parseColor("#F7BEC0"))

            layout.setOnTouchListener { view, motionEvent ->
                plus.visibility = View.VISIBLE
                sentiment.visibility = View.GONE
                delete.visibility = View.GONE
                button.setBackgroundColor(Color.WHITE)
                true
            }

            sentiment.setOnClickListener {
                plus.visibility = View.VISIBLE
                delete.visibility = View.GONE
                sentiment.visibility = View.GONE
                button.setBackgroundColor(Color.WHITE)
                val intent: Intent = Intent(context, Sentiments::class.java)
                intent.putExtra("text", dataset[position].diaryEntry)
                startActivity(context, intent, null)
            }

            delete.setOnClickListener {
                plus.visibility = View.VISIBLE
                delete.visibility = View.GONE
                sentiment.visibility = View.GONE
                button.setBackgroundColor(Color.WHITE)
                viewModel.delete(dataset[position])
            }


            true
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    private fun redButton():View?{
        return redButton
    }


}


class HomePageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val button: MaterialButton = itemView.findViewById(R.id.date)
}
