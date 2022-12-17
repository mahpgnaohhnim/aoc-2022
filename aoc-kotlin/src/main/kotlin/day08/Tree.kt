package day08

data class Tree(
        val colIndex: Int,
        val rowIndex: Int,
        val height: Int,
        var visible: Boolean? = null
) {
}
