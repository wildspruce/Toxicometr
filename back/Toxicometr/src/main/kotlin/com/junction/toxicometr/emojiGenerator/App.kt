
import kotlin.random.Random

fun main() {
    val app = EmojiGenerator()
    val foldersWithIds = app.doFoldersIdMapping()

    println("foldersWithIds.size: " + foldersWithIds.size)

    app.mapSwearWordsAndEmoji.forEach{ (wordsGroup, emojiBase) ->
        println("\nGroup ${wordsGroup.name}")

        var path = System.getProperty("user.dir") + "/src/main/resources/"
        val shouldBeCentered = emojiBase.contains("MoreShape")

        println("shouldBeCentered: " + shouldBeCentered)

        val appGenerator = AppGenerator()
        var resultUrl = appGenerator.drawBasicElements(app, emojiBase, shouldBeCentered, path, wordsGroup)

        val selectedFolders = List(2) {
            if (shouldBeCentered) Random.nextInt(9, 13) else Random.nextInt(0, 7)
        }

        val selectedItems = List(2) { Random.nextInt(1, 50) }


        path += "emoji-constructor/"

        if (wordsGroup.name != "dick-related") {
            for (i in 0..1) {
                println("resultUrl: $resultUrl")
                println("path: $path${foldersWithIds[selectedFolders[i]]}/${selectedItems[i]}.png")
                resultUrl = app.addElementToImage(
                    resultUrl,
                    "$path${foldersWithIds[selectedFolders[i]]}/${selectedItems[i]}.png", shouldBeCentered
                )
            }
        }

        if (wordsGroup.name == "bdsm-related"){
            resultUrl = app.addElementToImage(resultUrl,
                "${path}2-Misc/25.png", shouldBeCentered)
        }

        selectedFolders.forEachIndexed { idx, _ -> print("Folder num: ${selectedFolders[idx]}, Item num: ${selectedItems[idx]}\n") }


    }

}
class AppGenerator() {
    fun drawBasicElements(app: EmojiGenerator, emojiBase: String, shouldBeCentered: Boolean, path: String
                          , wordsGroup: SwearWordsGroup): String {
        var resultUrl = emojiBase

        resultUrl = app.addElementToImage(resultUrl,
            "${path}main-elements/Eyes/${Random.nextInt(1, 99)}.png", shouldBeCentered)

        val forbidden = listOf("dick-related", "cunt-related", "fuck-related")

        if (!forbidden.contains(wordsGroup.name)) {
        val pickMouth = Random.nextInt(1, 2)
            resultUrl = if (pickMouth == 1){
                app.addElementToImage(resultUrl,
                    "${path}main-elements/Mouth/HappyMouth/${Random.nextInt(1, 199)}.png", shouldBeCentered)
            } else {
                app.addElementToImage(resultUrl,
                    "${path}main-elements/Mouth/SadMouth/${Random.nextInt(1, 199)}.png", shouldBeCentered)
            }
        }
        return resultUrl
    }
}
