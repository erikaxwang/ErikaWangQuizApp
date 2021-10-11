package com.example.quizapp

class Quiz (var questions : List<Question>) {

    var score = 0
    var currentIndex = 0


    fun checkAnswer(answer : String): Boolean {
        if(answer == questions[currentIndex].correctAnswer) {
            score++
            currentIndex++
            return true
        }
        else {
            currentIndex++
            return false
        }
    }

    fun checkIndex(): Boolean {
        return currentIndex == 20
    }

    fun questionText(): String {
        return questions[currentIndex].question
    }

    fun buttonText1(): String {
        return questions[currentIndex].answers[0]
    }

    fun buttonText2(): String {
        return questions[currentIndex].answers[1]
    }

    fun buttonText3(): String {
        return questions[currentIndex].answers[2]
    }

    fun buttonText4(): String {
        return questions[currentIndex].answers[3]
    }


}