package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
    lateinit var finalText : TextView

    var answer = ""

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

        setUpNextQuestion()

        button1.setOnClickListener {
            answer = button1.text.toString()
            checkAnswerAndUpdateUI(answer)
        }
        button2.setOnClickListener {
            answer = button2.text.toString()
            checkAnswerAndUpdateUI(answer)
        }
        button3.setOnClickListener {
            answer = button3.text.toString()
            checkAnswerAndUpdateUI(answer)
        }
        button4.setOnClickListener {
            answer = button4.text.toString()
            checkAnswerAndUpdateUI(answer)
        }

    }

    private fun wireWidgets() {
        button1 = findViewById(R.id.button_main_answer1)
        button2 = findViewById(R.id.button_main_answer2)
        button3 = findViewById(R.id.button_main_answer3)
        button4 = findViewById(R.id.button_main_answer4)
        questionText = findViewById(R.id.textView_main_question)
        finalText = findViewById(R.id.textView_main_final)
        finalText.visibility = View.GONE
    }

    private fun checkAnswerAndUpdateUI(answer : String) {
        val response = quiz.checkAnswer(answer)

        if(response) {
            Toast.makeText(this@MainActivity, "Helium Yttrium Selenium Xenon", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this@MainActivity, "Bromine Uranium Hydrogen", Toast.LENGTH_SHORT).show()
        }

        setUpNextQuestion()
    }

    private fun setUpNextQuestion() {
        if(quiz.checkIndex()) {
            showFinalText()
        }
        else {
            button1.text = quiz.buttonText1()
            button2.text = quiz.buttonText2()
            button3.text = quiz.buttonText3()
            button4.text = quiz.buttonText4()
            questionText.text = quiz.questionText()
        }

    }

    private fun showFinalText() {
        button1.visibility = View.GONE
        button2.visibility = View.GONE
        button3.visibility = View.GONE
        button4.visibility = View.GONE
        questionText.visibility = View.GONE
        finalText.visibility = View.VISIBLE

        if (quiz.score == 20) {
            finalText.text = "Score = 20\n" + "The title of 'chem god' does not even deserve you. You have transcended beyond the chemistry knowledge and expertise even of that of a god. All chemistry teachers will forever bow at your feet."
        }
        else if (quiz.score >= 15) {
            finalText.text = "Score = "+ quiz.score + "\nYou know, you’re not too bad. If you are Asian, your family will probably disown you. However, compared to the general population, you’re still above average. But because you want to get better at chemistry and nail that perfect 20/20, you should join chemistry club. Instagram: @sphs.chemistry."
        }
        else if (quiz.score >= 10) {
            finalText.text = "Score = "+ quiz.score + "\nYou’re likely one of the kids that are currently failing AP Chem and need tutoring help. You can probably do better than this, but at least you’re above average. Although chemistry club is anything but average, we suppose that we can accept you. DM one of the officers to join."
        }
        else if (quiz.score >= 5) {
            finalText.text = "Score = "+ quiz.score + "\nYou did not even reach 50%! That’s not even half correct! If you got 50% on a test at school, you would probably be making your own dinner. If you had 50% of the money in your bank account, you would have 0 dollars. If you missed 50% of the shots you took, you would likely be single forever. Chemistry club is ashamed to be in your presence."
        }
        else {
            finalText.text = "Score = "+ quiz.score + "\nYou either never learned any chemistry or completely failed chemistry. If it’s the latter, please feel free by all means to be as embarrassed as possible. Absolutely shameful. Disgusting. Raw. Unseasoned. Chemistry club will never accept you. You are more of a joke than our memes."
        }

    }

}