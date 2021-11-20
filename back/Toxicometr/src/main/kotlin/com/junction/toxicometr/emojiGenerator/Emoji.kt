

enum class ShapeGroup{
    SHAPE_GROUP_1,
    SHAPE_GROUP_2
}

class Emoji(val shape: String) {
    var shapeGroup = if (shape.contains("MoreShape")) ShapeGroup.SHAPE_GROUP_2 else ShapeGroup.SHAPE_GROUP_1
    private lateinit var  eyes: String
    private lateinit var  mouth: String
    lateinit var  additionalElement1: String
    lateinit var  additionalElement2: String
    lateinit var  finalPath: String
    var numOfElements: Int = 0


    fun setAdditional1(value: String) {
        additionalElement1 = value
        numOfElements++
    }

    fun setAdditional2(value: String) {
        additionalElement2 = value
        numOfElements++
    }



}