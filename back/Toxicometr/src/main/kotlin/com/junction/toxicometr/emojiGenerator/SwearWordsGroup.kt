import java.io.File

class SwearWordsGroup(name: String) {

    val name = name
    val listOfWords: MutableList<String> = mutableListOf()

    init {
        val path =  System.getProperty("user.dir") + "/src/main/resources/swear-words-dataset/$name.txt"

        File(path).forEachLine { listOfWords.add(it) }
    }
}