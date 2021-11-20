

fun main() {
    val app = EmojiGenerator()
    val foldersWithIds = app.doFoldersIdMapping()

    println("foldersWithIds.size: " + foldersWithIds.size)

    app.generateElements(foldersWithIds)
    app.generateEmojisBasedOnPhrase("ass dick slut")

}

