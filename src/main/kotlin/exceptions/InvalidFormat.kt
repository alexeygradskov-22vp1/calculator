package exceptions

class InvalidFormat(message: String) : Exception(message) {
    fun what(): String? {
        return message;
    }
}