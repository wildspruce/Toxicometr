package com.junction.toxicometr.controller

import EmojiGenerator
import com.junction.toxicometr.model.OutgoingMessage
import com.junction.toxicometr.model.IncomingMessage
import com.junction.toxicometr.toneAnalyzer.ToneAnalyzerChat
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.HtmlUtils

//Here we should process our message
@RestController
@CrossOrigin
class ChatController {
    @MessageMapping("/income")
    @SendTo("/topic/outcome")
    @Throws(Exception::class)
    fun greeting(message: IncomingMessage): OutgoingMessage? =
        message.text?.let {
            OutgoingMessage(
                HtmlUtils.htmlEscape(it),
                EmojiGenerator().returnListOfReplacements(it),
                ToneAnalyzerChat.analyze(it)
            )
        }
}

