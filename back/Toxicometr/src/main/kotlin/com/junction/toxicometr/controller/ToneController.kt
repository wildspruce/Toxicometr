package com.junction.toxicometr.controller

import com.junction.toxicometr.model.IncomingMessage
import com.junction.toxicometr.model.OutgoingMessageDefault
import com.junction.toxicometr.toneAnalyzer.ToneAnalyzerChat
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class ToneController {
    @GetMapping("/tone/{text}")
    @Throws(Exception::class)
    fun greeting(@PathVariable(name = "text") message: String): OutgoingMessageDefault {
        return OutgoingMessageDefault(
            ToneAnalyzerChat.analyze(message)
        )
    }

}

