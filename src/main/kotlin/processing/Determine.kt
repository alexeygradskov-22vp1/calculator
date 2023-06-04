package processing

import exceptions.InvalidFormat

class Determine(private val data: String, private val type: Int) {
    private var result: String = ""
    private var _data = data
    private var _type = type

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
        return when (_type) {
            1 -> Integer.toBinaryString(resultOfCalculation)
            2 -> Integer.toHexString(resultOfCalculation)
            3 -> Integer.toOctalString(resultOfCalculation)
            4 -> resultOfCalculation.toString()
            else -> ""
        }
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
}