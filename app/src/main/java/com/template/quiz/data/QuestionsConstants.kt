package com.template.quiz.data

import com.template.quiz.domain.model.QuestionModel

object QuestionsConstants {
    fun getQuestions() = mutableListOf(
        QuestionModel(
            null,
            "The James Webb Space Telescope was launched on...",
            listOf(
                "25 December 2021",
                "09 June 2022",
                "15 January 2019",
                "25 March 2020"
            ),
            "25 December 2021"
        ),

        QuestionModel(
            null,
            "What is the largest earth-like planet in the solar system?",
            listOf(
                "Earth",
                "Mars, ",
                "Mercury",
                "Venus"
            ),
            "Earth"
        ),

        QuestionModel(
            null,
            "The original plan called for a 2007 launch date for the telescope, but various factors have delayed its completion and launch. Which of the following did not contribute to a delay?",
            listOf(
                "A CIA investigation into whether NASA scientists were Secret Communists.",
                "The project running over budget.",
                "Construction delays.",
                "Components failing tests to see if they would function properly."
            ),
            "A CIA investigation into whether NASA scientists were Secret Communists."
        ),

        QuestionModel(
            null,
            "Where is the Lagrange point of the James Webb Telescope?",
            listOf(
                "Lagrange 2",
                "Lagrange 1",
                "Lagrange 3",
                "Lagrange 4"
            ),
            "Lagrange 2"
        ),

        QuestionModel(
            null,
            "The JWST is planned to orbit the Sun at the Second Earth-Sun Lagrange Point. What is a Lagrange point?",
            listOf(
                "A point where the gravitational force between two objects balances out.",
                "An astronomical measurement meaning 1/10th the distance between the Earth and the Sun",
                "A point where a body can be between two objects and never be visible to the naked eye from either.",
                "An astronomical measurement proposed by ZZ Top's Billy Gibbons."
            ),
            "A point where the gravitational force between two objects balances out."
        ),

        QuestionModel(
            null,
            "How long after launch did The James Webb's  reach its planned orbital distance?",
            listOf(
                "30 Days",
                "50 Days",
                "1 Year",
                "Instantly"
            ),
            "30 Days"
        ),

        QuestionModel(
            null,
            "How much does the James Webb telescope cost?",
            listOf(
                "10 Billions",
                "45 Millions",
                "1.5 Billions",
                "500 Millions"
            ),
            "10 Billions"
        ),

        QuestionModel(
            null,
            "Which telescope sees the farthest?",
            listOf(
                "Hubble",
                "James Webb",
                "TESS",
                "Fermi"
            ),
            "Hubble"
        ),

        QuestionModel(
            null,
            "Which star is closest to the sun?",
            listOf(
                "Alpha Centauri",
                "Betelgeuse",
                "Sirius",
                "UY Scuti"
            ),
            "Alpha Centauri"
        ),

        QuestionModel(
            null,
            "What is the name of the largest star in the universe?",
            listOf(
                "UY Scuti",
                "Betelgeuse",
                "Sirius",
                "Sun"
            ),
            "UY Scuti"
        ),

        QuestionModel(
            null,
            "The telescope also represents a collaboration between three space agancies. Which of the below agencies has not been involved in the project?",
            listOf(
                "SpaceX (Elon Musk",
                "CSA(Canada)",
                "ESA (European Union",
                "NASA (USA)"
            ),
            "SpaceX"
        ),

        QuestionModel(
            null,
            "The Webb telescope is designed to provide scientists with a way to observe what part of the electromagnetic spectrum?",
            listOf(
                "Infrared",
                "Microwaves",
                "Ultraviolet",
                "X-Rays"
            ),
            "Infrared"
        ),

        QuestionModel(
            null,
            "Because it's designed for infrared the JWST will not study objects that are too close to it, or would appear too bright in its lens. Which of these objects will the JWST observe",
            listOf(
                "Mars",
                "Earth",
                "The Sun",
                "The Moon"
            ),
            "Mars"
        ),

        QuestionModel(
            null,
            "Which planet was excluded from the solar system",
            listOf(
                "Pluto",
                "Mars",
                "Venus",
                "Jupiter"
            ),
            "Pluto"
        ),

        QuestionModel(
            null,
            "What year was pluto excluded from the solar system?",
            listOf(
                "2006",
                "1994",
                "2012",
                "2021"
            ),
            "2006"
        ),

        QuestionModel(
            null,
            "What did the James Webb telescope see?",
            listOf(
                "Earendel",
                "Interstellar",
                "Kepler-150 f",
                "Chandra"
            ),
            "Earendel"
        ),

        QuestionModel(
            null,
            "Nearest exoplanet",
            listOf(
                "Proxima Centauri b",
                "Kepler-150 f",
                "Sirius",
                "Mars"
            ),
            "Proxima Centauri b"
        ),

        QuestionModel(
            null,
            "The most distant exoplanet",
            listOf(
                "Kepler-150 f ",
                "Miller",
                "Manna",
                "Gliese 876 d"
            ),
            "Kepler-150 f"
        ),

        QuestionModel(
            null,
            "Which one is a satellite of Saturn,",
            listOf(
                "Titan",
                "Moon",
                "Neptune",
                "Phobos"
            ),
            "Titan"
        ),

        QuestionModel(
            null,
            "First artificial earth satellite",
            listOf(
                "Sputnik-1",
                "Astra 2E.",
                "Asterix",
                "Astron"
            ),
            "Sputnik-1"
        )
    )
}