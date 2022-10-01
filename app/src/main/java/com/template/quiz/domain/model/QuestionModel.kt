package com.template.quiz.domain.model

data class QuestionModel(
    var question: String,
    var answers: List<String>,
    var correctAnswer: String
)