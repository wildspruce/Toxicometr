package com.junction.toxicometr.controller

import EmojiGenerator
import com.junction.toxicometr.model.OutgoingMessage
import com.junction.toxicometr.model.IncomingMessage
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
    fun greeting(message: IncomingMessage): OutgoingMessage? {
        val app = EmojiGenerator()
        val foldersWithIds = app.doFoldersIdMapping()
        app.generateElements(foldersWithIds)

        return OutgoingMessage(
            HtmlUtils.htmlEscape(message.text!!),
            app.returnListOfReplacements(message.text!!),
            null
        )
    }

}

