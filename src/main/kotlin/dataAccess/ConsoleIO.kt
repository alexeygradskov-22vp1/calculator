package dataAccess

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class ConsoleIO : IO {
    override fun read(): String {
        println("Введите данные:");
        val list = mutableListOf<String>()
        val regex = "stop".toRegex();
        InputStreamReader(System.`in`).use { inp ->
            BufferedReader(inp).use { buffer ->
                var line: String
                while (buffer.readLine().also { line = it } != null && !regex.containsMatchIn(line)) {
                    list.add(line)
                }
            }
        }
        var data = "";
        list.forEach {
            data += it + "\n";
        }
        return data;
    }

    override fun write(data: String) {
        println("Результат:")
        println(data);
    }
}