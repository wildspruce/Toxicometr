package com.junction.toxicometr.model

class OutgoingMessage {
    var text: String? = null
        private set

    constructor() {}
    constructor(content: String?) {
        this.text = content
    }
}