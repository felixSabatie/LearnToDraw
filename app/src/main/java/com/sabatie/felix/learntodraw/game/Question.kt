package com.sabatie.felix.learntodraw.game

import java.io.Serializable

class Question(val title: String, val responses: Iterable<Response>, val image: Int) : Serializable {
    fun getResponseText(): String {
        return getResponse().text
    }

    fun getResponse(): Response {
        return responses.find { it.valid } as Response
    }
}