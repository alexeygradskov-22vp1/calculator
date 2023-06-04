package dataAccess

interface IO {
    fun read(): String;
    fun write(data: String);
}