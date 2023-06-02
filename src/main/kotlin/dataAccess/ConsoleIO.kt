package dataAccess

class ConsoleIO : IO {
    override fun read(): String {
        println("Введите данные:");
        val data = readln();
        return data;
    }

    override fun write(data: String) {
        println(data);
    }
}