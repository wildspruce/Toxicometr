package com.junction.toxicometr.model

import com.junction.toxicometr.toneAnalyzer.TextTone

class OutgoingMessage : OutgoingMessageDefault {
    var text: String? = null
        private set

    var replacement: String = ""


    constructor(content: String?, replacement: String, tone: TextTone?) : super(tone) {
        this.text = content
        this.replacement = replacement
        this.tone = tone;
    }
}