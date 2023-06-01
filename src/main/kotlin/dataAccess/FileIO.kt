package dataAccess

import java.io.File

class FileIO : IO {
    override fun read(): String {
        var lines: List<String> = File("src/main/kotlin/input.txt").bufferedReader().readLines();
        var data: String = "";
        lines.forEach {
            data += (it + "\n");
        }
        return data;
    }

    override fun write(data: String) {
        TODO("Not yet implemented")
    }
}