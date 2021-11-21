import kotlin.random.Random

class ElementsPainter(private val app: EmojiGenerator, private val wordsGroup: SwearWordsGroup, private val shouldBeCentered: Boolean) {

    fun drawAdditionalElements(emojiBase: Emoji, url: String, initialPath: String,
                               foldersWithIds: HashMap<Int, String>): String {

        var path = initialPath
        var resultUrl = url
        println("Group: ${wordsGroup.name} num: ${emojiBase.numOfElements}")

        val selectedFolders = List(2 - emojiBase.numOfElements ) {
            if (shouldBeCentered) Random.nextInt(9, 13) else Random.nextInt(0, 7)
        }

        val selectedItems = List(2 - emojiBase.numOfElements ) { Random.nextInt(1, 50) }

        path += "emoji-constructor/"

        if (wordsGroup.name != "dick-related") {

            if (emojiBase.numOfElements in 0..1){
                val pth = "$path${foldersWithIds[selectedFolders[0]]}/${selectedItems[0]}.png"
                println("resultUrl: $resultUrl")
                println("path: $pth")
                emojiBase.setAdditional2(pth)
            }

            if (emojiBase.numOfElements == 1){
                val pth = "$path${foldersWithIds[selectedFolders[1]]}/${selectedItems[1]}.png"
                println("resultUrl: $resultUrl")
                println("path: $pth")
                emojiBase.setAdditional1(pth)
            }
        }

        if (emojiBase.numOfElements != 0){
            resultUrl = app.addElementToImage(resultUrl, emojiBase.additionalElement1, shouldBeCentered)
            resultUrl = app.addElementToImage(resultUrl, emojiBase.additionalElement2, shouldBeCentered)
        }

        selectedFolders.forEachIndexed { idx, _ -> print("Folder num: ${selectedFolders[idx]}, Item num: ${selectedItems[idx]}\n") }
        return resultUrl
    }


    fun drawBasicElements(emojiBase: Emoji, path: String): String {
        var resultUrl = emojiBase.shape

        resultUrl = app.addElementToImage(resultUrl,
            "${path}main-elements/Eyes/${Random.nextInt(1, 99)}.png", shouldBeCentered)

        println("resultUrl: $resultUrl")

        val forbidden = listOf("dick-related", "cunt-related", "fuck-related")

        if (!forbidden.contains(wordsGroup.name)) {
            val pickMouth = Random.nextInt(1, 2)
            resultUrl = if (pickMouth == 1){
                println("resultUrl: $resultUrl")

                app.addElementToImage(resultUrl,
                    "${path}main-elements/Mouth/HappyMouth/${Random.nextInt(1, 199)}.png", shouldBeCentered)
            } else {
                println("resultUrl: $resultUrl")

                app.addElementToImage(resultUrl,
                    "${path}main-elements/Mouth/SadMouth/${Random.nextInt(1, 199)}.png", shouldBeCentered)
            }
        }
        return resultUrl
    }

}
