package day07

import java.io.File

class Day07 {
    companion object {
        val inputFile = File(this::class.java.getResource("input.txt")?.path.orEmpty())
        fun getTotalSumSize(input: String, maxFolderSize: Int = 100000): Int {
            val rootFolder = createRootFolder(input)
            return sumUpByMaxSize(rootFolder, maxFolderSize)
        }

        fun findFolderToDelete(input: String, totalSpace: Int, freeSpaceNeeded: Int): Folder {
            val rootFolder = createRootFolder(input)
            val currentFreeSpace = totalSpace - rootFolder.getSize()
            val spaceToFree = freeSpaceNeeded - currentFreeSpace
            val possibleFolders = mutableListOf<Folder>()
            this.findFoldersByMinSize(possibleFolders, rootFolder, spaceToFree)
            return possibleFolders.minBy { it.getSize() }
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

        fun findFoldersByMinSize(targetFolder: MutableList<Folder>, currentFolder: Folder, minFolderSize: Int) {
            if (currentFolder.getSize() >= minFolderSize) {
                targetFolder.add(currentFolder)
            }
            currentFolder.folderList.forEach { findFoldersByMinSize(targetFolder, it, minFolderSize) }
        }
    }
}

fun main() {
    val resultPart1 = Day07.getTotalSumSize(Day07.inputFile.readText())
    val folderToDelete = Day07.findFolderToDelete(
            input = Day07.inputFile.readText(),
            totalSpace = 70000000,
            freeSpaceNeeded = 30000000
    )

    println(
            """
        resultPart1:
        $resultPart1
        
        resultPart2:
        ${folderToDelete.getSize()}
    """.trimIndent()
    )
}