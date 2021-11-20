
import kotlin.random.Random

fun main() {
    val app = EmojiGenerator()
    val path = System.getProperty("user.dir") + "/back/Toxicometr/src/main/resources/emoji-constructor/"
    val foldersWithIds = app.doFoldersIdMapping()

    app.mapSwearWordsAndEmoji.forEach{ (wordsGroup, emojiBase) ->
        println("\nGroup ${wordsGroup.name}")

        val selectedFolders = List(3) { Random.nextInt(0, 14) }
        val selectedItems = List(3) { Random.nextInt(1, 50) }

        var resultUrl = emojiBase

        for (i in 0..2){
            println("resultUrl: $resultUrl")
            println("path: $path${foldersWithIds[selectedFolders[i]]}/${selectedItems[i]}.png")
            resultUrl = app.addElementToImage(resultUrl, "$path${foldersWithIds[selectedFolders[i]]}/${selectedItems[i]}.png")
        }

        selectedFolders.forEachIndexed { idx, _ -> print("Folder num: ${selectedFolders[idx]}, Item num: ${selectedItems[idx]}\n") }


    }

    println(app.getSize())

}