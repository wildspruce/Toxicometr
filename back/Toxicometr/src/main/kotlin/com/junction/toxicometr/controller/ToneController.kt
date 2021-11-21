package com.junction.toxicometr.controller

import com.junction.toxicometr.model.IncomingMessage
import com.junction.toxicometr.model.OutgoingMessageDefault
import com.junction.toxicometr.toneAnalyzer.ToneAnalyzerChat
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class ToneController {
    @MessageMapping("/tone")
    @SendTo("/tone/outcome")
    @Throws(Exception::class)
    fun greeting(message: IncomingMessage): OutgoingMessageDefault {
        return OutgoingMessageDefault(
            ToneAnalyzerChat.analyze(message.text!!)
        )
    }

}

