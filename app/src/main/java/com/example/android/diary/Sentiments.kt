package com.example.android.diary
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest

import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.Charset

class Sentiments : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sentiments)

        val text = intent.getStringExtra("text").toString()

        val prediction:TextView = findViewById(R.id.prediction)
        val percentage:TextView = findViewById(R.id.percentage)
        val face:ImageView = findViewById(R.id.face)
        val progressBar:ProgressBar = findViewById(R.id.progressBar)
        apiCall(text,percentage,prediction,face,progressBar)
    }

    private fun apiCall(
        text: String,
        percentage: TextView,
        prediction: TextView,
        face: ImageView,
        progressBar: ProgressBar
    ) {
        val url = "https://sentiment-analysis9.p.rapidapi.com/sentiment"
        val body = "[{\"id\":\"1\",\"language\":\"en\",\"text\":\"$text\"}]"
        val jsonObject = object : JsonArrayRequest(
            Request.Method.POST, url, null,
            Response.Listener<JSONArray> {
                val jsonObject: JSONObject = it.getJSONObject(0)
                val array = jsonObject.getJSONArray("predictions")
                val json = array.getJSONObject(0)
                prediction.text = json.getString("prediction")
                percentage.text = ((json.getDouble("probability") * 100).toInt()).toString() + "%"
                if(json.getString("prediction") == "positive"){
                    face.setBackgroundResource(R.drawable.happy)
                }
                else{
                    face.setBackgroundResource(R.drawable.sad)
                }
                progressBar.visibility = View.GONE
            },
            Response.ErrorListener {
                Toast.makeText(this, "Error",Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            }
        ) {
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["x-rapidapi-key"] = "a56087bbe6msh7ea9fcbe14c5252p13739fjsne8785f6e7e85"
                headers["x-rapidapi-host"] = "sentiment-analysis9.p.rapidapi.com"
                return headers
            }

            override fun getBody(): ByteArray {
                return body.toByteArray(Charset.defaultCharset())
            }

            override fun getBodyContentType(): String {
                return "application/json"
            }

        }

        MySingleton.getInstance(this).addToRequestQueue(jsonObject)
    }
}