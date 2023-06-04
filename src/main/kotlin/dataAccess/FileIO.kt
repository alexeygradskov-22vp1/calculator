package dataAccess

import java.io.File

class FileIO : IO {
    override fun read(): String {
        val lines: List<String> = File("src/main/kotlin/input.txt").bufferedReader().readLines()
        val data = buildString {
            lines.forEach {
                append(it + "\n")
            }
        }

        return data
    }

    override fun write(data: String) {
        val file = File("src/main/kotlin/output.txt")
        file.createNewFile()
        file.writeText(data)
    }
}