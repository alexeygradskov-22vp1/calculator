package processing

import exceptions.InvalidFormat

class Determine(private val data: String) {
    private var result: String = ""
    private var _data = data

    init {
        determine()
    }

    private fun determine() {
        var list: List<String> = _data.split("\n")
        list = list.dropLast(1)
        list.forEach {
            val listOfString: List<String> = processAString(it).split(" ")
            result += try {
                if (listOfString[0].matches(Regex("stop"))
                ) {
                    result += "stop"
                    return
                }
                validData(listOfString)
                calculate(listOfString) + "\n"
            } catch (e: InvalidFormat) {
                e.what() + "\n"
            }

        }
    }

    private fun calculate(data: List<String>): String {
        val resultOfCalculation = when (data[1]) {
            "*" -> (data[0].toInt() * data[2].toInt())
            "/" -> (data[0].toInt() / data[2].toInt())
            "+" -> (data[0].toInt() + data[2].toInt())
            "-" -> (data[0].toInt() - data[2].toInt())
            else -> 0
        }
        return Int.toBinary(resultOfCalculation)
    }

    private fun validData(list: List<String>): Boolean {
        if (list[0].isEmpty() || list[2].isEmpty() || !list[0].matches(Regex("^\\d+\$")) || !list[2].matches(Regex("^\\d+\$"))) {
            throw InvalidFormat("Неверный формат строки")
        }
        return true
    }

    fun getResult(): String {
        return result
    }

    private fun processAString(string: String): String {
        var replacedString: String = string.replace("\\s+".toRegex(), " ")
        replacedString = replacedString.replace("*", " * ")
        replacedString = replacedString.replace("/", " / ")
        replacedString = replacedString.replace("+", " + ")
        replacedString = replacedString.replace("-", " - ")
        replacedString = replacedString.replace("\n$".toRegex(), "")
        return replacedString
    }


    private fun Int.Companion.toBinary(decimal: Int): String {
        var number = decimal
        val binary = StringBuilder()

        if (number == 0) {
            return "0"
        }

        while (number > 0) {
            val bit = number % 2
            binary.insert(0, bit)
            number /= 2
        }

        return binary.toString()
    }
}
