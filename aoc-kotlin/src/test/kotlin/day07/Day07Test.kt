package day07

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day07Test {
    val testFile = File(this::class.java.getResource("test1.txt")?.path.orEmpty())

    @Test
    fun getTotalSumSize() {
        val result = Day07.getTotalSumSize(testFile.readText())
        assertEquals(95437, result)

        val folderToDelete = Day07.findFolderToDelete(testFile.readText(), 70000000, 30000000)
        assertEquals(24933642, folderToDelete.getSize())
        assertEquals("d", folderToDelete.name)
    }
}