package com.junction.toxicometr.model

import com.junction.toxicometr.emojiGenerator.Replacement
import com.junction.toxicometr.toneAnalyzer.TextTone

class OutgoingMessage {
    var text: String? = null
        private set

    var replacement: String = ""

    var tone : TextTone? = null

    constructor() {}

    constructor(content: String?, replacement: String, tone: TextTone?) {
        this.text = content
        this.replacement = replacement
        this.tone = tone;
    }
}