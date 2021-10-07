package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    lateinit var quiz : Quiz
    lateinit var button1 : Button
    lateinit var button2 : Button
    lateinit var button3 : Button
    lateinit var button4 : Button
    lateinit var questionText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

        val inputStream = resources.openRawResource(R.raw.questions)
        val jsonText = inputStream.bufferedReader().use {
            it.readText()
        }

        Log.d(TAG, "onCreate: $jsonText")

        val gson = Gson()
        val type = object : TypeToken<List<Question>>() {}.type
        val questions = gson.fromJson<List<Question>>(jsonText, type)
        Log.d(TAG, "onCreate: \n${questions.toString()}")


        quiz = Quiz(questions)

    }

    private fun wireWidgets() {
        button1 = findViewById(R.id.button_main_answer1)
        button2 = findViewById(R.id.button_main_answer2)
        button3 = findViewById(R.id.button_main_answer3)
        button4 = findViewById(R.id.button_main_answer4)
        questionText = findViewById(R.id.textView_main_question)
    }

}