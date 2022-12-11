package day07

import java.io.File

class Day07 {
    companion object {
        fun getTotalSumSize(input: String, maxFolderSize: Int = 100000): Int {
            val rootFolder = createRootFolder(input)
            return sumUpByMaxSize(rootFolder, maxFolderSize)
        }

        fun createRootFolder(input: String): Folder {
            val root = Folder(
                    name = "/",
                    folderList = mutableListOf(),
                    fileList = mutableListOf(),
                    absolutePath = "/",
                    parentFolder = null
            )
            var currentFolder: Folder = root
            input.lines().forEach {
                when {
                    it.startsWith("\$ ls") -> println(
                            """
                        ${currentFolder.absolutePath} ls
                    """.trimIndent()
                    )

                    it == "\$ cd .." -> currentFolder = currentFolder.parentFolder ?: currentFolder
                    it == "\$ cd /" -> currentFolder = root
                    it.startsWith("\$ cd") -> currentFolder = getChildFolder(it, currentFolder)
                    else -> createFolderFile(it, currentFolder)
                }
            }
            return root
        }

        fun getChildFolder(command: String, currentFolder: Folder): Folder {
            val targetFolderName = command.replace("\$ cd ", "")
            return currentFolder.folderList.find { it.name == targetFolderName } ?: currentFolder
        }

        fun createFolderFile(dataInfo: String, currentFolder: Folder) {
            when {
                dataInfo.startsWith("dir") -> {
                    val dirname = dataInfo.replace("dir ", "")
                    val newFolder = Folder(
                            name = dirname,
                            folderList = mutableListOf(),
                            fileList = mutableListOf(),
                            absolutePath = "${currentFolder.absolutePath}$dirname/",
                            parentFolder = currentFolder
                    )
                    currentFolder.folderList.add(newFolder)
                }

                else -> {
                    val fileInfo = dataInfo.split(" ")
                    currentFolder.fileList.add(DataFile(name = fileInfo[1], size = fileInfo[0].toInt()))
                }
            }
        }

        fun sumUpByMaxSize(rootFolder: Folder, maxFolderSize: Int): Int {
            val targetFolders = mutableListOf<Folder>()
            findFoldersByMaxSize(targetFolders, rootFolder, maxFolderSize)
            return targetFolders.sumOf { it.getSize() }
        }

        fun findFoldersByMaxSize(targetFolder: MutableList<Folder>, currentFolder: Folder, maxFolderSize: Int) {
            if (currentFolder.getSize() <= maxFolderSize) {
                targetFolder.add(currentFolder)
            }
            currentFolder.folderList.forEach { findFoldersByMaxSize(targetFolder, it, maxFolderSize) }
        }
    }
}

fun main() {
    val file = File(Day07::class.java.getResource("input.txt")?.path.orEmpty())
    val resultPart1 = Day07.getTotalSumSize(file.readText())

    println(
            """
        resultPart1:
        $resultPart1
    """.trimIndent()
    )
}