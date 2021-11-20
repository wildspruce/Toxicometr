package com.junction.toxicometr

import EmojiGenerator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ToxicometrApplication

fun main(args: Array<String>) {
    runApplication<ToxicometrApplication>(*args)
    val app = EmojiGenerator()
    val foldersWithIds = app.doFoldersIdMapping()
    app.generateElements(foldersWithIds)
}
