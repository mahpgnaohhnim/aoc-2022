package day07

class Folder(
        var folderList: MutableList<Folder>,
        var fileList: MutableList<DataFile>,
        val name: String,
        val absolutePath: String,
        val parentFolder: Folder?
) {
    fun getSize(): Int {
        return fileList.sumOf { it.size } + folderList.sumOf { it.getSize() }
    }
}