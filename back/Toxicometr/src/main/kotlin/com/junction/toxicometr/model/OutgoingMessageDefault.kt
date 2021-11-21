package com.junction.toxicometr.model

import com.junction.toxicometr.toneAnalyzer.TextTone

open class OutgoingMessageDefault {

    var tone : TextTone? = null

    constructor(tone: TextTone?) {
        this.tone = tone
    }
}