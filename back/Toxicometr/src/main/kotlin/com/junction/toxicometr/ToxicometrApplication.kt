package com.junction.toxicometr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ToxicometrApplication

fun main(args: Array<String>) {
    runApplication<ToxicometrApplication>(*args)
}
