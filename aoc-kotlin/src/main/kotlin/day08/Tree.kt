package day08

data class Tree(
        val id: String,
        val colIndex: Int,
        val rowIndex: Int,
        val height: Int,
        var visible: Boolean? = null,
        var scenicScore: Int = 0
) {
}
