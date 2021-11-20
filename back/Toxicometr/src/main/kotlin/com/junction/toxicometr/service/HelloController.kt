package com.junction.toxicometr.service

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@CrossOrigin
class HelloController {
    @GetMapping("/")
    fun index(): String {
        return "Greetings from Spring Boot!"
    }
}
