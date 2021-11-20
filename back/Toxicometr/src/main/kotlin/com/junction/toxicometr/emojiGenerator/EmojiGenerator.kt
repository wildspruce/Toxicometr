import java.awt.AlphaComposite
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

class EmojiGenerator {

    private val mapSwearWordsAndEmoji: HashMap<SwearWordsGroup, Emoji>

    init {
        mapSwearWordsAndEmoji = setEmojisToWords()
    }

    private fun setEmojisToWords(): HashMap<SwearWordsGroup, Emoji> {
        val mapSwearWordsAndEmoji = HashMap<SwearWordsGroup, Emoji>()
        val path = System.getProperty("user.dir") + "/src/main/resources/emoji-constructor/"
        mapSwearWordsAndEmoji[SwearWordsGroup("2g1c-related")] = Emoji(path + "MoreShape/25.png")
        mapSwearWordsAndEmoji[SwearWordsGroup("ass-related")] = Emoji(path + "MoreShape/35.png")
        mapSwearWordsAndEmoji[SwearWordsGroup("balls-related")] = Emoji(path + "MoreShape/32.png")
        mapSwearWordsAndEmoji[SwearWordsGroup("bdsm-related")] = Emoji(path + "Shape/24.png").apply { setAdditional1("${path}2-Misc/25.png") }
        mapSwearWordsAndEmoji[SwearWordsGroup("body-related")] = Emoji(path + "Shape/25.png")
        mapSwearWordsAndEmoji[SwearWordsGroup("bitch-related")] = Emoji(path + "Shape/8.png")
        mapSwearWordsAndEmoji[SwearWordsGroup("cunt-related")] = Emoji(path + "MoreShape/36.png")
        mapSwearWordsAndEmoji[SwearWordsGroup("dick-related")] = Emoji(path + "MoreShape/29.png")
        mapSwearWordsAndEmoji[SwearWordsGroup("fuck-related")] = Emoji(path + "MoreShape/38.png")
        mapSwearWordsAndEmoji[SwearWordsGroup("gay-related")] = Emoji(path +"Shape/17.png")
        mapSwearWordsAndEmoji[SwearWordsGroup("general")] = Emoji(path + "MoreShape/44.png")
        mapSwearWordsAndEmoji[SwearWordsGroup("racism-related")] = Emoji(path + "MoreShape/43.png")
        mapSwearWordsAndEmoji[SwearWordsGroup("random")] = Emoji(path + "MoreShape/18.png")
        mapSwearWordsAndEmoji[SwearWordsGroup("sex-related")] = Emoji(path +"MoreShape/42.png")
        return mapSwearWordsAndEmoji
    }


    fun generateElements(foldersWithIds: HashMap<Int, String>){
        mapSwearWordsAndEmoji.forEach{ (wordsGroup, emojiBase) ->
            println("\nGroup ${wordsGroup.name}")

            var path = System.getProperty("user.dir") + "/src/main/resources/"
            val shouldBeCentered = (emojiBase.shapeGroup == ShapeGroup.SHAPE_GROUP_2)

            println("shouldBeCentered: $shouldBeCentered")

            val appGenerator = ElementsPainter(this, wordsGroup, shouldBeCentered)
            var resultUrl = appGenerator.drawBasicElements(emojiBase, path)
            emojiBase.finalPath = appGenerator.drawAdditionalElements(emojiBase, resultUrl, path, foldersWithIds)
        }
    }

    fun doFoldersIdMapping(): HashMap<Int, String> {
        val foldersIdMapping = HashMap<Int, String>()
        val path = System.getProperty("user.dir") + "/src/main/resources/emoji-constructor/"

        File(path).list().forEachIndexed { idx, it ->
            if (it != "MoreShape" && it != "Shape"){
                foldersIdMapping[idx] = it
            }
        }

        return foldersIdMapping
    }

    fun generateEmojisBasedOnPhrase(phrase: String){
        val words = phrase.split(" ")
        val groups = mutableListOf<SwearWordsGroup>()
        words.forEach { word -> mapSwearWordsAndEmoji.forEach { if (it.key.listOfWords.contains(word)) groups.add(it.key)} }
        groups.forEach {
            println("Generated emoji: ${ mapSwearWordsAndEmoji[it]?.finalPath}")
        }
    }

    fun addElementToImage(bottomImageFilepath: String, topImageFilepath: String, shouldBeCentered: Boolean): String {
        try {
            var path = System.getProperty("user.dir") + "/src/main/resources/"
            val f = File(path, "created-emoji/")
            f.mkdir()
            path += "created-emoji/"

            val imgA: BufferedImage = ImageIO.read(File( bottomImageFilepath))
            val imgB: BufferedImage = ImageIO.read(File( topImageFilepath))

            val alpha = 1.0f
            val compositeRule = AlphaComposite.SRC_OVER

            val imgW = imgA.width
            val imgH = imgA.height

            val overlay = BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_ARGB)
            val g = overlay.createGraphics()
            val ac: AlphaComposite = AlphaComposite.getInstance(compositeRule, alpha)
            g.drawImage(imgA, 0, 0, null)
            g.composite = ac
            if (shouldBeCentered){
                g.drawImage(imgB, 40, 30, null)
            } else {
                g.drawImage(imgB, 0, 0, null)
            }

            g.composite = ac

            ImageIO.write(overlay, "PNG", File(path,
                "${bottomImageFilepath.split("/").last().split(".").first()}-updated.png"))
            g.dispose()
            return path + "${bottomImageFilepath.split("/").last().split(".").first()}-updated.png"
        } catch (e: IOException) {
            return bottomImageFilepath
        }
    }

}
