import java.awt.AlphaComposite
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

class EmojiGenerator {

    val mapSwearWordsAndEmoji: HashMap<SwearWordsGroup, String>

    init {
        mapSwearWordsAndEmoji = setEmojisToWords()
    }

    fun getSize(): Int {
        return mapSwearWordsAndEmoji.size
    }

    private fun setEmojisToWords(): HashMap<SwearWordsGroup, String> {
        val mapSwearWordsAndEmoji = HashMap<SwearWordsGroup, String>()
        val path = System.getProperty("user.dir") + "/back/Toxicometr/src/main/resources/emoji-constructor/"
        mapSwearWordsAndEmoji[SwearWordsGroup("2g1c-related")] = path + "MoreShape/25.png"
        mapSwearWordsAndEmoji[SwearWordsGroup("ass-related")] = path + "MoreShape/22.png"
        mapSwearWordsAndEmoji[SwearWordsGroup("balls-related")] = path + "MoreShape/32.png"
        mapSwearWordsAndEmoji[SwearWordsGroup("bdsm-related")] = path + "Shape/24.png"
        mapSwearWordsAndEmoji[SwearWordsGroup("body-related")] = path + "Shape/25.png"
        mapSwearWordsAndEmoji[SwearWordsGroup("bitch-related")] = path + "Shape/8.png"
        mapSwearWordsAndEmoji[SwearWordsGroup("cunt-related")] = path + "MoreShape/48.png"
        mapSwearWordsAndEmoji[SwearWordsGroup("dick-related")] = path + "MoreShape/29.png"
        mapSwearWordsAndEmoji[SwearWordsGroup("fuck-related")] = path + "MoreShape/46.png"
        mapSwearWordsAndEmoji[SwearWordsGroup("gay-related")] = path +"MoreShape/17.png"
        mapSwearWordsAndEmoji[SwearWordsGroup("general")] = path + "Shape/66.png"
        mapSwearWordsAndEmoji[SwearWordsGroup("racism-related")] = path + "MoreShape/43.png"
        mapSwearWordsAndEmoji[SwearWordsGroup("random")] = path + "Shape/67.png"
        mapSwearWordsAndEmoji[SwearWordsGroup("sex-related")] = path +"MoreShape/42.png"
        //TODO case for other random words
        return mapSwearWordsAndEmoji
    }


    fun doFoldersIdMapping(): HashMap<Int, String> {
        val foldersIdMapping = HashMap<Int, String>()
        val path = System.getProperty("user.dir") + "/back/Toxicometr/src/main/resources/emoji-constructor/"

        File(path).list().forEachIndexed { idx, it ->
            if (it != "MoreShape" && it != "Shape"){
                foldersIdMapping[idx] = it
            }
        }

        return foldersIdMapping
    }

    fun addElementToImage(bottomImageFilepath: String, topImageFilepath: String): String {
        try {
            val path = System.getProperty("user.dir") + "/back/Toxicometr/src/main/resources/emoji-constructor/"

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
            g.drawImage(imgB, 0, 0, null)
            g.composite = ac

            ImageIO.write(overlay, "PNG", File(path, "${bottomImageFilepath.split("/").last()}-updated.png"))
            g.dispose()
            return path + "${bottomImageFilepath.split("/").last()}-updated.png"
        } catch (e: IOException) {
            return ""
        }
    }

}
