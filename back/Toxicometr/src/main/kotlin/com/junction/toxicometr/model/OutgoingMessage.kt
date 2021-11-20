package com.junction.toxicometr.model

import com.junction.toxicometr.emojiGenerator.Replacement
import com.junction.toxicometr.toneAnalyzer.TextTone

class OutgoingMessage {
    var text: String? = null
        private set

    var replacements: List<Replacement>? = null

    var tone : TextTone? = null

    constructor() {}

    constructor(content: String?, replacements: List<Replacement>?, tone: TextTone?) {
        this.text = content
        this.replacements = replacements
        this.tone = tone;
    }
}